package EasyIDE;
import EasyCompiler.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

class InputKeyAdapter extends KeyAdapter
{
    private MyConsole console;
    
    public InputKeyAdapter(MyConsole console)
    {   
        this.console = console;
    }
    public void keyPressed(KeyEvent ke)
    {
        if (ke.getKeyChar() == '\r')
            console.setReading(false);          
    }
    //void keyReleased(KeyEvent ke)
    //void keyTyped(KeyEvent ke)        
}
    
class MyConsole extends EasyConsole
{
    private JEditorPane outputBox;
    private JTextField inputBox;
    //private TextBoxBase inputBox;
    //private TextBoxBase outputBox;
    
    private boolean reading;
    
    public void setReading(boolean reading)
    {
        this.reading = reading;
    }
        
    public MyConsole(JTextField inputBox, JEditorPane outputBox)
    {
        this.inputBox = inputBox;
        this.outputBox = outputBox;
        reading = false;
        
        KeyListener myKeyAdapter = new InputKeyAdapter(this);
                
        this.inputBox.addKeyListener(myKeyAdapter);
                //+= new KeyPressEventHandler(inputBox_KeyPress);
    }
    
    /*
    void inputBox_KeyPress(Object sender, KeyPressEventArgs e)
    {
        if (e.KeyChar == '\r')
            reading = false;
    }
    */

    public void Write(String format, Object[] args)
    {
        outputBox.setText(outputBox.getText() + String.format(format, args) + "\r\n");        
        //AppendText(String.Format(format, args));
        //outputBox.AppendText("\r\n");
    }

    public String Read()
    {
        reading = true;
        inputBox.requestFocusInWindow();        
       // while (reading){};            
            //Application.DoEvents();            
        String value = inputBox.getText();
        //inputBox.setText(null);
        return value;
    }
}
