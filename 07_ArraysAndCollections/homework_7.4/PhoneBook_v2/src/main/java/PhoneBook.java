import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {
    private final static String NUMBER_VALIDATION = "[0-9]+";
    private final static String NAME_VALIDATION = "^[а-яА-Я\\s]+";
    private final HashMap<String, Set<String>> phoneBook = new HashMap<>();

    public void addContact(String name, String phone) {
        if (phone.matches(NUMBER_VALIDATION) && name.matches(NAME_VALIDATION)) {
            if (phoneBook.containsKey(name)) {
                phoneBook.get(name).add(phone);
                System.out.printf("Для контакта с именем %s добавлен номер %s \n", name, phone);
            } else {
                Set<String> phonesNumbers = new TreeSet<>();
                phonesNumbers.add(phone);
                phoneBook.put(name, phonesNumbers);
                System.out.printf("Создан новый контакт %s и к нему добавлен номер %s \n", name, phone);
            }
        } else {
            System.out.println("Передан неверный формат телефонного номера");
        }
    }

    public void getNameByPhone(String phone) {
        String result =
                phoneBook.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().contains(phone))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toSet()).toString();
        System.out.println(result);
    }

    public void getPhonesByName(String name) {
        String result = phoneBook.getOrDefault(name, Collections.emptySet()).toString();
        System.out.println(result);
    }

    public void getAllContacts() {
        phoneBook.forEach((k, v) -> System.out.println(k + v));
    }
}