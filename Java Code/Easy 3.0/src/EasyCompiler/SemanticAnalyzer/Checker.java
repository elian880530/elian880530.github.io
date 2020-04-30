
package EasyCompiler.SemanticAnalyzer;

import EasyCompiler.AbstractSyntaxTrees.*;
import EasyCompiler.EasyTypes;
import EasyCompiler.LexicalAnalyzer.*;
import EasyCompiler.Errors.*;
import EasyCompiler.SymbolsTable.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Checker implements Visitor
{
    private SymbolsTable symbolsTable;
    private ErrorReporter errorReporter;

    public Checker(SymbolsTable symbolsTable, ErrorReporter errorReporter)
    {
        this.symbolsTable = symbolsTable;
        this.errorReporter = errorReporter;
    }

    //Visitor Members

    public Object VisitProgram(Program program, Object arg)
    {
        ListIterator<SingleInstruction> it = program.Instructions().listIterator();        
        while (it.hasNext())        
            it.next().Visit(this, null);
        return null;
    }

    public Object VisitIdentifierDeclaration(IdentifierDeclaration identifierDeclaration, Object arg)
    {
        SymbolInfo info = symbolsTable.entry(identifierDeclaration.Entry());
        if (info.getDeclared())
            errorReporter.add(new CompilerError(identifierDeclaration.Position(), "A variable named '{0}' is already defined" + info.getLexeme()));
        info.setDeclared( true);
        info.setType( (EasyTypes)arg );
        return null;
    }

    public Object VisitVarType(VarType type, Object arg)
    {
        return type.EasyType();
    }

    public Object VisitVarDeclaration(VarDeclaration varDeclaration, Object arg)
    {
        varDeclaration.Identifier().Visit(this, varDeclaration.Type().Visit(this, null));
        return null;
    }

    public Object VisitEmptyInstruction(EmptyInstruction emptyInstruction, Object arg)
    {
        return null;
    }

    public Object VisitRead(Read read, Object arg)
    {
        EasyTypes typeIdentifier = (EasyTypes)read.Identifier().Visit(this, arg);
        if(typeIdentifier == EasyTypes.Bool)
            errorReporter.add(new CompilerError(read.Position(), "Cannot read a 'bool' value"));
        read.setOperandsType( typeIdentifier );
        return null;
    }

    public Object VisitWrite(Write write, Object arg)
    {
        EasyTypes expressionType = (EasyTypes)write.Expression().Visit(this, null);
        write.setOperandsType( expressionType );
        return null;
    }

    public Object VisitAssigment(Assigment assigment, Object arg)
    {
        EasyTypes idType = (EasyTypes)assigment.Identifier().Visit(this, null);
        EasyTypes expressionType = (EasyTypes)assigment.Expression().Visit(this, null);
        if ((idType != expressionType))
        {
            Object[] arg1 = new String[2];            
            arg1[0] = idType.toString().toLowerCase();
            arg1[1] = expressionType.toString().toLowerCase();
            errorReporter.add(new CompilerError(assigment.Position(), String.format("Cannot convert type '{%s}' to '{%s}'" , arg1 )));            
        }    
        return null;
    }

    public Object VisitIdentifierReference(IdentifierReference identifierReference, Object arg)
    {
        SymbolInfo info = symbolsTable.entry(identifierReference.Entry());
        if (!info.getDeclared())
            errorReporter.add(new CompilerError(identifierReference.Position(), "The name '{0}' does not exist" + identifierReference.Lexeme()));
        return info.getType();
    }

    public Object VisitGreaterThan(GreaterThan greaterThan, Object arg)
    {
        EasyTypes type1 = (EasyTypes)greaterThan.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)greaterThan.Arg2().Visit(this, null);
        if ((type1 != type2) || (type1 != EasyTypes.Int && type1 != EasyTypes.Float))
        {
            Object[] arg1 = new String[2];
            arg1[0] = type1.toString().toLowerCase();
            arg1[1] = type2.toString().toLowerCase();
            errorReporter.add(new CompilerError(greaterThan.Position(), String.format("Este: Operator '>' cannot be applied to operands of type '{%s}' and '{%s}'" , arg1 )));
        }    
        greaterThan.setOperandsType( type1 );
        return EasyTypes.Bool;
    }

    public Object VisitGreaterThanOrEqual(GreaterThanOrEqual greaterThanOrEqual, Object arg)
    {
        EasyTypes type1 = (EasyTypes)greaterThanOrEqual.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)greaterThanOrEqual.Arg2().Visit(this, null);
        if ((type1 != type2) || (type1 != EasyTypes.Int && type1 != EasyTypes.Float))
            errorReporter.add(new CompilerError(greaterThanOrEqual.Position(), "Operator '>=' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        greaterThanOrEqual.setOperandsType( type1 );
        return EasyTypes.Bool;
    }

    public Object VisitLessThan(LessThan lessThan, Object arg)
    {
        EasyTypes type1 = (EasyTypes)lessThan.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)lessThan.Arg2().Visit(this, null);
        if ((type1 != type2) || (type1 != EasyTypes.Int && type1 != EasyTypes.Float))
            errorReporter.add(new CompilerError(lessThan.Position(), "Operator '<' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        lessThan.setOperandsType( type1 );
        return EasyTypes.Bool;
    }

    public Object VisitLessThanOrEqual(LessThanOrEqual lessThanOrEqual, Object arg)
    {
        EasyTypes type1 = (EasyTypes)lessThanOrEqual.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)lessThanOrEqual.Arg2().Visit(this, null);
        if ((type1 != type2) || (type1 != EasyTypes.Int && type1 != EasyTypes.Float))
            errorReporter.add(new CompilerError(lessThanOrEqual.Position(), "Operator '<=' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        lessThanOrEqual.setOperandsType(type1);
        return EasyTypes.Bool;
    }

    public Object VisitInequality(Inequality inequality, Object arg)
    {
        EasyTypes type1 = (EasyTypes)inequality.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)inequality.Arg2().Visit(this, null);
        if ((type1 != type2))
            errorReporter.add(new CompilerError(inequality.Position(), "Operator '!=' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        inequality.setOperandsType( type1 );
        return EasyTypes.Bool;
    }

    public Object VisitEquality(Equality equality, Object arg)
    {
        EasyTypes type1 = (EasyTypes)equality.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)equality.Arg2().Visit(this, null);
        if (type1 != type2)
            errorReporter.add(new CompilerError(equality.Position(), "Operator '==' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        equality.setOperandsType( type1 );
        return EasyTypes.Bool;
    }

    public Object VisitSum(Sum sum, Object arg)
    {
        EasyTypes type1 = (EasyTypes)sum.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)sum.Arg2().Visit(this, null);
        if ((type1 != type2) || (type1 != EasyTypes.Int && type1 != EasyTypes.Float))
            errorReporter.add(new CompilerError(sum.Position(), "Operator '+' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        sum.setOperandsType(type1);
        return type1;
    }

    public Object VisitMinus(Minus minus, Object arg)
    {
        EasyTypes type1 = (EasyTypes)minus.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)minus.Arg2().Visit(this, null);
        if ((type1 != type2) || (type1 != EasyTypes.Int && type1 != EasyTypes.Float))
            errorReporter.add(new CompilerError(minus.Position(), "Operator '-' cannot be applied to operands of type '{0}' and '{1}'"+ type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        minus.setOperandsType( type1 );
        return type1;
    }

    public Object VisitOr(Or or, Object arg)
    {
        EasyTypes type1 = (EasyTypes)or.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)or.Arg2().Visit(this, null);
        if (type1 != EasyTypes.Bool || type2 != EasyTypes.Bool)
            errorReporter.add(new CompilerError(or.Position(), "Operator '||' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        or.setOperandsType( EasyTypes.Bool );
        return EasyTypes.Bool;
    }

    public Object VisitMultiplication(Multiplication multiplication, Object arg)
    {
        EasyTypes type1 = (EasyTypes)multiplication.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)multiplication.Arg2().Visit(this, null);
        if ((type1 != type2) || (type1 != EasyTypes.Int && type1 != EasyTypes.Float))
            errorReporter.add(new CompilerError(multiplication.Position(), "Operator '*' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        multiplication.setOperandsType( type1 );
        return type1;
    }

    public Object VisitDivision(Division division, Object arg)
    {
        EasyTypes type1 = (EasyTypes)division.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)division.Arg2().Visit(this, null);
        if ((type1 != type2) || (type1 != EasyTypes.Int && type1 != EasyTypes.Float))
            errorReporter.add(new CompilerError(division.Position(), "Operator '/' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        division.setOperandsType( type1 );
        return type1;
    }

    public Object VisitModulus(Modulus modulus, Object arg)
    {
        EasyTypes type1 = (EasyTypes)modulus.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)modulus.Arg2().Visit(this, null);
        if (type1 != EasyTypes.Int || type2 != EasyTypes.Int)
            errorReporter.add(new CompilerError(modulus.Position(), "Operator '%' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        modulus.setOperandsType(  EasyTypes.Int );
        return EasyTypes.Int;
    }

    public Object VisitAnd(And and, Object arg)
    {
        EasyTypes type1 = (EasyTypes)and.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes)and.Arg2().Visit(this, null);
        if (type1 != EasyTypes.Bool || type2 != EasyTypes.Bool)
            errorReporter.add(new CompilerError(and.Position(), "Operator '&&' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        and.setOperandsType( EasyTypes.Bool );
        return EasyTypes.Bool;
    }

    public Object VisitIdentifierValue(IdentifierValue identifierValue, Object arg)
    {
        SymbolInfo info = symbolsTable.entry(identifierValue.Entry());
        if (!info.getDeclared())
            errorReporter.add(new CompilerError(identifierValue.Position(), "The name '{0}' does not exist" + identifierValue.Lexeme()));
        return info.getType();
    }

    public Object VisitIntLireral(IntLiteral intLireral, Object arg)
    {
        return EasyTypes.Int;
    }

    public Object VisitFloatLiteral(FloatLiteral floatLiteral, Object arg)
    {
        return EasyTypes.Float;
    }

    public Object VisitBoolLiteral(BoolLiteral boolLiteral, Object arg)
    {
        return EasyTypes.Bool;
    }

    public Object VisitNot(Not not, Object arg)
    {
        EasyTypes type = (EasyTypes)not.Expression().Visit(this, null);
        if (type != EasyTypes.Bool)
            errorReporter.add(new CompilerError(not.Position(),"Operator '!' cannot be applied to operand of type '{0}'" + type.toString().toLowerCase()));
        not.setOperandsType( EasyTypes.Bool );
        return EasyTypes.Bool;
    }

    public Object VisitSingMinus(SingMinus singMinus, Object arg)
    {
        EasyTypes type = (EasyTypes)singMinus.Expression().Visit(this, null);
        if (type != EasyTypes.Int || type != EasyTypes.Float)
            errorReporter.add(new CompilerError(singMinus.Position(), "Operator '-' cannot be applied to operand of type '{0}'" + type.toString().toLowerCase()));
        singMinus.setOperandsType( type );
        return type;
    }

    public Object VisitSingPlus(SingPlus singPlus, Object arg)
    {
        EasyTypes type = (EasyTypes)singPlus.Expression().Visit(this, null);
        if (type != EasyTypes.Int || type != EasyTypes.Float)
            errorReporter.add(new CompilerError(singPlus.Position(), "Operator '+' cannot be applied to operand of type '{0}'" + type.toString().toLowerCase()));
        singPlus.setOperandsType( type );
        return type;
    }

    public Object VisitInstructionBlock(InstructionBlock instructionBlock, Object arg)
    {
        ListIterator<SingleInstruction> it = instructionBlock.Instructions().listIterator();
        while(it.hasNext())
            it.next().Visit(this, null);            
        return null;
    }

    public Object VisitIfThen(IfThen ifThen, Object arg)
    {
        EasyTypes expressionType = (EasyTypes)ifThen.Expression().Visit(this, null);
        if (expressionType != EasyTypes.Bool)
            errorReporter.add(new CompilerError(ifThen.Position(), "Cannot convert type '{0}' to 'bool'" + expressionType.toString().toLowerCase()));
        ifThen.Then().Visit(this, null);
        return null;
    }

    public Object VisitIfThenElse(IfThenElse ifThenElse, Object arg)
    {
        EasyTypes expressionType = (EasyTypes)ifThenElse.Expression().Visit(this, null);
        if (expressionType != EasyTypes.Bool)
            errorReporter.add(new CompilerError(ifThenElse.Position(), "Cannot convert type '{0}' to 'bool'" + expressionType.toString().toLowerCase()));
        ifThenElse.Then().Visit(this, null);
        ifThenElse.Else().Visit(this, null);
        return null;
    }

    public Object VisitWhile(While _while, Object arg)
    {
        EasyTypes expressionType = (EasyTypes)_while.Expression().Visit(this, null);
        if (expressionType != EasyTypes.Bool)
            errorReporter.add(new CompilerError(_while.Position(), "Cannot convert type '{0}' to 'bool'" + expressionType.toString().toLowerCase()));
        _while.Do().Visit(this, null);
        return null;
    }
}

