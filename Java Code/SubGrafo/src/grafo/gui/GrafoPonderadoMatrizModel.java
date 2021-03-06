package grafo.gui;

import grafo.GrafoPonderadoMatriz;
import grafo.ListaAdyacencia;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class GrafoPonderadoMatrizModel extends GrafoModel{

  public GrafoPonderadoMatrizModel(GrafoPonderadoMatriz grafo) {
    super(grafo);
  }

  public int getColumnCount() {
    return ((GrafoPonderadoMatriz)grafo).getMatrizAdyacencia().getColumnCount()+1;
  }

  public int getRowCount() {
    return ((GrafoPonderadoMatriz)grafo).getMatrizAdyacencia().getRowCount()+1;
  }

  protected Object getContent(int rowIndex, int columnIndex) {
    if (rowIndex == 0 && columnIndex == 0) return "Fila\\Col";
    if (rowIndex == 0) return Integer.toString(columnIndex);
    if (columnIndex == 0) return Integer.toString(rowIndex);
    return ((GrafoPonderadoMatriz)grafo).getMatrizAdyacencia().getContent(rowIndex, columnIndex);
  }

}
