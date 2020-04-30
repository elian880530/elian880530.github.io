package EasyCompiler.Runtime;

public class RuntimeJumpIfFalse extends RuntimeOperator
{
    public RuntimeJumpIfFalse()            
    {
        super();
    }

    public void Execute(Context context)
    {

        int address = ((Address)(context.Code().get(context.Current() + 1))).Value();            
        Boolean arg = (Boolean)context.Stack().pop();
        if (!arg)
            context.setCurrent( address );
        else
            context.setCurrent( context.Current() + 2 );                
    }
}

