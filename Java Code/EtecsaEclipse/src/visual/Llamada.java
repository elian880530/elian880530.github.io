package src.visual;
import src.clases.*;
import src.eda.*;


import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.JComboBox;


public class Llamada extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel21 = null;
	private JLabel jLabel22 = null;
	private JTextField jTextField3 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JLabel jLabel221 = null;
 CentralTelefonica a;
	private JLabel jLabel3 = null;
	private JComboBox cp1 = null;
	private JComboBox cp2 = null;
	private JComboBox cm1 = null;
	private JComboBox cm2 = null;
	/**
	 * @param owner
	 */
	public Llamada(CentralTelefonica a) {
		super();
		initialize();
		this.a=a;
		try {
			for(int i=0;i<a.GetProv().Longitud();i++){
			cp1.addItem(a.GetProv().Obtener(i));
			cp2.addItem(a.GetProv().Obtener(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			/*try {
				int  b=cp1.getSelectedIndex();
				String c= cp1.getSelectedItem().toString();
				String auxil=a.GetProv().Obtener(0);
				ListaSE<String>aux=a.GetMcpios(c);
				for(int i=0;i<aux.Longitud();i++){
				cm1.addItem(aux.Obtener(i));
				cm2.addItem(aux.Obtener(i));
				}
			} catch (Exception el) {
				// TODO Auto-generated catch block
				el.printStackTrace();
			}*/
		}
		/*try {
			int  b=cp1.getSelectedIndex();
			
			ListaSE<String>aux=a.GetMcpios3(a.GetProv().Obtener(b));
			for(int i=0;i<aux.Longitud();i++){
			cm1.addItem(aux.Obtener(i));
			cm2.addItem(aux.Obtener(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(424, 264);
		this.setTitle("Llamadas");
		this.setContentPane(getJContentPane());////aqui
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(175, 154, 38, 16);
			jLabel3.setText("");
			jLabel221 = new JLabel();
			jLabel221.setBounds(new Rectangle(16, 155, 148, 16));
			jLabel221.setText("El costo de la llamada es:");
			jLabel22 = new JLabel();
			jLabel22.setBounds(new Rectangle(16, 125, 204, 16));
			jLabel22.setText("Tiempo de Duracion de la Llamada:");
			jLabel21 = new JLabel();
			jLabel21.setBounds(new java.awt.Rectangle(201,81,80,22));
			jLabel21.setText("C.Municipal2:");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new java.awt.Rectangle(6,80,79,21));
			jLabel2.setText("C.Municipal 1:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new java.awt.Rectangle(201,33,83,20));
			jLabel1.setText("C.Provincial2:");
			jLabel = new JLabel();
			jLabel.setBounds(new java.awt.Rectangle(6,30,80,22));
			jLabel.setText("C.Provincial 1:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel21, null);
			jContentPane.add(jLabel22, null);
			jContentPane.add(getJTextField3(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(jLabel221, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getCp1(), null);
			jContentPane.add(getCp2(), null);
			jContentPane.add(getCm1(), null);
			jContentPane.add(getCm2(), null);
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
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setBounds(new Rectangle(0, 0, 292, 0));
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setBounds(new Rectangle(230, 124, 50, 20));
		}
		return jTextField3;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(211, 191, 81, 28));
			jButton.setText("Llamar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					try {
						double p=a.Precio(cp1.getSelectedItem().toString(),cp2.getSelectedItem().toString(),cm1.getSelectedItem().toString(),cm2.getSelectedItem().toString(),Double.parseDouble(jTextField3.getText()));
						
						jLabel3.setText(String.valueOf(p));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
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
			jButton1.setBounds(new Rectangle(300, 192, 90, 26));
			jButton1.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					try {
						int  b=cp1.getSelectedIndex();
						String c= cp1.getSelectedItem().toString();
						String d= cp2.getSelectedItem().toString();
						String auxil=a.GetProv().Obtener(0);
						ListaSE<String>aux=a.GetMcpios(c);
						ListaSE<String>aux1=a.GetMcpios(d);
						for(int i=0;i<aux.Longitud();i++){
						cm1.addItem(aux.Obtener(i));
						
						}
						for(int i=0;i<aux1.Longitud();i++){
							
							cm2.addItem(aux1.Obtener(i));
							}
					} catch (Exception el) {
						// TODO Auto-generated catch block
						el.printStackTrace();
					}
					System.out.println("actionPerformed()"); 
					//dispose();
				}
			});
			jButton1.setText("Actualizar");
		}
		return jButton1;
	}

	/**
	 * This method initializes cp1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */    
	private JComboBox getCp1() {
		
			if (cp1 == null) {
				cp1 = new JComboBox();
				cp1.setBounds(new java.awt.Rectangle(87,31,103,21));
				//ListaSE<String> aux=a.GetProv();
				
				
				cp1.addActionListener(new java.awt.event.ActionListener() { 
					
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					/*for(int i=0;i<a.GetProv().Longitud();i++){//aquiiii
						try {
							cp1.addItem(a.GetProv().Obtener(i));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}*/
				}
			});
			cp1.addItemListener(new java.awt.event.ItemListener() { 
				public void itemStateChanged(java.awt.event.ItemEvent e) {    
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
			cp1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ListaSE<String> lista = new ListaSE<String>();
					//try{
					/*for(int i=0;i<a.Provincias.Longitud();i++){
						cp1.addItem(a.Provincias.Obtener(i).getRaiz().nombre);
					}*/
						
						/*for(int i=0;i<a.GetProv().Longitud();i++){
							cp1.addItem(a.GetProv().Obtener(i));
						}*///esto es
					/*	
					cm1.addItem("Elegir");
					for (int i = 1; i < lista.Longitud(); i++){
						cm1.addItem(lista.Obtener(i));
					}*/
					//}
					//catch(Exception ee){}*/
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		
		return cp1;
		 
	}

	/**
	 * This method initializes cp2	
	 * 	
	 * @return javax.swing.JComboBox	
	 */    
	private JComboBox getCp2() {
		if (cp2 == null) {
			cp2 = new JComboBox();
			cp2.setBounds(new java.awt.Rectangle(286,32,124,21));
			cp2.addActionListener(new java.awt.event.ActionListener() { 
			public void actionPerformed(java.awt.event.ActionEvent e) {    
				System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
			}
		});
		cp2.addItemListener(new java.awt.event.ItemListener() { 
			public void itemStateChanged(java.awt.event.ItemEvent e) {    
				System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
			}
		});
		cp2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				ListaSE<String> lista = new ListaSE<String>();
				try{
				lista = a.GetMcpios(cp2.getItemAt(cp2.getSelectedIndex()).toString());
				if (cm2.getItemAt(cm2.getSelectedIndex()).toString() != "Elegir")
					cm2.addItem("Elegir");
				for (int i = 1; i < lista.Longitud(); i++){
					cm2.addItem(lista.Obtener(i));
				}
				}catch(Exception ee){}
				
				System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
			}
		});
	}
	return cp2;	
	}

	/**
	 * This method initializes cm1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */    
	private JComboBox getCm1() {
		if (cm1 == null) {
			cm1 = new JComboBox();
			cm1.setBounds(86, 84, 101, 19);
		}
		return cm1;
	}

	/**
	 * This method initializes cm2	
	 * 	
	 * @return javax.swing.JComboBox	
	 */    
	private JComboBox getCm2() {
		if (cm2 == null) {
			cm2 = new JComboBox();
			cm2.setBounds(281, 83, 115, 18);
		}
		return cm2;
	}

}
