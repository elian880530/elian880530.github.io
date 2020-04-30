/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lexeranalyzer;

/**
 *
 * @author Yosuan Hernandez
 */
import java.io.*;
import java.util.Hashtable;

public class LexerAnalyzer {
       
        protected FileReader reader;
        protected int row, col;
        private Hashtable<String,Object > keywords = new Hashtable<String, Object>();
       
        public LexerAnalyzer(String fname) throws FileNotFoundException 
        {
            reader = new FileReader(fname);
            InitKeywords();
            row = 1;
            col = 1;
        }
       

        /// <summary>
        /// Este metodo inicializa los valores del diccionario 'keywords'
        /// </summary>
        private void InitKeywords()
        {
           /*Agreagar al diccionario las palabras reservadas*/
        }

        private void SkipSpaces() throws IOException
        {
            while ((char)Read() == ' ') ;
        }

        private boolean  IsLetter(int symbol)
        {
            //Incompleto
            return true;
        }

        private boolean  IsDigit(int symbol)
        {
            //incompleto
            return true;
        }

        private boolean  IsIdChar(int symbol)
        {
            //Incompleto
            return true;
        }

        private Token ReadWord(SourcePosition sPos) throws IOException
        {
            int cSymbol = Read();
            String res = "" + (char)cSymbol;

            while (IsIdChar(Peek()))
            {
                cSymbol = Read();
                res += (char)cSymbol;
            }

            Object obj = keywords.get(res);
            if (obj != null)
                return new Token((Token.Type)obj, "", sPos);
            return null;
            
        }

        private Token ReadLitInt(SourcePosition sPos)
        {
            //Incompleto
            return new Token(Token.Type.TEof, "", sPos);
        }

        private Token ReadLitStr(SourcePosition sPos)
        {
            //Incompleto
            return new Token(Token.Type.TEof, "", sPos);
        }

       
        protected int Read() throws IOException
        {
            int symbol = reader.read();
            col++;
            if (symbol == '\n' || symbol == '\r')
            {
                row++;
                col = 1;
            }
            return symbol;
        }

        protected int Peek()
        {
            //incompleto
            return 0;
        }

        protected boolean HasNoneSymbol()
        {
            return Peek() != -1;
        }

        public Token NextToken() throws IOException
        {
            SkipSpaces();
            SourcePosition sPos=new SourcePosition(row, col);           

            if (!HasNoneSymbol())
                return new Token(Token.Type.TEof, "", sPos);

            int cSymbol = Peek();
            if (IsLetter(cSymbol))
                return ReadWord(sPos);
            if (IsDigit(cSymbol))
                return ReadLitInt(sPos);
            if ((char)cSymbol == '\'')
                return ReadLitStr(sPos);

            cSymbol = Read();
            int nextSymbol = Peek();
            Token.Type typect= Token.Type.TEof;

            switch (cSymbol)
            {
                case ',':
                    {
                        //typect = Token.Type.TComma;
                        break;
                    }
                case '+':
                    {
                        //Incompleto
                        //typect = Token.Type.TPlus;
                        break;
                    }
                case '=':
                    {
                        //Incompleto
                        break;
                    }
                    //Incompleto

            }

            return new Token(typect, "", sPos);
            
        }

}
