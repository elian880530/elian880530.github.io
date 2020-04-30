package EasyCompiler.Runtime;

public class RuntimeNewFloat extends RuntimeOperator
{
    public RuntimeNewFloat()        
    {
        super();
    }

    public void Execute(Context context)
    {
        int address = ((Address)(context.Code().get(context.Current() + 1))).Value();
        context.Memory().set(address , 0F );
        context.setCurrent( context.Current() + 2 );
    }
}
