
package Analizador_Sintactico;

/**
 *
 * @author EGH
 */
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import Analizador_Lexico.*;
import Error.*;
import Error.ErrorReporter;
import java.io.*;

public class Parser {

    private Scanner scanner;
    private ErrorReporter errorReporter;
    private Token currentToken;

    public Parser(Scanner scanner, ErrorReporter errorReporter) {
        this.scanner = scanner;
        this.errorReporter = errorReporter;
    }

    private void ReportSyntacticError(String text) {
        errorReporter.add(new SyntacticError(currentToken.getPosition(), text));
    }

    private void Match(Token_Kind tokenExpected) {
        if (currentToken.getKind() == tokenExpected) {
            AcceptIt();
        } else {
            ReportSyntacticError(tokenExpected.toString() + " tokenExpected");
        }
    }

    private void AcceptIt() {
        currentToken = scanner.scan();
    }

    public void S() throws IOException {
        if (scanner.scan().equals(currentToken.getKind().Declaraciones)) {
            Match(currentToken.getKind().Declaraciones);
            Match(currentToken.getKind().TK_DosPuntos);
            Declaracion();
            Match(currentToken.getKind().Producciones);
            Match(currentToken.getKind().TK_DosPuntos);
            Producciones();
            Match(currentToken.getKind().Operaciones);
            Match(currentToken.getKind().TK_DosPuntos);
            Operaciones();
        } else {
            ReportSyntacticError("Error Sintáctico: Se esperaba que se entraran los datos de Declaraciones y usted puso " + scanner.scan());
        }
    }

    public void Declaracion() {
        if (scanner.scan().equals(currentToken.getKind().Gramatica)) {
            Match(currentToken.getKind().Gramatica);
            Gramatica();
            Declaraciones1();
        } else if (scanner.scan().equals(currentToken.getKind().Cadena)) {
            Match(currentToken.getKind().Cadena);
            Cadena();
            Declaraciones1();
        } else {
            ReportSyntacticError("Error Sintáctico: Se esperaba que se entraran las palabras reservadas Gramatica o Cadena y usted puso " + scanner.scan());
        }
    }

    public void Producciones() {
        if (scanner.scan().equals(currentToken.getKind().TK_id)) {
            Match(currentToken.getKind().TK_id);
            Match(currentToken.getKind().TK_DosPuntos);
            Prod();
            Match(currentToken.getKind().TK_Implicacion);
            Prod1();
            Match(currentToken.getKind().TK_PuntoComa);
            PRODSGTE();
        } else {
            ReportSyntacticError("Error Sintáctico: Se esperaba que se entrara un id para la gramatica y usted puso " + scanner.scan());
        }
    }

    public void Operaciones() {
     if (scanner.scan().equals(currentToken.getKind().Read)){
     Match(currentToken.getKind().Read);
     Match(currentToken.getKind().TK_ParIzquierdo);
     Match(currentToken.getKind().TK_id);
     Match(currentToken.getKind().TK_ParDerecho);
     Match(currentToken.getKind().TK_PuntoComa);
     Operaciones();
     }
     else if(scanner.scan().equals(currentToken.getKind().Clasifica)){
     Match(currentToken.getKind().Clasifica);
     Match(currentToken.getKind().TK_ParIzquierdo);
     Match(currentToken.getKind().TK_id);
     Match(currentToken.getKind().TK_ParDerecho);
     Match(currentToken.getKind().TK_PuntoComa);
     Operaciones();
     }
     else if(scanner.scan().equals(currentToken.getKind().Pertenencia)){
     Match(currentToken.getKind().Pertenencia);
     Match(currentToken.getKind().TK_ParIzquierdo);
     Match(currentToken.getKind().TK_id);
     Match(currentToken.getKind().TK_Coma);
     Match(currentToken.getKind().TK_id);
     Match(currentToken.getKind().TK_ParDerecho);
     Match(currentToken.getKind().TK_PuntoComa);
     Operaciones();
     }
     else if(scanner.scan().equals(currentToken.getKind().TK_id)){
     Match(currentToken.getKind().TK_id);
     Operaciones1();
     }
     else {
            ReportSyntacticError("Error Sintáctico: Se esperaba que se introdujera una palabra reservada para operaciones y usted puso " + scanner.scan());
        }
    }

    public void Gramatica() {
       if(scanner.scan().equals(currentToken.getKind().Gramatica)){
       Match(currentToken.getKind().Gramatica);
       Match(currentToken.getKind().TK_id);
       Gramatica1();
       Match(currentToken.getKind().TK_PuntoComa);
       }
       else {
            ReportSyntacticError("Error Sintáctico: Se esperaba que se introdujera la palabra reservada Gramatica y usted puso " + scanner.scan());
        }
    }

    public void Declaraciones1() {
    }

    public void Cadena() {
    }

    public void Prod() {
    }

    public void Prod1() {
    }

    public void PRODSGTE() {
    }

    public void Operaciones1(){}

    public void Gramatica1(){}



}














