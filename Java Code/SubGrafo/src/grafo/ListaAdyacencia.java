package grafo;

import java.util.*;

public class ListaAdyacencia {

	protected Vector<Vector<Integer>> lista;

	public ListaAdyacencia(){
		lista = new Vector<Vector<Integer>>();
	}

	
	public boolean vacia(){
		return lista.isEmpty();
	}


	public boolean estaElArco(int v1, int v2){
		assert ((v1<= lista.size()) && (v2 <= lista.size()) && (v1 > 0) && (v2 > 0)) : "v1 < lista.size() && v2 < lista.size()";
		Vector<Integer> n = lista.elementAt(v1-1);
		return n.contains(new Integer( v2));
	}

	public void insertaElArco(int v1, int v2){
		assert (!estaElArco(v1,v2)) : "!estaElArco(v1,v2)";		
		Integer x = new Integer(v1);
		Integer y = new Integer(v2);
		Vector<Integer> n = lista.elementAt(v1-1);
		Vector<Integer> m = lista.elementAt(v2-1);
		n.addElement(y);		
		m.addElement(x);		
	}


	public void insertaVertice(int v){
		assert (v == lista.size()) : "v == lista.size()";//solo por si acaso
		lista.addElement(new Vector<Integer>());
	}
	
	public String getContent(int v1, int v2){
    if (estaElArco(v1,v2)) return ""+v2;
    else return "";
	}

  public int getColumnCount(){
    return lista.size();
  }
  public int getRowCount(){
    return lista.size();
  }
        

}