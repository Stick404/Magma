package Magma.Com;

import Magma.Com.TL.Core.Engine;
import Magma.Com.TL.Core.TLEnvironment;

import java.io.PrintStream;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws Exception {
        PrintStream logger = System.out;
        Engine engine = new Engine();

        // TODO: Make ExLambda work with optional values [Like (a 1) and b ]
        // TODO: Add in more Lisp Functions

        TLEnvironment env = engine.defaultEnvironment();
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
        logger.println(engine.execute("(^ 2 2 2)", env).getValue());
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
        logger.println(engine.execute("(< 2 2)", env).getValue());
        logger.println(engine.execute("(car '(1 2 3 4 5 6 7 8 9))",env).getValue());
        logger.println(engine.execute("(cdr '(1 2 3 4 5 6 7 8 9))",env).getValue());
        logger.println(engine.execute("(+ 5 5) (+ 1 5)",env).getValue());
        logger.println(engine.execute("(def v 1)",env).getValue());
        logger.println(engine.execute("(eval '(+ 3 v))",env).getValue());
        engine.execute("(defun averagenum (n1 n2 n3 n4) (/ ( + n1 n2 n3 n4) 4))",env);
        logger.println(engine.execute("(averagenum 10 20 30 40)",env));
        logger.println(engine.execute("(eval '(averagenum 10 20 30 40))",env).getValue());
        logger.println(engine.execute("(if (= 1 1) '(5) '(6))",env).getValue());
        engine.execute("(print 5)",env);
        logger.println("I am a line break! \n");
        engine.execute("(defun write (a b &optional c d) (print (list a b c d)))",env);

        //engine.execute("(write 1 2)",env);
        //engine.execute("(write 1 2 3 4 5 6 7 8 9 10)",env);
        //engine.execute("(write 1 2 3 4)",env);
        //engine.execute("(write 5 6 7 8)",env);
        //engine.execute("(defun fibonacci (n &optional (a 0) (b 1)) (if (zerop n) null ((cons a (fibonacci (1- n) b (+ a b))))))",env);
        engine.execute("(defun aaa (&optional (n 5)) (if (print (zerop n)) null (cons n (aaa (print (+ 0 0))))))",env);
        //engine.execute("(print (aaa 0))",env);
        engine.execute("(print 'HIII)",env);
        //engine.execute("(print (print (zerop 1)))",env);
        //engine.execute("(print (print (zerop 0)))",env); //(print (+ 0 0)
        engine.execute("(let* ((n 1) (b 2)) (print (+ b n)))",env);
        //engine.execute("(print (zerop (+ 0 0))))",env);
    }
}