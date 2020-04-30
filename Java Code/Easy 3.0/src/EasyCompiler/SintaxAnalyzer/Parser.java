/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyCompiler.SintaxAnalyzer;

/**
 *
 * @author tomaso
 */

import EasyCompiler.AbstractSyntaxTrees.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import EasyCompiler.LexicalAnalyzer.*;
import EasyCompiler.Errors.*;
import EasyCompiler.*;
import java.io.*;

/* Easy Gramar in Backus–Naur Form (BNF)
     * 
     * <program> ::= program id; <var> <body>
     * <var> ::= [var <declarations>]
     * <declarations> ::= [<declaration> <declarations>]
     * <declaration> ::= <variables> : <type>;
     * <variables> ::= id <others_variables>
     * <others_variables> ::= [, <variables>]
     * <type> ::= int | float | bool
     * <body> ::= <instructions_block>
     * <instructions_block> :== begin <instructions_list> end
     * <instructions_list> ::= [<single_instruction> <instructions_list>]
     * <single_instruction> ::= <read> | <write> | <assignment> | <if_then_else> | <while_do> | ; 
     * <read> ::= read id;
     * <write> ::= write <expression>;
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

public class Parser {
    
    private Scanner scanner;
    private ErrorReporter errorReporter;
    private Token currentToken;
    
    private void ReportSyntacticError(String text) 
    {
        errorReporter.add(new SyntacticError(currentToken.getPosition(), text));
    }
    
    private void Match(TokenKind tokenExpected)
    {
        if(currentToken.getKind() == tokenExpected)
            AcceptIt();
        else
            ReportSyntacticError(tokenExpected.toString() + " tokenExpected");
    }
    
    private void AcceptIt()
    {
        currentToken = scanner.scan();
    }
    
    public Parser(Scanner scanner, ErrorReporter errorReporter)
    {
        this.scanner = scanner;
        this.errorReporter = errorReporter;
    }
    
    public AST Parse()
    {
        currentToken = scanner.scan();
        AST program = ParseProgram();      
        
        if(currentToken.getKind() != TokenKind.EOT)
        {
            ReportSyntacticError(currentToken.getLexeme() + " not expected after end of program");
            currentToken = scanner.scan();
        }  
        return program;
    }
    
    private AST ParseProgram()
    {
        //<program> ::= program id; <var> <body>
        Match(TokenKind.Program);
        SourcePosition position = currentToken.getPosition();
        Match(TokenKind.Id);
        Match(TokenKind.SemiColon);
        List<SingleInstruction> instructions = new LinkedList<SingleInstruction>();
        
        List<VarDeclaration> varDeclarations  = ParseVar();        
        ListIterator<VarDeclaration> it = varDeclarations.listIterator();
        
        while (it.hasNext())        
            instructions.add(it.next());        
        
        List<SingleInstruction> addInstructions = ParseBody().Instructions();
        instructions.addAll(addInstructions);
        return new Program(instructions, position);
    }

    private List<VarDeclaration> ParseVar() 
    {
        //<var> ::= [var <declarations>]
        if (currentToken.getKind() == TokenKind.Var) 
        {
            AcceptIt();
            return ParseDeclarations();
        }
        return new LinkedList<VarDeclaration>();
    }

    private List<VarDeclaration> ParseDeclarations()
    {
        //<declarations> ::= [<declaration> <declarations>]
        List<VarDeclaration> varDeclarations = new LinkedList<VarDeclaration>();
        if (currentToken.getKind() == TokenKind.Id) 
        {
            varDeclarations.addAll(ParseDeclaration());
            varDeclarations.addAll(ParseDeclarations());
        }
        return varDeclarations;
    }

    private List<VarDeclaration> ParseDeclaration()
    {
        //<declaration> ::= <variables> : <type>;
        List<VarDeclaration> varDeclarations = new LinkedList<VarDeclaration>();
        List<IdentifierDeclaration> indentifiers = ParseVariables();
        Match(TokenKind.Colon);
        VarType type = ParseType();
        if (type != null)
        {
            ListIterator<IdentifierDeclaration> it = indentifiers.listIterator();
            while (it.hasNext())
            {
                IdentifierDeclaration idDec = it.next();
                varDeclarations.add(new VarDeclaration(idDec, type, idDec.Position()));
            }
        } 
        Match(TokenKind.SemiColon);
        return varDeclarations;
    }

    private VarType ParseType() 
    {
        //<type> ::= int | float  | bool
        if (IsType(currentToken.getKind()))
        {
            VarType type = new VarType(GetType(currentToken.getKind()), currentToken.getPosition());
            AcceptIt();
            return type;
        }
        ReportSyntacticError("Type expected");        
        return null;
    }

    private EasyTypes GetType(TokenKind tokenKind)
    {
        switch (tokenKind)
        {
            case Bool: return EasyTypes.Bool;
            case Int: return EasyTypes.Int;
            case Float: return EasyTypes.Float;
            default: return EasyTypes.Undefined;
        }
    }

    private boolean IsType(TokenKind tokenKind)
    {
        return tokenKind == TokenKind.Int || tokenKind == TokenKind.Float || tokenKind == TokenKind.Bool;
    }

    private List<IdentifierDeclaration> ParseVariables()
    {
        //<variables> ::= id <others_variables>
        List<IdentifierDeclaration> indentifiers = new LinkedList<IdentifierDeclaration>();
        if(currentToken.getKind() == TokenKind.Id)
            indentifiers.add(new IdentifierDeclaration(currentToken.getLexeme(), currentToken.getEntry(), currentToken.getPosition()));
        Match(TokenKind.Id);
        indentifiers.addAll(ParseOthersVariables());
        return indentifiers;
    }

    private List<IdentifierDeclaration> ParseOthersVariables()
    {
        //<others_variables> ::= [, <variables>]
        if (currentToken.getKind() == TokenKind.Comma) 
        {
            AcceptIt();
            return ParseVariables();
        }
        return new LinkedList<IdentifierDeclaration>();
    }

    private InstructionBlock ParseBody()
    {
        //<body> ::= <instructions_block>
        return ParseInstructionsBlock();
    }

    private InstructionBlock ParseInstructionsBlock()
    {
        //<instructions_block> :== begin <instructions_list> end
        SourcePosition position = currentToken.getPosition();
        Match(TokenKind.Begin);
        List<SingleInstruction> instructions = ParseInstructionsList();
        Match(TokenKind.End);
        return new InstructionBlock(instructions, position);
    }

    private List<SingleInstruction> ParseInstructionsList()
    {
        //<instructions_list> ::= [<single_instruction> <instructions_list>]
        List<SingleInstruction> instructions = new LinkedList<SingleInstruction>();
        if (currentToken.getKind() == TokenKind.Write ||
            currentToken.getKind() == TokenKind.Read ||
            currentToken.getKind() == TokenKind.Id ||
            currentToken.getKind() == TokenKind.If ||
            currentToken.getKind() == TokenKind.While ||
            currentToken.getKind() == TokenKind.SemiColon)
        {
            instructions.add(ParseSingleInstruction());
            instructions.addAll(ParseInstructionsList());
        }
        return instructions;
    }

    private SingleInstruction ParseSingleInstruction()
    {
        //<single_instruction> ::= <read> | <write> | <assignment> | <if_then_else> | <while_do> | ; 
        switch (currentToken.getKind()) 
        {
            case Read:
                return ParseRead();
            case Write:
                return ParseWrite();
            case Id:
                return ParseAssigment();
            case If:
                return ParseIfThenElse();
            case While:
                return ParseWhileDo();
            case SemiColon:default:
                Match(TokenKind.SemiColon);
                return new EmptyInstruction(currentToken.getPosition());
        }
    }

    private SingleInstruction ParseRead()
    {
        //<read> ::= read id;
        SourcePosition position = currentToken.getPosition();
        Match(TokenKind.Read);
        if(currentToken.getKind() == TokenKind.Id)
        {
            Identifier identifier = new IdentifierReference(currentToken.getLexeme(), currentToken.getEntry(), currentToken.getPosition());
            AcceptIt();
            Match(TokenKind.SemiColon);
            return new Read(identifier, position);
        }
        ReportSyntacticError("Identifier expected");
        return null;
    }

    private SingleInstruction ParseWrite()
    {
        //<write> ::= write <expression>;
        SourcePosition position = currentToken.getPosition();
        Match(TokenKind.Write);
        Expression expression = ParseExpression();
        Match(TokenKind.SemiColon);
        return new Write(expression , position);
    }

    private SingleInstruction ParseAssigment()
    {
        //<assignment> ::= id = <expression>;
        Identifier identifier = new IdentifierReference(currentToken.getLexeme(), currentToken.getEntry(), currentToken.getPosition());
        AcceptIt();
        SourcePosition position = currentToken.getPosition();
        Match(TokenKind.Assignment);
        Expression expression = ParseExpression();
        Match(TokenKind.SemiColon);
        return new Assigment(identifier, expression,position);
    }

    private Expression ParseExpression()
    {
        //<expression> ::= <single_expression><others_singles_expressions>
        Expression singleExpression = ParseSingleExpression();
        return ParseOthersSinglesExpressions(singleExpression);

    }

    private Expression ParseOthersSinglesExpressions(Expression singleExpression)
    {
        //<others_singles_expressions> ::= [<operators_level_0> <expression>]
        //<operators_level_0> ::= > | < | >= | <= | != | ==
        SourcePosition position = currentToken.getPosition();
        switch (currentToken.getKind())
        {
            case GreaterThan:
                AcceptIt();
                return new GreaterThan(singleExpression, ParseExpression(), position);
            case GreaterThanOrEqual:
                AcceptIt();
                return new GreaterThanOrEqual(singleExpression, ParseExpression(), position);
            case LessThan:
                AcceptIt();
                return new LessThan(singleExpression, ParseExpression(), position);
            case LessThanOrEqual:
                AcceptIt();
                return new LessThanOrEqual(singleExpression, ParseExpression(), position);
            case Inequality:
                AcceptIt();
                return new Inequality(singleExpression, ParseExpression(), position);
            case Equality:
                AcceptIt();
                return new Equality(singleExpression, ParseExpression(), position);
            default:
                return singleExpression;
        }
    }

    private Expression ParseSingleExpression()
    {
        //<single_expression> ::= <term> <others_terms>
        Expression term = ParseTerm();
        return ParseOthersTerms(term);
    }

    private Expression ParseOthersTerms(Expression term)
    {
        //<others_terms> ::= [<operators_level_1> <single_expression>]
        //<operators_level_1> ::= + | - | "||"
        SourcePosition position = currentToken.getPosition();
        switch (currentToken.getKind())
        {
            case Sum:
                AcceptIt();
                return new Sum(term, ParseSingleExpression(), position);
            case Minus:
                AcceptIt();
                return new Minus(term, ParseSingleExpression(), position);
            case Or:
                AcceptIt();
                return new Or(term, ParseSingleExpression(), position);
            default:
                return term;
        }
    }

    private Expression ParseTerm()
    {
        //<term> ::= <factor> <others_factors>
        Expression factor = ParseFactor();
        return ParseOthersFactors(factor);
    }

    private Expression ParseOthersFactors(Expression factor)
    {
        //<others_factors> ::= [<operators_level_2> <term>]
        //<operators_level_2> ::= * | / | % | &&
        SourcePosition position = currentToken.getPosition();
        switch (currentToken.getKind())
        {
            case Multiplication:
                AcceptIt();
                return new Multiplication(factor, ParseTerm(), position);
            case Division:
                AcceptIt();
                return new Division(factor, ParseTerm(), position);
            case Modulus:
                AcceptIt();
                return new Modulus(factor, ParseTerm(), position);
            case And:
                AcceptIt();
                return new And(factor, ParseTerm(), position);
            default:
                return factor;
        }
    }

    private Expression ParseFactor()
    {
        //<factor> ::= int_literal | float_literal | <bool_literal> | id | ! <factor> | <sign> <factor> | (<expression>)
        //<bool_literal> ::= true | false
        //<sign> ::= + | -
        SourcePosition position = currentToken.getPosition();
        Expression factor;
        switch (currentToken.getKind())
        {
            case IntLiteral:
                factor = new IntLiteral(currentToken.getLexeme(), currentToken.getEntry(), position);
                AcceptIt();
                break;
            case FloatLiteral:
                factor = new FloatLiteral(currentToken.getLexeme(), currentToken.getEntry(), position);
                AcceptIt();
                break;
            case BoolLiteral:
                factor = new BoolLiteral(currentToken.getLexeme(), currentToken.getEntry(), position);
                AcceptIt();
                break;
            case Id:
                factor = new IdentifierValue(currentToken.getLexeme(), currentToken.getEntry(), position);
                AcceptIt();
                break;
            case Not:
                AcceptIt(); 
                factor = new Not(ParseFactor(), position);
                break;
            case Minus:
                AcceptIt();
                factor = new SingMinus(ParseFactor(), position);
                break;
            case Sum:
                AcceptIt();
                factor = new SingPlus(ParseFactor(), position);
                break;
            case LeftParen:
                AcceptIt();
                factor = ParseExpression();
                Match(TokenKind.RigthParen);
                break;
            default:
                ReportSyntacticError("Wrong expression");
                factor = null;
                break;
        }
        return factor;
    }

    private SingleInstruction ParseIfThenElse()
    {
        //<if_then_else> ::= if <expression> then <instructions>[else <instructions>]
        SourcePosition position = currentToken.getPosition();
        Match(TokenKind.If);
        Expression expression = ParseExpression();
        Match(TokenKind.Then);
        Instruction instructionThen = ParseInstructions();
        if (currentToken.getKind() == TokenKind.Else) 
        {
            AcceptIt();
            Instruction instructionElse = ParseInstructions();
            return new IfThenElse(expression, instructionThen, instructionElse, position);
        }
        return new IfThen(expression, instructionThen, position);
    }

    private Instruction ParseInstructions()
    {
        //<instructions> ::= <instructions_block> | <single_instruction>
        if (currentToken.getKind() == TokenKind.Begin)
            return ParseInstructionsBlock();
        return ParseSingleInstruction();
    }

    private SingleInstruction ParseWhileDo()
    {
        //<while_do> ::= while <expression> do <instructions>
        SourcePosition position = currentToken.getPosition();
        Match(TokenKind.While);
        Expression expression = ParseExpression();
        Match(TokenKind.Do);
        Instruction instruction = ParseInstructions();
        return new While(expression, instruction, position);
    }
}
