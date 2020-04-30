/*
 * MyCanvas.java
 *
 * Created on 7 de noviembre de 2007, 0:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package laboratorio3grafos;

import java.awt.*;
import TDA_Grafo.*;
import TDALista.*;

public class MyCanvas extends Canvas{
    
    /** Creates a new instance of MyCanvas */
    public MyCanvas() {
    }
    
    protected Grafo<String> grafo;
    
    public void paint(Graphics g) {
        super.paint(g);
        if(grafo != null)
        {
            if(grafo instanceof GrafoNoPonderadoMA)
                matrizAdyacencia(g);
            else
                listaAdyacentes(g);
        }
    }
    
    public void setGrafo(Grafo g)
    {
        grafo = g;
    }
    
    protected void listaAdyacentes(Graphics g) {
        int ancho = 40, alto = 20;
     
        GrafoNoPonderadoLA<String> grafo_ma = (GrafoNoPonderadoLA<String>)grafo;
        ILista<ILista<Integer>> listaA = grafo_ma.ListaAdyacencia();//Obtener la lista real
        
         ILista<Integer> listas = new ListaSE();
        
        int longitud = listaA.Longitud();
        for(int i = 0; i < longitud; i++) {
            g.setColor(Color.BLUE);
            g.drawString( i + ": ", 0, alto * (i + 1) - 3);
            listas = listaA.Obtener(i);
            int longitud1 = listas.Longitud();
            String imprimir = "";
            for(int j = 0; j < longitud1; j++)
                imprimir += listas.Obtener(j) + ", ";
            g.setColor(Color.BLACK);
            if(imprimir.length() > 0) {
                imprimir = imprimir.substring(0, imprimir.length() - 2);
                g.drawString( imprimir, 20, alto * (i + 1) - 3);
            }else {
                g.setColor(Color.RED);
                g.drawString( "No tiene adyacentes", 20, alto * (i + 1) - 3);
            }
                        
        }
    }
    
    protected void matrizAdyacencia(Graphics g) {
        int ancho = 40, alto = 20;
        GrafoNoPonderadoMA<String> grafo_ma = (GrafoNoPonderadoMA<String>)grafo;
        //Ejes
        Integer[][] matriz = grafo_ma.MatrizAdyacencia();//Obtener la matriz real
        g.setColor(Color.BLUE);
        int cant_vert = grafo_ma.NumeroDeVertices();
        for(int i = 1; i <= cant_vert; i++) 
        {    
            g.draw3DRect(ancho * i, 0, ancho - 1, alto - 1, false);
            g.drawString( i + "", ancho * i + (ancho / 2), alto - 3);
            g.draw3DRect(0, alto * i, ancho - 1, alto - 1, false);
            g.drawString( i + "", ancho  / 2, alto * (i + 1) - 3);
        }
        //Matriz
        g.setColor(Color.BLACK);
        for(int i = 0; i < cant_vert; i++)
            for(int j = 0; j < cant_vert; j++)
            {
                g.draw3DRect(ancho * (j + 1), alto * (i + 1), ancho - 1, alto - 1, false);
                g.drawString(matriz[i][j].toString(), ancho * (j + 1) + (ancho / 2), alto * (i + 2) - 3);
            }
    }
    
}
