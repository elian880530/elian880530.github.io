package Visual;

import Clases.*;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JTabbedPane;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private Tienda tienda = null;
	private JMenuItem jMenuItem1 = null;
	private DefaultTableModel defaultTableModel = null; // @jve:decl-index=0:visual-constraint=""
	private JTabbedPane jTabbedPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	private JScrollPane jScrollPane2 = null;
	private JTable jTable2 = null;
	private DefaultTableModel defaultTableModel1 = null; // @jve:decl-index=0:visual-constraint=""
	private JTabbedPane jTabbedPane1 = null;
	private JScrollPane jScrollPane3 = null;
	private JTable jTable3 = null;
	private JScrollPane jScrollPane4 = null;
	private JTable jTable4 = null;
	private JMenuItem jMenuItem2 = null;
	private JMenuItem jMenuItem3 = null;
	private JButton jButton = null;
	private JLabel jLabel = null;
	private MostrarMayores mostrarMayores;

	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
		}
		return jJMenuBar;
	}

	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("Archivo");
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItem1());
			jMenu.add(getJMenuItem2());
			jMenu.add(getJMenuItem3());
		}
		return jMenu;
	}

	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Insertar Mascota");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					InsertarMascota yo = new InsertarMascota(new Principal(),
							tienda);
					yo.setModal(true);
					yo.setVisible(true);
					tienda = yo.getTienda();
					System.out.println(tienda.getCantReal());
					actualizar();
				}
			});
		}
		return jMenuItem;
	}

	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Venta de Mascota");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					VenderMascota tu = new VenderMascota(new Principal(),tienda);							
					tu.setVisible(true);
					/*int a = tu.isM();
					if (a != -1) {
						try {
							TableModel modelo;
							if (tienda.obtenerMascota(a) instanceof Perro){
								modelo = jTable3.getModel();
								for (int i = a; i < modelo.getRowCount()-1; i++){
									modelo.setValueAt(modelo.getValueAt(i+1, 0), i, 0);
									modelo.setValueAt(modelo.getValueAt(i+1, 1), i, 1);
									modelo.setValueAt(modelo.getValueAt(i+1, 2), i, 2);
									modelo.setValueAt(modelo.getValueAt(i+1, 3), i, 3);
									modelo.setValueAt(modelo.getValueAt(i+1, 4), i, 4);
								}
								jTable3.setModel(modelo);
							}else{
								modelo = jTable4.getModel();
								for (int i = a; i < modelo.getRowCount()-1; i++){
									modelo.setValueAt(modelo.getValueAt(i+1, 0), i, 0);
									modelo.setValueAt(modelo.getValueAt(i+1, 1), i, 1);
									modelo.setValueAt(modelo.getValueAt(i+1, 2), i, 2);
									modelo.setValueAt(modelo.getValueAt(i+1, 3), i, 3);
									modelo.setValueAt(modelo.getValueAt(i+1, 4), i, 4);
									modelo.setValueAt(modelo.getValueAt(i+1, 5), i, 5);
									modelo.setValueAt(modelo.getValueAt(i+1, 6), i, 6);
								}
								jTable4.setModel(modelo);
							}
							tienda.eliminarMascota(tienda.obtenerMascota(a));							
						} catch (Exception e1) {
			                JOptionPane.showMessageDialog(jMenuItem1, e1.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
						}
					}*/
				}
			});
		}
		return jMenuItem1;
	}

	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setBounds(13, 10, 380, 129);
			jTabbedPane1.addTab("Perro", null, getJScrollPane3(), null);
			jTabbedPane1.addTab("Gato", null, getJScrollPane4(), null);
		}
		return jTabbedPane1;
	}

	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setViewportView(getJTable3());
		}
		return jScrollPane3;
	}

	private JTable getJTable3() {
		if (jTable3 == null) {
			jTable3 = new JTable(30, 5);
			jTable3.getColumn("A").setHeaderValue("País");
			jTable3.getColumn("B").setHeaderValue("Edad");
			jTable3.getColumn("C").setHeaderValue("Raza");
			jTable3.getColumn("D").setHeaderValue("Color");
			jTable3.getColumn("E").setHeaderValue("Codigo");
		}
		return jTable3;
	}

	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setViewportView(getJTable4());
		}
		return jScrollPane4;
	}

	private JTable getJTable4() {
		if (jTable4 == null) {
			jTable4 = new JTable(30, 7);
			jTable4.getColumn("A").setHeaderValue("País");
			jTable4.getColumn("B").setHeaderValue("Edad");
			jTable4.getColumn("C").setHeaderValue("Sexo");
			jTable4.getColumn("D").setHeaderValue("Color");
			jTable4.getColumn("E").setHeaderValue("Codigo");
			jTable4.getColumn("F").setHeaderValue("Color de Ojos");
			jTable4.getColumn("G").setHeaderValue("Pedigrí");
		}
		return jTable4;
	}

	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("Cantidad de Mascotas de un País");
			jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					CantidadDePaises Leiser = new CantidadDePaises(
							new Principal(), tienda.getMisMascotas(),tienda);
					Leiser.setVisible(true);
					Leiser.setModal(true);
				}
			});
		}
		return jMenuItem2;
	}

	/**
	 * This method initializes jMenuItem3
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("Mayores");
			jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					Mayores Mc=new Mayores(new Principal(),tienda.Mayores());					
					Mc.setVisible(true);
					Mc.setModal(true);
					int a = Mc.isM();
					if (a!=-1){
						try{
							tienda.Mayores();
						}catch(Exception e1){
							JOptionPane.showMessageDialog(jMenuItem1, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}
		return jMenuItem3;
	}
	
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(69, 169, 260, 19));
			jButton.setText("Ordenar Alfabeticamente por Paises");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					tienda.OrdAlf();
					actualizar();
				}
			});
		}
		return jButton;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Principal thisClass = new Principal();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
				thisClass.setLocationRelativeTo(null);
			}
		});
	}

	public Principal() {
		super();
		initialize();
		tienda = new Tienda();
		tienda.Cargar();
	}

	private void initialize() {
		this.setSize(446, 274);
		this.addFocusListener(new java.awt.event.FocusAdapter() { 
			
		});
		this.addWindowListener(new java.awt.event.WindowAdapter() {   
			public void windowClosing(java.awt.event.WindowEvent e) {    
				tienda.Guardar();
			} 
			public void windowActivated(java.awt.event.WindowEvent e) {    
				actualizar();
			}
		});
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Tienda:");

	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setText("JLabel");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTabbedPane1(), null);

			jContentPane.add(getJButton(), null);
			jContentPane.add(jLabel, null);
		}
		return jContentPane;
	}

	public void actualizar() {
		Mascota nueva;
		jTable3=getJTable3();
		jTable4=getJTable4();
		DefaultTableModel a = new DefaultTableModel();
		DefaultTableModel b = new DefaultTableModel();
		Object[] title={"País","Edad","Raza","Color","Codigo"};
		a.setColumnCount(4);
		a.setRowCount(tienda.getCantReal());
		a.setColumnIdentifiers(title);
		Object[] title1={"País","Edad","Sexo","Color","Codigo","Color Ojos","Pedigri"};
		b.setColumnCount(7);
		b.setColumnIdentifiers(title1);
		b.setRowCount(tienda.getCantReal());
		for (int i = 0, p = 0, g = 0; i < tienda.getCantReal(); i++) {
			nueva = tienda.getObjPos(i);
			if (nueva instanceof Perro) {
				a.setValueAt(nueva.getPaisProced(), p, 0);
				a.setValueAt(String.valueOf(nueva.getCantMeses()), p, 1);
				a.setValueAt(((Perro) nueva).getRaza(), p, 2);
				a.setValueAt(nueva.getColorPredom(), p, 3);
				a.setValueAt(nueva.getCode(), p, 4);
				p++;
			} else {
				b.setValueAt(nueva.getPaisProced(), g, 0);
				b.setValueAt(String.valueOf(nueva.getCantMeses()), g, 1);
				b.setValueAt(((Gato) nueva).getSexo(), g, 2);
				b.setValueAt(nueva.getColorPredom(), g, 3);
				b.setValueAt(nueva.getCode(), g, 4);
				b.setValueAt(((Gato) nueva).getColorOjos(), g, 5);
				b.setValueAt(String.valueOf(((Gato) nueva).isPedigri()), g, 6);
				g++;
			}
		}
		jTable3.setModel(a);
		jTable4.setModel(b);
	}
} // @jve:decl-index=0:visual-constraint="10,10"
