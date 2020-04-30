package Visual;

import java.awt.Event;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Clases.UCI;

public class Principal {

	private JFrame jFrame = null;
	private JMenuBar jJMenuBar = null;
	private JMenu Persona = null;
	private JMenuItem Insertar = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	
	UCI Universidad = new UCI();
	private JMenuItem Eliminar = null;
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * 
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
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
			jTable = new JTable();
			jTable.setModel(new DefaultTableModel(1000,3));
			jTable.getColumn("A").setHeaderValue("Nro");
			jTable.getColumn("B").setHeaderValue("CI");
			jTable.getColumn("C").setHeaderValue("Nombre");
		}
		return jTable;
	}

	/**
	 * This method initializes Eliminar	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getEliminar() {
		if (Eliminar == null) {
			Eliminar = new JMenuItem();
			Eliminar.setText("Eliminar");
			Eliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(Universidad.CantidadReal()>0){
						new EliminarPersona(Universidad,jTable).setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(getJFrame(), "No hay Elementos en la lista", "Información", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}
		return Eliminar;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Principal application = new Principal();
				application.getJFrame().setVisible(true);
			}
		});
	}

	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setSize(300, 200);
			jFrame.setContentPane(getJScrollPane());
			jFrame.setTitle("Principal");
		}
		return jFrame;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getPersona());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getPersona() {
		if (Persona == null) {
			Persona = new JMenu();
			Persona.setText("Persona");
			Persona.add(getInsertar());
			Persona.add(getEliminar());
		}
		return Persona;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getInsertar() {
		if (Insertar == null) {
			Insertar = new JMenuItem();
			Insertar.setText("Insertar");
			Insertar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
			Insertar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 new InsertarPersona(Universidad,jTable).setVisible(true); 
				}
			});
		}
		return Insertar;
	}

}
