public class Main {
    public static void main(String[] args) {
        PhoneBook myPhoneBook = new PhoneBook();

        myPhoneBook.addContact("Никита", "79220211976");
        myPhoneBook.addContact("Никита", "79220211976");
        myPhoneBook.addContact("Никита", "2511816");
        myPhoneBook.addContact("Никита", "34332144");
        myPhoneBook.addContact("Никита", "not-phone-number");
        System.out.println();

        myPhoneBook.addContact("Миша", "79040235456");
        myPhoneBook.addContact("Миша", "79220211976"); //добавим телефон, как у Никиты
        myPhoneBook.addContact("Миша", "500299");
        System.out.println();

        myPhoneBook.addContact("Катя", "99999");
        System.out.println();

        System.out.println("Все контакты:");
        myPhoneBook.getAllContacts();
        System.out.println();

        System.out.println("Поиск телефонов по имени \"Миша\"");
        myPhoneBook.getPhonesByName("Миша");
        System.out.println("Передает несуществующее имя \"Гоги\"");
        myPhoneBook.getPhonesByName("Гоги");

        System.out.println("Поиск контактов по телефону 79220211976 (у Миши и Никиты одинаковые)");
        myPhoneBook.getNameByPhone("79220211976");

        System.out.println("Поиск контактов по телефону 99999 (Катя)");
        myPhoneBook.getNameByPhone("99999");

        System.out.println("Поиск контактов по телефону 42141241241 (Не сушествует)");
        myPhoneBook.getNameByPhone("42141241241");
    }
}