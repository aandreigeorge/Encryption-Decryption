
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String message = scanner.nextLine();
        int key = scanner.nextInt();
        chooseAction(command, message, key);
    }

    static void chooseAction(String command, String text, int key){

        if(command.equalsIgnoreCase("ENC")){
            encryptMessage(text, key);
        } else if (command.equalsIgnoreCase("DEC")){
            decryptMessage(text, key);
        }
    }

    static void encryptMessage(String message, int key) {

        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            int encryptedAscii = (message.charAt(i) + key) % 128;
            char encryptedChar = (char) (encryptedAscii);
            encryptedMessage.append(encryptedChar);
        }
        System.out.println(encryptedMessage);
    }

    static void decryptMessage(String message, int key) {

        StringBuilder decryptedMessage = new StringBuilder();

        for(int i = 0; i< message.length(); i++) {
            int decryptedAscii = (message.charAt(i) - key + 128) % 128;
            char decryptedChar = (char) (decryptedAscii);
            decryptedMessage.append(decryptedChar);
        }
        System.out.println(decryptedMessage);
    }

}
