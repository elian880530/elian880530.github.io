package Stream;

import java.io.*;

/**
 *
 * @author EGH
 */
public abstract class SourceStream {

    protected Reader textReader;
    private int currentLine;
    private int currentPosition;

    public SourceStream() {
        currentLine = 1;
        currentPosition = 0;
    }

    public char Read() throws IOException {
        int charReaded = textReader.read();
        if (charReaded == -1) {
            return '\0';
        }
        currentPosition++;
        char c = (char) charReaded;
        if (c == '\n') {
            currentLine++;
        }
        return c;
    }

    public int getCurrentLine() {
        return currentLine;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}
