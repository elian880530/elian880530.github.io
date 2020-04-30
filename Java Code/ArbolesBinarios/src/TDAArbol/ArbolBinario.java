/*
 * CArbolBinario.java
 *
 * Created on 19 de octubre de 2006, 14:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDAArbol;

import TDALista.ExcepcionParametroNoValido;
import TDALista.ILista;
import TDALista.ListaSE;
/**
 *
 * @author lester
 */
public class ArbolBinario<Tipo> implements IArbolBinario<Tipo> {
    
    protected Tipo raiz;
    protected IArbolBinario<Tipo> der;
    protected IArbolBinario<Tipo> izq;
    
    /** Creates a new instance of CArbolBinario */
    public ArbolBinario(Tipo raiz)  {
        this.raiz = raiz;
        if(raiz != null)
        {
            der = new ArbolBinario<Tipo>(null);
            izq = new ArbolBinario<Tipo>(null);
        }
    }
    
    public Tipo getRaiz() {
        return raiz;
    }
    
    public boolean esHoja() {
        return (izq.esVacio())&&(der.esVacio());
    }
    
     public boolean esVacio() {
        return ((getRaiz() == null)&&(izq == null)&&(der == null));
    }
    
    public int grado() {
       if(esVacio())
           return 0;
       return 2;
    }
    
    public int altura() {            
        if(!esHoja())
        {
            int auxI = this.subArbolIzquierdo().altura() + 1;
            int auxD = this.subArbolIzquierdo().altura() + 1;
            if(auxD > auxI)
                return auxD;
            return auxI;
         
        }
        return 0;
    }
    
    public IArbolBinario<Tipo> subArbolIzquierdo() {
        return izq;
    }
    
    public IArbolBinario<Tipo> subArbolDerecho() {
        return der;
    }
    
    public void adicionarIzquierdo(IArbolBinario<Tipo> nsa) {
        if(nsa == null)
            throw new ExcepcionParametroNoValido();
        izq = nsa;
    }
    
    public void adicionarDerecho(IArbolBinario<Tipo> nsa) {
        if(nsa == null)
            throw new ExcepcionParametroNoValido();
        der = nsa;
    }
    
    public void podarIzquierdo() {
        izq = new ArbolBinario<Tipo>(null);
    }
    
    public void podarDerecho() {
        der = new ArbolBinario<Tipo>(null);
    }
    
     public ILista<Tipo> preOrden() {
        ILista<Tipo> result= new ListaSE<Tipo>();

        result.adicionar(raiz);
                   
        if(!esHoja())
        {
            ILista<Tipo> auxI = izq.preOrden();
            ILista<Tipo> auxD = der.preOrden();
            for(int j = 0; j < auxI.longitud(); j++)
               result.adicionar(auxI.obtener(j));
            
            for(int j = 0; j < auxD.longitud(); j++)
               result.adicionar(auxD.obtener(j));
        }
        
        return result;
    }

    public ILista<Tipo> posOrden() {
        ILista<Tipo> result= new ListaSE<Tipo>();
        
        if(!esHoja())
        {
            ILista<Tipo> auxI = izq.posOrden();
            ILista<Tipo> auxD = der.posOrden();
            for(int j = 0; j < auxI.longitud(); j++)
               result.adicionar(auxI.obtener(j));
    
            for(int j = 0; j < auxD.longitud(); j++)
               result.adicionar(auxD.obtener(j));
            
        }
        
        result.adicionar(raiz);
        
        return result;
    }

    public ILista<Tipo> entreOrden() {
        ILista<Tipo> result= new ListaSE<Tipo>();
        
        if(!esHoja())
        {
            ILista<Tipo> auxI = izq.entreOrden();
            ILista<Tipo> auxD = der.entreOrden();
            for(int j = 0; j < auxI.longitud(); j++)
               result.adicionar(auxI.obtener(j));
            
            result.adicionar(raiz);
            
            for(int j = 0; j < auxD.longitud(); j++)
               result.adicionar(auxD.obtener(j));
       
        }
        else 
           result.adicionar(raiz);
        
        return result;
    }

    public ILista<Tipo> aLoAncho() {
        ILista<IArbolBinario<Tipo>> aux = new ListaSE<IArbolBinario<Tipo>>();
        ILista<Tipo> result = new ListaSE<Tipo>();
        if(!esVacio())
        {
            aux.adicionar(this);  
            result.adicionar(raiz);
       
           for(int i = 0; i < aux.longitud(); i++)
           {
                if(!aux.obtener(i).subArbolIzquierdo().esVacio())
                {
                    aux.adicionar(aux.obtener(i).subArbolIzquierdo());
                    result.adicionar(aux.obtener(i).subArbolIzquierdo().getRaiz());
                }
            
                if(!aux.obtener(i).subArbolDerecho().esVacio())
                {
                    aux.adicionar(aux.obtener(i).subArbolDerecho());
                    result.adicionar(aux.obtener(i).subArbolDerecho().getRaiz());
                }
           }
        }
           
        return result;
    }
   public int ContarDerechos(){
   
       if(esHoja())
           return 0;
       if(izq.esVacio()&&!der.esVacio())
           return der.ContarDerechos()+1;
       if(!izq.esVacio()&&der.esVacio())
           return izq.ContarDerechos()+1;
       if(!izq.esVacio()&&!der.esVacio())
           return izq.ContarDerechos()+der.ContarDerechos()+1;
       return 0;
      
   }
    
}
