/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyCompiler.LexicalAnalyzer;

/**
 *
 * @author DDC Programación, UCI
 */

import java.util.Hashtable;
import java.io.*;
import java.util.LinkedList;

import EasyCompiler.SourcePosition;
import EasyCompiler.Stream.*;
import EasyCompiler.SymbolsTable.*;
import EasyCompiler.Errors.*;

enum ScannerState
{
    Start, BeginToken, Id, NumLiteral, BeginFloatLiteral, FloatLiteral, Colon, SemiColon, Comma, LeftParen,
    RigthParen, Sum, Minus, Multiplication, Division, Modulus, Equality, Assignment, Not, Inequality, LessThan,
    GreaterThan, GreaterThanOrEqual, LessThanOrEqual, BeginAnd, BeginOr, And, Or, EOT, Error
}

public class Scanner {
    
    private SourceStream sourceStream;
    private char currentChar;
    private String lexeme;
    private Hashtable<String, TokenKind> keywordsTable;
    private SymbolsTable symbolsTable;
    private ErrorReporter errorReporter;
    private LinkedList<Token> output; 
    
    private boolean isLetter(char c)
    {
        return (Character.isLetter(c) || c == '_');
    }
    private boolean isDigit(char c)
    {
        return Character.isDigit(c);
    }
    private boolean isSeparator(char c)
    {
        switch (c)
        {
            case ' ':
            case '\n':
            case '\r':
            case '\t': 
                    return true;
            default:
                    return false;
        }
    }
    
    private void takeIt()
    {
        try
        {
            lexeme = lexeme + Character.toString(currentChar);
            currentChar = sourceStream.Read();
        }
        catch(Exception e){throw new ScanningProblemException(e);}
    }
    
    private void skipIt()
    {
        try
        {
            currentChar = sourceStream.Read();
        }
        catch(Exception e){throw new ScanningProblemException(e);}
    }
    
    private void fillKeywordsTable()
    {
        keywordsTable.put("begin", TokenKind.Begin);
        keywordsTable.put("bool", TokenKind.Bool);
        keywordsTable.put("true", TokenKind.BoolLiteral);
        keywordsTable.put("false", TokenKind.BoolLiteral);
        keywordsTable.put("end", TokenKind.End);
        keywordsTable.put("float", TokenKind.Float);
        keywordsTable.put("int", TokenKind.Int);
        keywordsTable.put("program", TokenKind.Program);
        keywordsTable.put("read", TokenKind.Read);
        keywordsTable.put("var", TokenKind.Var);
        keywordsTable.put("write", TokenKind.Write);
        keywordsTable.put("if", TokenKind.If);
        keywordsTable.put("then", TokenKind.Then);
        keywordsTable.put("else", TokenKind.Else);
        keywordsTable.put("while", TokenKind.While);
        keywordsTable.put("do", TokenKind.Do);
    }
    private TokenKind defineTokenKind(String lexeme) 
    {
        TokenKind kind;
        if (keywordsTable.containsKey(lexeme))
        {
            kind = keywordsTable.get(lexeme);
            return kind;
        }
        return TokenKind.Id;
    }
    
    public Scanner(SourceStream source, SymbolsTable symbolsTable, ErrorReporter errorReporter) throws IOException
    {
        this.sourceStream = source;
        this.symbolsTable = symbolsTable;
        this.errorReporter = errorReporter;
        this.output = new LinkedList<Token>();
        currentChar = source.Read();
        lexeme = new String("");
        keywordsTable = new Hashtable<String, TokenKind>();
        fillKeywordsTable();
    }
    
