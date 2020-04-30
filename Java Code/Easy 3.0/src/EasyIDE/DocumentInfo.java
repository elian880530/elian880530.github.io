/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyIDE;
/**
 *
 * @author DDC Programación, UCI
 */
public class DocumentInfo {
    private boolean save;
    private String filename;
    
    public DocumentInfo() 
    {
        newDocument();
    }

    public void newDocument() 
    {
        save = true;
        filename = "";
    }

    public void open(String filename)
    {
        this.filename = filename;
        save = true;
    }

    public void save() 
    {
        save = true;
    }

    public void saveAs(String filename) 
    {
        this.filename = filename;
        save = true;
    }

    public void modified() 
    {
        save = false;
    }

    public boolean isSave() {
        return save;
    }

    public String getFilename() {
        return filename;
    }
}
