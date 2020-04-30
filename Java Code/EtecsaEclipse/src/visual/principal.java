package src.visual;
import src.clases.*;
import src.eda.*;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import src.clases.*;
import src.eda.*;



public class principal extends JFrame {

	private static final long serialVersionUID = 1L;	
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu Archivo = null;
	private JMenuItem Salir = null;
	private JMenu Operaciones = null;
	private JMenuItem AdicionarCProv = null;
	private JMenuItem AdicionarCMcpio = null;
	private JMenuItem HacerLlamada = null;
	private CentralTelefonica b=null;
	
	public principal(CentralTelefonica b) {
		super();
		this.b=b;
		initialize();
		
	}	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getArchivo());
			jJMenuBar.add(getOperaciones());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes Archivo	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getArchivo() {
		if (Archivo == null) {
			Archivo = new JMenu();
			Archivo.setName("Archivo");
			Archivo.setText("Archivo");
			Archivo.add(getSalir());
		}
		return Archivo;
	}

	/**
	 * This method initializes Salir	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSalir() {
		if (Salir == null) {
			Salir = new JMenuItem();
			Salir.setName("Salir");
			Salir.setText("Salir");
			Salir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return Salir;
	}

	/**
	 * This method initializes Operaciones	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getOperaciones() {
		if (Operaciones == null) {
			Operaciones = new JMenu();
			Operaciones.setText("Operaciones");
			Operaciones.add(getAdicionarCProv());
			Operaciones.add(getAdicionarCMcpio());
			Operaciones.add(getHacerLlamada());
		}
		return Operaciones;
	}

	/**
	 * This method initializes AdicionarCProv	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAdicionarCProv() {
		if (AdicionarCProv == null) {
			AdicionarCProv = new JMenuItem();			
			AdicionarCProv.setText("Adicionar Central Provincial");
			AdicionarCProv.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					AddProv prov = new AddProv(central);
					prov.setModal(true);
					prov.setVisible(true);
					
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return AdicionarCProv;
	}

	/**
	 * This method initializes AdicionarCMcpio	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAdicionarCMcpio() {
		if (AdicionarCMcpio == null) {
			AdicionarCMcpio = new JMenuItem();
			AdicionarCMcpio.setText("Adicionar Central Municipal");
			AdicionarCMcpio.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					AddMcpio mcpio = new AddMcpio(central);
					mcpio.setModal(true);
					mcpio.setVisible(true);
					
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return AdicionarCMcpio;
	}

	/**
	 * This method initializes HacerLlamada	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getHacerLlamada() {
		if (HacerLlamada == null) {
			HacerLlamada = new JMenuItem();
			HacerLlamada.setText("Hacer Llamada Telefonica");
			HacerLlamada.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Llamada a=new Llamada(central);
					a.setModal(true);
					a.setVisible(true);
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return HacerLlamada;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				principal thisClass = new principal();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	CentralTelefonica central;
	public principal() {
		super();
		initialize();
		central = new CentralTelefonica();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
		}
		return jContentPane;
	}

}
