import java.text.Collator;
import java.util.*;

public class EmailList {
    Collection<String> listOfEmails = new TreeSet<>(Collator.getInstance());

    public void add(String email) {
        email = email.toLowerCase();
        if (email.matches("\\D+@\\D+\\.\\D+")) {
            listOfEmails.add(email);
        } else
            System.out.println(Main.WRONG_EMAIL_ANSWER);
    }

    public Collection<String> getSortedEmails() {
        TreeSet treeSet = new TreeSet(listOfEmails);
        System.out.println(listOfEmails);
        return new ArrayList<>(treeSet);
    }
}
