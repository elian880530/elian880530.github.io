package Visual;

import Clases.*;
import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JDialog;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;


import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JMenuItem;

public class InsertarMascota extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JRadioButton jRadioButton = null;
	private JRadioButton jRadioButton1 = null;
	private JRadioButton jRadioButton2 = null;
	private JComboBox jComboBox = null;
	private JLabel jLabel = null;
	private Tienda tienda;
	private Mascota mascota;
	private JTextField jTextField = null;
	private JComboBox jComboBox1 = null;
	private JLabel jLabel1 = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField2 = null;
	private JTextField jTextField3 = null;
	private JTextField jTextField4 = null;
	private JTextField jTextField5 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JTextField jTextField6 = null;
	private JTextField jTextField7 = null;
	private JTextField jTextField8 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel9 = null;
	private JLabel jLabel10 = null;

	/**
	 * @param owner
	 */
	public InsertarMascota(Frame owner, Tienda mas) {
		super(owner);
		initialize();
		tienda = mas;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Insertar Mascota");
		this.setBounds(new Rectangle(0, 0, 500, 336));
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
			jContentPane.setVisible(true);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJRadioButton1(), null);
			jContentPane.add(getJRadioButton2(), null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanel1(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(16, 155, 83, 19));
			jLabel10.setText("Codigo:");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(18, 124, 79, 22));
			jLabel9.setText("ColorPredom:");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(18, 92, 74, 22));
			jLabel8.setText("PaisProce:");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(20, 61, 70, 20));
			jLabel7.setText("CantMeses:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(18, 27, 67, 25));
			jLabel1.setText("Raza");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBorder(BorderFactory.createTitledBorder(null, "Perro",
					TitledBorder.CENTER, TitledBorder.TOP, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel.setBounds(new Rectangle(96, 8, 350, 200));
			jPanel.setVisible(true);
			jPanel.add(getJTextField(), null);
			jPanel.add(getJComboBox1(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getJTextField6(), null);
			jPanel.add(getJTextField7(), null);
			jPanel.add(getJTextField8(), null);
			jPanel.add(jLabel7, null);
			jPanel.add(jLabel8, null);
			jPanel.add(jLabel9, null);
			jPanel.add(jLabel10, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(184, 134, 60, 22));
			jLabel6.setText("ColorOjos:");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(184, 82, 59, 20));
			jLabel5.setText("Codigo:");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(8, 151, 69, 25));
			jLabel4.setText("ColorPredom:");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(7, 111, 68, 24));
			jLabel3.setText("PaisProce:");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(8, 70, 68, 24));
			jLabel2.setText("CantMeses:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(7, 30, 71, 25));
			jLabel.setText("Sexo");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Gato",
					TitledBorder.CENTER, TitledBorder.TOP, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel1.setVisible(false);
			jPanel1.setBounds(new Rectangle(96, 8, 350, 200));
			jPanel1.add(getJRadioButton(), null);
			jPanel1.add(getJComboBox(), null);
			jPanel1.add(jLabel, null);
			jPanel1.add(getJTextField1(), null);
			jPanel1.add(getJTextField2(), null);
			jPanel1.add(getJTextField3(), null);
			jPanel1.add(getJTextField4(), null);
			jPanel1.add(getJTextField5(), null);
			jPanel1.add(jLabel2, null);
			jPanel1.add(jLabel3, null);
			jPanel1.add(jLabel4, null);
			jPanel1.add(jLabel5, null);
			jPanel1.add(jLabel6, null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(92, 256, 104, 30));
			jButton.setText("Insertar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					Mascota Leztat;
					try {
						if(jRadioButton1.isSelected()){
							Leztat=new Perro(Integer.parseInt(jTextField.getText()),jTextField6.getText(),jTextField7.getText(),jTextField8.getText(), String.valueOf(jComboBox1.getSelectedItem()));
							tienda.ingresarMascotas(Leztat);
						}
						else
							if(jRadioButton2.isSelected())
						{
							Leztat=new Gato(Integer.parseInt(jTextField1.getText()),jTextField4.getText(),jTextField5.getText(),jTextField2.getText(),jTextField3.getText(),String.valueOf(jComboBox.getSelectedItem()),jRadioButton.isSelected());
							tienda.ingresarMascotas(Leztat);
						}						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);						
				}
					setVisible(false);
					setModal(false);
				}});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(270, 254, 105, 33));
			jButton1.setText("Cancelar");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					setVisible(false);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jRadioButton
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getJRadioButton() {
		if (jRadioButton == null) {
			jRadioButton = new JRadioButton();
			jRadioButton.setBounds(new Rectangle(225, 30, 75, 27));
			jRadioButton.setText("Pedigri");
		}
		return jRadioButton;
	}

	/**
	 * This method initializes jRadioButton1
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getJRadioButton1() {
		if (jRadioButton1 == null) {
			jRadioButton1 = new JRadioButton();
			jRadioButton1.setBounds(new Rectangle(11, 29, 67, 24));
			jRadioButton1.setText("Perro");
			jRadioButton1.setSelected(true);
			jRadioButton1.setVisible(true);
			jRadioButton1
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()");
							jPanel1.setVisible(false);
							jPanel.setVisible(true);
							jRadioButton2.setSelected(false);
							jRadioButton1.setSelected(true);
						}
					});
		}
		return jRadioButton1;
	}

	/**
	 * This method initializes jRadioButton2
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getJRadioButton2() {
		if (jRadioButton2 == null) {
			jRadioButton2 = new JRadioButton();
			jRadioButton2.setBounds(new Rectangle(11, 62, 59, 20));
			jRadioButton2.setText("Gato");
			jRadioButton2.setVisible(true);
			jRadioButton2
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()");
							jPanel1.setVisible(true);
							jPanel.setVisible(false);
							jRadioButton2.setSelected(true);
							jRadioButton1.setSelected(false);
						}
					});
		}
		return jRadioButton2;
	}

	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(84, 29, 85, 24));
			jComboBox.addItem("M");			
			jComboBox.addItem("F");
			}
		
		return jComboBox;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(109, 63, 93, 22));
		}
		return jTextField;
	}

	/**
	 * This method initializes jComboBox1
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.setBounds(new Rectangle(103, 29, 101, 23));
			jComboBox1.addItem("Dálmata");
			jComboBox1.addItem("Chow Chow");
			jComboBox1.addItem("Bulldog");
		}
		return jComboBox1;
	}

	/**
	 * This method initializes jTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(79, 72, 95, 26));
		}
		return jTextField1;
	}

	/**
	 * This method initializes jTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setBounds(new Rectangle(248, 82, 95, 23));
		}
		return jTextField2;
	}

	/**
	 * This method initializes jTextField3
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setBounds(new Rectangle(248, 133, 96, 24));
		}
		return jTextField3;
	}

	/**
	 * This method initializes jTextField4
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setBounds(new Rectangle(79, 111, 96, 27));
		}
		return jTextField4;
	}

	/**
	 * This method initializes jTextField5
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			jTextField5.setBounds(new Rectangle(80, 151, 96, 27));
		}
		return jTextField5;
	}

	/**
	 * This method initializes jTextField6
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setBounds(new Rectangle(108, 91, 92, 21));
		}
		return jTextField6;
	}

	/**
	 * This method initializes jTextField7
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setBounds(new Rectangle(108, 120, 91, 20));
		}
		return jTextField7;
	}

	/**
	 * This method initializes jTextField8
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField8() {
		if (jTextField8 == null) {
			jTextField8 = new JTextField();
			jTextField8.setBounds(new Rectangle(109, 150, 91, 20));
		}
		return jTextField8;
	}

	public Tienda getTienda() {
		return tienda;
	}	
} 
