import org.antlr.v4.runtime.tree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PhoneBook {
    public final String NUMBER_VALIDATION = "[0-9]+";
    public final String NAME_VALIDATION = "^[а-яА-Я\\s]+";

    HashMap<String, String> phoneBook = new HashMap<>();

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if (phone.matches(NUMBER_VALIDATION) && name.matches(NAME_VALIDATION)) {
            phoneBook.put(phone, name);
            System.out.println("Контакт " + name + " сохранен");
        } else {
            System.out.println("Передан неверный формат телефонного номера");
        }
    }

    public String getNameByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        String res = "";
        if (phoneBook.containsKey(phone)) {
            res = phoneBook.get(phone) + " - " + phone;
        }
        return res;
    }

    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        TreeSet<String> res = new TreeSet<>();
        for (Map.Entry<String, String> entry : phoneBook.entrySet())
            if (entry.getValue().equals(name)) {
                res.add(name + " - " + entry.getKey());
//                System.out.println(entry);
            }
        return res;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        TreeSet<String> res = new TreeSet<>();
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            res.add(entry.getValue() + " - " + entry.getKey());
        }
        System.out.println(res);
        return res;
    }
}