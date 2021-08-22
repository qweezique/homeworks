import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    String text = input;

  }

  public static int calculateSalarySum(String text){
    //TODO: реализуйте метод
    int friendsIncome = 0;
    Pattern digits = Pattern.compile("\\b\\d+\\b");
    Matcher matcher = digits.matcher(text);
    while (matcher.find()) {
      friendsIncome +=Integer.parseInt(matcher.group());
    }
    return friendsIncome;
    }
  }
