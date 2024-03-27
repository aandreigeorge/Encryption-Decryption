public class Main {
    public static void main(String[] args) {
        String message = "we found a treasure!";
        String ciphertext = encryptMessage(message);
        System.out.println(ciphertext);
    }

    static String encryptMessage(String message) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

            if (Character.isLetter(currentChar)) {
                char encryptedChar = encryptChar(currentChar);
                ciphertext.append(encryptedChar);
            } else {
                ciphertext.append(currentChar);
            }
        }
        return ciphertext.toString();
    }

    static char encryptChar(char c) {
        int encryptedAscii = 'z' - (c - 'a');
        return (char) encryptedAscii;
    }

}
