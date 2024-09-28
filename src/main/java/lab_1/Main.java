package lab_1;

import java.util.Scanner;

public class Main {
    private static final String initialAlphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя .,?!-";
    private static final String endAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ`~@#$%^&*()_+={}[]/|";

    public static String encryption(String input) {
        if (input == null || input.isBlank()) {
            System.out.println("invalid input");
        } else {
            for (int i = 0; i < input.length(); i++) { //проверка на знакомость символов
                String symbol = String.valueOf(input.charAt(i));
                if (!initialAlphabet.contains(symbol)) {
                    System.out.println("Незнакомый символ");
                    return null;
                }
            }
            String cryptogramm = "";
            for (int i = 0; i < input.length(); i++) {
                String symbol = String.valueOf(input.charAt(i));
                int index = initialAlphabet.indexOf(symbol);
                cryptogramm += endAlphabet.charAt(index);
            }
            return cryptogramm;
        }
        return null;
    }
    public static String decryption(String input) {
        if (input == null || input.isBlank()) {
            System.out.println("invalid input");
        } else {
            for (int i = 0; i < input.length(); i++) { //проверка на знакомость символов
                String symbol = String.valueOf(input.charAt(i));
                if (!endAlphabet.contains(symbol)) {
                    System.out.println("Незнакомый символ");
                    return null;
                }
            }
            String initText = "";
            for (int i = 0; i < input.length(); i++) {
                String symbol = String.valueOf(input.charAt(i));
                int index = endAlphabet.indexOf(symbol);
                initText += initialAlphabet.charAt(index);
            }
            return initText;
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Исходный и заменяющий алфавиты:");
            System.out.println(initialAlphabet);
            System.out.println(endAlphabet);
            System.out.println("Зашифровать сообщение - введите 1\n" +
                    "Расшифровать сообщение - введите 2");
            int numInput = Integer.parseInt(sc.nextLine());

            if (numInput == 1) {
                System.out.println("Введите сообщение");
                String input = sc.nextLine();
                System.out.println("Криптограмма: " + encryption(input));

            } else if (numInput == 2) {
                System.out.println("Введите сообщение");
                String input = sc.nextLine();
                System.out.println("Расшифровка: " + decryption(input));
            } else {
                System.out.println("invalid input");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}