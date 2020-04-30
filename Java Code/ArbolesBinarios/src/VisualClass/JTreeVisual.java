/*
 * CTreeVisual.java
 *
 * Created on 20 de octubre de 2006, 15:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package VisualClass;

import TDAArbol.IArbolBinario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author lester
 */
public class JTreeVisual extends javax.swing.JPanel {
    
    protected IArbolBinario<String> arbol;
    protected IArbolBinario<String> actual;
    protected int radio;
    protected int textHeigth;
    protected Color nodeColor;
    protected Color textColor;
    
    private JPanel drawingPane;
    private Dimension area;
    
    /** Creates a new instance of CTreeVisual */
    public JTreeVisual(IArbolBinario<String> arbol) {
        super(new BorderLayout());
        initTree();
        this.arbol = arbol;
        this.actual = arbol;
    }
    
    private void initTree() {
        this.radio = 10;
        this.textHeigth = 8;
        this.nodeColor = Color.YELLOW;
        this.textColor = Color.black;
        area = new Dimension(0,0);

        //Set up the drawing area.
        drawingPane = new DrawingPane();
        drawingPane.setBackground(Color.white);

        //Put the drawing area in a scroll pane.
        JScrollPane scroller = new JScrollPane(drawingPane);
        scroller.setPreferredSize(new Dimension(200,200));

        //Lay out this demo.
        add(scroller, BorderLayout.CENTER);
    }
    
/** The component inside the scroll pane. */
    public class DrawingPane extends JPanel {
        
        protected Graphics g;
        
        private void drawTree(IArbolBinario<String> arb, int x0, int xf, int y) {
            int x = (xf + x0)/2;
            if(arb.esVacio())
                drawNodeNull(x, y);
            else
                drawNode(x, y, arb.getRaiz());
        
            if(!arb.esVacio())
            {
                drawTree(arb.subArbolIzquierdo(), x0, x, y + 3*radio);
                drawLine(x, y, (x + x0)/2, y + 3*radio);
                drawTree(arb.subArbolDerecho(), x, xf, y + 3*radio);
                drawLine(x, y, (x + xf)/2, y + 3*radio);
            }
        }
    
        private void drawNode(int x, int y, String text) {
            g.setColor(nodeColor);
            g.fillOval(x - radio, y - radio, 2*radio, 2*radio);
        
            Font f = new Font("Arial", Font.BOLD, textHeigth);
            g.setFont(f);
            int fontHeight = g.getFontMetrics().getHeight();
            g.setColor(textColor);
            g.drawString(text, x - radio + 1, y);
        }
    
        private void drawNodeNull(int x, int y) {
            g.setColor(nodeColor);
            g.fillRect(x - radio, y - radio, 2*radio, 2*radio);
        }
        
        private void drawLine(int x0, int y0, int xf, int yf) {
            g.setColor(textColor);
            g.drawLine(x0, y0, xf, yf);
        }
        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.g = g;
            if(actual != null)  
                drawTree(actual, 0, 4*radio*getCantidadNodosHojas(actual), 15);
        }
    }
    
    public void repaintComponent() {
        int x = 4*radio*getCantidadNodosHojas(actual);
        int y = 3*radio*(actual.altura() + 1);
      
        Rectangle rect = new Rectangle(0, 0, x, y);
        drawingPane.scrollRectToVisible(rect);

        area.width = x; 
        area.height = y;   

        drawingPane.setPreferredSize(area);
        drawingPane.revalidate();
        drawingPane.repaint();
    }
    
    public int getTextHeigth() {
        return textHeigth;
    }
    
    public void setTextHeigth(int textHeight) {
        this.textHeigth = textHeight;
        repaintComponent();
    }
    
    public int getRadio() {
        return radio;
    }
    
    public void setRadio(int radio) {
        this.radio = radio;
        repaintComponent();
    }
    
    public Color getTextColor() {
        return textColor;
    }
    
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        repaintComponent();
    }
    
    public Color getNodeColor() {
        return nodeColor;
    }
    
    public void setNodeColor(Color nodeColor) {
        this.nodeColor = nodeColor;
        repaintComponent();
    }
    
    
    public void setArbol(IArbolBinario<String> arb) {
        arbol = arb;
        actual = arb;
        repaintComponent();
    }
    
    public IArbolBinario<String> getArbol() {
        return arbol;
    }
    
    public IArbolBinario<String> getArbolActual() {
        return actual;
    }
    
    public void podarIzquierda() {
        if((actual != null)&&(!actual.esHoja()))
        {
            actual.podarIzquierdo();
            repaintComponent();
        }
        else
            throw new ExcepcionFueraDeRango();
    }
    
    public void podarDerecha() {
        if((actual != null)&&(!actual.esHoja()))
        {
            actual.podarDerecho();
            repaintComponent();
        }
        else
            throw new ExcepcionFueraDeRango();
    }
    
    public void moveIzquierda() {
        if((actual != null)&&(!actual.esHoja()))
        {
            actual = actual.subArbolIzquierdo();
            repaintComponent();
        }
        else
            throw new ExcepcionFueraDeRango();
    }
     
    public void moveDerecha() {
        if((actual != null)&&(!actual.esHoja()))
        {
            actual = actual.subArbolDerecho();
            repaintComponent();
        }
        else
            throw new ExcepcionFueraDeRango();
    }
    
    public void moveRaiz() {
        actual = arbol;
        repaintComponent();
    }
    
    public void movePadre() {
        if(arbol != null)
        {
            IArbolBinario<String> temp = getPadre(arbol);
            if(temp == null)
                throw new ExcepcionFueraDeRango();
            actual = temp;
            repaintComponent();
        }
        else
            throw new ExcepcionFueraDeRango();
    }
     
    private int getCantidadNodosHojas(IArbolBinario<String> arb) {
        if(arb != null) 
        {
            int c = 1;
            for(int i = 0; i < arb.altura(); i++)
                c *= 2;
            return c;
        }
        return 0;
    }
    
     private IArbolBinario<String> getPadre(IArbolBinario<String> aux) {
       if(aux.subArbolDerecho() == actual)
            return aux;
       if(aux.subArbolIzquierdo() == actual)
            return aux;            
        if(!aux.esHoja())
        {
            IArbolBinario<String> temp = getPadre(aux.subArbolDerecho());
            if(temp == actual)
                return temp;
            
            temp = getPadre(aux.subArbolIzquierdo());
            if(temp == actual)
                return temp;
        }
        
        return null; 
    }
}


