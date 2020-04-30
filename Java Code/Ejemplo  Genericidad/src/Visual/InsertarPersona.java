package Visual;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Clases.Persona;
import Clases.UCI;

public class InsertarPersona extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField CI_Persona = null;
	private JButton Boton_InsertarPersona = null;

	UCI MiUCI;
	JTable Tabla;
	private JLabel jLabel1 = null;
	private JTextField Nombre_Persona = null;
	private JButton Boton_CancelarInsertarPersona = null;
	
	/**
	 * This is the default constructor
	 */
	public InsertarPersona(UCI pUCI, JTable pTabla) {
		super();
		initialize();
		MiUCI = pUCI;
		Tabla = pTabla;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Insertar Persona");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(9, 40, 45, 16));
			jLabel1.setText("Nombre");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(5, 10, 19, 16));
			jLabel.setText("CI");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getCI_Persona(), null);
			jContentPane.add(getBoton_InsertarPersona(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getNombre_Persona(), null);
			jContentPane.add(getBoton_CancelarInsertarPersona(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes CI_Persona	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCI_Persona() {
		if (CI_Persona == null) {
			CI_Persona = new JTextField();
			CI_Persona.setBounds(new Rectangle(35, 10, 135, 20));
		}
		return CI_Persona;
	}

	/**
	 * This method initializes Boton_InsertarPersona	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBoton_InsertarPersona() {
		if (Boton_InsertarPersona == null) {
			Boton_InsertarPersona = new JButton();
			Boton_InsertarPersona.setBounds(new Rectangle(9, 98, 102, 19));
			Boton_InsertarPersona.setText("Insertar");
			Boton_InsertarPersona.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						MiUCI.Adicionar(new Persona(CI_Persona.getText(),Nombre_Persona.getText()));
						Persona P  = MiUCI.Obtener(MiUCI.DevolverLista().CantidadReal()-1);
						Tabla.getModel().setValueAt(MiUCI.DevolverLista().CantidadReal(), MiUCI.DevolverLista().CantidadReal()-1, 0);
						Tabla.getModel().setValueAt(P.getCI(), MiUCI.DevolverLista().CantidadReal()-1, 1);
						Tabla.getModel().setValueAt(P.getNombre(), MiUCI.DevolverLista().CantidadReal()-1, 2);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(getContentPane(),e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return Boton_InsertarPersona;
	}

	/**
	 * This method initializes Nombre_Persona	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNombre_Persona() {
		if (Nombre_Persona == null) {
			Nombre_Persona = new JTextField();
			Nombre_Persona.setBounds(new Rectangle(67, 40, 101, 20));
		}
		return Nombre_Persona;
	}

	/**
	 * This method initializes Boton_CancelarInsertarPersona	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBoton_CancelarInsertarPersona() {
		if (Boton_CancelarInsertarPersona == null) {
			Boton_CancelarInsertarPersona = new JButton();
			Boton_CancelarInsertarPersona.setBounds(new Rectangle(130, 96, 93, 20));
			Boton_CancelarInsertarPersona.setText("Cancelar");
			Boton_CancelarInsertarPersona.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return Boton_CancelarInsertarPersona;
	}

}
