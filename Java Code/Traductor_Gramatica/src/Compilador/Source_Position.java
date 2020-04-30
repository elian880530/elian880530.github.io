/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Compilador;

public class Source_Position {

    private int start;
    private int finish;
    private int line;

    public Source_Position(int start, int finish, int line) 
    {
        this.start = start;
        this.finish = finish;
        this.line = line;           
    }
    
    @Override
    public String toString()
    {
        return "line {" + Integer.toString(line) + "}";
    }
    
    public int getStart()
    {
        return start;
    }
    public int getFinish()
    {
        return finish;
    }
    public int getLine()
    {
        return line;
    }
}
