package EasyCompiler.Runtime;

public class RuntimeJump extends RuntimeOperator
{
    public RuntimeJump()
    {
        super();
    }

    public void Execute(Context context)
    {
        
        int address = ((Address)(context.Code().get(context.Current() + 1))).Value();
        context.setCurrent( address );
    }
}

