package AnalizadorSemantico;

/**
 *
 * @author EGH
 */
import Compilador.EasyTypes;
import AbstractSyntaxTrees.*;
import Errores.CompilerError;
import Errores.ErrorReporter;
import SimbolosdeTabla.SymbolInfo;
import SimbolosdeTabla.SymbolsTable;
import java.util.ListIterator;

public class Checker implements Visitor {

    private SymbolsTable symbolsTable;
    private ErrorReporter errorReporter;

    public Checker(SymbolsTable symbolsTable, ErrorReporter errorReporter) {
        this.symbolsTable = symbolsTable;
        this.errorReporter = errorReporter;
    }

    //Visitor Members
    public Object VisitProgram(Program program, Object arg) {
        ListIterator<SingleInstruction> it = program.Instructions().listIterator();
        while (it.hasNext()) {
            it.next().Visit(this, null);
        }
        return null;
    }

    public Object VisitIdentifierDeclaration(IdentifierDeclaration identifierDeclaration, Object arg) {
        SymbolInfo info = symbolsTable.entry(identifierDeclaration.Entry());
        if (info.getDeclared()) {
            errorReporter.add(new CompilerError(identifierDeclaration.Position(), "A variable named '{0}' is already defined" + info.getLexeme()));
        }
        info.setDeclared(true);
        info.setType((EasyTypes) arg);
        return null;
    }

    public Object VisitVarType(VarType type, Object arg) {
        return type.EasyType();
    }

    public Object VisitVarDeclaration(VarDeclaration varDeclaration, Object arg) {
        varDeclaration.Identifier().Visit(this, varDeclaration.Type().Visit(this, null));
        return null;
    }

    public Object VisitEmptyInstruction(EmptyInstruction emptyInstruction, Object arg) {
        return null;
    }

    public Object VisitPrint(Print write, Object arg) {
        EasyTypes expressionType = (EasyTypes) write.Expression().Visit(this, null);
        write.setOperandsType(expressionType);
        return null;
    }

    public Object VisitAssigment(Assigment assigment, Object arg) {
        EasyTypes idType = (EasyTypes) assigment.Identifier().Visit(this, null);
        EasyTypes expressionType = EasyTypes.BinariLiteral;//(EasyTypes)assigment.Expression().Visit(this, null);
        if ((idType != expressionType)) {
            Object[] arg1 = new String[2];
            arg1[0] = idType.toString().toLowerCase();
            arg1[1] = expressionType.toString().toLowerCase();
            errorReporter.add(new CompilerError(assigment.Position(), String.format("Cannot convert type '{%s}' to '{%s}'", arg1)));
        }
        return null;
    }

    public Object VisitIdentifierReference(IdentifierReference identifierReference, Object arg) {
        SymbolInfo info = symbolsTable.entry(identifierReference.Entry());
        if (!info.getDeclared()) {
            errorReporter.add(new CompilerError(identifierReference.Position(), "The name '{0}' does not exist" + identifierReference.Lexeme()));
        }
        return info.getType();
    }

    public Object VisitOr(Or or, Object arg) {
        EasyTypes type1 = (EasyTypes) or.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes) or.Arg2().Visit(this, null);
        if (type1 != EasyTypes.BinariLiteral || type2 != EasyTypes.BinariLiteral) {
            errorReporter.add(new CompilerError(or.Position(), "Operator '|' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        }
        or.setOperandsType(EasyTypes.BinariLiteral);
        return EasyTypes.BinariLiteral;
    }

    public Object VisitAnd(And and, Object arg) {
        EasyTypes type1 = (EasyTypes) and.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes) and.Arg2().Visit(this, null);
        if (type1 != EasyTypes.BinariLiteral || type2 != EasyTypes.BinariLiteral) {
            errorReporter.add(new CompilerError(and.Position(), "Operator '&' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        }
        and.setOperandsType(EasyTypes.BinariLiteral);
        return EasyTypes.BinariLiteral;
    }

    public Object VisitIdentifierValue(IdentifierValue identifierValue, Object arg) {
        SymbolInfo info = symbolsTable.entry(identifierValue.Entry());
        if (!info.getDeclared()) {
            errorReporter.add(new CompilerError(identifierValue.Position(), "The name '{0}' does not exist" + identifierValue.Lexeme()));
        }
        return info.getType();
    }

    public Object VisitIntLireral(binaryLiteral binaryLireral, Object arg) {
        return EasyTypes.BinariLiteral;
    }

    public Object VisitRead(Read read, Object arg) {
        EasyTypes typeIdentifier = (EasyTypes) read.Identifier().Visit(this, arg);
        if (typeIdentifier == EasyTypes.BinariLiteral) {
            errorReporter.add(new CompilerError(read.Position(), "Cannot read a 'bool' value"));
        }
        read.setOperandsType(typeIdentifier);
        return null;
    }

    public Object VisitNot(Not not, Object arg) {
        EasyTypes type = (EasyTypes) not.Expression().Visit(this, null);
        if (type != EasyTypes.BinariLiteral) {
            errorReporter.add(new CompilerError(not.Position(), "Operator '!' cannot be applied to operand of type '{0}'" + type.toString().toLowerCase()));
        }
        not.setOperandsType(EasyTypes.BinariLiteral);
        return EasyTypes.BinariLiteral;
    }

    public Object VisitInstructionBlock(InstructionBlock instructionBlock, Object arg) {
        ListIterator<SingleInstruction> it = instructionBlock.Instructions().listIterator();
        while (it.hasNext()) {
            it.next().Visit(this, null);
        }
        return null;
    }

    public Object VisitImplicacion(Implicacion implicacion, Object arg) {
        EasyTypes type1 = (EasyTypes) implicacion.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes) implicacion.Arg2().Visit(this, null);
        if (type1 != EasyTypes.BinariLiteral || type2 != EasyTypes.BinariLiteral) {
            errorReporter.add(new CompilerError(implicacion.Position(), "Operator '->' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        }
        implicacion.setOperandsType(EasyTypes.BinariLiteral);
        return EasyTypes.BinariLiteral;
    }

    public Object VisitEquivalencia(Equivalencia equivalencia, Object arg) {
        EasyTypes type1 = (EasyTypes) equivalencia.Arg1().Visit(this, null);
        EasyTypes type2 = (EasyTypes) equivalencia.Arg2().Visit(this, null);
        if (type1 != EasyTypes.BinariLiteral || type2 != EasyTypes.BinariLiteral) {
            errorReporter.add(new CompilerError(equivalencia.Position(), "Operator '&' cannot be applied to operands of type '{0}' and '{1}'" + type1.toString().toLowerCase() + type2.toString().toLowerCase()));
        }
        equivalencia.setOperandsType(EasyTypes.BinariLiteral);
        return EasyTypes.BinariLiteral;
    }
}

