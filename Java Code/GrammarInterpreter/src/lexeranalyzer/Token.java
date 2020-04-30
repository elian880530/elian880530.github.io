/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lexeranalyzer;

/**
 *
 * @author Yosuan Hernandez
 */
public class Token {
     public enum Type
        {
            TEof,TDeclaraciones,TTwoPoints,TProducciones,TOperaciones,TGramatica
            /*nuestros tokens*/
        };

        public Token(Type kind, String text, SourcePosition sPos)
        {
            TypeTok = kind;
            Text = text;
            SPos = sPos;
        }

        public SourcePosition SPos;       
        public Type TypeTok;
        public int IntVal;
        public char CharVal;
        public String Text;

}

