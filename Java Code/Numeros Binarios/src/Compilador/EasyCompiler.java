package Compilador;

/**
 *
 * @author EGH
 */
import AnalizadorLexico.Scanner;
import AnalizadorSintactico.Parser;
import AbstractSyntaxTrees.*;
import AnalizadorSemantico.*;
import Runtime.*;
import Errores.ErrorReporter;
import SimbolosdeTabla.SymbolsTable;
import Stream.SourceStream;
import java.io.IOException;
import java.util.List;

public class EasyCompiler {

    private Scanner scanner;
    private ErrorReporter errorReporter;
    private SymbolsTable symbolsTable;
    private Parser parser;
    private AST tree;

    public EasyCompiler(SourceStream source) throws IOException {
        symbolsTable = new SymbolsTable();
        errorReporter = new ErrorReporter();
        scanner = new Scanner(source, symbolsTable, errorReporter);
        parser = new Parser(scanner, errorReporter);
        tree = null;
    }

    public List<RuntimeEntity> Compile() {
        InOut.Write("            Analizando el código", null);
        InOut.Write("            Syntactic Analysis ...", null);
        tree = parser.Parse();
        if (errorReporter.size() > 0) {
            Integer[] arg = new Integer[1];
            arg[0] = errorReporter.size();
            InOut.Write("{0} errors found.", arg);
            InOut.Write("Compilation was unsuccessful.", null);
        } else {
            InOut.Write("            Semantic Analysis ...", null);
            Visitor checker = new Checker(symbolsTable, errorReporter);
            tree.Visit(checker, null);
            if (errorReporter.size() > 0) {
                Object[] arg = new Integer[1];
                arg[0] = errorReporter.size();
                InOut.Write(String.format("%d: errors found.", arg), null);
                InOut.Write("Compilation was unsuccessful.", null);
            } else {
                Encoder encoder = new Encoder(symbolsTable);
                tree.Visit(encoder, null);
                InOut.Write("            Compilation was successful.", null);
                return encoder.Code();
            }
        }
        return null;
    }

    public ErrorReporter ErrorReporter() {
        return errorReporter;
    }

    public AST AST() {
        return tree;
    }
}
