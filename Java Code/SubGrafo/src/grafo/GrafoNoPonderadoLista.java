package grafo;
import auxiliar.ILista;
import auxiliar.JAListaNodoSE;
import auxiliar.JListaNodoSE;
import auxiliar.JListaArreglo;
import auxiliar.JNodoSE;
import java.util.*;

public class GrafoNoPonderadoLista<T> extends GrafoNoPonderado<T>{

	
	protected ListaAdyacencia lista;
	protected Vector<T> vertices;

	public GrafoNoPonderadoLista(){
		lista = new ListaAdyacencia();
		vertices = new Vector<T>();
	}


	public boolean esVacio(){
		return lista.vacia();
	}

	public boolean estaElVertice(T v){
		return vertices.contains(v);
	}

	public boolean estaElArco(Arco<T> a){
		assert ((vertices.contains(a.v1) && (vertices.contains(a.v2)))) : "vertices.contains(a.v1) && (vertices.contains(a.v2) fail";
		return lista.estaElArco(vertices.indexOf(a.v1)+1, vertices.indexOf(a.v2)+1);
	}

	public void insertaVertice(T v){
		assert (!estaElVertice(v)) : "!estaElVertice(v) fail";
		lista.insertaVertice(vertices.size());
		vertices.addElement(v);
	}

	public void insertaArco(Arco<T> a){
		assert (!estaElArco(a))	: "!estaElArco(a) fail";
		lista.insertaElArco(vertices.indexOf(a.v1)+1, vertices.indexOf(a.v2)+1);
	}

    public ListaAdyacencia getListaAdyacencia() {
        return lista;
    }

    public int cantVertices(){
        return vertices.size();
    }

}