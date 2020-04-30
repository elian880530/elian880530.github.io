package Visual;

import javax.swing.JPanel;
import Clases.*;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListModel;

import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JList;

public class OrdenarAlfabeticamentePorPaises extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private Mascota[] mascota;
	private Tienda tienda;
	private JScrollPane jScrollPane = null;
	private JList jList = null;

	/**
	 * @param owner
	 */
	public OrdenarAlfabeticamentePorPaises(Frame owner,Tienda tienda) {
		super(owner);
		initialize();
		this.tienda=tienda;
	}
	private void cargarListado(){
		tienda.OrdAlf();
		String[] paises = new String[9];
		JList list=new JList(paises);
		jList.setModel(list.getModel());
		
		}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.addWindowListener(new java.awt.event.WindowAdapter() { 
			public void windowActivated(java.awt.event.WindowEvent e) {    
				cargarListado();
			}
		});
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(23, 23, 222, 111);
			jScrollPane.setViewportView(getJList());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */    
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
		}
		return jList;
	}

}
