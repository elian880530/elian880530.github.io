/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SyntaxAnalyzer;

import java.io.IOException;
import lexeranalyzer.*;

/**
 *
 * @author Yosuan Hernandez
 */
public class SyntaxAnalyzer {
    
        protected LexerAnalyzer lexAnalyzer;
        private Token cToken;

        public SyntaxAnalyzer(LexerAnalyzer lexAnalyzer) {
            this.lexAnalyzer = lexAnalyzer;
        }        

        public void S() throws IOException
        {
            if(cToken.TypeTok == Token.Type.TDeclaraciones)
            {
                Match(Token.Type.TDeclaraciones);
                Match(Token.Type.TTwoPoints);
                Declaration();
                Match(Token.Type.TProducciones);
                Match(Token.Type.TTwoPoints);
                Production();
                Match(Token.Type.TOperaciones);
                Match(Token.Type.TTwoPoints);
                Operation();
            }
            else
            {/*error*/}
        }
        
        public void Declaration()
        {
            if(cToken.TypeTok == Token.Type.TGramatica)
            {
                
            }
        }
        
        public void Production()
        {
        }
        
        public void Operation(){
        
        }
        
        private void Match(Token.Type kind) throws IOException
        {
            cToken = lexAnalyzer.NextToken();
            //incompleto
        }


}
