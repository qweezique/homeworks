import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите путь копирования");
            String sourceInput = scanner.nextLine();
            if (sourceInput.equals("STOP")) {
                break;
            }

            System.out.println("Введите путь вставки");
            String destinationInput = scanner.nextLine();
            if (destinationInput.equals("STOP")) {
                break;

            }
            if (sourceInput.equals(destinationInput)) {
                System.out.println("Защита от дублирования");
                continue;
            }
            FileUtils.copyFolder(sourceInput, destinationInput);
        }
    }
}