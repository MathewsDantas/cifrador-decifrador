package com.seguranca.cifradordecifrador;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private static String alphaUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String alphaLower = "abcdefghijklmnopqrstuvwxyz";
    private static String numbers = "0123456789";
    private static String specials = " ,.-";
    private static final String RULES = alphaUpper+alphaLower+numbers+specials;

    String encrypt(String text, String[] key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyIndex = 0;

        for (char c : text.toCharArray()) {
            if (RULES.contains(String.valueOf(c))) {
                int charIndex = RULES.indexOf(c);

                charIndex += Integer.parseInt(key[keyIndex]);
                charIndex %= RULES.length();
                encryptedText.append(RULES.charAt(charIndex));

                keyIndex = (keyIndex + 1) % key.length;
            } else {
                return null;
            }
        }

        return encryptedText.toString();
    }

    String decrypt(String encryptedText, String[] key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyIndex = 0;

        for (char c : encryptedText.toCharArray()) {
            if (RULES.contains(String.valueOf(c))) {
                int charIndex = RULES.indexOf(c);

                charIndex -= Integer.parseInt(key[keyIndex]);

                while (charIndex < 0) {
                    charIndex += RULES.length();
                }

                decryptedText.append(RULES.charAt(charIndex));

                keyIndex = (keyIndex + 1) % key.length;
            } else {
                return null;
            }
        }

        return decryptedText.toString();
    }
}
