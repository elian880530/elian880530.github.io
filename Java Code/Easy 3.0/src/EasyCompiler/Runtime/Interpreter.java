package EasyCompiler.Runtime;
import EasyCompiler.*;

import java.util.List;

public class Interpreter
{
    private Context context;

    public Interpreter(List<RuntimeEntity> code)
    {
        context = new Context(code);
    }

    public void Execute() throws Exception 
    {
        InOut.Write("********** Easy Interpreter (C# Version 3.0) **********", null);
        InOut.Write("Program is running...", null);        
        while (!context.Halt()) 
            context.Code().get(context.Current()).Execute(context);
    }
}
