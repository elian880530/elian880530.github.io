package AnalizadorSintactico;

/**
 *
 * @author EGH
 */
import AnalizadorLexico.*;
import Compilador.EasyTypes;
import Compilador.SourcePosition;
import AbstractSyntaxTrees.*;
import Errores.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

    private Scanner scanner;
    private ErrorReporter errorReporter;
    private Token currentToken;

    private void ReportSyntacticError(String text) {
        errorReporter.add(new SyntacticError(currentToken.getPosition(), text));
    }

    private void Match(TokenKind tokenExpected) throws IOException {
        if (currentToken.getKind() == tokenExpected) {
            AcceptIt();
        } else {
            ReportSyntacticError(tokenExpected.toString() + " tokenExpected");
        }
    }

    private void AcceptIt() throws IOException {
        try {
            currentToken = scanner.nextToken();
        } catch (Exception ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Parser(Scanner scanner, ErrorReporter errorReporter) {
        this.scanner = scanner;
        this.errorReporter = errorReporter;
    }


    /*  Gramar
     *
     * <program> ::= program id; <var> <body>
     * <var> ::= [var <declarations>]
     * <declarations> ::= [<declaration> <declarations>]
     * <declaration> ::= <variables> : <type>;
     * <variables> ::= id <others_variables>
     * <others_variables> ::= [, <variables>]
     * <type> ::= int
     * <body> ::= <instructions_block>
     * <instructions_block> :== begin <instructions_list> end
     * <instructions_list> ::= [<single_instruction> <instructions_list>]
     * <single_instruction> ::= <print> | <not> | <assignment>;
     * <print> ::= print id;
     * <not> ::= ! id;
     * <assignment> ::= id = <expression>;



     * <expression> ::= <single_expression><others_singles_expressions>
     * <others_singles_expressions> ::= [<operators_level_0> <single_expression> <others_singles_expressions>]
     * <operators_level_0> ::= > | < | >= | <= | != | ==
     * <single_expression> ::= <term> <others_terms>
     * <others_terms> ::= [<operators_level_1> <term> <others_terms>]
     * <operators_level_1> ::= + | - | "||"
     * <term> ::= <factor> <others_factors>
     * <others_factors> ::= [<operators_level_2> <factor> <others_factors>]
     * <operators_level_2> ::= * | / | % | &&
     * <factor> ::= int_literal | float_literal | <bool_literal> | id | ! <factor> | <signo> <factor> | (<expression>)
     * <bool_literal> ::= true | false
     * <signo> ::= + | -
     * <if_then_else> ::= if <expression> then <instructions>[else <instructions>]
     * <instructions> ::= <instructions_block> | <single_instruction>
     * <while_do> ::= while <expression> do <instructions>
     *
     */


    public AST Parse() {
        AST program = null;
        try {
            currentToken = scanner.nextToken();
            program = ParseProgram();
            if (currentToken.getKind() != TokenKind.EOT) {
                ReportSyntacticError(currentToken.getLexeme() + " not expected after end of program");
                currentToken = scanner.nextToken();
            }

        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return program;

    }

    private AST ParseProgram() {

         //<program> ::= program id <var> <body>

        List<SingleInstruction> instructions = null;
        SourcePosition position = null;
        try {
            Match(TokenKind.Program);
            position = currentToken.getPosition();
            Match(TokenKind.Id);
            instructions = new LinkedList<SingleInstruction>();

            List<VarDeclaration> varDeclarations = ParseVar();
            ListIterator<VarDeclaration> it = varDeclarations.listIterator();

            while (it.hasNext()) {
                instructions.add(it.next());
            }

            List<SingleInstruction> addInstructions = ParseBody().Instructions();
            instructions.addAll(addInstructions);

        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Program(instructions, position);
    }

    private List<VarDeclaration> ParseVar() {

         //<var> ::= [var <declarations>]

        try {
            if (currentToken.getKind() == TokenKind.Var) {
                AcceptIt();
            }
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ParseDeclarations();

    }
    VarType type;

    private List<VarDeclaration> ParseDeclarations() {

        //<declarations> ::= [<declaration> <declarations>]

        List<VarDeclaration> varDeclarations = new LinkedList<VarDeclaration>();
        type = ParseType();
        varDeclarations.addAll(ParseDeclaration());
        if (currentToken.getKind() != TokenKind.Begin) {
            varDeclarations.addAll(ParseDeclarations());
        }

        return varDeclarations;
    }

    private List<VarDeclaration> ParseDeclaration() {

        //<declaration> ::= <variables> : <type>;

        List<VarDeclaration> varDeclarations = new LinkedList<VarDeclaration>();
        List<IdentifierDeclaration> indentifiers;
        try {
            indentifiers = ParseVariables();
            if (type != null) {
                ListIterator<IdentifierDeclaration> it = indentifiers.listIterator();
                while (it.hasNext()) {
                    IdentifierDeclaration idDec = it.next();
                    varDeclarations.add(new VarDeclaration(idDec, type, idDec.Position()));
                }
            }
            Match(TokenKind.SemiColon);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return varDeclarations;
    }

    private VarType ParseType() {

        //<type> ::=  binaryLiteral

        VarType typee;
        try {
            if (IsType(currentToken.getKind())) {
                typee = new VarType(GetType(currentToken.getKind()), currentToken.getPosition());
                AcceptIt();
                return typee;
            } else {
                ReportSyntacticError("Type expected");
            }
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    private EasyTypes GetType(TokenKind tokenKind) {
        switch (tokenKind) {
            case Bin:
                return EasyTypes.BinariLiteral;
            case binaryLiteral:
                return EasyTypes.BinariLiteral;
            default:
                return EasyTypes.Undefined;
        }
    }

    private boolean IsType(TokenKind tokenKind) {
        return tokenKind == TokenKind.Bin;
    }

    private List<IdentifierDeclaration> ParseVariables() {

         //<variables> ::= id <others_variables>

        List<IdentifierDeclaration> indentifiers = null;
        try {
            indentifiers = new LinkedList<IdentifierDeclaration>();
            if (currentToken.getKind() == TokenKind.Id) {
                indentifiers.add(new IdentifierDeclaration(currentToken.getLexeme(), currentToken.getEntry(), currentToken.getPosition()));
            }
            Match(TokenKind.Id);
            indentifiers.addAll(ParseOthersVariables());

        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return indentifiers;
    }

    private List<IdentifierDeclaration> ParseOthersVariables() {

        //<others_variables> ::= [, <variables>]

        try {
            if (currentToken.getKind() == TokenKind.Comma) {
                Match(TokenKind.Comma);
                return ParseVariables();
            }
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new LinkedList<IdentifierDeclaration>();
    }

    private InstructionBlock ParseBody() {

         //<body> ::= <instructions_block>

        return ParseInstructionsBlock();
    }

    private InstructionBlock ParseInstructionsBlock() {

        //<instructions_block> :== begin <instructions_list> end

        SourcePosition position = null;
        List<SingleInstruction> instructions = null;
        try {
            position = currentToken.getPosition();
            Match(TokenKind.Begin);
            instructions = ParseInstructionsList();
            Match(TokenKind.End);

        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new InstructionBlock(instructions, position);
    }

    private List<SingleInstruction> ParseInstructionsList() {

        //<instructions_list> ::= [<single_instruction> <instructions_list>]

        List<SingleInstruction> instructions = new LinkedList<SingleInstruction>();
        if (currentToken.getKind() == TokenKind.print
                || currentToken.getKind() == TokenKind.Id) {
            instructions.add(ParseSingleInstruction());
            instructions.addAll(ParseInstructionsList());
        }
        return instructions;
    }

    private SingleInstruction ParseSingleInstruction() {

        //<single_instruction> ::= <print> | <not> | <assignment>;

        switch (currentToken.getKind()) {
            case print:
                return ParsePrint();
            case Not:
                return ParseNot();
            case Id:
                return ParseAssigment();
            default:
                return new EmptyInstruction(currentToken.getPosition());
        }
    }

    private SingleInstruction ParsePrint() {

        //<print> ::= print id;

        Identifier identifier = null;
        SourcePosition position = null;
        try {
            position = currentToken.getPosition();
            Match(TokenKind.print);
            identifier = new IdentifierValue(currentToken.getLexeme(), currentToken.getEntry(), currentToken.getPosition());
            Match(TokenKind.Id);
            Match(TokenKind.SemiColon);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Print(identifier, position);
    }

    private SingleInstruction ParseNot() {

        //<not> ::= ! id;

        Identifier identifier = null;
        SourcePosition position = null;
        try {
            position = currentToken.getPosition();
            Match(TokenKind.Not);
            identifier = new IdentifierReference(currentToken.getLexeme(), currentToken.getEntry(), currentToken.getPosition());
            Match(TokenKind.Id);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Read(identifier, position);
    }

    private SingleInstruction ParseAssigment() {

        //<assignment> ::= id = <expression>;

        Identifier identifier = null;
        SourcePosition position = null;
        Expression expression = null;
        try {
            identifier = new IdentifierReference(currentToken.getLexeme(), currentToken.getEntry(), currentToken.getPosition());
            Match(TokenKind.Id);
            position = currentToken.getPosition();
            Match(TokenKind.Assignment);
            expression = ParseExpression();
            Match(TokenKind.SemiColon);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Assigment(identifier, expression, position);
    }

    private Expression ParseExpression() {


        //<expresion> ::= binaryLiteral <AfterBinaryL_Id> | id <AfterBinaryL_Id> | ! <AfterNot> | ( <expresion> ) <AfterBinaryL_Id> ;

        SourcePosition position = currentToken.getPosition();
        Expression factor = null;
        switch (currentToken.getKind()) {
            case binaryLiteral:
                try {
                    factor = new binaryLiteral(currentToken.getLexeme(), currentToken.getEntry(), position);
                    AcceptIt();
                    return AfterBinaryL_Id(factor);
                } catch (IOException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case Id:
                try {
                    factor = new IdentifierValue(currentToken.getLexeme(), currentToken.getEntry(), position);
                    AcceptIt();
                   
                    return AfterBinaryL_Id(factor);

                } catch (IOException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case Not:
                try {
                    AcceptIt();
                   
                    return AfterNot(position);

                } catch (IOException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
            case LeftParen:
                try {
                    AcceptIt();
                    factor = ParseExpression();
                    Match(TokenKind.RigthParen);
                    if (currentToken.getKind() != TokenKind.SemiColon) {
                        return AfterBinaryL_Id(factor);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
            default:
                return factor;
        }
        return factor;
    }

    private void ParseSingleExpression() {

        //<single_expression> ::= <term> <others_terms>

        ParseTerm();
        ParseOthersTerms();
    }

    private void ParseOthersTerms() {

         //<others_terms> ::= [<operators_level_1> <single_expression>]
        //<operators_level_1> ::= -> | <-> | "|"

        try {
            TokenKind kind = currentToken.getKind();
            if (kind == TokenKind.Implicacion
                    || kind == TokenKind.Equivalencia
                    || kind == TokenKind.Or) {
                Match(kind);
                ParseSingleExpression();
            }
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ParseTerm() {

        //<term> ::= <factor> <others_factors>

        ParseFactor();
        ParseOthersFactors();
    }

    private void ParseOthersFactors() {

        //<others_factors> ::= [<operators_level_2> <term>]
        //<operators_level_2> ::= &

        TokenKind kind = currentToken.getKind();
        if (kind == TokenKind.And) {
            try {
                Match(kind);
            } catch (IOException ex) {
                Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
            }
            ParseTerm();
        }
    }

    private void ParseFactor() {

         //<factor> ::= binaryLiteral | Id | ! | (<expression>)
        //<bool_literal> ::= true | false
        //<sign> ::= + | -

        switch (currentToken.getKind()) {
            case binaryLiteral:
                try {
                    AcceptIt();
                } catch (IOException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case Id:
                try {
                    AcceptIt();
                } catch (IOException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case Not:
                try {
                    AcceptIt();
                    break;
                } catch (IOException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
            case LeftParen:
                try {
                    AcceptIt();
                    ParseExpression();
                    Match(TokenKind.RigthParen);
                    break;
                } catch (IOException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
            default:
                ReportSyntacticError("Wrong expression");
                break;
        }
    }

    private Expression AfterBinaryL_Id(Expression factor) throws IOException {

        SourcePosition position = currentToken.getPosition();
        switch (currentToken.getKind()) {
            case And:
                try {
                    AcceptIt();
                } catch (IOException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
                return new And(factor, AfterFactor(), position);
            case Or:
                try {
                    AcceptIt();
                } catch (IOException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
                return new Or(factor, AfterFactor(), position);
            case Implicacion:
                try {
                    AcceptIt();
                } catch (IOException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
                return new Implicacion(factor, AfterFactor(), position);
            case Equivalencia:
                try {
                    AcceptIt();
                } catch (IOException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
                return new Equivalencia(factor, AfterFactor(), position);
            default:
                return factor;
        }
    }

    private Expression AfterNot(SourcePosition positionot) throws IOException {

        SourcePosition position = currentToken.getPosition();
        Expression factor = null;
        if (currentToken.getKind() == TokenKind.Id) {
            factor = new Not(new IdentifierValue(currentToken.getLexeme(), currentToken.getEntry(), position), positionot);
            AcceptIt();
            if (currentToken.getKind() != TokenKind.SemiColon) {
                return AfterBinaryL_Id(factor);
            } else {
                return factor;
            }
        } else {
            return new Not(ParseExpression(), position);
        }
    }

    


    private Expression AfterFactor() throws IOException {

        SourcePosition position = currentToken.getPosition();
        Expression factor = null;
        if (currentToken.getKind() == TokenKind.Id) {
            factor = new IdentifierValue(currentToken.getLexeme(), currentToken.getEntry(), position);
            AcceptIt();
        } else if (currentToken.getKind() == TokenKind.Not) {
            AcceptIt();
            factor = new Not(ParseExpression(), position);
            if (currentToken.getKind() != TokenKind.Id) {
                Match(TokenKind.Id);
            } else {
                factor = new IdentifierValue(currentToken.getLexeme(), currentToken.getEntry(), position);
                AcceptIt();
            }
        } else if (currentToken.getKind() == TokenKind.LeftParen) {
            factor = ParseExpression();
        }
        else if (currentToken.getKind() == TokenKind.binaryLiteral) {
            factor = new binaryLiteral(currentToken.getLexeme(), currentToken.getEntry(), position);
            AcceptIt();
        }

        else {
            Match(TokenKind.Id);
        }

        return factor;
    }

    
}
