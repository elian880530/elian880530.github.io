package Arbol;

import Compilador.*;

public abstract class AST
{
    private Source_Position position;

    public Source_Position Position()
    {                
        return position; 
    }

    public AST(Source_Position position)
    {
        this.position = position;
    }

    public abstract Object Visit(Visitor visitor, Object arg);
}
