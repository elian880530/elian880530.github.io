package EasyCompiler.Runtime;

import EasyCompiler.InOut;

public class RuntimeReadFloat extends RuntimeOperator
{
    public RuntimeReadFloat()
    {
        super();
    }

    public void Execute(Context context)
    {
        Integer address = (Integer)context.Stack().pop();
        String svalue = InOut.Read();
        Float value;        
        try{
            value = Float.parseFloat(svalue);
            context.Memory().set(address, value);
            context.setCurrent(context.Current() + 1);
        }
        catch( NumberFormatException e )
        {
            String[] arg = new String[1];
            arg[0] = svalue;
            InOut.Write("Cannot convert '{0}' to 'float'" , arg);
            context.setHalt(true);            
        }
    }
}

