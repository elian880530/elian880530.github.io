package AnalizadorSemantico;

/**
 *
 * @author EGH
 */
import AnalizadorLexico.TokenKind;
import Compilador.EasyTypes;
import AbstractSyntaxTrees.*;

import Runtime.*;

import SimbolosdeTabla.SymbolInfo;
import SimbolosdeTabla.SymbolsTable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Encoder implements Visitor {

    private List<RuntimeEntity> code;

    public List<RuntimeEntity> Code() {
        return code;
    }
    private SymbolsTable symbolsTable;

    public Encoder(SymbolsTable symbolsTable) {
        code = new LinkedList<RuntimeEntity>();
        this.symbolsTable = symbolsTable;
    }

    private void MemoryManager() {
        int address = 0;
        code.add(new RuntimeMemorySize());
        code.add(new IntValue("0"));
        int memSizePos = code.size() - 1;
        for (int i = 0; i < symbolsTable.Count(); i++) {
            if (symbolsTable.entry(i).getKind() == TokenKind.Id && symbolsTable.entry(i).getDeclared()) {
                symbolsTable.entry(i).setAddress(address++);
            } else if (symbolsTable.entry(i).getKind() == TokenKind.binaryLiteral)//cambio aqui de int por binary
            {
                code.add(new RuntimeNewInt());
                symbolsTable.entry(i).setAddress(address++);
                code.add(new Address(String.valueOf(symbolsTable.entry(i).getAddress())));
                code.add(new RuntimePush());
                code.add(new Address(String.valueOf(symbolsTable.entry(i).getAddress())));
                code.add(new RuntimePush());
                code.add(new IntValue(symbolsTable.entry(i).getLexeme()));
                code.add(new RuntimeStore());
            }
        }
        code.set(memSizePos, new IntValue(String.valueOf(address)));
    }

    //region IVisitor Members
    public Object VisitProgram(Program program, Object arg) {
        MemoryManager();
        ListIterator<SingleInstruction> it = program.Instructions().listIterator();
        while (it.hasNext()) {
            it.next().Visit(this, null);
        }
        code.add(new RuntimeHalt());
        return null;
    }

    public Object VisitIdentifierDeclaration(IdentifierDeclaration identifierDeclaration, Object arg) {
        SymbolInfo info = symbolsTable.entry(identifierDeclaration.Entry());
        code.add(new Address(String.valueOf(info.getAddress())));
        return null;
    }

    public Object VisitVarType(VarType type, Object arg) {
        switch (type.EasyType()) {
            case BinariLiteral:
                code.add(new RuntimeNewInt());
                break;
        }
        return null;
    }

    public Object VisitVarDeclaration(VarDeclaration varDeclaration, Object arg) {
        varDeclaration.Type().Visit(this, null);
        varDeclaration.Identifier().Visit(this, null);
        return null;
    }

    public Object VisitEmptyInstruction(EmptyInstruction emptyInstruction, Object arg) {
        return null;
    }

    public Object VisitRead(Read read, Object arg) {
        read.Identifier().Visit(this, null);
        switch (read.OperandsType()) {

            case BinariLiteral:
                code.add(new RuntimeReadInt());
                break;
        }
        return null;
    }

    public Object VisitPrint(Print print, Object arg) {
        print.Expression().Visit(this, null);
        code.add(new RuntimePrint());
        return null;
    }

    public Object VisitAssigment(Assigment assigment, Object arg) {
        assigment.Identifier().Visit(this, null);
        assigment.Expression().Visit(this, null);
        code.add(new RuntimeStore());
        return null;
    }

    public Object VisitIdentifierReference(IdentifierReference identifierReference, Object arg) {
        SymbolInfo info = symbolsTable.entry(identifierReference.Entry());
        code.add(new RuntimePush());
        code.add(new Address(String.valueOf(info.getAddress())));
        return null;
    }

    public Object VisitOr(Or or, Object arg) {
        or.Arg1().Visit(this, null);
        or.Arg2().Visit(this, null);
        code.add(new RuntimeOr());
        return null;
    }

    public Object VisitAnd(And and, Object arg) {
        and.Arg1().Visit(this, null);
        and.Arg2().Visit(this, null);
        code.add(new RuntimeAnd());
        return null;
    }

    public Object VisitIdentifierValue(IdentifierValue identifierValue, Object arg) {
        SymbolInfo info = symbolsTable.entry(identifierValue.Entry());
        code.add(new RuntimeLoad());
        code.add(new Address(String.valueOf(info.getAddress())));
        return null;
    }

    public Object VisitNot(Not not, Object arg) {
        not.Expression().Visit(this, null);
        code.add(new RuntimeNot());
        return null;
    }

    public Object VisitInstructionBlock(InstructionBlock instructionBlock, Object arg) {
        ListIterator<SingleInstruction> it = instructionBlock.Instructions().listIterator();
        while (it.hasNext()) {
            it.next().Visit(this, null);
        }
        return null;
    }

    public Object VisitImplicacion(Implicacion implicacion, Object arg) {

        implicacion.Arg1().Visit(this, null);
        implicacion.Arg2().Visit(this, null);
        code.add(new RuntimeImplicacion());
        return null;

    }

    public Object VisitEquivalencia(Equivalencia equivalencia, Object arg) {

        equivalencia.Arg1().Visit(this, null);
        equivalencia.Arg2().Visit(this, null);
        code.add(new RuntimeEquivalencia());
        return null;
    }

    public Object VisitIntLireral(binaryLiteral intLireral, Object arg) {

        SymbolInfo info = symbolsTable.entry(intLireral.Entry());
        code.add(new RuntimeLoad());
        code.add(new Address(String.valueOf(info.getAddress())));
        return null;
    }
}
