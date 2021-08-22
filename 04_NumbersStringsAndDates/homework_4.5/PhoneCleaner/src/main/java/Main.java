import javax.swing.*;
import java.lang.module.FindException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      //TODO:напишите ваш код тут, результат вывести в консоль.
      String bezProbelov = input.replaceAll("[^\\d]+", ""); //Оставляем только цифры
      Pattern p = Pattern.compile("[9]\\d{9}"); //Приводим к 10 числам (формату 9*********)
      Matcher m = p.matcher(bezProbelov);

      // Запрет на использование более 11 или менее 10 цифр в телефоне; защита от кода страны НЕ 7 или 8

      if (bezProbelov.length() > 11 || bezProbelov.length() < 10 || bezProbelov.matches("([^7-8]\\d{10})"))
        System.out.println("Неверный формат номера");
      else
      while (m.find())
        System.out.printf("7"+m.group());
    }
  }
}
