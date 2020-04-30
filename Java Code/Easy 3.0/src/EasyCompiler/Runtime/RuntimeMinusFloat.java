package EasyCompiler.Runtime;

public class RuntimeMinusFloat extends RuntimeOperator
{
    public RuntimeMinusFloat()            
    {
        super();
    }

    public void Execute(Context context)
    {
        Float arg2 = (Float)context.Stack().pop();
        Float arg1 = (Float)context.Stack().pop();
        context.Stack().push(arg1 - arg2);
        context.setCurrent( context.Current() + 1 );
    }
}
