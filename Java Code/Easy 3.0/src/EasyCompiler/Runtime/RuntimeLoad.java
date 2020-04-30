package EasyCompiler.Runtime;

public class RuntimeLoad extends RuntimeOperator
{
    public RuntimeLoad()        
    {
        super();
    }

    public void Execute(Context context)
    {
        int address = ((Address)(context.Code().get(context.Current() + 1))).Value();
        context.Stack().push(context.Memory().get(address));
        context.setCurrent( context.Current() + 2 );
    }
}

