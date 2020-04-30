/*
 * CListaArregloRedim.java
 *
 * Created on 26 de septiembre de 2006, 13:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package auxiliar;

/**
 *
 * @author lester
 */
public class JListaArregloRedim<Tipo> extends JListaArreglo<Tipo> implements ILista<Tipo> {
    
    int redim;
    
    /** Creates a new instance of CListaArregloRedim */
    public JListaArregloRedim() {
        super();
        this.redim = 10;
    }
    
     public JListaArregloRedim(int maxValue) {
        super();
        this.redim = 10;
    }
     
    public JListaArregloRedim(int maxValue, int redim) {
        super();
        this.redim = redim;
    }
    
    protected void redimencionar() {
        Tipo[] aux = (Tipo[]) (new Object[arrayList.length + redim]);
        for(int i = 0; i < arrayList.length;  i++)
            aux[i] = arrayList[i];
    }
    
     public void insertar(Tipo x, int i) {
         if((i < pos) && (i >= 0))
         {
             if(pos >= arrayList.length)
                 redimencionar();
             for(int j = pos - 1; j >=i;  j--)
                 arrayList[j + 1] = arrayList[j];
             arrayList[i] = x;
             pos++;   
         }
         else 
            throw new JExcepcionParametroNoValido(); 
    }
    
    public void adicionar(Tipo x) {
        if(pos >= arrayList.length )
            redimencionar();
        arrayList[pos] = x;
        pos++;    
    }
    
}
