package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */

public interface Visitor {

    Object VisitProgram(Program program, Object arg);

    Object VisitIdentifierDeclaration(IdentifierDeclaration identifierDeclaration, Object arg);

    Object VisitVarType(VarType type, Object arg);

    Object VisitVarDeclaration(VarDeclaration varDeclaration, Object arg);

    Object VisitIdentifierReference(IdentifierReference identifierReference, Object arg);

    Object VisitEmptyInstruction(EmptyInstruction emptyInstruction, Object arg);

    Object VisitRead(Read read, Object arg);

    Object VisitPrint(Print write, Object arg);

    Object VisitAssigment(Assigment assigment, Object arg);

    Object VisitImplicacion(Implicacion greaterThanOrEqual, Object arg);

    Object VisitEquivalencia(Equivalencia inequality, Object arg);

    Object VisitOr(Or or, Object arg);

    Object VisitAnd(And and, Object arg);

    Object VisitIdentifierValue(IdentifierValue identifierValue, Object arg);

    Object VisitIntLireral(binaryLiteral intLireral, Object arg);

    Object VisitNot(Not not, Object arg);

    Object VisitInstructionBlock(InstructionBlock instructionBlock, Object arg);
}
