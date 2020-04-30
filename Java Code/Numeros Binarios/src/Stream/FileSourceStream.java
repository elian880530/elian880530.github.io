package Stream;

import java.io.*;

/**
 *
 * @author EGH
 */
public class FileSourceStream extends SourceStream {

    public FileSourceStream(String fileName) throws FileNotFoundException {
        super();
        FileInputStream in = new FileInputStream(fileName);
        this.textReader = new InputStreamReader(in);
    }
}
