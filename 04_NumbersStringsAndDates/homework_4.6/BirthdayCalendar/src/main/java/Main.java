import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        int day = 31;
        int month = 12;
        int year = 1990;
        System.out.println(collectBirthdays(year, month, day));
    }

    public static String collectBirthdays(int year, int month, int day) {
         StringBuilder whatWasTheDayOnMyBirthday = new StringBuilder();
         SimpleDateFormat format = new SimpleDateFormat(" - dd.MM.yyyy - EEE", Locale.US);
         Calendar birthdayCalendar = new GregorianCalendar(year, month-1, day, 0, 0, 0); //1ый месяц в java  - 0 значение "массива", поэтому вычитаем 1 месяц
         Calendar todayCalendar = Calendar.getInstance();
         int howOld = 0;
         while (birthdayCalendar.before(todayCalendar)) {
             whatWasTheDayOnMyBirthday.append(howOld)
                     .append(format.format(birthdayCalendar.getTime()))
                     .append(System.lineSeparator());
             birthdayCalendar.add(Calendar.YEAR, 1);
             howOld++;
         }
        return whatWasTheDayOnMyBirthday.toString();
    }
}

