package EasyCompiler.Runtime;

public class RuntimeNewInt extends RuntimeOperator
{
    public RuntimeNewInt()        
    {
        super();
    }

    public void Execute(Context context)
    {
        int address = ((Address)(context.Code().get(context.Current() + 1))).Value();
        context.Memory().set(address , 0 );
        context.setCurrent( context.Current() + 2 );
    }
}