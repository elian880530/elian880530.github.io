package grafo.gui;

import grafo.Arco;
import grafo.Grafo;
import grafo.GrafoNoPonderadoLista;
import grafo.GrafoNoPonderadoMatriz;
import grafo.GrafoPonderado;
import grafo.GrafoPonderadoLista;
import grafo.GrafoPonderadoMatriz;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.lang.reflect.*;
import javax.naming.ldap.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;

public class Frame extends javax.swing.JFrame {
  
  protected Grafo grafo;
  protected AbstractTableModel model;//es necesario que el modelo no pierda la referencia al grafo
    
  /** Creates new form Frame */
  public Frame() {
    initComponents();
    myInit();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        radioGroup = new javax.swing.ButtonGroup();
        scrollGrafo = new javax.swing.JScrollPane();
        pnlGrafo = new javax.swing.JPanel();
        scrollGrafoVertices = new javax.swing.JScrollPane();
        pnlAux = new javax.swing.JPanel();
        pnlGrafoVertices = new javax.swing.JPanel();
        pnlSpace = new javax.swing.JPanel();
        pnlTiposGrafos = new javax.swing.JPanel();
        radio1 = new javax.swing.JRadioButton();
        radio2 = new javax.swing.JRadioButton();
        radio3 = new javax.swing.JRadioButton();
        radio4 = new javax.swing.JRadioButton();
        pnlInsercion = new javax.swing.JPanel();
        btInsertar = new javax.swing.JButton();
        tfInsertar = new javax.swing.JTextField();
        btInsertarRandom = new javax.swing.JButton();
        lbInsertarRandom = new javax.swing.JLabel();
        lbSpace = new javax.swing.JLabel();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        scrollGrafo.setBorder(null);
        scrollGrafo.setMinimumSize(new java.awt.Dimension(0, 0));
        pnlGrafo.setMinimumSize(new java.awt.Dimension(0, 0));
        scrollGrafo.setViewportView(pnlGrafo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(scrollGrafo, gridBagConstraints);

        scrollGrafoVertices.setBorder(null);
        scrollGrafoVertices.setMinimumSize(new java.awt.Dimension(0, 0));
        scrollGrafoVertices.setPreferredSize(new java.awt.Dimension(0, 20));
        pnlAux.setLayout(new java.awt.GridBagLayout());

        pnlGrafoVertices.setLayout(new javax.swing.BoxLayout(pnlGrafoVertices, javax.swing.BoxLayout.X_AXIS));

        pnlGrafoVertices.setAlignmentX(0.0F);
        pnlGrafoVertices.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlAux.add(pnlGrafoVertices, gridBagConstraints);

        pnlSpace.setMinimumSize(new java.awt.Dimension(0, 0));
        pnlSpace.setPreferredSize(new java.awt.Dimension(0, 0));
        org.jdesktop.layout.GroupLayout pnlSpaceLayout = new org.jdesktop.layout.GroupLayout(pnlSpace);
        pnlSpace.setLayout(pnlSpaceLayout);
        pnlSpaceLayout.setHorizontalGroup(
            pnlSpaceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        pnlSpaceLayout.setVerticalGroup(
            pnlSpaceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.01;
        pnlAux.add(pnlSpace, gridBagConstraints);

        scrollGrafoVertices.setViewportView(pnlAux);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 60;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(scrollGrafoVertices, gridBagConstraints);

        pnlTiposGrafos.setLayout(new java.awt.GridBagLayout());

        pnlTiposGrafos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlTiposGrafos.setMinimumSize(new java.awt.Dimension(300, 100));
        radio1.setText("Grafo Ponderado Matriz de Adyacencia");
        radio1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        radio1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        radio1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radio1StateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 8, 4, 8);
        pnlTiposGrafos.add(radio1, gridBagConstraints);

        radio2.setText("Grafo No Ponderado Matriz de Adyacencia");
        radio2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        radio2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        radio2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radio2StateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 8, 4, 8);
        pnlTiposGrafos.add(radio2, gridBagConstraints);

        radio3.setText("Grafo Ponderado Lista de Adyacencia");
        radio3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        radio3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        radio3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio3ActionPerformed(evt);
            }
        });
        radio3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radio3StateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 8, 4, 8);
        pnlTiposGrafos.add(radio3, gridBagConstraints);

        radio4.setText("Grafo No Ponderado Lista de Adyacencia");
        radio4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        radio4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        radio4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radio4StateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 8, 4, 8);
        pnlTiposGrafos.add(radio4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(pnlTiposGrafos, gridBagConstraints);

        pnlInsercion.setLayout(new java.awt.GridBagLayout());

        pnlInsercion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btInsertar.setMnemonic('V');
        btInsertar.setText("Insertar Vertice");
        btInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInsertarActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlInsercion.add(btInsertar, gridBagConstraints);

        tfInsertar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlInsercion.add(tfInsertar, gridBagConstraints);

        btInsertarRandom.setMnemonic('R');
        btInsertarRandom.setText("Insertar Vertice Random");
        btInsertarRandom.setDisplayedMnemonicIndex(17);
        btInsertarRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInsertarRandomActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlInsercion.add(btInsertarRandom, gridBagConstraints);

        lbInsertarRandom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlInsercion.add(lbInsertarRandom, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlInsercion.add(lbSpace, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(pnlInsercion, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radio3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio3ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_radio3ActionPerformed
  
  private String getVerticeRandom(){
      String result = "";
      do{
          result += ""+(char)('a' + (char)(Math.random() * 26));
      }while(grafo.estaElVertice(result));
      return result;
  }
  
  /*Esto para insertar un vertice sea cual sea*/
  private void insertarVertice(VerticeVisual v){
	    v.addMouseListener(listener);
	    pnlGrafoVertices.add(v);
	    v.revalidate();//no si esto traiga problemas despues
	    grafo.insertaVertice(v.getText());
	    JTable t = new JTable(model);
	    pnlGrafo.removeAll();
	    pnlGrafo.add(t);
	    t.revalidate();
	    scrollGrafo.revalidate();
	    fixit();
	}	
  
  private void btInsertarRandomActionPerformed(java.awt.event.ActionEvent e) {
    VerticeVisual v = new VerticeVisual(grafo.cantVertices()+1, getVerticeRandom());
    insertarVertice(v);
    lbInsertarRandom.setText(v.getText());
  }
  
  private void btInsertarActionPerformed(java.awt.event.ActionEvent e) {
      String text = tfInsertar.getText();
    if (text == ""){
          JOptionPane.showMessageDialog(this, "El valor del vertice no puede ser vacio", "Error", JOptionPane.ERROR_MESSAGE);
          return;
    }
    if (grafo.estaElVertice(text)){
          JOptionPane.showMessageDialog(this, "Este valor ya existe en el grafo", "Error", JOptionPane.ERROR_MESSAGE);
          return;
    }
    VerticeVisual v = new VerticeVisual(grafo.cantVertices()+1, tfInsertar.getText());
    insertarVertice(v);
  }
  
  private void radio4StateChanged(javax.swing.event.ChangeEvent e) {
				grafo = new GrafoNoPonderadoLista<String>();
				model = new GrafoNoPonderadoListaModel((GrafoNoPonderadoLista<String>)grafo);			
        pnlGrafo.removeAll();
        pnlGrafoVertices.removeAll();
				JTable t = new JTable(model);
				pnlGrafo.add(t);
  }
  
  private void radio3StateChanged(javax.swing.event.ChangeEvent e) {
				grafo = new GrafoPonderadoLista<String>();
				model = new GrafoPonderadoListaModel((GrafoPonderadoLista<String>)grafo);			
        pnlGrafo.removeAll();
        pnlGrafoVertices.removeAll();
				JTable t = new JTable(model);
				pnlGrafo.add(t);
  }
  
  private void radio2StateChanged(javax.swing.event.ChangeEvent e) {
				grafo = new GrafoNoPonderadoMatriz<String>();
				model = new GrafoNoPonderadoMatrizModel((GrafoNoPonderadoMatriz<String>)grafo);			
        pnlGrafo.removeAll();
        pnlGrafoVertices.removeAll();
				JTable t = new JTable(model);
				pnlGrafo.add(t);
  }
  
  private void radio1StateChanged(javax.swing.event.ChangeEvent e) {
				grafo = new GrafoPonderadoMatriz<String>();
				model = new GrafoPonderadoMatrizModel((GrafoPonderadoMatriz<String>)grafo);			
        pnlGrafo.removeAll();
        pnlGrafoVertices.removeAll();
				JTable t = new JTable(model);
				pnlGrafo.add(t);
  }
  
  // <editor-fold desc=" My Init Code ">
  private void myInit() {
    radioGroup.add(radio1);
    radioGroup.add(radio2);
    radioGroup.add(radio3);
    radioGroup.add(radio4);
    radio1.setSelected(true);
    scrollGrafoVertices.getViewport().addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        fixit();
      }
    });
 }
  
  private void fixit() {
    pnlGrafoVertices.setPreferredSize(null);
    pnlSpace.setPreferredSize(null);
    pnlAux.setPreferredSize(null);
    Dimension dim = pnlGrafoVertices.getPreferredSize();
    System.out.println("before: "+dim);
    if (dim.width == 0) return;
    dim.height = scrollGrafoVertices.getViewport().getSize().height;
    pnlGrafoVertices.setPreferredSize(dim);
    System.out.println(" after: "+dim);
    JTable t = new JTable(model);
    pnlGrafo.removeAll();
    pnlGrafo.add(t);
pnlGrafo.revalidate();
pnlGrafo.repaint();
    pnlAux.revalidate();
    pnlAux.repaint();
  }
  
  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Frame().setVisible(true);
      }
    });
  }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btInsertar;
    private javax.swing.JButton btInsertarRandom;
    private javax.swing.JLabel lbInsertarRandom;
    private javax.swing.JLabel lbSpace;
    private javax.swing.JPanel pnlAux;
    private javax.swing.JPanel pnlGrafo;
    private javax.swing.JPanel pnlGrafoVertices;
    private javax.swing.JPanel pnlInsercion;
    private javax.swing.JPanel pnlSpace;
    private javax.swing.JPanel pnlTiposGrafos;
    private javax.swing.JRadioButton radio1;
    private javax.swing.JRadioButton radio2;
    private javax.swing.JRadioButton radio3;
    private javax.swing.JRadioButton radio4;
    private javax.swing.ButtonGroup radioGroup;
    private javax.swing.JScrollPane scrollGrafo;
    private javax.swing.JScrollPane scrollGrafoVertices;
    private javax.swing.JTextField tfInsertar;
    // End of variables declaration//GEN-END:variables
  
  private VerticeVisual vertSeleccionado;
  
  private MouseListener listener = new MouseAdapter() {
    public void mousePressed(MouseEvent e) {
      if (e.getButton() == e.BUTTON3) 
      {
        if (vertSeleccionado != null) 
        {
          vertSeleccionado.setSelected(false);
          vertSeleccionado = null;
        }
        return;
      }
      if (e.getButton() != e.BUTTON1) return;
      VerticeVisual v = (VerticeVisual)e.getSource();
      if (vertSeleccionado == null)
      {
        vertSeleccionado = v;
        vertSeleccionado.setSelected(true);
      }
      else 
      {
        v.setSelected(true);
        //ahora se va a insertar el arco pero con precaucion
        Arco<String> arco = new Arco<String>(vertSeleccionado.getText(), v.getText());
        if (grafo.estaElArco(arco))
        {
          JOptionPane.showMessageDialog(null, "El arco ya existe", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
        //en primer lugar hay que ver si es ponderado para pedir el peso
        if (esPonderado())
        {
            int peso = getPeso();
            ((GrafoPonderado)grafo).insertaArco(arco, peso);//aqui se inserta
        }
        else 
        {
            grafo.insertaArco(arco);//aqui se inserta
        }
        JTable t = new JTable(model);
        //t.doLayout();
        pnlGrafo.removeAll();
        pnlGrafo.add(t);
        t.revalidate();
        scrollGrafo.revalidate();
        fixit();
        final VerticeVisual v1 = vertSeleccionado;
        final VerticeVisual v2 = v;
        vertSeleccionado = null;
        SwingUtilities.invokeLater(new Runnable()
        {
             public void run()
             {
                try { Thread.sleep(100); } catch (InterruptedException ex) { }
                v1.setSelected(false);
                v2.setSelected(false);
            }
        }
      );
      }
    }
  };
  
  private int getPeso(){
      DialogPeso dialog = new DialogPeso(this, true);
      dialog.setVisible(true);
      return dialog.getPeso();
  }
  
  private boolean esPonderado(){
  	return (grafo instanceof GrafoPonderado);
  }
  
}