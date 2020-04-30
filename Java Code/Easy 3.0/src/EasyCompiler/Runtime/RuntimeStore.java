package EasyCompiler.Runtime;

public class RuntimeStore extends RuntimeOperator
{
    public RuntimeStore()
    {
        super();
    }

    public void Execute(Context context)
    {
        Object value = context.Stack().pop();
        Integer address = (Integer)context.Stack().pop();        
        context.Memory().set(address, value);
        context.setCurrent(context.Current() + 1);
    }
}

