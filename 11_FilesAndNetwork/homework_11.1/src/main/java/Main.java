import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("STOP")) {
                break;
            }
            if (input.equals("/")) {
                System.out.println("Уточните путь");
                continue;
            }

            FileUtils.calculateFolderSize(input);
            FileUtils.folderInfo(input);
        }
    }
}