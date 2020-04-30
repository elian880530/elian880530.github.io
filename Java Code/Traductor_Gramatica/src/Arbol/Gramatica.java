/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import Compilador.Source_Position;
import java.util.List;

/**
 *
 * @author EGH
 */
public class Gramatica extends Declaraciones {

    private String Id_Gramatica;
    private List <String> Id_Terminales;
    private List <String> Id_NoTerminales;
    private String Id_Produccion;
    private String Id_Distinguido;

    public Gramatica(Source_Position position, String Id_Gramatica, List<String> Id_Terminales, List<String> Id_NoTerminales, String Id_Produccion, String Id_Distinguido) {
        super(position);
        this.Id_Gramatica = Id_Gramatica;
        this.Id_Terminales = Id_Terminales;
        this.Id_NoTerminales = Id_NoTerminales;
        this.Id_Produccion = Id_Produccion;
        this.Id_Distinguido = Id_Distinguido;
    }

   

       
}
