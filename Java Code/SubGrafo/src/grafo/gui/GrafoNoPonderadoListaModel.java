package grafo.gui;

import grafo.GrafoNoPonderadoLista;
import grafo.ListaAdyacencia;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class GrafoNoPonderadoListaModel extends GrafoModel{

  public GrafoNoPonderadoListaModel(GrafoNoPonderadoLista grafo) {
    super(grafo);
  }

  public int getColumnCount() {
    return ((GrafoNoPonderadoLista)grafo).getListaAdyacencia().getColumnCount();
  }

  public int getRowCount() {
    return ((GrafoNoPonderadoLista)grafo).getListaAdyacencia().getRowCount()+1;
  }

  protected Object getContent(int rowIndex, int columnIndex) {
    if (rowIndex == 0) return Integer.toString(columnIndex+1);
    return ((GrafoNoPonderadoLista)grafo).getListaAdyacencia().getContent(rowIndex, columnIndex+1);
  }

}
