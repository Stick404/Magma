package magma.com.TL.Core.TL_OLD;

import magma.com.TL.Core.Engine;

import java.util.Collections;
import java.util.List;

import static magma.com.TL.Core.Engine.listToString;

public abstract class TLFunction implements Engine.TLExpression {
    public abstract Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception;
    protected List<?> getParameterHelpNames() {
        return Collections.emptyList();
    }
    @Override public Object getValue() {
        return this;
    }
    @Override public boolean asBoolean() {
        return true;
    }
    @Override public String toString() {
        return listToString("TLFunction(", getParameterHelpNames(), ",", ")");
    }
}