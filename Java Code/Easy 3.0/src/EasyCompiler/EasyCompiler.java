package EasyCompiler;

import EasyCompiler.Errors.*;
import EasyCompiler.LexicalAnalyzer.*;
import EasyCompiler.SintaxAnalyzer.*;
import EasyCompiler.AbstractSyntaxTrees.*;
import EasyCompiler.SemanticAnalyzer.*;
import EasyCompiler.Runtime.*;
import EasyCompiler.Stream.*;
import EasyCompiler.SymbolsTable.*;
import java.io.IOException;
import java.util.List;

public class EasyCompiler 
{
    private Scanner scanner;
    private ErrorReporter errorReporter;
    private SymbolsTable symbolsTable;
    private Parser parser;
    private AST tree;

    public EasyCompiler(SourceStream source) throws IOException 
    {
        symbolsTable = new SymbolsTable();
        errorReporter = new ErrorReporter();
        scanner = new Scanner(source, symbolsTable, errorReporter);
        parser = new Parser(scanner, errorReporter);
        tree = null;
    }

    public List<RuntimeEntity> Compile()
    {
        InOut.Write("Easy Compiler (C# Version 3.0)", null);
        InOut.Write("Syntactic Analysis ...", null);
        tree = parser.Parse();
        if (errorReporter.size() > 0)
        {
            Integer[] arg = new Integer[1];
            arg[0] = errorReporter.size();
            InOut.Write("{0} errors found.", arg);
            InOut.Write("Compilation was unsuccessful.", null);
        }
        else
        {
            InOut.Write("Semantic Analysis ...", null);
            Visitor checker = new Checker(symbolsTable, errorReporter);
            tree.Visit(checker, null);
            if (errorReporter.size() > 0)
            {
                Object[] arg = new Integer[1];
                arg[0] = errorReporter.size();
                InOut.Write(String.format("%d: errors found.", arg), null);
                InOut.Write("Compilation was unsuccessful.", null);
            }
            else
            {
                Encoder encoder = new Encoder(symbolsTable);
                tree.Visit(encoder, null);
                InOut.Write("Compilation was successful.", null);
                return encoder.Code();
            }
        }
        return null;
    }

    public ErrorReporter ErrorReporter()
    {
        return errorReporter;            
    }

    public AST AST()
    {
        return tree;     
    }
}