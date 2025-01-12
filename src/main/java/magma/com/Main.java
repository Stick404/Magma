package magma.com;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TLEnvironment;

import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws Exception {
        PrintStream logger = System.out;
        Engine engine = new Engine();
        TLEnvironment env = engine.defaultEnvironment();

        // TODO: Rework inner core to use a stack of Lambdas, rather then recursion, recursion, recursion, recursion. (making a stack, rather then using the stack)
        // TODO: Make a better system for Java-Level functions; possibly copy Hex Casting for this

        // TODO: Make ExLambda work with optional values [Like (a 1) and b ]
        // TODO: Add in more Lisp Functions
        // I know this is really, *really* bad, but this works to test stuff
        // and I know Tests exist


        LambdaTesting.init(logger);
        LambdaTestingKT.INSTANCE.init(logger);

        /*
            logger.println("I am a line break!");
            logger.println(engine.execute("(+ 1 1)", env).getValue());
            logger.println(engine.execute("(+ 2 1)", env).getValue());
            logger.println(engine.execute("(- 2 1)", env).getValue());
            logger.println(engine.execute("(- 1 2)", env).getValue());
            logger.println(engine.execute("(+ 1 3)", env).getValue());
            logger.println(engine.execute("(+ 1 1)", env).getValue());
            logger.println(engine.execute("(* 1 1)", env).getValue());
            logger.println(engine.execute("(* 1 2)", env).getValue());
            logger.println(engine.execute("(* 3 2)", env).getValue());
            logger.println(engine.execute("(* 2 2 2)", env).getValue());
            logger.println(engine.execute("(add 2 2 2)", env).getValue());
            logger.println(engine.execute("(/ 2 2)", env).getValue());
            logger.println(engine.execute("(/ 2 2 2)", env).getValue());
            logger.println(engine.execute("(/ 2 2 2 2)", env).getValue());
            logger.println(engine.execute("(/ 2 2 2 2 2)", env).getValue());
            logger.println(engine.execute("(^ 2 2 2)", env).getValue()); // These are testing Math

            logger.println(engine.execute("(> 2 1)", env).getValue());
            logger.println(engine.execute("(> 1 2)", env).getValue());
            logger.println(engine.execute("(< 1 2)", env).getValue());
            logger.println(engine.execute("(< 2 1)", env).getValue());
            logger.println("I am a line break! \n");
            logger.println(engine.execute("(<= 2 1)", env).getValue());
            logger.println(engine.execute("(<= 1 2)", env).getValue());
            logger.println(engine.execute("(>= 2 1)", env).getValue());
            logger.println(engine.execute("(>= 1 2)", env).getValue());
            logger.println(engine.execute("(<= 2 2)", env).getValue());
            logger.println(engine.execute("(>= 2 1)", env).getValue());
            logger.println(engine.execute("(> 2 2)", env).getValue());
            logger.println(engine.execute("(< 2 2)", env).getValue()); // These are testing Boolean stuff

            logger.println(engine.execute("(car '(1 2 3 4 5 6 7 8 9))",env).getValue());
            logger.println(engine.execute("(cdr '(1 2 3 4 5 6 7 8 9))",env).getValue()); // These are list

            logger.println(engine.execute("(+ 5 5) (+ 1 5)",env).getValue()); // This is testing running 2 evals

            logger.println(engine.execute("(def v 1)",env).getValue());
            logger.println(engine.execute("(eval '(+ 3 v))",env).getValue()); //these are testing def/eval

            engine.execute("(defun averagenum (n1 n2 n3 n4) (/ ( + n1 n2 n3 n4) 4))",env);
            logger.println(engine.execute("(averagenum 10 20 30 40)",env));
            logger.println(engine.execute("(eval '(averagenum 10 20 30 40))",env).getValue()); //testing a basic defun

            logger.println(engine.execute("(if (= 1 1) '(5) '(6))",env).getValue()); //testing if

            logger.println("I am a line break! \n");
            engine.execute("(defun write (a b &optional c d) (print (list a b c d)))",env); //testing &optional

            engine.execute("(write 1 2)",env);
            //engine.execute("(write 1 2 3 4 5 6 7 8 9 10)",env);
            engine.execute("(write 1 2 3 4)",env);
            //engine.execute("(write 5 6 7 8)",env);
            //engine.execute("(defun fibonacci (n &optional (a 0) (b 1)) (if (zerop n) null ((cons a (fibonacci (1- n) b (+ a b))))))",env);
            //engine.execute("(defun aaa (&optional (n 5)) (if (print (zerop n)) null (cons n (aaa (print (+ 0 0))))))",env);
            //engine.execute("(print (aaa 0))",env);
            engine.execute("(print 'HIII)",env);
            engine.execute("(print (print (zerop 1)))",env);
            engine.execute("(print (print (zerop 0)))",env); //(print (+ 0 0)
            engine.execute("(let* ((n 1) (b 2)) (print (+ b n)))",env);
            //engine.execute("(print (zerop (+ 0 0))))",env);

            //the graveyard of recursion, zerop, and default
            //this has brought me much pain
        */
        //engine.execute("(def fact (lambda (n) (if (<= n 1) 1 (* n (fact (- n 1))))))",env);
        //engine.execute("(print (fact 420))",env); //so this doesn't fail due to any code reason; just the Java Stack overflows...
    }
}