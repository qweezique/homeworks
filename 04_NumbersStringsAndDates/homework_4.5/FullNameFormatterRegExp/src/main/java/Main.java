import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            String[] fio = input.split(" ");
            if (!input.matches("[а-яА-Я -]+") || fio.length != 3)
                System.out.println("Введенная строка не является ФИО");
            else
                System.out.printf("Фамилия: %s%nИмя: %s%nОтчество: %s", fio[0], fio[1], fio[2]);
        }
    }
}