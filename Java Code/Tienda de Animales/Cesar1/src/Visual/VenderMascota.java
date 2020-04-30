package Visual;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import Clases.Mascota;
import Clases.Tienda;

public class VenderMascota extends JDialog {
	int m;
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JComboBox jComboBox = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private Mascota mascota = null;
	private JButton jButton1 = null;
	private Tienda tienda;	

	/**
	 * @param owner
	 */
	public VenderMascota(Frame owner, Tienda n) {
		super(owner);
		initialize();
		tienda = n;	
		Mascota[] mascotas=tienda.getMisMascotas();
		for (int i = 0; i < mascotas.length; i++) {
			if (mascotas[i] != null) {
				getJComboBox().addItem(String.valueOf(mascotas[i].getCode()));	
			} else if (mascotas[i] == null && i == 0) {
				getJComboBox().addItem("No hay Mascotas disponibles");
				break;
			} else
				break;
		}
		jLabel2.setText(tienda.getMisMascotas()[0].getPaisProced());
		jLabel4.setText(String.valueOf(tienda.getMisMascotas()[0].precios()));
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 282);
		this.addWindowListener(new java.awt.event.WindowAdapter() { 
			public void windowClosing(java.awt.event.WindowEvent e) {    
				clean();
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
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(135, 116, 94, 26));
			jLabel4.setText("");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(12, 115, 97, 25));
			jLabel3.setText("Precio");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(134, 73, 126, 27));
			jLabel2.setText("");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(12, 75, 100, 26));
			jLabel1.setText("PaisProcedente:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(12, 39, 71, 23));
			jLabel.setText("Mascotas:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(getJButton1(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(19, 210, 96, 28));
			jButton.setText("Comprar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (jComboBox.getSelectedItem() != null) {
						try {
							tienda.eliminarMascota(jComboBox.getSelectedIndex());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null,"Mascota vendida","Informacion",JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
					}
					System.out.println("actionPerformed()");					
					setM(getJComboBox().getSelectedIndex());					
					setVisible(false);
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(106, 37, 179, 25));
			jComboBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
if (jComboBox.getSelectedItem() != null) {
						
						jLabel2.setText(tienda.getMisMascotas()[jComboBox.getSelectedIndex()].getPaisProced());
						jLabel4.setText(String.valueOf(tienda.getMisMascotas()[jComboBox.getSelectedIndex()].precios()));
					}
				}
			});
		}
		return jComboBox;
	}
	private void clean(){
		jComboBox.removeAll();
		jLabel2.setText("");
		jLabel4.setText("");
	}
	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(175, 210, 91, 28));
			jButton1.setText("Cancelar");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {					
					setM(-1);
					setVisible(false);
					setModal(false);
				}
			});
		}
		return jButton1;
	}

	public int isM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	
}
