

package SimbolosdeTabla;

import AnalizadorLexico.*;
import java.util.*;
/**
 *
 * @author EGH
 */
public class SymbolsTable {
    
    private LinkedList<SymbolInfo> items;
    
    public SymbolsTable()
    {
        items = new LinkedList<SymbolInfo>();
    }
    
    public int add(String lexeme, TokenKind kind) throws Exception
    {
        SymbolInfo item = new SymbolInfo(lexeme, kind);
        
        int index = items.indexOf(item);
        if (index == -1)
        {
            items.add(item);
            index = items.size() - 1;
        }
        return index;
    }
    
    public SymbolInfo entry(int index) 
    {
        return items.get(index);
    }
    
    public int Count()
    {
        return items.size();
    }
}
