import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;
    private final String exMessage = ", воспользуйтесь командой \"help\"";

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        final String phoneMatches = "(\\+*)\\d{11}";
        final String emailMatches = "\\D+@\\D+\\.\\D+";

        String[] components = data.split("\\s+");
        if (components.length != 4)
            throw new AddCustomerException(exMessage);
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];

        if (!components[INDEX_PHONE].matches(phoneMatches))
            throw new WrongCustomerPhone(exMessage);

        if (!components[INDEX_EMAIL].matches(emailMatches))
            throw new WrongCustomerEmail(exMessage);

        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));

    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}

class AddCustomerException extends RuntimeException {
    public AddCustomerException(String msg) {
        System.out.println("Неверное добавление Customer" + msg);
    }
}

class WrongCustomerEmail extends RuntimeException {
    public WrongCustomerEmail(String msg) {
        System.out.println("Неверный формат E-mail" + msg);
    }
}

class WrongCustomerPhone extends RuntimeException {
    public WrongCustomerPhone(String msg) {
        System.out.println("Неверный формат телефона" + msg);
    }
}