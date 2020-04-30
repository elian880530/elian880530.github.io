package src.visual;
import src.clases.*;
import src.eda.*;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.Rectangle;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;



public class AddMcpio extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JTextField jTextField = null;
	private JLabel jLabel1 = null;
	private JComboBox jComboBox = null;
	/**
	 * @param owner
	 */
	CentralTelefonica central;
	private JLabel jLabel11 = null;
	private JComboBox jComboBox1 = null;
	public AddMcpio( CentralTelefonica cen){
		super();
		initialize();
		central = cen;
		ListaSE<String> lista = new ListaSE<String>();
		try{
		lista = central.GetProv();
		jComboBox.addItem("Elegir");
		for (int i = 0; i < lista.Longitud(); i++){
			jComboBox.addItem(lista.Obtener(i));
		}
		jComboBox1.addItem("Elegir");
		}catch(Exception ee)
		{
			
		}
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setTitle("Adicionar Central Municipal");
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
			jContentPane.add(getJPanel(), null);
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
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(30, 104, 87, 18));
			jLabel11.setText("Central Mcpio:");
			jLabel11.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(32, 73, 80, 16));
			jLabel1.setText("Central Prov:");
			jLabel1.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(32, 44, 61, 16));
			jLabel.setText("Nombre:");
			jLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(2, 1, 288, 172));
			jPanel.add(jLabel, null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);
			jPanel.add(getJTextField(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getJComboBox(), null);
			jPanel.add(jLabel11, null);
			jPanel.add(getJComboBox1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(108, 133, 84, 25));
			jButton.setText("Aceptar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (jTextField.getText() == "")
						JOptionPane.showMessageDialog(null, "Llene los campos","Error",JOptionPane.ERROR_MESSAGE);
					else {
						CentralMunicipal cm = new CentralMunicipal(jTextField.getText());
						CentralProvincial cp = new CentralProvincial(jComboBox.getItemAt(jComboBox.getSelectedIndex()).toString());
						try{
							if (jComboBox1.getItemAt(jComboBox1.getSelectedIndex()).toString() == "Elegir")
								central.AdicionarMcpio(cm, cp, "");
							else
								central.AdicionarMcpio(cm, cp, jComboBox1.getItemAt(jComboBox1.getSelectedIndex()).toString());
						}
						catch(Exception ee)
						{
							JOptionPane.showMessageDialog(null, ee.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						}
					}dispose();
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
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
			jButton1.setBounds(new Rectangle(196, 132, 89, 26));
			jButton1.setText("Cancelar");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(126, 42, 115, 20));
		}
		return jTextField;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(126, 72, 124, 21));
			jComboBox.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
			jComboBox.addItemListener(new java.awt.event.ItemListener() { 
				public void itemStateChanged(java.awt.event.ItemEvent e) {    
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
			jComboBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ListaSE<String> lista = new ListaSE<String>();
					try{
					//lista = central.GetMcpios(jComboBox.getItemAt(jComboBox.getSelectedIndex()).toString());
						lista = central.GetMcpios(jComboBox.getSelectedItem().toString());
					/*if (jComboBox1.getItemAt(jComboBox1.getSelectedIndex()).toString() != "Elegir")
						jComboBox1.addItem("Elegir");*/
					
					for (int i = 0; i < lista.Longitud(); i++){
						jComboBox1.addItem(lista.Obtener(i));
					}
					}catch(Exception ee){}
					
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jComboBox;
	}

	/**
	 * This method initializes jComboBox1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.setBounds(new Rectangle(127, 103, 123, 19));
		}
		return jComboBox1;
	}

}
