package Visual;

/**
 *
 * @author EGH
 */
import java.net.MalformedURLException;
import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import SimbolosdeTabla.*;
import AnalizadorLexico.*;
import Errores.*;
import AnalizadorSintactico.Parser;
import Compilador.EasyCompiler;
import Compilador.InOut;
import Runtime.*;
import Stream.*;
import java.util.List;
import java.util.ListIterator;

public class IDEManager {

    private NumerosBinarios numbin;
    private static IDEManager instance = null;
    private LinkedList<String> scannerOutput;

    private IDEManager() {
        numbin = new NumerosBinarios();
        numbin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        scannerOutput = new LinkedList<String>();
        InOut.SetConsole(new MyConsole(numbin.getjTextField1(), numbin.getjEditorPane2()));
    }

    public NumerosBinarios getNumerosBinarios() {
        return numbin;
    }

    public static IDEManager getInstance() {
        if (instance == null) {
            instance = new IDEManager();
        }
        return instance;
    }

    public void newDocument() throws FileNotFoundException, IOException {

        numbin.getSourceEditor().setText(null);
    }

    public void exit() {
        numbin.dispose();
        System.exit(0);
    }

    private void prepareToBuild() {
        numbin.getjEditorPane2().setText(null);
        scannerOutput.clear();
    }

    private void ShowErrors(ErrorReporter errorReporter) {
        StringBuilder txt = new StringBuilder();
        ListIterator<CompilerError> it = errorReporter.listIterator();
        while (it.hasNext()) {
            CompilerError elem = it.next();
            txt.append(elem.Text());
            txt.append("\n");
        }
        numbin.getScannerOutput().setText(txt.toString());
    }
    private int line;

    public List<RuntimeEntity> build() throws IOException, Exception {
        prepareToBuild();

        EasyCompiler compiler = new EasyCompiler(new StringSourceStream(numbin.getSourceEditor().getText()));
        List<RuntimeEntity> code = compiler.Compile();
        ShowErrors(compiler.ErrorReporter());

        SymbolsTable symbolsTable = new SymbolsTable();
        ErrorReporter errorReporter = new ErrorReporter();
        Scanner scanner = new Scanner(new StringSourceStream(numbin.getSourceEditor().getText()), symbolsTable, errorReporter);
        Parser parser = new Parser(scanner, errorReporter);
        parser.Parse();
        //este es el lio
        LinkedList<Token> output = scanner.getCurrentOutput();
        int tokensCount = output.size();
        scannerOutput.clear();
        line = 1;
        for (int i = 0; i < tokensCount - 1; i++) {
            addTokenToScannerOutput(output.get(i));
        }

        writeScannerOutput();

        String text = "";
        int errorCount = errorReporter.size();
        if (errorCount > 0) {
            text = "Se han detectado " + errorCount + " error(es):\r\n\n";
            for (int i = 0; i < errorCount; i++) {
                CompilerError currentError = errorReporter.get(i);
                if (currentError.getClass() == LexicalError.class) {
                    text += "   Lexical error: " + currentError.Text() + "  -> Linea " + currentError.Position().getLine() + "\r\n";
                } else if (currentError.getClass() == SyntacticError.class) {
                    text += "   Syntactic error: " + currentError.Text() + "  -> Linea " + currentError.Position().getLine() + "\r\n";
                }
            }
        } else {
            text = "Análisis Sintático realizado...\r\n"
                    + "Compilacion exitosa.\n\n\n";
        }

        numbin.getjEditorPane1().setText(text);
        return code;

    }

    public void run() throws IOException, Exception {
        List<RuntimeEntity> code = build();
        if (code != null) {
            Interpreter interpreter = new Interpreter(code);
            interpreter.Execute();
        }
    }

    private void addTokenToScannerOutput(Token current) {
        if (current.getPosition().getLine() > line) {
            line = current.getPosition().getLine();
            newLine();
        }
        String strToken = "<" + current.getKind().toString() + " , " + "\"" + current.getLexeme() + "\"" + ">";
        scannerOutput.add(strToken);
    }

    private void writeScannerOutput() {
        String text = "";
        for (int i = 0; i < scannerOutput.size(); i++) {
            text = text + " " + scannerOutput.get(i);
        }
        numbin.getScannerOutput().setText(text);
    }

    private void newLine() {
        scannerOutput.add("\r\n");
    }
    /* public void grammar() throws MalformedURLException, IOException
    {
    GrammarDialog grammarDialog = new GrammarDialog(numbin, true);
    grammarDialog.getGrammarEditorPane().setEditable(false);
    java.net.URL url = grammarDialog.getClass().getResource("/Resources/gramar.html");
    System.out.println(url);
    if (url != null)
    grammarDialog.getGrammarEditorPane().setPage(url);
    grammarDialog.setSize(numbin.getWidth(), numbin.getHeight());
    grammarDialog.setVisible(true);
    }*/
}
