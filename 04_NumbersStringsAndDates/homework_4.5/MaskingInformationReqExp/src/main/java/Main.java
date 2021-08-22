import com.sun.source.doctree.StartElementTree;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String text = input;
        String placeholder = "***";
        while (input.contains("<") && input.contains(">")) {
            input = searchAndReplaceDiamonds(text, placeholder);
            System.out.printf(input);
        }
    }
    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений
            String newText = text.replaceAll("<[^>]+>", placeholder);
            return newText;
    }
}