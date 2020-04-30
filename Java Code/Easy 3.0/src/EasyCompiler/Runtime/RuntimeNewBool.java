package EasyCompiler.Runtime;

public class RuntimeNewBool extends RuntimeOperator
{
    public RuntimeNewBool()        
    {
        super();
    }

    public void Execute(Context context)
    {
        int address = ((Address)(context.Code().get(context.Current() + 1))).Value();
        context.Memory().set(address , false );
        context.setCurrent( context.Current() + 2 );
    }
}

