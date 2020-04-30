package EasyCompiler.Runtime;

public class RuntimePop extends RuntimeOperator
{
    public RuntimePop()        
    {
        super();
    }

    public void Execute(Context context)
    {
        context.Stack().pop();
        context.setCurrent(context.Current() + 1);
    }
}

