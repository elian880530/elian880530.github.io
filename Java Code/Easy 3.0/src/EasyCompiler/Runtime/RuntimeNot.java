package EasyCompiler.Runtime;

public class RuntimeNot extends RuntimeOperator
{
    public RuntimeNot()
    {
        super();
    }

    public void Execute(Context context)
    {
        Boolean arg = (Boolean)context.Stack().pop();
        context.Stack().push(!arg);
        context.setCurrent( context.Current() + 1 );
    }
}

