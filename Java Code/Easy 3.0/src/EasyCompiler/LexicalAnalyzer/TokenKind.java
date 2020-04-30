/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyCompiler.LexicalAnalyzer;

/**
 *
 * @author trujillo
 */
public enum TokenKind {
    Program, 
    Begin,
    End,
    Var,
    If,
    Then,
    Else,
    While,
    Do,
        
    Int, 
    Float,
    Bool,
        
    Id, 
        
    Read,
    Write, 
        
    IntLiteral,
    FloatLiteral,
    BoolLiteral,
        
    Colon,
    SemiColon,
    Comma,
        
    LeftParen,
    RigthParen,
        
    Sum,
    Minus, 
    Multiplication,
    Division,
    Modulus, 
        
    Assignment,
        
    Equality,
    Inequality,
    LessThan,
    GreaterThan,
    GreaterThanOrEqual,
    LessThanOrEqual,
        
    And,
    Not,
    Or,

    Error,
    EOT    
}
