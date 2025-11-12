package Java_Lab10.Task2_3;

import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class CryptoManager {

    private static final Logger logger = LogManager.getLogger(CryptoManager.class);

    public void encryptFile(String inputFile, String outputFile, char key, ResourceBundle bundle) throws IOException {

        logger.info(bundle.getString("status.encryptStart"), inputFile, outputFile);

        logger.debug("Шифрування з ключем: {}", key);

        try (Reader reader = new FileReader(inputFile);
             Writer writer = new CryptoWriter(new FileWriter(outputFile), key))
        {
            int c;
            while ((c = reader.read()) != -1) {

                writer.write(c);
            }
        }

        logger.info(bundle.getString("status.encryptSuccess"), outputFile);
    }

    public void decryptFile(String inputFile, String outputFile, char key, ResourceBundle bundle) throws IOException {

        logger.info(bundle.getString("status.decryptStart"), inputFile, outputFile);
        logger.debug("Дешифрування з ключем: {}", key);

        try (Reader reader = new CryptoReader(new FileReader(inputFile), key);
             Writer writer = new FileWriter(outputFile))
        {
            int c;
            while ((c = reader.read()) != -1) {

                writer.write(c);
            }
        }

        logger.info(bundle.getString("status.decryptSuccess"), outputFile);
    }
}