package EasyCompiler.Runtime;

import EasyCompiler.InOut;

public class RuntimeDivisionInt extends RuntimeOperator
{
    public RuntimeDivisionInt()        
    {
        super();
    }

    public void Execute(Context context)
    {
        Integer arg2 = (Integer)context.Stack().pop();
        Integer arg1 = (Integer)context.Stack().pop();
        if (arg2 != 0)
        {
            context.Stack().push(arg1 / arg2);
            context.setCurrent(context.Current() + 1);
        }
        else
        {
            InOut.Write("Program has failed due to division by zero.", null);
            context.setHalt(true);
        }
    }
}
