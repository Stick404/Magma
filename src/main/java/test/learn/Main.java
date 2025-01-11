package test.learn;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.Engine.*;
import test.learn.TL.Core.TLEnvironment;

import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws Exception {
        PrintStream logger = System.out;
        Engine engine = new Engine();

        // TODO: Make the "evaluate" method in Engine.java use a hashmap, not a else-if chain
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
        logger.println(engine.execute("(eval '(+ 3 v))",env).getValue());
        logger.println(engine.execute("(parse \"progn\")",env).getValue());
    }
}