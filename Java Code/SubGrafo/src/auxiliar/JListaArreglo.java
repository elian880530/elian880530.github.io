/*
 * CListaArreglo.java
 *
 * Created on 26 de septiembre de 2006, 3:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package auxiliar;

import java.lang.reflect.Array;

/**
 *
 * @author lester
 */
public class JListaArreglo<Tipo> implements ILista<Tipo> {
    
    protected Tipo[] arrayList;
    protected int pos;
    
    /** Creates a new instance of CListaArreglo */

    public JListaArreglo() {
        arrayList = (Tipo[]) (new Object[100]);
        pos = 0;
    }
    
    public JListaArreglo(int maxValue) {
        arrayList = (Tipo[]) (new Object[maxValue]);
        pos = 0;
    }
    
    public void insertar(Tipo x, int i) {
         if((i < pos) && (i >= 0))
         {
             if(pos < arrayList.length)
             {
                 for(int j = pos - 1; j >=i;  j--)
                     arrayList[j + 1] = arrayList[j];
                 arrayList[i] = x;
                 pos++;
             }
             else
                 throw new JExcepcionMemoria();
         }
         else 
            throw new JExcepcionParametroNoValido(); 
    }
    
    public void adicionar(Tipo x) {
        if(pos < arrayList.length )
        {
            arrayList[pos] = x;
            pos++;
        }
        else
            throw new JExcepcionMemoria();
    }
    
    public Tipo obtener(int i) {
        if((i < pos )&&(i >= 0))
            return arrayList[i];
        throw new JExcepcionParametroNoValido();
    }
    
    public boolean eliminar(int i) {
        if((i < pos) && (i >= 0))
        {     
            for(int j = i; j < pos;  j++)
                arrayList[j] = arrayList[j + 1];
            pos--;
            return true;
        }
        else 
           throw new JExcepcionParametroNoValido(); 
    }
    
    public int longitud() {
        return pos;
    }
    public boolean vacia() {
        return pos == 0;
    }
}
