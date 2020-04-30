/*
 * Main.java
 *
 * Created on 10 de octubre de 2006, 17:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package arbolesbinarios;

/**
 *
 * @author lester
 */
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
                new JFrameArbolBinario().setVisible(true);
            }
        });
    }
    
}
