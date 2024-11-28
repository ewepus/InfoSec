package lab_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String initialAlphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz .,?!-";

    public static String encryption(String input, String key) {
        String cryptogram = "";
        int counter = 0;
        String[] endAlphabets = new String[key.length()];

        for (int i = 0; i < key.length(); i++) {
            String endAlphabet = initialAlphabet.substring(initialAlphabet.indexOf(key.charAt(i))) +
                    initialAlphabet.substring(0, initialAlphabet.indexOf(key.charAt(i)));
            endAlphabets[i] = endAlphabet;
        }

        for (int i = 0; i < input.length(); i++) {
            counter = counter == 3 ? 0 : counter;
            int index = initialAlphabet.indexOf(input.charAt(i));
            cryptogram += endAlphabets[counter].charAt(index);
            counter++;
        }
        System.out.println("Исходный и заменяющие алфавиты:");
        System.out.println(initialAlphabet);
        for (String a : endAlphabets) {
            System.out.println(a);
        }
        System.out.println();
        return cryptogram;
    }

    public static String decryption(String input, String key) {
        String transcrypt = "";
        int counter = 0;
        String[] endAlphabets = new String[key.length()];

        for (int i = 0; i < key.length(); i++) {
            String endAlphabet = initialAlphabet.substring(initialAlphabet.indexOf(key.charAt(i))) +
                    initialAlphabet.substring(0, initialAlphabet.indexOf(key.charAt(i)));
            endAlphabets[i] = endAlphabet;
        }

        for (int i = 0; i < input.length(); i++) {
            counter = counter == 3 ? 0 : counter;
            int index = endAlphabets[counter].indexOf(input.charAt(i));
            transcrypt += initialAlphabet.charAt(index);
            counter++;
        }
        System.out.println("Исходный и заменяющие алфавиты:");
        System.out.println(initialAlphabet);
        for (String a : endAlphabets) {
            System.out.println(a);
        }
        System.out.println();
        return transcrypt;
    }

    public static boolean validKey(String key) {
        if (key.isBlank()) return false;
        List<Character> knownChars = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            char character = key.charAt(i);
            if (!knownChars.contains(character)) {
                knownChars.add(character);
            } else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Зашифровать сообщение - введите 1\n" +
                    "Расшифровать сообщение - введите 2");
            int numInput = Integer.parseInt(sc.nextLine());

            if (numInput == 1) {
                System.out.println("Введите сообщение");
                String input = sc.nextLine();
                System.out.println("Введите ключ (в ключе не может быть повторяющихся символов)");

                String key = sc.nextLine();
                while (!validKey(key)) {
                    System.out.println("Недопустимый ключ, попробуйте ещё раз");
                    key = sc.nextLine();
                }

                System.out.println("Криптограмма: " + encryption(input, key));

            } else if (numInput == 2) {
                System.out.println("Введите сообщение");
                String input = sc.nextLine();
                System.out.println("Введите ключ (в ключе не может быть повторяющихся символов)");

                String key = sc.nextLine();
                while (!validKey(key)) {
                    System.out.println("Недопустимый ключ, попробуйте ещё раз");
                    key = sc.nextLine();
                    validKey(key);
                }

                System.out.println("Расшифровка: " + decryption(input, key));
            } else {
                System.out.println("invalid input");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
