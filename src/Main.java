import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> argumentsMap = parseArguments(args);

        String mode = argumentsMap.getOrDefault("-mode", "enc");
        int key = Integer.parseInt(argumentsMap.getOrDefault("-key", "0"));
        String data = argumentsMap.getOrDefault("-data", "");

        if (!data.isEmpty()) {
            performAction(mode, data, key, System.out);
        } else {
            String inputFile = argumentsMap.get("-in");
            String outputFile = argumentsMap.get("-out");

            if (inputFile != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                    StringBuilder inputData = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        inputData.append(line);
                    }
                    performAction(mode, inputData.toString(), key, getOutputStream(outputFile));
                } catch (IOException e) {
                    System.out.println("Error: Unable to read from input file.");
                }
            } else {
                performAction(mode, "", key, getOutputStream(outputFile));
            }
        }
    }

    static Map<String, String> parseArguments(String[] args) {
        Map<String, String> argumentsMap = new HashMap<>();
        for (int i = 0; i < args.length - 1; i += 2) {
            argumentsMap.put(args[i], args[i + 1]);
        }
        return argumentsMap;
    }

    static OutputStream getOutputStream(String outputFile) {
        if (outputFile != null) {
            try {
                return new FileOutputStream(outputFile);
            } catch (FileNotFoundException e) {
                System.out.println("Error: Unable to write to output file.");
            }
        }
        return System.out;
    }

    static void performAction(String mode, String data, int key, OutputStream outputStream) {
        if (mode.equalsIgnoreCase("ENC")) {
            encryptMessage(data, key, outputStream);
        } else if (mode.equalsIgnoreCase("DEC")) {
            decryptMessage(data, key, outputStream);
        }
    }

    static void encryptMessage(String message, int key, OutputStream outputStream) {
        try (PrintWriter writer = new PrintWriter(outputStream)) {
            for (int i = 0; i < message.length(); i++) {
                int encryptedAscii = (message.charAt(i) + key) % 128;
                writer.print((char) encryptedAscii);
            }
        }
    }

    static void decryptMessage(String message, int key, OutputStream outputStream) {
        try (PrintWriter writer = new PrintWriter(outputStream)) {
            for (int i = 0; i < message.length(); i++) {
                int decryptedAscii = (message.charAt(i) - key + 128) % 128;
                writer.print((char) decryptedAscii);
            }
        }
    }
}
