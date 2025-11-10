package Java_Lab10.Task2_3;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class CryptoWriter extends FilterWriter {

    private final int key;

    public CryptoWriter(Writer out, char keyChar) {
        super(out);
        this.key = (int) keyChar;
    }

    @Override
    public void write(int c) throws IOException {
        int encryptedChar = c + this.key;

        super.write(encryptedChar);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            write(cbuf[off + i]);
        }
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            write(str.charAt(off + i));
        }
    }
}