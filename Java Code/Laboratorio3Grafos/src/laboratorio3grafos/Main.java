/*
 * Main.java
 *
 * Created on 4 de noviembre de 2007, 23:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package laboratorio3grafos;


public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameCPGrafos().setVisible(true);
            }
        });
    }
    
}
