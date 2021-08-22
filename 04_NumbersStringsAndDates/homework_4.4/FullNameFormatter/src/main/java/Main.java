import java.util.Scanner;

public class Main {

    public static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static boolean isDigit(char ch) {
        for (char digit : digits) {
            if (ch == digit)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String surname; //фамилия
        String name; //имя
        String lastname; //отчество

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int spaceCount = 0;
            boolean readyToPrint = true; //при значении true - мы будем готовы печататать ФИО, если конечно пройдем все проверки

            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            for (int i = 0; i < input.length(); i++) { //В одном цикле for проверяем два условия: на цифры и считаем пробелы

                if (isDigit(input.charAt(i))) { //Проверка char на соответствие символу
                    readyToPrint = false;
                }
                if (Character.isSpaceChar(input.charAt(i))) {
                    spaceCount++;
                }
            }

            if (spaceCount != 2) {
                readyToPrint = false;
            }

            if (readyToPrint) {
                surname = input.substring(0, input.indexOf(" "));
                name = input.substring(input.indexOf(" "), input.lastIndexOf(" "));
                lastname = input.substring(input.lastIndexOf(" "));

                System.out.println("Фамилия: " + surname);
                System.out.println("Имя:" + name);
                System.out.println("Отчество:" + lastname);

            } else {
                System.out.println("Введенная строка не является ФИО");
            }
        }
    }
}