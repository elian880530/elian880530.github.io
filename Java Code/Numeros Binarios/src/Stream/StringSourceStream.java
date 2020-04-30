
package Stream;

import java.io.*;

/**
 *
 * @author EGH
 */
public class StringSourceStream extends SourceStream {

    public StringSourceStream(String sourceCode) {
        super();
        this.textReader = new StringReader(sourceCode);
    }
}
