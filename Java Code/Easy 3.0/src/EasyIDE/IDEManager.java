/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyIDE;
/**
 *
 * @author DDC Programación, UCI
 */
import EasyCompiler.AbstractSyntaxTrees.AST;
import java.net.MalformedURLException;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import EasyCompiler.SymbolsTable.*;
import EasyCompiler.LexicalAnalyzer.*;
import EasyCompiler.SintaxAnalyzer.*;
import EasyCompiler.Errors.*;
import EasyCompiler.Stream.*;
import EasyCompiler.*;
import EasyCompiler.Runtime.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class IDEManager {

    private MainFrame mainFrame;
    private DocumentInfo document;
    private static IDEManager instance = null;
    private LinkedList<String> scannerOutput;
    
    private IDEManager()
    {
        mainFrame = new MainFrame();
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        document = new DocumentInfo();
        scannerOutput = new LinkedList<String>();
        InOut.SetConsole(new  MyConsole( mainFrame.getInputBox(), mainFrame.getConsoleBox() ));
    }
    
    void sourceEditorTextChanged()
    {
        document.modified();
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    public static IDEManager getInstance() {
        if (instance == null)
            instance = new IDEManager();
        return instance;
    } 
    
    public void newDocument() throws FileNotFoundException, IOException
    {
        if (!document.isSave())
        {
            int option = JOptionPane.showConfirmDialog(mainFrame, "Save changes?", "Save", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION)
                save();
        }
        mainFrame.getSourceEditor().setText(null);
        mainFrame.getJTabbedPane1().setSelectedIndex(0);
        mainFrame.getConsole().setText(null);
        mainFrame.getScannerOutput().setText(null);
        document.newDocument();
    }

    private void saveSource(String filename) throws FileNotFoundException, IOException
    {
        FileOutputStream out = new FileOutputStream(filename);
        out.write(mainFrame.getSourceEditor().getText().getBytes());
        out.close();
        document.saveAs(filename);
    }
    public void save() throws FileNotFoundException, IOException
    {
        if (!document.isSave())
            if (document.getFilename().equals(""))
                saveAs();
            else
                saveSource(document.getFilename());
    }
    
    public void saveAs() throws FileNotFoundException, IOException
    {
        JFileChooser chooser = new JFileChooser();
        int modalResult = chooser.showSaveDialog(mainFrame);
        
        if (modalResult == JFileChooser.APPROVE_OPTION)
            saveSource(chooser.getSelectedFile().getName());
    }
    
    public void open() throws FileNotFoundException, IOException
    {
        if (!document.isSave())
        {
            int option = JOptionPane.showConfirmDialog(mainFrame, "Save changes?", "Save", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION)
                save();
        }
        
        JFileChooser chooser = new JFileChooser();
        int modalResult = chooser.showOpenDialog(mainFrame);
        if (modalResult == JFileChooser.APPROVE_OPTION)
        {
            mainFrame.getSourceEditor().setText(null);
            FileInputStream in = new FileInputStream(chooser.getSelectedFile());
            
            String text = new String("");
            int c = in.read();
            while (c != -1)
            {
                text = text + Character.toString((char)c);
                c = in.read();
            }
            
            mainFrame.getSourceEditor().setText(text);
            mainFrame.getJTabbedPane1().setSelectedIndex(0);
            mainFrame.getConsole().setText(null);
            mainFrame.getScannerOutput().setText(null);
            
            document.open(chooser.getSelectedFile().getName());
            in.close();
            
        }
    }
    
    public void exit() 
    {
        mainFrame.dispose();
    }
    
    /*
    private void prepareToBuild() 
    {
        mainFrame.getScannerOutput().setText(null);
        mainFrame.getJTabbedPane1().setSelectedIndex(1);
        mainFrame.getConsole().setText(null);
        mainFrame.getScannerOutput().setText(null);
    }
    */
    private int line;    
  
    /*
    public void build() throws IOException, Exception
    {
        prepareToBuild();
        SymbolsTable symbolsTable = new SymbolsTable();
        ErrorReporter errorReporter = new ErrorReporter(); 
        
        Scanner scanner = new Scanner(new StringSourceStream(mainFrame.getSourceEditor().getText()), symbolsTable, errorReporter);
        Parser parser = new Parser(scanner, errorReporter);
        
        AST miAST = parser.Parse();
        
        LinkedList<Token> output = scanner.getCurrentOutput();
        int tokensCount = output.size();
        scannerOutput.clear();
        line = 1;
        //not included token EOT
        for(int i = 0; i < tokensCount - 1; i++)
           addTokenToScannerOutput(output.get(i)); 
        
        writeScannerOutput();
        
        String text = "";
        int errorCount = errorReporter.size();
        if(errorCount > 0)
        {
            text = "There are " + errorCount + " error(s):\r\n\n";
            for(int i = 0; i < errorCount; i++)
            {
                CompilerError currentError = errorReporter.get(i);
                if(currentError.getClass() == LexicalError.class)
                    text += "   Lexical error: " + currentError.Text() + "\r\n";
                else 
                    if(currentError.getClass() == SyntacticError.class)
                        text += "   Syntactic error: " + currentError.Text() + "\r\n";           
            }    
        }
        else
        {
            text = "Easy Compiler (Java Version 3.0)\r\nSyntax Analysis...\r\n" + 
                   "Compilation was successful.\n\n\n";
        }
        
        mainFrame.getConsole().setText(text);    
    }
    */
    private void addTokenToScannerOutput(Token current)
    {
        if (current.getPosition().getLine() > line)
        {
            line = current.getPosition().getLine();
            newLine();
        }
        String strToken = "<"+current.getKind().toString()+" , "+"\"" + current.getLexeme() + "\""+">";
        scannerOutput.add(strToken);
    }
    
    private void writeScannerOutput()
    {
        String text = "";
        for (int i = 0; i < scannerOutput.size(); i++)
            text = text + " " + scannerOutput.get(i);
        mainFrame.getScannerOutput().setText(text);
    }
    
    private void newLine()
    {
       scannerOutput.add("\r\n");
    }
    
    public void about()
    {
        new AboutDialog(mainFrame, true).setVisible(true);
    }
    
    public void grammar() throws MalformedURLException, IOException
    {
        GrammarDialog grammarDialog = new GrammarDialog(mainFrame, true);
        grammarDialog.getGrammarEditorPane().setEditable(false);
        java.net.URL url = grammarDialog.getClass().getResource("/Resources/gramar.html");
        if (url != null)
            grammarDialog.getGrammarEditorPane().setPage(url);
        grammarDialog.setSize(mainFrame.getWidth(), mainFrame.getHeight());
        grammarDialog.setVisible(true);
    }
    
    
    private void ClearErros()
    {
        mainFrame.getErrorsBox().setText(null);
    }

    private void ShowErrors(ErrorReporter errorReporter)
    {        
        StringBuilder txt = new StringBuilder();
        ListIterator<CompilerError> it = errorReporter.listIterator();
        while(it.hasNext()) 
        {
            CompilerError elem = it.next();
            txt.append(elem.Text());
            txt.append("\n");
        }
        mainFrame.getErrorsBox().setText(txt.toString());
    }
    private void ClearConsole()
    {
        mainFrame.getConsoleBox().setText(null);
    }
        
    private void PrepareToBuild() 
    {
        ClearConsole();
        ClearErros();
    }

    public List<RuntimeEntity> build() throws IOException
    {
        PrepareToBuild();
        EasyCompiler compiler = new EasyCompiler(new StringSourceStream(mainFrame.getSourceEditor().getText()));
        List<RuntimeEntity> code = compiler.Compile();
        ShowErrors(compiler.ErrorReporter());
        return code;
    }
    
    public void run() throws IOException, Exception
    {
        List<RuntimeEntity> code = build();
        if (code != null)
        {
            Interpreter interpreter = new Interpreter(code);
            interpreter.Execute();
        }
    }    
}
