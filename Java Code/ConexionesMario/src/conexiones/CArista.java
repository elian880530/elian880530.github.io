/*
 * CArista.java
 *
 * Created on 15 de enero de 2009, 0:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package conexiones;

/**
 *
 * @author eghernandez
 */

public class CArista 
{
    private CVertice v1;
    private CVertice v2;
    private int peso;
    
    public CArista(CVertice v1, CVertice v2, int peso) 
    {
        this.v1 = v1;
        this.v2 = v2;
        this.peso = peso;
    }
    
    public CVertice getV1() 
    {
        return v1;
    }
    
    public CVertice getV2() 
    {
        return v2;
    }
    
    public int getPeso() 
    {
        return peso;
    }
}
