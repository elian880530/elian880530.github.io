package Visual;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JTable;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import Clases.*;
import javax.swing.table.DefaultTableModel;
public class Mayores extends JDialog {
    int m;
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private Mascota[]mascota;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private TableModel tableModel = null;  //  @jve:decl-index=0:visual-constraint=""

	/**
	 * @param owner
	 */
	public Mayores(Frame owner,Mascota[]mas) {
		super(owner);
		initialize();
		Mascota nueva;
		TableModel a= jTable.getModel();
		for(int i = 0; i < mas.length;i++){
			nueva = mas[i];			
			a.setValueAt(nueva.getPaisProced(),i,0);
			a.setValueAt(String.valueOf(nueva.getCantMeses()),i,1);
			a.setValueAt(nueva.getColorPredom(),i,2);
			a.setValueAt(nueva.getCode(),i,3);
			setVisible(true);
			setModal(true);
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setTitle("Mayores");
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
			jScrollPane.setBounds(new Rectangle(32, 26, 215, 91));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable(15, 4);
			jTable.getColumn("A").setHeaderValue("País");
			jTable.getColumn("B").setHeaderValue("Edad");
			jTable.getColumn("C").setHeaderValue("Color");
			jTable.getColumn("D").setHeaderValue("Codigo");
			
		}
		return jTable;
	}

	public int isM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public Mascota[] getMascota() {
		return mascota;
	}

	public void setMascota(Mascota[] mascota) {
		this.mascota = mascota;
	}

	/**
	 * This method initializes defaultTableModel	
	 * 	
	 * @return javax.swing.table.DefaultTableModel	
	 */    
	

}
