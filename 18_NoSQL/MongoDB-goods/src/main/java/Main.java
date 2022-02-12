import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MongoService mongo = new MongoService();
        Scanner scanner = new Scanner(System.in);
        GoodsController goodsController = new GoodsController(mongo, scanner); //Для работы программы необходим экземпляр GoodsController
//        mongo.resetDataBase();


        System.out.println("Введите команду или \"HELP\" ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("HELP")) {
                System.out.println("""
                        * Введите команду из списка ниже, затем следуйте инструкциям:
                        + "ДОБАВИТЬ_МАГАЗИН" или "ADD_SHOP" - добавление магазина в базу данных
                        + "ДОБАВИТЬ_ТОВАР" или "ADD_PRODUCT" - добавление товара на склад
                        + "ВЫСТАВИТЬ_ТОВАР" или "PLACE_PRODUCT" - выставляем товар со склада в магазин
                        + "СТАТИСТИКА" - показать статистику по товарам
                        + "СТОП" или "STOP" - завершение работы
                        """);
            }
            if (input.equals("ДОБАВИТЬ_МАГАЗИН") || input.equals("ADD_SHOP")) {
                goodsController.addShop();
            }

            if (input.equals("ДОБАВИТЬ_ТОВАР") || input.equals("ADD_PRODUCT")) {
                goodsController.addProduct();
            }

            if (input.equals("ВЫСТАВИТЬ_ТОВАР") || input.equals("PLACE_PRODUCT")) {
                goodsController.addProductToShop();
            }
            if (input.equals("СТАТИСТИКА")) {
                goodsController.showStatistics();
            }
            if (input.equals("СТОП") || input.equals("STOP")) {
                break;
            }
        }
    }
}