import java.util.Scanner;

public class GoodsController {
    private final MongoService mongoService;
    private final Scanner scanner;
    private int productNumber;

    public GoodsController(MongoService mongoService, Scanner scanner) {
        this.mongoService = mongoService;
        this.scanner = scanner;
    }

    public void addShop() {
        System.out.println("Введите название магазина: ");
        String shopName = scanner.nextLine().toUpperCase().trim();
        mongoService.addShop(shopName);
        System.out.println("Магазин " + "\"" + shopName + "\"" + " добавлен");
    }

    public void addProduct() {
        System.out.println("Введите название товара:");
        String productName = scanner.nextLine().toUpperCase().trim();
        System.out.println("Введите цену товара:");
        int productPrice = scanner.nextInt();
        System.out.println("Вы можете добавить описание товара:");
        scanner.nextLine();
        String productDescription = scanner.nextLine();

        int thisProductNumber = ++productNumber;
        mongoService.addProduct(thisProductNumber, productName, productPrice, productDescription);
        String confirmation = "Товар " + "\"" + productName + "\"" + " добавлен: "
                + "\nНомер в каталоге: " + thisProductNumber
                + "\nЦена: " + productPrice
                + "\nОписание: " + productDescription;
        System.out.println(confirmation);
    }

    public void addProductToShop() {
        System.out.println("Введите номер товара по каталогу:");
        int productNumber = scanner.nextInt();
        System.out.println("Введите название магазина:");
        scanner.nextLine(); // после scanner.nextInt() добавляю эту строчку, иначе программа не дожидается ввода названия магазина
        String shopName = scanner.nextLine().toUpperCase().trim();
        mongoService.addProductToShop(productNumber, shopName);
    }

    public void showStatistics() {
        showStatisticsLabel();
        String confirmation = scanner.nextLine();
        switch (confirmation) {
            case "1" -> mongoService.getProductsCount();
            case "2" -> mongoService.getAveragePrice();
            case "3" -> mongoService.getCheapestItem();
            case "4" -> mongoService.getMostExpensiveItem();
            case "5" -> {
                System.out.println("Дешевле какой суммы вам нужно сосчиать количество продуктов в магазине? Введите значение:");
                long value = scanner.nextLong();    //не стал обрабатывать null pointer exception
                mongoService.getProductsCountLessThan(value);
            }
            default -> {
                System.out.println("Выберите один из преложенных выраиантов ниже, введите цифру\n");
                showStatistics();
            }
        }
    }

    public void showStatisticsLabel() {
        System.out.println("""
                Какую статистику вы хотите увидеть?
                1. Количество выставленных товаров в магазинах.
                2. Средняя цена товаров в магазине.
                3. Самый дешевый товар в магазине.
                4. Самый дорогой товар в магазине.
                5. Количество товаров дешевле Х рублей.
                """);
    }
}
