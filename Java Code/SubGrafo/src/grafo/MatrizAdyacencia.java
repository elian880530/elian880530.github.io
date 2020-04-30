package grafo;

public class MatrizAdyacencia {

	protected int[][] matriz;

	public MatrizAdyacencia(){
		matriz = new int[0][0];
	}

	
	public boolean vacia(){
		return matriz.length == 0;
	}


	public boolean estaElArco(int v1, int v2){
		assert ((v1 <= matriz.length && v2 <= matriz.length) && (v1 > 0 && v2 > 0)) : "v1 < lista.size() && v2 < lista.size()";
		return matriz[v1-1][v2-1] > 0;
	}

	public void insertaElArco(int v1, int v2, int peso){
		assert (!estaElArco(v1,v2)) : "!estaElArco(v1,v2)";
		assert (peso > 0) : "peso > 0";
		matriz[v1-1][v2-1] = peso;
		matriz[v2-1][v1-1] = peso;
	}


	public void insertaVertice(int v){
		assert (v == matriz.length) : "v == lista.size()";//solo por si acaso
		int[][] tmp = matriz ;
		matriz = new int[tmp.length+1][tmp.length+1];
		for (int i = 0; i < tmp.length; i++){
			for (int j = 0; j < tmp.length; j++){
				matriz[i][j] = tmp[i][j];
			}
		}
	}
        
        public int getColumnCount(){
            return matriz.length;
        }
        public int getRowCount(){
            return matriz.length;
        }
        
        public String getContent(int v1, int v2){
            return ""+matriz[v1-1][v2-1];
        }

}