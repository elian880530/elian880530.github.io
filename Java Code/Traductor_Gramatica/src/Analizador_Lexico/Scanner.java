package Analizador_Lexico;

import Codigo_Fuente.SourceStream;
import Compilador.Source_Position;
import Tabla_Simbolo.SymbolsTable;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import Error.*;

/**
 *
 * @author EGH
 */
enum ScannerState {

    Start, BeginToken, Id, Cadena, NumLiteral, Colon, SemiColon, Comma, LeftParen, LeftLlave, RigthLlave,
    RigthParen, Sum, Multiplication, Assignment, Implicacion, BeginOr, Vacio, EOT, Error
}

public class Scanner {

    private SourceStream sourceStream;
    private char currentChar;
    private String lexeme;
    private Hashtable<String, Token_Kind> keywordsTable;
    private SymbolsTable symbolsTable;
    private ErrorReporter errorReporter;
    private LinkedList<Token> output;

    private boolean isLetter(char c) {
        return (Character.isLetter(c) || c == '_');
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

    private void takeIt() {
        try {
            lexeme = lexeme + Character.toString(currentChar);
            currentChar = sourceStream.Read();
        } catch (Exception e) {
            throw new Scanning_Problem_Exception(e);
        }
    }

    private void skipIt() {
        try {
            currentChar = sourceStream.Read();
        } catch (Exception e) {
            throw new Scanning_Problem_Exception(e);
        }
    }

    private void fillKeywordsTable() {
        keywordsTable.put("Gramatica", Token_Kind.Gramatica);
        keywordsTable.put("Cadena", Token_Kind.Cadena);
        keywordsTable.put("Producciones", Token_Kind.Producciones);
        keywordsTable.put("Operaciones", Token_Kind.Operaciones);
        keywordsTable.put("Read", Token_Kind.Read);
        keywordsTable.put("Clasifica", Token_Kind.Clasifica);
        keywordsTable.put("Pertenencia", Token_Kind.Pertenencia);
        keywordsTable.put("Declaraciones", Token_Kind.Declaraciones);

    }

    private Token_Kind defineToken_Kind(String lexeme) {
        Token_Kind kind;
        if (keywordsTable.containsKey(lexeme)) {
            kind = keywordsTable.get(lexeme);
            return kind;
        }
        return Token_Kind.TK_id;
    }

    public Scanner(SourceStream source, SymbolsTable symbolsTable, ErrorReporter errorReporter) throws IOException {
        this.sourceStream = source;
        this.symbolsTable = symbolsTable;
        this.errorReporter = errorReporter;
        this.output = new LinkedList<Token>();
        currentChar = source.Read();
        lexeme = new String("");
        keywordsTable = new Hashtable<String, Token_Kind>();
        fillKeywordsTable();
    }

    public Token scan() {
        Token_Kind kind = Token_Kind.Error;
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
                    } else if (currentChar == ':') {
                        takeIt();
                        state = ScannerState.Colon;
                    } else if (currentChar == ';') {
                        takeIt();
                        state = ScannerState.SemiColon;
                    } else if (currentChar == '{') {
                        takeIt();
                        state = ScannerState.LeftLlave;
                    } else if (currentChar == '}') {
                        takeIt();
                        state = ScannerState.RigthLlave;
                    } else if (currentChar == ',') {
                        takeIt();
                        state = ScannerState.Comma;
                    } else if (currentChar == ')') {
                        takeIt();
                        state = ScannerState.RigthParen;
                    } else if (currentChar == '(') {
                        takeIt();
                        state = ScannerState.LeftParen;
                    } else if (currentChar == '+') {
                        takeIt();
                        state = ScannerState.Sum;
                    } else if (currentChar == '*') {
                        takeIt();
                        state = ScannerState.Multiplication;
                    } else if (currentChar == '=') {
                        takeIt();
                        state = ScannerState.Assignment;
                    } else if (currentChar == '&') {
                        takeIt();
                        state = ScannerState.Vacio;
                    } else if (currentChar == '|') {
                        takeIt();
                        state = ScannerState.BeginOr;
                    } else if (currentChar == '\0') {
                        state = ScannerState.EOT;
                    } else if (currentChar == '"') {
                        state = ScannerState.Cadena;
                    } else if (currentChar == '~') {
                        state = ScannerState.Implicacion;
                    } else {
                        takeIt();
                        state = ScannerState.Error;
                    }
                    break;

                case Id:
                    if (isLetter(currentChar)) {
                        takeIt();
                    } else {
                        scaning = false;
                        kind = Token_Kind.TK_id;
                    }
                    break;

                case Cadena:
                    if (currentChar != '"') {
                        takeIt();
                    } else {
                        scaning = false;
                        kind = Token_Kind.TK_cadena;
                    }
                    break;

                case Colon:
                    scaning = false;
                    kind = Token_Kind.TK_DosPuntos;
                    break;


                case SemiColon:
                    scaning = false;
                    kind = Token_Kind.TK_PuntoComa;
                    break;

                case Implicacion:
                    scaning = false;
                    kind = Token_Kind.TK_Implicacion;
                    break;

                case Comma:
                    scaning = false;
                    kind = Token_Kind.TK_Coma;
                    break;

                case LeftParen:
                    scaning = false;
                    kind = Token_Kind.TK_ParIzquierdo;
                    break;

                case RigthParen:
                    scaning = false;
                    kind = Token_Kind.TK_ParDerecho;
                    break;


                case RigthLlave:
                    scaning = false;
                    kind = Token_Kind.TK_Llavederecha;
                    break;

                case LeftLlave:
                    scaning = false;
                    kind = Token_Kind.TK_LlaveIzquierda;
                    break;

                case Sum:
                    scaning = false;
                    kind = Token_Kind.TK_Suma;
                    break;

                case Multiplication:
                    scaning = false;
                    kind = Token_Kind.TK_Multiplicacion;
                    break;

                case Assignment:
                    scaning = false;
                    kind = Token_Kind.TK_Asignacion;
                    break;

                case Vacio:
                    scaning = false;
                    kind = Token_Kind.TK_Vacio;
                    break;

                case BeginOr:
                    scaning = false;
                    kind = Token_Kind.TK_OR;
                    break;

                case Error:
                    scaning = false;
                    kind = Token_Kind.Error;
                    break;

                case EOT:
                    scaning = false;
                    kind = Token_Kind.EOT;
                    break;
            }
        }
        int line = sourceStream.getCurrentLine();
        Source_Position position = new Source_Position(sourceStream.getCurrentPosition() - lexeme.length(), sourceStream.getCurrentPosition() - 1, line);

        if (kind == Token_Kind.Error) {
            errorReporter.add(new LexicalError(position, "Unexpected character '" + lexeme + "'"));
            output.add(new Token(Token_Kind.Error, lexeme, position));
            return scan();
        }
        Token currentToken;
        if (kind == Token_Kind.TK_id) {

            kind = defineToken_Kind(lexeme);
        }
        if (kind == Token_Kind.TK_id || kind == Token_Kind.Cadena) {
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

