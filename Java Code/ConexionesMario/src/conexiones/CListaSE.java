/*
 * CListaSE.java
 *
 *  Created on 15 de enero de 2009, 0:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package conexiones;

/**
 *
 * @author eghernandez
 */

public class CListaSE<T>
{
   private CNodo<T> cabeza;
    
    public CListaSE() 
    {
        cabeza = null;
    }
    
    public int Longitud()
    {
        if(EsVacia())
            return 0;
        else
        {
            int longitud = 1;
            CNodo<T> cursor = cabeza;
            while(cursor.getSiguiente() != null)
            {
                cursor = cursor.getSiguiente();
                longitud++;
            }
            return longitud;
        }
    }
    
    public boolean EsVacia()
    {
        if(cabeza == null)
            return true;
        else
            return false;
    }
    
    public void Adicionar(T dato)
    {
        CNodo<T> nuevo_nodo = new CNodo<T>(dato, null);
        if(EsVacia())
            cabeza = nuevo_nodo;
        else
        {
            CNodo<T> cursor = cabeza;
            while(cursor.getSiguiente() != null)
                cursor = cursor.getSiguiente();
            cursor.setSiguiente(nuevo_nodo);
        }  
    }
    
    public void Insertar(T dato, int pos)throws Exception
    {
        CNodo<T> nuevo_nodo = new CNodo<T>(dato,null);
        if(pos == 1)
        {
            nuevo_nodo.setSiguiente(cabeza);
            cabeza = nuevo_nodo;
        }
        else if(pos == Longitud() + 1)
        {
            Adicionar(dato);
        }
        else
        {
            if(pos < 1 || pos > Longitud())
                throw new Exception("La posicion es incorrecta");
            CNodo<T> cursor = cabeza;
            for (int i = 1; i < pos; i++) 
                cursor = cursor.getSiguiente();
            nuevo_nodo.setSiguiente(cursor.getSiguiente());
            cursor.setSiguiente(nuevo_nodo);
        }
    }
    
    public T Obtener(int pos)throws Exception
    {
        if(pos >= 1 && pos <= Longitud())
        {
            if(pos == 1)
                return cabeza.getDato();
            else
            {
                 CNodo<T> cursor = cabeza;
                 int pos_cursor = 1;
                 while(cursor.getSiguiente() != null && pos_cursor != pos)
                 {
                     cursor = cursor.getSiguiente();
                     pos_cursor++;
                 }
                 return cursor.getDato();
            }
        }
        else
            throw new Exception("La posicion es incorrecta");
    }
    
    public void Eliminar(int pos)throws Exception
    {
        if(pos >= 1 && pos <= Longitud())
        {
            if(pos == 1)
                cabeza = cabeza.getSiguiente();
            else
            {
                CNodo<T> cursor = cabeza;
                int pos_cursor = 1;
                while(cursor.getSiguiente() != null && pos_cursor != pos - 1)
                {
                    cursor = cursor.getSiguiente();
                    pos_cursor++;
                }
                cursor.setSiguiente(cursor.getSiguiente().getSiguiente());
            }
        }
        else
            throw new Exception("La posicion es incorrecta");
    }    
    
    public void AdicionarTodo(CListaSE<T> lista)throws Exception
    {
        for(int i = 1; i <= lista.Longitud(); i++) 
        {
            this.Adicionar(lista.Obtener(i));
        }
    }
}
