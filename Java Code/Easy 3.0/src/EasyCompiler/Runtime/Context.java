package EasyCompiler.Runtime;

import java.util.List;
import java.util.LinkedList;
import java.util.Stack;


public class Context
{
    private Stack<Object> stack;
    private List<Object> memory;
    private int current;
    private boolean halt;
    private List<RuntimeEntity> code;

    public Context(List<RuntimeEntity> code)
    {
        this.code = code;
        this.stack = new Stack<Object>();
        this.memory = new LinkedList<Object>();
        current = 0;
        halt = false;
    }

    public Stack<Object> Stack()
    {
        return stack;     
    }

    public List<Object> Memory()
    {
        return memory;     
    }

    public int Current()
    {
        return current;
    }
    
    public void setCurrent( int current )
    {
        this.current = current;
    }

    public boolean Halt()
    {
            return halt;
    }
    
    public void setHalt(   boolean halt )
    {
        this.halt = halt;
    }
    
    public List<RuntimeEntity> Code()
    {
        return code;      
    }
}