import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

  }

  public static String splitTextInToWords(String text) {
    //TODO реализуйте метод
    String correctedText = text.trim().replaceAll("[0-9!,?;:.]", "").replace("-"," ").replaceAll("\\s+", "\n");
    return correctedText;
  }

}