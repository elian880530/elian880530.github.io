package grafo;

import java.util.*;

public class GrafoPonderadoMatriz<T> extends GrafoPonderado<T>{

	
	protected MatrizAdyacencia matriz;
	protected Vector<T> vertices;

	public GrafoPonderadoMatriz(){
		matriz = new MatrizAdyacencia();
		vertices = new Vector<T>();
	}


	public boolean esVacio(){
		return matriz.vacia();
	}

	public boolean estaElVertice(T v){
		return vertices.contains(v);
	}

	public boolean estaElArco(Arco<T> a){
		assert (vertices.contains(a.v1) && (vertices.contains(a.v2))) : "vertices.contains(a.v1) && (vertices.contains(a.v2) fail";
		return matriz.estaElArco(vertices.indexOf(a.v1)+1, vertices.indexOf(a.v2)+1);
	}

	public void insertaVertice(T v){
		assert (!estaElVertice(v)) : "!estaElVertice(v) fail";
		matriz.insertaVertice(vertices.size());
		vertices.addElement(v);
	}

	public void insertaArco(Arco<T> a){
		insertaArco(a, 1);
	}

	public void insertaArco(Arco<T> a, int peso){
		assert (!estaElArco(a))	: "!estaElArco(a) fail";
		matriz.insertaElArco(vertices.indexOf(a.v1)+1, vertices.indexOf(a.v2)+1, peso);
	}

    public MatrizAdyacencia getMatrizAdyacencia() {
        return matriz;
    }

    public int cantVertices(){
        return vertices.size();
    }

}