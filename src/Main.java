import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> argumentsMap = new HashMap<>();
        for (int i = 0; i < args.length - 1; i += 2) {
            argumentsMap.put(args[i], args[i + 1]);
        }


        String mode = argumentsMap.getOrDefault("-mode", "enc");
        int key = Integer.parseInt(argumentsMap.getOrDefault("-key", "0"));
        String data = argumentsMap.getOrDefault("-data", "");

        chooseAction(mode, data, key);
    }

    static void chooseAction(String mode, String text, int key) {
        if (mode.equalsIgnoreCase("ENC")) {
            encryptMessage(text, key);
        } else if (mode.equalsIgnoreCase("DEC")) {
            decryptMessage(text, key);
        }
    }

    static void encryptMessage(String message, int key) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            int encryptedAscii = (message.charAt(i) + key) % 128;
            char encryptedChar = (char) encryptedAscii;
            encryptedMessage.append(encryptedChar);
        }
        System.out.println(encryptedMessage);
    }

    static void decryptMessage(String message, int key) {
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            int decryptedAscii = (message.charAt(i) - key + 128) % 128;
            char decryptedChar = (char) decryptedAscii;
            decryptedMessage.append(decryptedChar);
        }
        System.out.println(decryptedMessage);
    }
}
