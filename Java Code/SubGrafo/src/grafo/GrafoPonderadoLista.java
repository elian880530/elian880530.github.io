package grafo;

import java.util.*;

public class GrafoPonderadoLista<T> extends GrafoPonderado<T>{

	
	protected ListaAdyacenciaPonderada lista;
	protected Vector<T> vertices;

	public GrafoPonderadoLista(){
		lista = new ListaAdyacenciaPonderada();
		vertices = new Vector<T>();
	}


	public boolean esVacio(){
		return lista.vacia();
	}

	public boolean estaElVertice(T v){
		return vertices.contains(v);
	}

	public boolean estaElArco(Arco<T> a){
		assert (vertices.contains(a.v1) && (vertices.contains(a.v2))) : "vertices.contains(a.v1) && (vertices.contains(a.v2) fail";
		return lista.estaElArco(vertices.indexOf(a.v1)+1, vertices.indexOf(a.v2)+1);
	}

	public void insertaVertice(T v){
		assert (!estaElVertice(v)) : "!estaElVertice(v) fail";
		vertices.addElement(v);
		lista.insertaVertice(vertices.size()-1);
	}

	public void insertaArco(Arco<T> a){
		insertaArco(a, 1);
	}

	public void insertaArco(Arco<T> a, int peso){
		assert (peso > 0) : "peso > 0";
		assert (!estaElArco(a))	: "!estaElArco(a) fail";
		lista.insertaElArco(vertices.indexOf(a.v1)+1, vertices.indexOf(a.v2)+1, peso);
	}

    public ListaAdyacenciaPonderada getListaAdyacencia() {
        return lista;
    }
    public int cantVertices(){
        return vertices.size();
    }

}