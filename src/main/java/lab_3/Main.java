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

    public static String encryption(String input, int[] contourLengths) {
        String cryptogram = "";
        int i = 0, counter = 0;

        while (i < input.length()) {
            for (int contourIndex = 0; contourIndex < contourLengths.length; contourIndex++) {
                counter = counter > 3 ? 0 : counter;
                int contourLength = contourLengths[contourIndex];
                for (int length = 0; length < contourLength; length++) {
                    if (i >= input.length()) {
                        break;
                    }
                    int indexString = initialAlphabet.indexOf(input.charAt(i));
                    cryptogram += endAlphabets[contourIndex][counter % 3].charAt(indexString);
                    i++;
                    counter++;
                }
                if (i >= input.length()) {
                    break;
                }
            }
        }
        return cryptogram;
    }

    public static String decryption(String input, int[] contourLengths) {
        String transcrypt = "";
        int i = 0, counter = 0;

        while (i < input.length()) {
            for (int contourIndex = 0; contourIndex < contourLengths.length; contourIndex++) {
                counter = counter > 3 ? 0 : counter;
                int contourLength = contourLengths[contourIndex];
                for (int length = 0; length < contourLength; length++) {
                    if (i >= input.length()) {
                        break;
                    }
                    int indexString = endAlphabets[contourIndex][counter % 3].indexOf(input.charAt(i));
                    transcrypt += initialAlphabet.charAt(indexString);
                    i++;
                    counter++;
                }
                if (i >= input.length()) {
                    break;
                }
            }
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

                System.out.println("Введите период применения для каждого отдельного контура\n" +
                        "(натуральными числами, через enter)");
                int contour1 = sc.nextInt();
                int contour2 = sc.nextInt();
                int contour3 = sc.nextInt();
                int[] contourLengths = new int[]{contour1, contour2, contour3};

                System.out.println("Криптограмма: " + encryption(input, contourLengths));

            } else if (numInput == 2) {
                System.out.println("Введите сообщение");
                String input = sc.nextLine();

                System.out.println("Введите период применения для каждого отдельного контура\n" +
                        "(натуральными числами, через enter)");
                int contour1 = sc.nextInt();
                int contour2 = sc.nextInt();
                int contour3 = sc.nextInt();
                int[] contourLengths = new int[]{contour1, contour2, contour3};

                System.out.println("Расшифровка: " + decryption(input, contourLengths));
            } else {
                System.out.println("invalid input");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}