import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook myPhoneBook = new PhoneBook();
        System.out.println("Введите номер, имя или команду");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("STOP")) {
                break;
            }
            if (input.equals("LIST")) {
                myPhoneBook.getAllContacts();
            }
            //Вводим номер телефона (Key) - затем вводим Имя (Value);
            // если такого телефона (K) нет,
            // то просим указать Имя (Value) затем вводим имя, сохраняем "addContact"
            if (input.matches(myPhoneBook.NUMBER_VALIDATION)) {
                if (myPhoneBook.phoneBook.containsKey(input)) {
                    System.out.println("Данный номер телефона уже содержится в телефонной книге");
                    System.out.println(myPhoneBook.getNameByPhone(input));
                    continue;
                }
                System.out.println("Введите имя контата для номера " + input.trim());
                String nameInput = scanner.nextLine();
                myPhoneBook.addContact(input, nameInput);
            }
            //Вводим Имя (Value)
            // Если нет такого имени в тлф книге, то добавляем номер и сохраняем addContact
            // Если есть, просто добавляем дополнительный номер телефона
            if (input.matches(myPhoneBook.NAME_VALIDATION)) {
                String nameInput = input.trim();
                if (myPhoneBook.phoneBook.containsValue(nameInput)) {
                    System.out.println(myPhoneBook.getPhonesByName(nameInput));
                    System.out.println("Данный контакт уже есть в телефонной книге, впишите дополнительный номер телефона:");
                    String keyInput = scanner.nextLine();
                    myPhoneBook.addContact(keyInput, nameInput);
                    continue;
                } else
                    System.out.println("Контакта с именем" + nameInput + " нет в телефонной книге, введите номер телефона для сохранения");
                String keyInput = scanner.nextLine();
                myPhoneBook.addContact(keyInput, nameInput);
            }
        }
    }
}