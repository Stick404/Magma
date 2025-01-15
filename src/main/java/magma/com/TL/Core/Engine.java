package magma.com.TL.Core;

import magma.com.TL.Core.TL_OLD.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static magma.com.TL.Expressions.TLExpressionRegistry.EXPRES;
import static magma.com.TL.Functions.TLFunctionRegistry.FUNCS;

public class Engine {

    public static final String VERSION = "0.1-Stickia";

    //TODO: Rework all expressions/atoms/classes to be cleaned to work with new Args system
    //TODO: Implement and work on new Args system (based on Hex Casting's args.get)

    /* TODO: types to reimplement
     * Objects (Arbitrary Java Objects; may use <T> system to make it more readable)
     * Lambdas/Functions (Functions are just Lambdas saved in the Env)
     * Numbers (Floats, Ints, Longs, etc)
     * Arrays (might still be useful...)
     * Symbols (make them hold data rather then being place holders (or optional both?))
     *
    */

    //converts an object into an Atom readable by the Interpreter
    public static TLAtomExpression<?> expressionOf(Object value) {
        if (value == null) {
            return TLJavaObjectExpression.of(null);
        } else if (value.getClass().isArray()) {
            return TLArrayExpression.of(value);
        } else {
            return TLJavaObjectExpression.of(value);
        }
    }

    //the most basic requirements for an Expression
    public interface TLExpression {
        Object getValue();
        boolean asBoolean();
    }

    //returns basic Ops/Funcs
    public TLEnvironment defaultEnvironment() {
        final TLEnvironment environment = new TLEnvironment();
        for(Map.Entry<String, TLFunction> entry : FUNCS.entrySet()) {
            environment.put(TLSymbolExpression.of(entry.getKey()), entry.getValue());
        }
        return environment;
    }

    //runs a function in an environment and engine
    public TLExpression apply(TLFunction function, TLListExpression arguments, TLEnvironment environment, Engine engine) throws Exception {
        return function.invoke(arguments, environment, engine);
    }

    //the main brain, see comments inside
    public TLExpression evaluate(TLExpression object, TLEnvironment environment) throws Exception {
        if (object instanceof TLSymbolExpression symbol) { //if it is a symbol, return its value
            TLExpression result = environment.get(symbol);
            if (result == null) {
                throw new RuntimeException("Symbol undefined: " + symbol);
            }
            return result;
        } else if (object instanceof TLAtomExpression) {
            return object; //if an atom, nothing needs to be done with it; return it
        } else if (object instanceof TLListExpression expression) {
            if (expression.isEmpty()) { //return a blank expression if its empty
                // Empty list is nil/false
                return expression;
            }
            // The first item in a list must be a symbol
            TLExpression first = expression.get(0);
            if (EXPRES.containsKey(first.getValue())) {
                return EXPRES.get(first.toString()).invoke(expression,environment,this); //get value of the expression from the key
            } else {
                // First item wasn't a special form so it must evaluate to a function
                TLFunction function = (TLFunction) evaluate(first, environment); //gets the symbol of the function
                TLListExpression args = new TLListExpression(); //sets up the possible args
                for (TLExpression exp : expression.subList(1, expression.size())) { //evaluate sub lists for the function, adding them to args
                    args.add(evaluate(exp, environment));
                }
                try {
                    return apply(function, args, environment, this); //try to run the function
                } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
                    throw new TLRuntimeException(first + ": " + function + "\n" + ex, ex);
                }
            }
        } else {
            throw new IllegalArgumentException("Can't evaluate " + object);
        }
    }
    //Past here, my knowledge drops off. But I will still try to comment

    //converts a string into an expression/runnable code
    public TLExpression parse(String input) {
        ArrayList<String> tokens = tokenize(input); //converts the string into tokens
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

    //converts tokens into runnable code by converting punctuation into "real code"
    private TLExpression readTokens(ArrayList<String> tokens) {
        String token = popToNext(tokens);
        tokens.remove(0);
        switch (token) {
            case "(": { //while we are still making an expression...
                TLListExpression expression = new TLListExpression(); //start building the expression
                while (!")".equals(popToNext(tokens))) {
                    expression.add(readTokens(tokens)); //finish up and add the last token
                }
                tokens.remove(0);
                return expression;
            }
            case "[":
                List<Object> values = new ArrayList<>();
                while (!"]".equals(popToNext(tokens))) { //while we are still making an array...
                    // Arrays can only contain atoms
                    TLAtomExpression<?> atom = (TLAtomExpression<?>) readTokens(tokens);
                    values.add(atom.getValue());
                }
                tokens.remove(0);
                return TLArrayExpression.from(values); //return array
            case "\"":
                String string = tokens.remove(0);
                tokens.remove(0);
                return TLJavaObjectExpression.of(string);
            case "'": {
                TLListExpression expression = new TLListExpression(); //start expression
                expression.add(atomize("quote")); //add the expression "quote" to the start of the expression
                expression.add(readTokens(tokens)); //add the rest of the tokens to this
                return expression;
            }
            default:
                return atomize(token);
        }
    }

    //gets the next token needed
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

    //converts a basic token into an expression
    private TLExpression atomize(String token) {
        try { //Stickia here, I would like to say I did not write any of this parsing code
              //may Linus have blessing on your soul
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
            return TLJavaObjectExpression.of(null); //if the token is null, return a Java Object Atom of null
        } else if ("true".equals(token)) {
            return TLJavaObjectExpression.of(true); //if the token is false, return a Java Object Atom of false
        } else if ("false".equals(token)) {
            return TLJavaObjectExpression.of(false); //if the token is true, return a Java Object Atom of true
        } else {
            return TLSymbolExpression.of(token); //else, just make it a symbol
        }
    }

    // the main string -> Array<String>
    public ArrayList<String> tokenize(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        boolean inString = false;
        boolean inComment = false;
        for (int i = 0; i < input.length(); i++) { //for every character
            char c = input.charAt(i);
            if (inString) { //if we are in a string...
                if (c == '"') { //stop putting it inside a string
                    inString = false;
                    tokens.add(token.toString()); //convert this to a string, and add it to tokens
                    token = new StringBuilder(); //and start the next token
                    tokens.add(String.valueOf(c));
                } else if (c == '\\') { //put the exact char next char over
                    token.append(input.charAt(++i));
                } else { //add it
                    token.append(c);
                }
            } else if (inComment) { //if we are in a comment...
                if (c == '\n' || i == input.length() - 1) { //stop putting it inside a comment
                    inComment = false;
                    token.append(c);
                    tokens.add(token.toString()); //convert this to a string, and add it to tokens
                    token = new StringBuilder(); //and start the next token
                } else {
                    token.append(c); //add it
                }
            } else { //if we are not in a comment/string then...
                if (isBreakingChar(c)) { //if we are in a breaking character
                    if (!token.isEmpty()) {
                        tokens.add(token.toString()); //convert this to a string, and add it to tokens
                        token = new StringBuilder(); //and start the next token
                    }
                    tokens.add(String.valueOf(c));
                    inString = c == '"'; //we are in a string
                    inComment = c == ';'; //we are in a comment
                } else {
                    token.append(c); //add to current builder
                }
            }
        }
        if (!token.isEmpty()) {
            tokens.add(token.toString()); //add current token to string
        }
        return tokens;
    }

    //the main entrance system
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

    public static String escapeString(String str) {
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

    //used in Serializing running functions into a string. Which can be turned back into functions.
    public static String listToString(String prefix, Iterable<?> items, String delimiter, String suffix) {
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

    //converts a Number into a BigDecimal
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

    //converts a BigDecimal into a Number
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