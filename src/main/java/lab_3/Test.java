package lab_3;

import java.util.Scanner;

public class Test {

    public static String lol(String input) {
        String newString = "";
        newString += input.charAt(input.length() - 1);
        for (int i = 0; i < input.length() - 1; i++) {
            newString += input.charAt(i);
        }
        return newString;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("end")) {
            System.out.println(lol(input));
            input = sc.nextLine();
        }
    }
}
