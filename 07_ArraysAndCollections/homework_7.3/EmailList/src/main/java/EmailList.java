import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

public class EmailList {
    private static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
    Collection<String> setOfEmails = new TreeSet<>();

    public void add(String email) {
        email = email.toLowerCase();
        if (email.matches("\\D+@\\D+\\.\\D+")) {
            setOfEmails.add(email);
            System.out.println(email + " добавлен");
        } else
            System.out.println(WRONG_EMAIL_ANSWER);
    }

    public Collection<String> getSortedEmails() {
        ArrayList<String> arrayListOfEmails = new ArrayList<>(setOfEmails);
        arrayListOfEmails.forEach(System.out::println);
        return arrayListOfEmails;
    }
}
