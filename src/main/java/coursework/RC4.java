package coursework;

import java.io.*;
import java.util.*;

public class RC4 {

    private static final String initialAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-–_=+[]{}<>\\|;:'\",./?`~ ";

    public static int[] KSA(byte[] key) {
        int[] S = new int[initialAlphabet.length()];
        for (int i = 0; i < S.length; i++) {
            S[i] = i;
        }
        int j = 0;
        for (int i = 0; i < S.length; i++) {
            j = (j + S[i] + (key[i % key.length] & 0xFF)) % S.length;
            int temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }
        return S;
    }

    public static Iterator<Integer> PRGA(int[] S) {
        return new Iterator<>() {
            int i = 0, j = 0;

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Integer next() {
                i = (i + 1) % S.length;
                j = (j + S[i]) % S.length;
                int temp = S[i];
                S[i] = S[j];
                S[j] = temp;
                return S[(S[i] + S[j]) % S.length];
            }
        };
    }

    public static String encryption(byte[] key, String input) {
        StringBuilder cryptogram = new StringBuilder();
        int[] S = KSA(key);
        Iterator<Integer> keyStream = PRGA(S);

        for (char character : input.toCharArray()) {
            if (!initialAlphabet.contains(String.valueOf(character))) {
                cryptogram.append(character);
                continue;
            }
            int keyStreamValue = keyStream.next();
            int charIndex = initialAlphabet.indexOf(character);
            int newIndex = (charIndex + keyStreamValue) % initialAlphabet.length();
            cryptogram.append(initialAlphabet.charAt(newIndex));
        }
        return cryptogram.toString();
    }

    public static String decryption(byte[] key, String input) {
        StringBuilder transcrypt = new StringBuilder();
        int[] S = KSA(key);
        Iterator<Integer> keyStream = PRGA(S);

        for (char character : input.toCharArray()) {
            if (!initialAlphabet.contains(String.valueOf(character))) {
                transcrypt.append(character);
                continue;
            }
            int keyStreamValue = keyStream.next();
            int charIndex = initialAlphabet.indexOf(character);
            int newIndex = (charIndex - keyStreamValue + initialAlphabet.length()) % initialAlphabet.length();
            transcrypt.append(initialAlphabet.charAt(newIndex));
        }
        return transcrypt.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Зашифровать");
            System.out.println("2. Расшифровать");
            System.out.println("3. Зашифровать файл");
            System.out.println("4. Расшифровать файл");
            System.out.println("5. Выход");

            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.println("Введите текст для зашифровки");
                String input = scanner.nextLine();

                System.out.println("Введите ключ");
                String key = scanner.nextLine();

                System.out.println("Криптограмма: " + encryption(key.getBytes(), input));
            } else if (choice.equals("2")) {
                System.out.println("Введите текст для расшифровки");
                String input = scanner.nextLine();

                System.out.println("Введите ключ");
                String key = scanner.nextLine();

                System.out.println("Расшифровка: " + decryption(key.getBytes(), input));
            } else if (choice.equals("3")) {
                System.out.println("Введите название файла, который нужно зашифровать");
                String inputFileName = scanner.nextLine();

                System.out.println("Введите название, под которым будет сохранён зашифрованный файл");
                String outputFileName = scanner.nextLine();

                System.out.println("Введите ключ");
                String key = scanner.nextLine();

                try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(encryption(key.getBytes(), line));
                        writer.newLine();
                    }
                    System.out.println("Файл успешно зашифрован");
                } catch (Exception e) {
                    System.out.println("Возможно, вы указали имя несуществующего файла, повторите попытку ещё раз");
                }
            } else if (choice.equals("4")) {
                System.out.println("Введите название файла, который нужно расшифровать");
                String inputFileName = scanner.nextLine();

                System.out.println("Введите название, под которым будет сохранён расшифрованный файл");
                String outputFileName = scanner.nextLine();

                System.out.println("Введите ключ");
                String key = scanner.nextLine();

                try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(decryption(key.getBytes(), line));
                        writer.newLine();
                    }
                    System.out.println("Файл успешно расшифрован");
                } catch (Exception e) {
                    System.out.println("Возможно, вы указали имя несуществующего файла, повторите попытку ещё раз");
                }
            } else if (choice.equals("5")) {
                break;
            } else {
                System.out.println("Неверный ввод, повторите попытку");
            }
        }
    }
}
