package lab_5;

import java.util.*;

public class Main {
    private static int[] keyNumbers;

    public static String encryption(String input, int[] key) {
        String cryptogram = "";
        int n = key.length;
        int m = (int) Math.ceil((double) input.length() / n);
        String[][] table = new String[m][n];
        int counter = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (counter < input.length()) {
                    table[i][j] = String.valueOf(input.charAt(counter));
                } else {
                    table[i][j] = null;
                }
                counter++;
            }
        }
        for (String[] row : table) {
            for (String cell : row) {
                System.out.print((cell != null ? cell : "#") + " ");
            }
            System.out.println();
        }
        List<Integer> sortedKeyIndexes = new ArrayList<>();
        for (int i = 0; i < key.length; i++) {
            sortedKeyIndexes.add(i);
        }
        sortedKeyIndexes.sort(Comparator.comparingInt(a -> key[a]));
        for (int column : sortedKeyIndexes) {
            for (int i = 0; i < m; i++) {
                if (table[i][column] != null) {
                    cryptogram += table[i][column];
                }
            }
        }

        return cryptogram;
    }

    public static String decryption(String input, int[] key) {
        String transcrypt = "";
        int n = key.length;
        int m = (int) Math.ceil((double) input.length() / n);

        // Определяем количество реальных символов в последней строке
        int fullColumns = input.length() % n;
        if (fullColumns == 0) {
            fullColumns = n; // Если остаток 0, значит все столбцы заполнены
        }
        String[][] table = new String[m][n];

        List<Integer> sortedKeyIndexes = new ArrayList<>();
        for (int i = 0; i < key.length; i++) {
            sortedKeyIndexes.add(i);
        }
        sortedKeyIndexes.sort(Comparator.comparingInt(a -> key[a]));

        // Разбиваем зашифрованное сообщение на группы по столбцам
        int counter = 0;
        for (int column : sortedKeyIndexes) {
            for (int i = 0; i < m; i++) {
                if (i == m - 1 && column >= fullColumns) {
                    continue; // Пропускаем заполнение последних ячеек, если они пустые
                }
                if (counter < input.length()) {
                    table[i][column] = String.valueOf(input.charAt(counter));
                    counter++;
                }
            }
        }
        for (String[] row : table) {
            for (String cell : row) {
                System.out.print((cell != null ? cell : "#") + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] != null) {
                    transcrypt += table[i][j];
                }
            }
        }

        return transcrypt;
    }

    public static boolean isNotValidKey(String key) {
        if (key.isBlank()) return true;
        String[] keyArray = key.split(" ");
        keyNumbers = new int[keyArray.length];
        try {
            for (int i = 0; i < keyArray.length; i++) {
                int number = Integer.parseInt(keyArray[i]);
                keyNumbers[i] = number;
            }
        } catch (NumberFormatException e) {
            return true;
        }
        List<Integer> keyNumbersList = new ArrayList<>();
        for (int num : keyNumbers) {
            keyNumbersList.add(num);
        }
        keyNumbersList.sort(Comparator.comparingInt(Integer::intValue));
        if (keyNumbersList.get(0) < 1) return true;
        for (int i = 1; i < keyNumbersList.size(); i++) {
            if (keyNumbersList.get(i) - 1 != keyNumbersList.get(i - 1)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        /*try {*/
        Scanner sc = new Scanner(System.in);
        System.out.println("Зашифровать сообщение - введите 1\n" +
                "Расшифровать сообщение - введите 2");
        int numInput = Integer.parseInt(sc.nextLine());

        if (numInput == 1) {
            System.out.println("Введите сообщение");
            String input = sc.nextLine();
            System.out.println("Введите ключ (последовательность чисел, начиная с 1, в произвольном порядке) " +
                    "в одну строку и через пробел)");

            String key = sc.nextLine();
            while (isNotValidKey(key)) {
                System.out.println("Недопустимый ключ, попробуйте ещё раз");
                key = sc.nextLine();
            }
            System.out.println("Криптограмма: " + encryption(input, keyNumbers));

        } else if (numInput == 2) {
            System.out.println("Введите сообщение");
            String input = sc.nextLine();
            System.out.println("Введите ключ (последовательность чисел, начиная с 1, в произвольном порядке) " +
                    "в одну строку и через пробел)");

            String key = sc.nextLine();
            while (isNotValidKey(key)) {
                System.out.println("Недопустимый ключ, попробуйте ещё раз");
                key = sc.nextLine();
            }

            System.out.println("Расшифровка: " + decryption(input, keyNumbers));
        } else {
            System.out.println("invalid input");
        }
        /*} catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
    }
}
