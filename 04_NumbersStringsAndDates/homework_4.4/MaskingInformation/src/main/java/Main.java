import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String text = input;
        String placeholder = " *** ";
        while (input.contains("<") && input.contains(">")) {
            input = searchAndReplaceDiamonds(text, placeholder);
            System.out.printf(input);
        }

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений
        int indexStart = text.indexOf('<');
        int indexEnd = text.indexOf('>');

        if (indexStart > -1 && indexEnd > -1) {
            text = text.substring(0, indexStart) + placeholder + text.substring(indexEnd+1);
        }

        // Задание *
        int lastIndexStart = text.lastIndexOf('<');
        int lastIndexEnd = text.lastIndexOf('>');

        if (lastIndexStart > -1 && lastIndexEnd > -1) {
            text = text.substring(0, lastIndexStart) + placeholder + text.substring(lastIndexEnd+1);
        }
        return text;
    }

}