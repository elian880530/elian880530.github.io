package grafo.gui;

import javax.swing.table.*;

import grafo.*;

/** comment 2006-Oct-28 Trujillo
 * Esta clase surge a partir de la necesidad de en primer lugar tratar
* a lo cuatro grafos de la misma forma, y en esegundo lugar mejorar
* algunos ordenes de complejidad respecto al calculo de la cantidad
* de filas que tiene una lista de adyacencia por ejemplo.
* La clase es abstracta y de ella heredan los dos tipos
 */

public abstract class GrafoModel extends AbstractTableModel{

  protected Grafo<String> grafo;

  public GrafoModel(Grafo<String> g) {
    assert(g != null) : "assert(g != null)";
    grafo = g;
  }

  public abstract int getColumnCount();

  public abstract int getRowCount();

  public Object getValueAt(int rowIndex, int columnIndex){
    assert((rowIndex >= 0 && rowIndex < getRowCount())) : "assert((rowIndex >= 0 && rowIndex < getRowCount()))";
    assert((columnIndex >= 0 && columnIndex < getColumnCount())) : "assert((rowIndex >= 0 && rowIndex < getRowCount()))";
    return getContent(rowIndex, columnIndex);
  }

  protected abstract Object getContent(int rowIndex, int columnIndex);

  public boolean hasVertice(String text) {
    return grafo.estaElVertice(text);
  }

  public void insertaVertice(String text){
    grafo.insertaVertice(text);
  }


}
