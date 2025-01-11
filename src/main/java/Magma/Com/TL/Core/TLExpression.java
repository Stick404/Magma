package Magma.Com.TL.Core;

import java.util.Collections;
import java.util.List;

import static Magma.Com.TL.Core.Engine.listToString;

public abstract class TLExpression extends TLFunction{
    public abstract Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception;
}
