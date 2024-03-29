package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ciphertext = scanner.nextLine();
        int key = scanner.nextInt();
        String encryptedMessage = encryptMessage(ciphertext, key);
        System.out.println(encryptedMessage);
    }

    static String encryptMessage(String message, int key) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

            if (Character.isLetter(currentChar)) {
                char encryptedChar = encryptChar(currentChar, key);
                ciphertext.append(encryptedChar);
            } else {
                ciphertext.append(currentChar);
            }
        }
        return ciphertext.toString();
    }

    static char encryptChar(char c, int shift) {
        return (char) ('a' + (c - 'a' + shift) % 26);
    }

}
