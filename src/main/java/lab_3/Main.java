package lab_3;

import java.util.Scanner;

public class Main {
    private static final String initialAlphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя .,?!-";
    private static final String endAlphabet00 = "F_KOHi^mvzUXoD(weAky%TSEhI|V[Yt~qlgZ#`u/JQrLGx{)p&$WBP}Nda=bCfRj@s]c*Mn+";
    private static final String endAlphabet01 = "n+F_KOHi^mvzUXoD(weAky%TSEhI|V[Yt~qlgZ#`u/JQrLGx{)p&$WBP}Nda=bCfRj@s]c*M";
    private static final String endAlphabet02 = "c*Mn+F_KOHi^mvzUXoD(weAky%TSEhI|V[Yt~qlgZ#`u/JQrLGx{)p&$WBP}Nda=bCfRj@s]";

    private static final String endAlphabet10 = "j@s]c*Mn+F_KOHi^mvzUXoD(weAky%TSEhI|V[Yt~qlgZ#`u/JQrLGx{)p&$WBP}Nda=bCfR";
    private static final String endAlphabet11 = "a=bCfRj@s]c*Mn+F_KOHi^mvzUXoD(weAky%TSEhI|V[Yt~qlgZ#`u/JQrLGx{)p&$WBP}Nd";
    private static final String endAlphabet12 = "p&$WBP}Nda=bCfRj@s]c*Mn+F_KOHi^mvzUXoD(weAky%TSEhI|V[Yt~qlgZ#`u/JQrLGx{)";

    private static final String endAlphabet20 = "gZ#`u/JQrLGx{)p&$WBP}Nda=bCfRj@s]c*Mn+F_KOHi^mvzUXoD(weAky%TSEhI|V[Yt~ql";
    private static final String endAlphabet21 = "oD(weAky%TSEhI|V[Yt~qlgZ#`u/JQrLGx{)p&$WBP}Nda=bCfRj@s]c*Mn+F_KOHi^mvzUX";
    private static final String endAlphabet22 = "TSEhI|V[Yt~qlgZ#`u/JQrLGx{)p&$WBP}Nda=bCfRj@s]c*Mn+F_KOHi^mvzUXoD(weAky%";
    private static final String[][] endAlphabets = {
            {endAlphabet00, endAlphabet01, endAlphabet02},
            {endAlphabet10, endAlphabet11, endAlphabet12},
            {endAlphabet20, endAlphabet21, endAlphabet22}
    };

    public static String encryption(String input) {
        String cryptogram = "";
        int counterX = 0, counterY = 0;

        for (int i = 0; i < input.length(); i++) {
            if (counterX == 3) {
                counterX = 0;
                counterY++;
            }
            counterY = counterY == 3 ? 0 : counterY;
            int indexString = initialAlphabet.indexOf(input.charAt(i));
            cryptogram += endAlphabets[counterY][counterX].charAt(indexString);
            counterX++;
        }

        return cryptogram;
    }

    public static String decryption(String input) {
        String transcrypt = "";
        int counterX = 0, counterY = 0;

        for (int i = 0; i < input.length(); i++) {
            if (counterX == 3) {
                counterX = 0;
                counterY++;
            }
            counterY = counterY == 3 ? 0 : counterY;
            int indexString = endAlphabets[counterY][counterX].indexOf(input.charAt(i));
            transcrypt += initialAlphabet.charAt(indexString);
            counterX++;
        }

        return transcrypt;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Исходный и заменяющий алфавиты:");
            for (String[] array : endAlphabets) {
                System.out.println(initialAlphabet);
                for (String string : array) {
                    System.out.println(string);
                }
                System.out.println();
            }

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
