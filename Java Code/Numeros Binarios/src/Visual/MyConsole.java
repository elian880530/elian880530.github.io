package Visual;

/**
 *
 * @author EGH
 */
import Compilador.EasyConsole;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

class InputKeyAdapter extends KeyAdapter {

    private MyConsole console;

    public InputKeyAdapter(MyConsole console) {
        this.console = console;
    }

    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() == '\r') {
            console.setReading(false);
        }
    }
}

class MyConsole extends EasyConsole {

    private JEditorPane outputBox;
    private JTextField inputBox;
    private boolean reading;

    public void setReading(boolean reading) {
        this.reading = reading;
    }

    public MyConsole(JTextField inputBox, JEditorPane outputBox) {
        this.inputBox = inputBox;
        this.outputBox = outputBox;
        reading = false;

        KeyListener myKeyAdapter = new InputKeyAdapter(this);

        this.inputBox.addKeyListener(myKeyAdapter);

    }

    public void Write(String format, Object[] args) {
        outputBox.setText(outputBox.getText() + String.format(format, args) + "\r\n");

    }

    public String Read() {
        reading = true;
        inputBox.requestFocusInWindow();

        String value = inputBox.getText();

        return value;
    }
}
