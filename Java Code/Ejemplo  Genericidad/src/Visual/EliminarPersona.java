package Visual;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import Clases.*;

public class EliminarPersona extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField Nro_EliminarPersona = null;
	private JButton Boton_EliminarPersona = null;
	UCI MiUCI;
	JTable Tabla;

	/**
	 * This is the default constructor
	 */
	public EliminarPersona(UCI pUCI, JTable pTabla) {
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
		this.setTitle("Eliminar Persona");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(9, 11, 38, 16));
			jLabel.setText("Nro");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getNro_EliminarPersona(), null);
			jContentPane.add(getBoton_EliminarPersona(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes Nro_EliminarPersona	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNro_EliminarPersona() {
		if (Nro_EliminarPersona == null) {
			Nro_EliminarPersona = new JTextField();
			Nro_EliminarPersona.setBounds(new Rectangle(54, 8, 119, 20));
		}
		return Nro_EliminarPersona;
	}

	/**
	 * This method initializes Boton_EliminarPersona	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBoton_EliminarPersona() {
		if (Boton_EliminarPersona == null) {
			Boton_EliminarPersona = new JButton();
			Boton_EliminarPersona.setBounds(new Rectangle(64, 37, 110, 19));
			Boton_EliminarPersona.setText("Eliminar");
			Boton_EliminarPersona.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if(Integer.parseInt(Nro_EliminarPersona.getText()) >0 && Integer.parseInt(Nro_EliminarPersona.getText()) <= MiUCI.CantidadReal()){
							//Borro la Persona de la Lista
							MiUCI.Eliminar(Integer.parseInt(Nro_EliminarPersona.getText())-1);
							//Borro la Persona de la Tabla
							((DefaultTableModel)Tabla.getModel()).removeRow(Integer.parseInt(Nro_EliminarPersona.getText())-1);
							//Actualizo los valores de la Columna Nro en la tabla
							for (int i = Integer.parseInt(Nro_EliminarPersona.getText())-1; i <= MiUCI.CantidadReal(); i++) {
								Tabla.getModel().setValueAt(i, i-1, 0);
							}
						}
						else {
							throw new Exception("Número Incorrecto");
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return Boton_EliminarPersona;
	}

}
