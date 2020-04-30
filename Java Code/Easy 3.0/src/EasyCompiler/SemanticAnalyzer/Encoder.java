package EasyCompiler.SemanticAnalyzer;

import EasyCompiler.AbstractSyntaxTrees.*;
import EasyCompiler.EasyTypes;
import EasyCompiler.LexicalAnalyzer.*;
import EasyCompiler.Errors.*;
import EasyCompiler.Runtime.*;
import EasyCompiler.SymbolsTable.*;
import EasyCompiler.SymbolsTable.SymbolsTable;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Encoder implements Visitor
{
    private List<RuntimeEntity> code;

    public List<RuntimeEntity> Code()
    {
        return code; 
    }

    private SymbolsTable symbolsTable;

    public Encoder(SymbolsTable symbolsTable)
    {
        code = new LinkedList<RuntimeEntity>();
        this.symbolsTable = symbolsTable;
    }

    private void MemoryManager()
    {
        int address = 0;
        code.add(new RuntimeMemorySize());
        code.add(new IntValue(0));
        int memSizePos = code.size() - 1;
        for (int i = 0; i < symbolsTable.Count(); i++)
        {
            if (symbolsTable.entry(i).getKind() == TokenKind.Id && symbolsTable.entry(i).getDeclared())
                symbolsTable.entry(i).setAddress( address++ );
            else if (symbolsTable.entry(i).getKind() == TokenKind.IntLiteral)
            {
                code.add(new RuntimeNewInt());
                symbolsTable.entry(i).setAddress( address++ );
                code.add(new Address(symbolsTable.entry(i).getAddress()));
                code.add(new RuntimePush());
                code.add(new Address(symbolsTable.entry(i).getAddress()));
                code.add(new RuntimePush());
                code.add(new IntValue(Integer.parseInt(symbolsTable.entry(i).getLexeme())));
                code.add(new RuntimeStore());
            }
            else if (symbolsTable.entry(i).getKind() == TokenKind.FloatLiteral)
            {
                code.add(new RuntimeNewFloat());
                symbolsTable.entry(i).setAddress( address++ );
                code.add(new Address(symbolsTable.entry(i).getAddress()));
                code.add(new RuntimePush());
                code.add(new Address(symbolsTable.entry(i).getAddress()));
                code.add(new RuntimePush());
                code.add(new FloatValue(Float.parseFloat(symbolsTable.entry(i).getLexeme())));
                code.add(new RuntimeStore());
            }
            else if (symbolsTable.entry(i).getKind() == TokenKind.BoolLiteral)
            {
                code.add(new RuntimeNewBool());
                symbolsTable.entry(i).setAddress( address++ );
                code.add(new Address(symbolsTable.entry(i).getAddress()));
                code.add(new RuntimePush());
                code.add(new Address(symbolsTable.entry(i).getAddress()));
                code.add(new RuntimePush());
                code.add(new BoolValue(Boolean.parseBoolean(symbolsTable.entry(i).getLexeme())));
                code.add(new RuntimeStore());
            }
        }
        code.set(memSizePos, new IntValue(address));
    }

    //region IVisitor Members

    public Object VisitProgram(Program program, Object arg)
    {
        MemoryManager();        
        ListIterator<SingleInstruction> it = program.Instructions().listIterator();
        while(it.hasNext())
            it.next().Visit(this, null);            
        code.add(new RuntimeHalt());
        return null;
    }

    public Object VisitIdentifierDeclaration(IdentifierDeclaration identifierDeclaration, Object arg)
    {
        SymbolInfo info = symbolsTable.entry(identifierDeclaration.Entry());
        code.add(new Address(info.getAddress()));
        return null;
    }

    public Object VisitVarType(VarType type, Object arg)
    {
        switch (type.EasyType()) 
        {
            case Int:
                code.add(new RuntimeNewInt());
                break;
            case Float:
                code.add(new RuntimeNewFloat());
                break;
            case Bool:
                code.add(new RuntimeNewBool());
                break;
        }
        return null;
    }

    public Object VisitVarDeclaration(VarDeclaration varDeclaration, Object arg)
    {
        varDeclaration.Type().Visit(this, null);
        varDeclaration.Identifier().Visit(this, null);
        return null;
    }

    public Object VisitEmptyInstruction(EmptyInstruction emptyInstruction, Object arg)
    {
        return null;
    }

    public Object VisitRead(Read read, Object arg)
    {
        read.Identifier().Visit(this, null);
        switch (read.OperandsType())
        {
            case Int:
                code.add(new RuntimeReadInt());
                break;
            case Float:
                code.add(new RuntimeReadFloat());
                break;
        }
        return null;
    }

    public Object VisitWrite(Write write, Object arg)
    {
        write.Expression().Visit(this, null);
        code.add(new RuntimeWrite());
        return null;
    }

    public Object VisitAssigment(Assigment assigment, Object arg)
    {
        assigment.Identifier().Visit(this, null);
        assigment.Expression().Visit(this, null);
        code.add(new RuntimeStore());
        return null;
    }

    public Object VisitIdentifierReference(IdentifierReference identifierReference, Object arg)
    {
        SymbolInfo info = symbolsTable.entry(identifierReference.Entry());
        code.add(new RuntimePush());
        code.add(new Address(info.getAddress()));
        return null;
    }

    public Object VisitGreaterThan(GreaterThan greaterThan, Object arg)
    {
        greaterThan.Arg1().Visit(this, null);
        greaterThan.Arg2().Visit(this, null);
        if (greaterThan.OperandsType() == EasyTypes.Int)
            code.add(new RuntimeGreaterThanInt());
        else
            code.add(new RuntimeGreaterThanFloat());
        return null;
    }

    public Object VisitGreaterThanOrEqual(GreaterThanOrEqual greaterThanOrEqual, Object arg)
    {
        greaterThanOrEqual.Arg1().Visit(this, null);
        greaterThanOrEqual.Arg2().Visit(this, null);
        if (greaterThanOrEqual.OperandsType() == EasyTypes.Int)
            code.add(new RuntimeGreaterThanOrEqualInt());
        else
            code.add(new RuntimeGreaterThanOrEqualFloat());
        return null;
    }

    public Object VisitLessThan(LessThan lessThan, Object arg)
    {
        lessThan.Arg1().Visit(this, null);
        lessThan.Arg2().Visit(this, null);
        if (lessThan.OperandsType() == EasyTypes.Int)
            code.add(new RuntimeLessThanInt());
        else
            code.add(new RuntimeLessThanFloat());
        return null;
    }

    public Object VisitLessThanOrEqual(LessThanOrEqual lessThanOrEqual, Object arg)
    {
        lessThanOrEqual.Arg1().Visit(this, null);
        lessThanOrEqual.Arg2().Visit(this, null);
        if (lessThanOrEqual.OperandsType() == EasyTypes.Int)
            code.add(new RuntimeLessThanOrEqualInt());
        else
            code.add(new RuntimeLessThanOrEqualInt());
        return null;
    }

    public Object VisitInequality(Inequality inequality, Object arg)
    {
        inequality.Arg1().Visit(this, null);
        inequality.Arg2().Visit(this, null);
        code.add(new RuntimeInequality());
        return null;
    }

    public Object VisitEquality(Equality equality, Object arg)
    {
        equality.Arg1().Visit(this, null);
        equality.Arg2().Visit(this, null);
        code.add(new RuntimeEquality());
        return null;
    }

    public Object VisitSum(Sum sum, Object arg)
    {
        sum.Arg1().Visit(this, null);
        sum.Arg2().Visit(this, null);
        if(sum.OperandsType() == EasyTypes.Int)
            code.add(new RuntimeSumInt());
        else
            code.add(new RuntimeSumFloat());
        return null;
    }

    public Object VisitMinus(Minus minus, Object arg)
    {
        minus.Arg1().Visit(this, null);
        minus.Arg2().Visit(this, null);
        if (minus.OperandsType() == EasyTypes.Int)
            code.add(new RuntimeMinusInt());
        else
            code.add(new RuntimeNewFloat());
        return null;
    }

    public Object VisitOr(Or or, Object arg)
    {
        or.Arg1().Visit(this, null);
        or.Arg2().Visit(this, null);
        code.add(new RuntimeOr());
        return null;
    }

    public Object VisitMultiplication(Multiplication multiplication, Object arg)
    {
        multiplication.Arg1().Visit(this, null);
        multiplication.Arg2().Visit(this, null);
        if (multiplication.OperandsType() == EasyTypes.Int)
            code.add(new RuntimeMultiplicationInt());
        else
            code.add(new RuntimeMultiplicationFloat());
        return null;
    }

    public Object VisitDivision(Division division, Object arg)
    {
        division.Arg1().Visit(this, null);
        division.Arg2().Visit(this, null);
        if (division.OperandsType() == EasyTypes.Int)
            code.add(new RuntimeDivisionInt());
        else
            code.add(new RuntimeDivisionFloat());
        return null;
    }

    public Object VisitModulus(Modulus modulus, Object arg)
    {
        modulus.Arg1().Visit(this, null);
        modulus.Arg2().Visit(this, null);
        code.add(new RuntimeModulus());
        return null;
    }

    public Object VisitAnd(And and, Object arg)
    {
        and.Arg1().Visit(this, null);
        and.Arg2().Visit(this, null);
        code.add(new RuntimeAnd());
        return null;
    }

    public Object VisitIdentifierValue(IdentifierValue identifierValue, Object arg)
    {
        SymbolInfo info = symbolsTable.entry(identifierValue.Entry());
        code.add(new RuntimeLoad());
        code.add(new Address(info.getAddress()));
        return null;
    }

    public Object VisitIntLireral(IntLiteral intLireral, Object arg)
    {
        SymbolInfo info = symbolsTable.entry(intLireral.Entry());
        code.add(new RuntimeLoad());
        code.add(new Address(info.getAddress()));
        return null;
    }

    public Object VisitFloatLiteral(FloatLiteral floatLiteral, Object arg)
    {
        SymbolInfo info = symbolsTable.entry(floatLiteral.Entry());
        code.add(new RuntimeLoad());
        code.add(new Address(info.getAddress()));
        return null;
    }

    public Object VisitBoolLiteral(BoolLiteral boolLiteral, Object arg)
    {
        SymbolInfo info = symbolsTable.entry(boolLiteral.Entry());
        code.add(new RuntimeLoad());
        code.add(new Address(info.getAddress()));
        return null;
    }

    public Object VisitNot(Not not, Object arg)
    {
        not.Expression().Visit(this, null);
        code.add(new RuntimeNot());
        return null;
    }

    public Object VisitSingMinus(SingMinus singMinus, Object arg)
    {
        singMinus.Expression().Visit(this, null);
        if (singMinus.OperandsType() == EasyTypes.Int)
            code.add(new RuntimeSingMinusInt());
        else
            code.add(new RuntimeSingMinusFloat());
        return null;
    }

    public Object VisitSingPlus(SingPlus singPlus, Object arg)
    {
        singPlus.Expression().Visit(this, null);
        return null;
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
        ifThen.Expression().Visit(this, null);
        code.add(new RuntimeJumpIfFalse());
        code.add(new Address(-1));
        int addreesPos = code.size()-1;
        ifThen.Then().Visit(this, null);
        code.set(addreesPos, new Address(code.size()));
        return null;
    }

    public Object VisitIfThenElse(IfThenElse ifThenElse, Object arg)
    {
        ifThenElse.Expression().Visit(this, null);
        code.add(new RuntimeJumpIfFalse());
        code.add(new Address(-1));
        int addreesPos = code.size() - 1;
        ifThenElse.Then().Visit(this, null);
        code.add(new RuntimeJump());
        code.add(new Address(-1));
        code.set(addreesPos, new Address(code.size()));
        addreesPos = code.size() - 1;
        ifThenElse.Else().Visit(this, null);
        code.set(addreesPos,  new Address(code.size()));
        return null;
    }

    public Object VisitWhile(While _while, Object arg)
    {
        int addreesJump = code.size();
        _while.Expression().Visit(this, null);
        code.add(new RuntimeJumpIfFalse());
        code.add(new Address(-1));
        int addreesPos = code.size() - 1;
        _while.Do().Visit(this, null);
        code.add(new RuntimeJump());
        code.add(new Address(addreesJump));
        code.set(addreesPos, new Address(code.size()));
        return null;
    }
}
