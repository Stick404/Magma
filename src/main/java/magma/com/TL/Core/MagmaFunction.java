package magma.com.TL.Core;

import magma.com.TL.Core.TL_OLD.TLEnvironment;

// How this system *should* work:
// Instead of calling a method that calls a method that calls a method-
// we instead "queue" each function as lambdas, then feed the output of the lambda into the next
// until there are no more lambdas

public abstract class MagmaFunction implements MagmaRoot<MagmaFunction> {
    protected abstract MagmaRoot execute(MagmaList args, TLEnvironment environment, Engine engine); //and this is the "function"

    @Override
    public MagmaFunction getValue() {
        return this;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }
}
