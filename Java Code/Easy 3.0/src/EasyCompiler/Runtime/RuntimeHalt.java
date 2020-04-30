package EasyCompiler.Runtime;
import EasyCompiler.InOut;

public class RuntimeHalt extends RuntimeOperator
{
    public RuntimeHalt()        
    {
        super();
    }

    public void Execute(Context context)
    {
        context.setHalt( true );
        InOut.Write("Program has halted normally.", null);
    }
}

