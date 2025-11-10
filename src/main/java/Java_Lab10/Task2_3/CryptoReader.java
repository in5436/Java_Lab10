package Java_Lab10.Task2_3;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class CryptoReader extends FilterReader {

    private final int key;

    public CryptoReader(Reader in, char keyChar) {
        super(in);
        this.key = (int) keyChar;
    }

    @Override
    public int read() throws IOException {
        int encryptedChar = super.read();

        if (encryptedChar == -1) {
            return -1;
        }

        int decryptedChar = encryptedChar - this.key;

        return decryptedChar;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int charsRead = 0;
        for (int i = 0; i < len; i++) {
            int ch = read();
            if (ch == -1) {
                if (charsRead == 0) {
                    return -1;
                } else {
                    break;
                }
            }
            cbuf[off + i] = (char) ch;
            charsRead++;
        }
        return charsRead;
    }
}