import java.util.Scanner;

public class Main {
    private static EmailList emailList = new EmailList();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            if (input.contains("ADD")) {
                emailList.add(input.replace("ADD", "").trim());
            }

            if (input.contains("LIST")) {
                emailList.getSortedEmails();
            }
        }
    }
}
