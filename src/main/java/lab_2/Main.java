package lab_2;

import java.util.Scanner;

public class Main {
    private static final String initialAlphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя .,?!-";
    private static final String end_Alphabet_Fi = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ`~@#$%^&*()_+={}[]/|";
    private static final String end_Alphabet_Se = "+*PmIUrDJo)EA&SZLpV#}csg]xnu^%iq=GYNtzb[Xw{`Wv(Q@deB~KCFMjT/O_$|HkfRylha";
    private static final String end_Alphabet_Th = "F_KOHi^mvzUXoD(weAky%TSEhI|V[Yt~qlgZ#`u/JQrLGx{)p&$WBP}Nda=bCfRj@s]c*Mn+";
    private static final String[] endAlphabets = {end_Alphabet_Fi, end_Alphabet_Se, end_Alphabet_Th};

    public static String encryption(String input) {
        // АББА
        String cryptogram = "";
        int counter = 0;

        for (int i = 0; i < input.length(); i++) {
            counter = counter == 3 ? 0 : counter;
            int index = initialAlphabet.indexOf(input.charAt(i));
            cryptogram += endAlphabets[counter].charAt(index);
            counter++;
        }
        return cryptogram;
    }

    public static String decryption(String input) {
        String transcrypt = "";
        int counter = 0;

        for (int i = 0; i < input.length(); i++) {
            counter = counter == 3 ? 0 : counter;
            int index = endAlphabets[counter].indexOf(input.charAt(i));
            transcrypt += initialAlphabet.charAt(index);
            counter++;
        }
        return transcrypt;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Исходный и заменяющий алфавиты:");
            System.out.println(initialAlphabet);
            System.out.println(end_Alphabet_Fi);
            System.out.println(end_Alphabet_Se);
            System.out.println(end_Alphabet_Th);
            System.out.println();
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
