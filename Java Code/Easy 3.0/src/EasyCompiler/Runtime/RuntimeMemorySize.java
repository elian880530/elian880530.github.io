package EasyCompiler.Runtime;

public class RuntimeMemorySize extends RuntimeOperator
{
    public RuntimeMemorySize()        
    {
        super();
    }

    public void Execute(Context context)
    {        
        int size = ((IntValue)(context.Code().get(context.Current() + 1))).Value();
        while (context.Memory().size() < size)
            context.Memory().add(null);
        context.setCurrent( context.Current() + 2 );
    }
}

