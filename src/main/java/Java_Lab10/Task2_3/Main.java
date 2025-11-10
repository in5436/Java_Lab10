package Java_Lab10.Task2_3;

import java.io.IOException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CryptoManager manager = new CryptoManager();

        System.out.println("Оберіть мову / Select language (1=UA, 2=EN):");
        String langChoice = scanner.nextLine();

        Locale locale = "2".equals(langChoice) ? new Locale("en") : new Locale("uk");

        ResourceBundle bundle = ResourceBundle.getBundle("location.messages", locale);

        logger.info(bundle.getString("status.appStart"));

        try {
            logger.info(bundle.getString("menu.selectMode"));
            logger.info(bundle.getString("menu.encrypt"));
            logger.info(bundle.getString("menu.decrypt"));
            String mode = scanner.nextLine();

            logger.debug("Користувач обрав режим: {}", mode);

            logger.info(bundle.getString("prompt.inputFile"));
            String inputFile = scanner.nextLine();

            logger.info(bundle.getString("prompt.outputFile"));
            String outputFile = scanner.nextLine();

            logger.info(bundle.getString("prompt.key"));
            String keyString = scanner.nextLine();

            if (keyString.length() != 1) {
                throw new IllegalArgumentException(bundle.getString("error.keyLength"));
            }
            char key = keyString.charAt(0);

            if ("1".equals(mode)) {
                manager.encryptFile(inputFile, outputFile, key, bundle);
            } else if ("2".equals(mode)) {
                manager.decryptFile(inputFile, outputFile, key, bundle);
            } else {
                logger.warn(bundle.getString("menu.invalidChoice"), mode);;
            }

        } catch (IOException e) {
            logger.error(bundle.getString("error.fileIO"), e);
        } catch (Exception e) {
            logger.error(bundle.getString("error.unknown"), e);
        } finally {
            logger.info(bundle.getString("status.appEnd"));
            scanner.close();
        }
    }
}