    public Token scan()
    {
        TokenKind kind = TokenKind.Error;
        ScannerState state = ScannerState.Start;
        boolean scaning = true;
        lexeme = "";
        while(scaning)
        {
            switch(state)
            {
                case Start :
                    if(isSeparator(currentChar))
                        skipIt();
                    else
                        state = ScannerState.BeginToken;
                    break;
                case BeginToken:
                    if (isLetter(currentChar))
                    {
                        takeIt();
                        state = ScannerState.Id;
                    }
                    else if (isDigit(currentChar)) 
                    {
                        takeIt();
                        state = ScannerState.NumLiteral;
                    }
                    else if(currentChar == ':')
                    {
                        takeIt();
                        state = ScannerState.Colon;
                    }
                    else if (currentChar == ';')
                    {
                        takeIt();
                        state = ScannerState.SemiColon;
                    }
                    else if (currentChar == ',')
                    {
                        takeIt();
                        state = ScannerState.Comma;
                    }
                    else if (currentChar == '(')
                    {
                        takeIt();
                        state = ScannerState.LeftParen;
                    }
                    else if (currentChar == ')')
                    {
                        takeIt();
                        state = ScannerState.RigthParen;
                    }
                    else if (currentChar == '+')
                    {
                        takeIt();
                        state = ScannerState.Sum;
                    }
                    else if (currentChar == '-')
                    {
                        takeIt();
                        state = ScannerState.Minus;
                    }
                    else if (currentChar == '*')
                    {
                        takeIt();
                        state = ScannerState.Multiplication;
                    }
                    else if (currentChar == '/')
                    {
                        takeIt();
                        state = ScannerState.Division;
                    }
                    else if (currentChar == '%')
                    {
                        takeIt();
                        state = ScannerState.Modulus;
                    }
                    else if (currentChar == '=')
                    {
                        takeIt();
                        state = ScannerState.Assignment;
                    }
                    else if (currentChar == '!')
                    {
                        takeIt();
                        state = ScannerState.Not;
                    }
                    else if (currentChar == '<')
                    {
                        takeIt();
                        state = ScannerState.LessThan;
                    }
                    else if (currentChar == '>')
                    {
                        takeIt();
                        state = ScannerState.GreaterThan;
                    }
                    else if (currentChar == '&')
                    {
                        takeIt();
                        state = ScannerState.BeginAnd;
                    }
                    else if (currentChar == '|')
                    {
                        takeIt();
                        state = ScannerState.BeginOr;
                    }
                    else if (currentChar == '\0')
                    {
                        state = ScannerState.EOT;
                    }
                    else
                    {
                        takeIt();
                        state = ScannerState.Error;
                    }
                    break;
                case Id:
                    if (isLetter(currentChar) || isDigit(currentChar))
                        takeIt();
                    else
                    {
                        scaning = false;
                        kind = TokenKind.Id;
                    }
                    break;
                case NumLiteral:
                    if (isDigit(currentChar))
                        takeIt();
                    else if (currentChar == '.')
                    {
                        takeIt();
                        state = ScannerState.BeginFloatLiteral;
                    }
                    else 
                    {
                        scaning = false;
                        kind = TokenKind.IntLiteral;
                    }
                    break;
                case BeginFloatLiteral:
                    if (isDigit(currentChar))
                    {
                        takeIt();
                        state = ScannerState.FloatLiteral;
                    }
                    else 
                        state = ScannerState.Error; 
                    break;
                case FloatLiteral:
                    if (isDigit(currentChar))
                    {
                        takeIt();
                    }
                    else
                    {
                        scaning = false;
                        kind = TokenKind.FloatLiteral;
                    }
                    break;
                case Colon:
                    scaning = false;
                    kind = TokenKind.Colon;
                    break;
                case SemiColon:
                    scaning = false;
                    kind = TokenKind.SemiColon;
                    break;
                case Comma:
                    scaning = false;
                    kind = TokenKind.Comma;
                    break;
                case LeftParen:
                    scaning = false;
                    kind = TokenKind.LeftParen;
                    break;
                case RigthParen:
                    scaning = false;
                    kind = TokenKind.RigthParen;
                    break;
                case Sum:
                    scaning = false;
                    kind = TokenKind.Sum;
                    break;
                case Minus:
                    scaning = false;
                    kind = TokenKind.Minus;
                    break;
                case Multiplication:
                    scaning = false;
                    kind = TokenKind.Multiplication;
                    break;
                case Division:
                    scaning = false;
                    kind = TokenKind.Division;
                    break;
                case Modulus:
                    scaning = false;
                    kind = TokenKind.Modulus;
                    break;
                case Assignment:
                    if (currentChar == '=')
                    {
                        takeIt();
                        state = ScannerState.Equality;
                    }
                    else 
                    {
                        scaning = false;
                        kind = TokenKind.Assignment;
                    }
                    break;
                case Equality:
                    scaning = false;
                    kind = TokenKind.Equality;
                    break;
                case Not:
                    if (currentChar == '=')
                    {
                        takeIt();
                        state = ScannerState.Inequality;
                    }
                    else
                    {
                        scaning = false;
                        kind = TokenKind.Not;
                    }
                    break;
                case Inequality:
                    scaning = false;
                    kind = TokenKind.Inequality;
                    break;
                case LessThan:
                    if (currentChar == '=')
                    {
                        takeIt();
                        state = ScannerState.LessThanOrEqual;
                    }
                    else
                    {
                        scaning = false;
                        kind = TokenKind.LessThan;
                    }
                    break;
                case LessThanOrEqual:
                    scaning = false;
                    kind = TokenKind.LessThanOrEqual;
                    break;
                case GreaterThan:
                    if (currentChar == '=')
                    {
                        takeIt();
                        state = ScannerState.GreaterThanOrEqual;
                    }
                    else
                    {
                        scaning = false;
                        kind = TokenKind.GreaterThan;
                    }
                    break;
                case GreaterThanOrEqual:
                    scaning = false;
                    kind = TokenKind.GreaterThanOrEqual;
                    break;
                case BeginAnd:
                    if (currentChar == '&')
                    {
                        takeIt();
                        state = ScannerState.And;
                    }
                    else
                        state = ScannerState.Error;
                    break;
                case And:
                    scaning = false;
                    kind = TokenKind.And;
                    break;
                case BeginOr:
                    if (currentChar == '|')
                    {
                        takeIt();
                        state = ScannerState.Or;
                    }
                    else
                        state = ScannerState.Error;
                    break;
                case Or:
                    scaning = false;
                    kind = TokenKind.Or;
                    break;
                case Error:
                    scaning = false;
                    kind = TokenKind.Error;
                    break;
                case EOT:
                    scaning = false;
                    kind = TokenKind.EOT;
                    break;
            } 
        }
        int line = sourceStream.getCurrentLine();
        SourcePosition position = new SourcePosition(sourceStream.getCurrentPosition() - lexeme.length(), sourceStream.getCurrentPosition() -1, line);
        
        if(kind == TokenKind.Error)
        {   
            errorReporter.add(new LexicalError(position, "Unexpected character '" + lexeme + "'"));
            output.add(new Token(TokenKind.Error, lexeme, position));
            return scan();
        }
        
        Token currentToken;
        
        if (kind == TokenKind.Id)
            kind = defineTokenKind(lexeme);
        
        if(kind == TokenKind.Id || kind == TokenKind.BoolLiteral || kind == TokenKind.IntLiteral || kind == TokenKind.FloatLiteral)
            currentToken = new Token(kind, lexeme, position, symbolsTable.add(lexeme, kind));
        else 
            currentToken = new Token(kind, lexeme, position);
        
        output.add(currentToken);
        
        return currentToken;
    }
    
    public LinkedList<Token> getCurrentOutput()
    {
        return output;    
    }
}
