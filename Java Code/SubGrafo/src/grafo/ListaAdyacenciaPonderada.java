package grafo;

import java.awt.Point;
import java.util.*;

public class ListaAdyacenciaPonderada extends ListaAdyacencia{

	protected Vector<Vector<Point>> lista;

	public ListaAdyacenciaPonderada(){
		lista = new Vector<Vector<Point>>();
	}

	
	public boolean vacia(){
		return lista.isEmpty();
	}


	public boolean estaElArco(int v1, int v2){
		assert (v1 <= lista.size() && v2 <= lista.size() && v1 > 0 && v2 > 0) : "v1 < lista.size() && v2 < lista.size()";
		Vector<Point> n = lista.elementAt(v1-1);
		Point p;
		for (int i = 0; i < n.size(); i++){
			p = n.elementAt(i);
			if (p.x == v2) return true;
		}
		return false;
	}

	public void insertaElArco(int v1, int v2){
		insertaElArco(v1,v2,1);
	}

	public void insertaElArco(int v1, int v2, int peso){
		assert (!estaElArco(v1,v2)) : "!estaElArco(v1,v2)";
		Vector<Point> n = lista.elementAt(v1-1);
		Vector<Point> m = lista.elementAt(v2-1);
		n.addElement(new Point(v2, peso));		
		m.addElement(new Point(v1, peso));		
	}


	public void insertaVertice(int v){
		assert (v == lista.size()) : "v == lista.size()";//solo por si acaso
		lista.addElement(new Vector<Point>());
	}
	
        public String getContent(int v1, int v2){
            if (estaElArco(v1,v2)) {
							Vector<Point> n = lista.elementAt(v1-1);
							Point p;
							for (int i = 0; i < n.size(); i++){
								p = n.elementAt(i);
								if (p.x == v2) return "<"+v2+","+p.y+">";
							}
							throw new RuntimeException("bugs");
					  }
            else return "";
        }
	
}