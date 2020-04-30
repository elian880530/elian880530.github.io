package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public interface Visitor
{
    Object VisitProgram(Program program, Object arg);
    Object VisitIdentifierDeclaration(IdentifierDeclaration identifierDeclaration, Object arg);
    Object VisitVarType(VarType type, Object arg);
    Object VisitVarDeclaration(VarDeclaration varDeclaration, Object arg);
    Object VisitIdentifierReference(IdentifierReference identifierReference, Object arg);
    Object VisitEmptyInstruction(EmptyInstruction emptyInstruction, Object arg);
    Object VisitRead(Read read, Object arg);
    Object VisitWrite(Write write, Object arg);
    Object VisitAssigment(Assigment assigment, Object arg);
    Object VisitGreaterThan(GreaterThan greaterThan, Object arg);
    Object VisitGreaterThanOrEqual(GreaterThanOrEqual greaterThanOrEqual, Object arg);
    Object VisitLessThan(LessThan lessThan, Object arg);
    Object VisitLessThanOrEqual(LessThanOrEqual lessThanOrEqual, Object arg);
    Object VisitInequality(Inequality inequality, Object arg);
    Object VisitEquality(Equality equality, Object arg);
    Object VisitSum(Sum sum, Object arg);
    Object VisitMinus(Minus minus, Object arg);
    Object VisitOr(Or or, Object arg);
    Object VisitMultiplication(Multiplication multiplication, Object arg);
    Object VisitDivision(Division division, Object arg);
    Object VisitModulus(Modulus modulus, Object arg);
    Object VisitAnd(And and, Object arg);
    Object VisitIdentifierValue(IdentifierValue identifierValue, Object arg);
    Object VisitIntLireral(IntLiteral intLireral, Object arg);
    Object VisitFloatLiteral(FloatLiteral floatLiteral, Object arg);
    Object VisitBoolLiteral(BoolLiteral boolLiteral, Object arg);
    Object VisitNot(Not not, Object arg);
    Object VisitSingMinus(SingMinus singMinus, Object arg);
    Object VisitSingPlus(SingPlus singPlus, Object arg);
    Object VisitInstructionBlock(InstructionBlock instructionBlock, Object arg);
    Object VisitIfThen(IfThen ifThen, Object arg);
    Object VisitIfThenElse(IfThenElse ifThenElse, Object arg);
    Object VisitWhile(While _while, Object arg);
}
