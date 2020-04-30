package Visual;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JButton;

import Clases.*;
import java.awt.Dimension;
import javax.swing.JTextArea;
public class CantidadDePaises extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField jTextField = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private Mascota []mas;
	private JButton jButton2 = null;
	private Tienda tienda;
	private JTextArea jTextArea = null;

	/**
	 * @param owner
	 */
	public CantidadDePaises(Frame owner,Mascota []mas,Tienda tien) {
		super(owner);		
		initialize();
		this.mas=mas;
		tienda=tien;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(419, 328);
		this.setTitle("Cantidad de un País");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(124, 68, 70, 28));
			jLabel2.setText("");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(32, 68, 74, 26));
			jLabel1.setText("Cantidad:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(30, 25, 42, 26));
			jLabel.setText("País:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			jContentPane.add(getJTextArea(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(95, 25, 125, 27));
			jTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					char a = e.getKeyChar();
					if(a<97||a>121){
						e.consume();
					}
				}
			});
		}
		return jTextField;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(239, 69, 91, 27));
			jButton.setText("Buscar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int cont=0;
					for(int i=0;mas[i]!=null;i++){						
						if(jTextField.getText().equals(mas[i].getPaisProced())){
							cont++;
						}						
					}
					jLabel2.setText(String.valueOf(cont));
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
			jButton1.setBounds(new Rectangle(137, 256, 91, 27));
			jButton1.setText("OK");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					setVisible(false);
					setModal(false);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(63, 111, 263, 16));
			jButton2.setText("Color Menos Predominante");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String a ="";
					String [] c = tienda.colorMenosPredominante();
					for(int i=0;i<c.length;i++){						
						if(i!=0&&i%5==0)
							a+="\n";
						a+=c[i]+",";					
					}
					jTextArea.setText(a);
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setBounds(new Rectangle(85, 133, 222, 110));
		}
		return jTextArea;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
