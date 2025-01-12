package Magma.Com.TL.Core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static Magma.Com.TL.Expressions.TLExpressionRegistry.EXPRES;
import static Magma.Com.TL.Functions.TLFunctionRegistry.FUNCS;

public class Engine {

    public static final String VERSION = "0.1-Stickia";

    public static TLAtomExpression<?> expressionOf(Object value) {
        if (value == null) {
            return TLJavaObjectExpression.of(null);
        } else if (value.getClass().isArray()) {
            return TLArrayExpression.of(value);
        } else {
            return TLJavaObjectExpression.of(value);
        }
    }

    public interface TLExpression {
        Object getValue();
        boolean asBoolean();
    }

    public TLEnvironment defaultEnvironment() {
        final TLEnvironment environment = new TLEnvironment();
        for(Map.Entry<String, TLFunction> entry : FUNCS.entrySet()) {
            environment.put(TLSymbolExpression.of(entry.getKey()), entry.getValue());
        }
        return environment;
    }

    public TLExpression apply(TLFunction function, TLListExpression arguments, TLEnvironment environment, Engine engine) throws Exception {
        return function.invoke(arguments, environment, engine);
    }

    public TLExpression evaluate(TLExpression object, TLEnvironment environment) throws Exception {
        if (object instanceof TLSymbolExpression symbol) {
            TLExpression result = environment.get(symbol);
            if (result == null) {
                throw new RuntimeException("Symbol undefined: " + symbol);
            }
            return result;
        } else if (object instanceof TLAtomExpression) {
            return object;
        } else if (object instanceof TLListExpression expression) {
            if (expression.isEmpty()) {
                // Empty list is nil/false
                return expression;
            }
            // The first item in a list must be a symbol
            TLExpression first = expression.get(0);
            if (EXPRES.containsKey(first.getValue())) {
                return EXPRES.get(first.toString()).invoke(expression,environment,this);
            } else {
                // First item wasn't a special form so it must evaluate to a function
                TLFunction function = (TLFunction) evaluate(first, environment);
                TLListExpression args = new TLListExpression();
                for (TLExpression exp : expression.subList(1, expression.size())) {
                    args.add(evaluate(exp, environment));
                }
                try {
                    return apply(function, args, environment, this);
                } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
                    throw new TLRuntimeException(first + ": " + function + "\n" + ex, ex);
                }
            }
        } else {
            throw new IllegalArgumentException("Can't evaluate " + object);
        }
    }

    public TLExpression parse(String input) {
        ArrayList<String> tokens = tokenize(input);
        TLExpression expression = readTokens(tokens);
        if (tokens.isEmpty()) {
            return expression;
        } else {
            TLListExpression result = new TLListExpression();
            result.add(TLSymbolExpression.of("progn"));
            result.add(expression);
            while (!tokens.isEmpty()) {
                result.add(readTokens(tokens));
            }
            return result;
        }
    }

    private TLExpression readTokens(ArrayList<String> tokens) {
        String token = popToNext(tokens);
        tokens.remove(0);
        switch (token) {
            case "(": {
                TLListExpression expression = new TLListExpression();
                while (!")".equals(popToNext(tokens))) {
                    expression.add(readTokens(tokens));
                }
                tokens.remove(0);
                return expression;
            }
            case "[":
                List<Object> values = new ArrayList<>();
                while (!"]".equals(popToNext(tokens))) {
                    // Arrays can only contain atoms
                    TLAtomExpression<?> atom = (TLAtomExpression<?>) readTokens(tokens);
                    values.add(atom.getValue());
                }
                tokens.remove(0);
                return TLArrayExpression.from(values);
            case "\"":
                String string = tokens.remove(0);
                tokens.remove(0);
                return TLJavaObjectExpression.of(string);
            case "'": {
                TLListExpression expression = new TLListExpression();
                expression.add(atomize("quote"));
                expression.add(readTokens(tokens));
                return expression;
            }
            default:
                return atomize(token);
        }
    }

    private String popToNext(ArrayList<String> tokens) {
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("End of token list");
        }
        while (true) {
            String token = tokens.get(0);
            if (token.trim().isEmpty()) {
                tokens.remove(0);
            } else if (";".equals(token)) {
                tokens.subList(0, 2).clear();
            } else {
                return token;
            }
        }
    }

    private TLExpression atomize(String token) {
        try {
            return TLJavaObjectExpression.of(Integer.parseInt(token));
        } catch (NumberFormatException ex) {
            // Not an int
        }
        try {
            return TLJavaObjectExpression.of(Double.parseDouble(token));
        } catch (NumberFormatException ex) {
            // Not a double
        }
        if ("null".equals(token)) {
            return TLJavaObjectExpression.of(null);
        } else if ("true".equals(token)) {
            return TLJavaObjectExpression.of(true);
        } else if ("false".equals(token)) {
            return TLJavaObjectExpression.of(false);
        } else {
            return TLSymbolExpression.of(token);
        }
    }

    public ArrayList<String> tokenize(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        boolean inString = false;
        boolean inComment = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (inString) {
                if (c == '"') {
                    inString = false;
                    tokens.add(token.toString());
                    token = new StringBuilder();
                    tokens.add(String.valueOf(c));
                } else if (c == '\\') {
                    token.append(input.charAt(++i));
                } else {
                    token.append(c);
                }
            } else if (inComment) {
                if (c == '\n' || i == input.length() - 1) {
                    inComment = false;
                    token.append(c);
                    tokens.add(token.toString());
                    token = new StringBuilder();
                } else {
                    token.append(c);
                }
            } else {
                if (isBreakingChar(c)) {
                    if (!token.isEmpty()) {
                        tokens.add(token.toString());
                        token = new StringBuilder();
                    }
                    tokens.add(String.valueOf(c));
                    inString = c == '"';
                    inComment = c == ';';
                } else {
                    token.append(c);
                }
            }
        }
        if (!token.isEmpty()) {
            tokens.add(token.toString());
        }
        return tokens;
    }

    public TLExpression execute(String program, TLEnvironment environment) throws Exception {
        return evaluate(parse(program), environment);
    }

    public static class TLRuntimeException extends RuntimeException {
        public TLRuntimeException() {
        }
        public TLRuntimeException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /* Utility functions */

    private boolean isBreakingChar(char c) {
        return c == '(' || c == ')' || c == '[' || c == ']' || c == '\'' || c == '"' || c == ';' || Character.isWhitespace(c);
    }

    static String escapeString(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '\\' || c == '"') {
                result.append('\\');
            }
            result.append(c);
        }
        return result.toString();
    }

    private static boolean isSymbol(Object obj, String name) {
        if (obj instanceof TLSymbolExpression symbol) {
            return name.equals(symbol.getValue());
        } else {
            return false;
        }
    }

    static String listToString(String prefix, Iterable<?> items, String delimiter, String suffix) {
        StringBuilder builder = new StringBuilder(prefix);
        for (Object item : items) {
            builder.append(item).append(delimiter);
        }
        if (builder.substring(builder.length() - delimiter.length()).equals(delimiter)) {
            builder.delete(builder.length() - delimiter.length(), builder.length());
        }
        builder.append(suffix);
        return builder.toString();
    }

    public static BigDecimal toBigDecimal(Number value) {
        if (value instanceof Double) {
            return BigDecimal.valueOf(value.doubleValue());
        } else if (value instanceof Integer) {
            return BigDecimal.valueOf(value.intValue());
        } else if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else {
            throw new IllegalArgumentException("Unsupported number type: " + value.getClass());
        }
    }

    public static Number reduceBigDecimal(BigDecimal value) {
        if (value.signum() == 0 || value.scale() <= 0 || value.stripTrailingZeros().scale() <= 0) {
            try {
                return value.intValueExact();
            } catch (ArithmeticException ex) {
                // Doesn't fit in an int, but is an integer
                return value.setScale(0, RoundingMode.UNNECESSARY);
            }
        } else {
            double dbl = value.doubleValue();
            if (!Double.isInfinite(dbl)) {
                return dbl;
            } else {
                return value.stripTrailingZeros();
            }
        }
    }
}