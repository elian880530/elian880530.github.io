package AnalizadorLexico;

/**
 *
 * @author EGH
 */
import java.util.Hashtable;
import java.io.*;

import Compilador.SourcePosition;
import Errores.ErrorReporter;
import Errores.LexicalError;
import Stream.*;
import SimbolosdeTabla.*;
import java.util.LinkedList;

enum ScannerState {

    Start, BeginToken, Id, binaryLiteral, SemiColon, Comma, LeftParen, RigthParen, Assignment, Not, LessThan,
    Equivalencia, Implicacion, Menos, And, Or, EOT, Error
}

public class Scanner {

    private SourceStream sourceStream;
    private char currentChar;
    private String lexeme;
    private Hashtable<String, TokenKind> keywordsTable;
    private SymbolsTable symbolsTable;
    private LinkedList<Token> output;
    private ErrorReporter errorReporter;

    private boolean isLetter(char c) {
        if (isSeparator(c) || c == ',' || c == ';' || c == '=') {
            return false;
        } else {
            return (Character.isLetter(c) || c == '_');
        }
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private boolean isSeparator(char c) {
        switch (c) {
            case ' ':
            case '\n':
            case '\r':
            case '\t':
                return true;
            default:
                return false;
        }
    }

    private void takeIt() throws IOException {
        lexeme = lexeme + Character.toString(currentChar);
        currentChar = sourceStream.Read();
    }

    private void skipIt() throws IOException {
        currentChar = sourceStream.Read();
    }

    private void fillKeywordsTable() {
        keywordsTable.put("begin", TokenKind.Begin);
        keywordsTable.put("end", TokenKind.End);
        keywordsTable.put("program", TokenKind.Program);
        keywordsTable.put("var", TokenKind.Var);
        keywordsTable.put("print", TokenKind.print);
        keywordsTable.put("bin", TokenKind.Bin);
    }

    private TokenKind defineTokenKind(String lexeme) {
        TokenKind kind;
        if (keywordsTable.containsKey(lexeme)) {
            kind = keywordsTable.get(lexeme);
            return kind;
        }
        return TokenKind.Id;
    }

    public Scanner(SourceStream source, SymbolsTable symbolsTable, ErrorReporter errorReporter) throws IOException {
        this.sourceStream = source;
        this.symbolsTable = symbolsTable;
        this.output = new LinkedList<Token>();
        this.errorReporter = errorReporter;
        currentChar = source.Read();
        lexeme = new String("");
        keywordsTable = new Hashtable<String, TokenKind>();
        fillKeywordsTable();
    }

    private Token ID() throws Exception {

        while (isLetter(this.currentChar) || isDigit(this.currentChar)) {
            takeIt();
        }
        Token currentToken;
        TokenKind kind = defineTokenKind(lexeme);
        SourcePosition position = new SourcePosition(sourceStream.getCurrentPosition() - lexeme.length(), sourceStream.getCurrentPosition() - 1, sourceStream.getCurrentLine());
        int entry = -1;
        if (kind == TokenKind.Id) {
            entry = symbolsTable.add(lexeme, kind);
        }
        currentToken = new Token(kind, lexeme, position, entry);
        output.add(currentToken);
        return currentToken;
    }

    public Token Binari() throws Exception {
        Token currenttoken;
        TokenKind kind = TokenKind.binaryLiteral;

        while (isDigit(currentChar)) {
            if (currentChar != '0' && currentChar != '1') {
                kind = TokenKind.Error;
            }
            takeIt();
        }

        SourcePosition position = new SourcePosition(sourceStream.getCurrentPosition() - lexeme.length(), sourceStream.getCurrentPosition() - 1, sourceStream.getCurrentLine());

        if (kind == TokenKind.binaryLiteral) {
            currenttoken = new Token(kind, lexeme, position, symbolsTable.add(lexeme, kind));
        } else {
            currenttoken = new Token(kind, lexeme, position);
        }

        output.add(currenttoken);
        return currenttoken;
    }

    public Token nextToken() throws IOException, Exception {
        TokenKind kind = TokenKind.Error;
        ScannerState state = ScannerState.Start;
        boolean scaning = true;
        lexeme = "";
        while (scaning) {
            switch (state) {
                case Start:
                    if (isSeparator(currentChar)) {
                        skipIt();
                    } else {
                        state = ScannerState.BeginToken;
                    }
                    break;
                case BeginToken:
                    if (isLetter(currentChar)) {
                        takeIt();
                        state = ScannerState.Id;
                    } else if (isDigit(currentChar)) {
                        //takeIt();
                        state = ScannerState.binaryLiteral;
                    } else if (currentChar == '-') {
                        takeIt();
                        state = ScannerState.Menos;
                    } else if (currentChar == ';') {
                        takeIt();
                        state = ScannerState.SemiColon;
                    } else if (currentChar == ',') {
                        takeIt();
                        state = ScannerState.Comma;
                    } else if (currentChar == '(') {
                        takeIt();
                        state = ScannerState.LeftParen;
                    } else if (currentChar == ')') {
                        takeIt();
                        state = ScannerState.RigthParen;
                    } else if (currentChar == '=') {
                        takeIt();
                        state = ScannerState.Assignment;
                    } else if (currentChar == '!') {
                        takeIt();
                        state = ScannerState.Not;
                    } else if (currentChar == '<') {
                        takeIt();
                        state = ScannerState.LessThan;
                    } else if (currentChar == '&') {
                        takeIt();
                        state = ScannerState.And;
                    } else if (currentChar == '|') {
                        takeIt();
                        state = ScannerState.Or;
                    } else if (currentChar == '\0') {
                        state = ScannerState.EOT;
                    } else {
                        takeIt();
                        state = ScannerState.Error;
                    }
                    break;
                case Id:
                    return ID();

                case binaryLiteral:
                    return Binari();
                case SemiColon:
                    scaning = false;
                    kind = TokenKind.SemiColon;
                    break;
                case Comma:
                    scaning = false;
                    kind = TokenKind.Comma;
                    break;
                case Menos:
                    if (currentChar == '>') {
                        takeIt();
                        scaning = false;
                        kind = TokenKind.Implicacion;
                    } else {
                        scaning = false;
                        kind = TokenKind.Error;
                    }
                    break;
                case LeftParen:
                    scaning = false;
                    kind = TokenKind.LeftParen;
                    break;
                case RigthParen:
                    scaning = false;
                    kind = TokenKind.RigthParen;
                    break;
                case Assignment:
                    scaning = false;
                    kind = TokenKind.Assignment;
                    break;
                case Not:
                    scaning = false;
                    kind = TokenKind.Not;
                    break;
                case LessThan:
                    if (currentChar == '-') {
                        takeIt();
                        state = ScannerState.Equivalencia;
                    } else {
                        scaning = false;
                        kind = TokenKind.Error;
                    }
                    break;
                case Equivalencia:
                    if (currentChar == '>') {
                        takeIt();
                        scaning = false;
                        kind = TokenKind.Equivalencia;
                    } else {
                        scaning = false;
                        kind = TokenKind.Error;
                    }
                    break;
                case And:
                    scaning = false;
                    kind = TokenKind.And;
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
        SourcePosition position = new SourcePosition(sourceStream.getCurrentPosition() - lexeme.length(), sourceStream.getCurrentPosition() - 1, line);

        if (kind == TokenKind.Error) {
            errorReporter.add(new LexicalError(position, "Unexpected character '" + lexeme + "'"));
            output.add(new Token(TokenKind.Error, lexeme, position));
            return nextToken();
        }

        Token currentToken;

        if (kind == TokenKind.Id) {
            kind = defineTokenKind(lexeme);
        }

        if (kind == TokenKind.Id || kind == TokenKind.binaryLiteral) {
            currentToken = new Token(kind, lexeme, position, symbolsTable.add(lexeme, kind));
        } else {
            currentToken = new Token(kind, lexeme, position);
        }

        output.add(currentToken);

        return currentToken;


    }

    public LinkedList<Token> getCurrentOutput() {
        return output;
    }
}
