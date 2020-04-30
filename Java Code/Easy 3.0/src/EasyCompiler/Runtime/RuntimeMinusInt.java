package EasyCompiler.Runtime;

public class RuntimeMinusInt extends RuntimeOperator
{
    public RuntimeMinusInt()            
    {
        super();
    }

    public void Execute(Context context)
    {
        Integer arg2 = (Integer)context.Stack().pop();
        Integer arg1 = (Integer)context.Stack().pop();
        context.Stack().push(arg1 - arg2);
        context.setCurrent( context.Current() + 1 );
    }
}
