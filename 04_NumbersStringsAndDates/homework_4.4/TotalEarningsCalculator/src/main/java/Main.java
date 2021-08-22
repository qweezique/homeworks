import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    //TODO: напишите ваш код, результат вывести в консоль
    char [] a = text.toCharArray();
    StringBuilder builder = new StringBuilder();
    List <Integer> integers = new ArrayList<>();
    for (int i = 0; i < text.length(); i++){
      if ((char) a[i] >= 48 && (char)a[i] <= 57){
        builder.append(a[i]);
        if ((char) a[i+1] < 48 || (char) a[i+1] > 57){
          integers.add(Integer.parseInt(builder.toString()));
          builder = new StringBuilder();

          }
      }
    }
    System.out.println(integers.stream().mapToInt(Integer::intValue).sum());
  }
}