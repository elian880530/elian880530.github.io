package EasyCompiler.Runtime;

import EasyCompiler.InOut;

public class RuntimeDivisionFloat extends RuntimeOperator
{
    public RuntimeDivisionFloat()
    {
        super();
    }

    public void Execute(Context context)
    {
        Float arg2 = (Float)context.Stack().pop();
        Float arg1 = (Float)context.Stack().pop();
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

