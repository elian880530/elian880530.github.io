package grafo.gui;

import grafo.GrafoPonderadoLista;
import grafo.ListaAdyacencia;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class GrafoPonderadoListaModel extends GrafoModel{

  public GrafoPonderadoListaModel(GrafoPonderadoLista grafo) {
    super(grafo);
  }

  public int getColumnCount() {
    return ((GrafoPonderadoLista)grafo).getListaAdyacencia().getColumnCount();
  }

  public int getRowCount() {
    return ((GrafoPonderadoLista)grafo).getListaAdyacencia().getRowCount()+1;
  }

  protected Object getContent(int rowIndex, int columnIndex) {
    if (rowIndex == 0) return Integer.toString(columnIndex+1);
    return ((GrafoPonderadoLista)grafo).getListaAdyacencia().getContent(rowIndex, columnIndex+1);
  }

}
