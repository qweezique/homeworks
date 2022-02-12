import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;


public class MongoService {

    private final MongoClient mongoClient = new MongoClient("localhost", 27017);
    private final MongoDatabase database = mongoClient.getDatabase("goods_administrator");
    private final MongoCollection<Document> collectionShops = database.getCollection("shops");
    private final MongoCollection<Document> collectionProducts = database.getCollection("products");

    private static void printDocument(Iterable<Document> objects) {
        objects.forEach(document -> document.forEach((key, value) -> System.out.println(key + " = " + value)));
    }


    public void resetDataBase() {
        collectionShops.drop();
        collectionProducts.drop();
        database.drop();
    }

    public void addShop(String shop) {
        collectionShops.insertOne(new Document()
                .append("name", shop)
                .append("products", Collections.emptyList()));
    }

    public void addProduct(int productNumber, String product, int price, String description) {
        collectionProducts.insertOne(new Document()
                .append("product number", productNumber)
                .append("name", product)
                .append("price", price)
                .append("description", description));
    }

    public void addProductToShop(int productNumber, String shop) {
        Bson findShop = collectionShops.find(eq("name", shop)).first();
        Bson findProduct = collectionProducts.find(eq("product number", productNumber)).first();
        if (findProduct != null && findShop != null) {
            Bson getProductName = new Document("products", collectionProducts.find(eq("product number", productNumber)).first().get("name"));
            Bson addProductToSet = new Document("$addToSet", getProductName);
            collectionShops.updateOne(findShop, addProductToSet);
            System.out.println("Товар добавлен в магазин");
        } else {
            System.out.println("Товар или магазин не найдены");
        }
    }

    public void getProductsCount() {

        AggregateIterable<Document> result = collectionShops.aggregate(
                Arrays.asList(new Document("$unwind",
                                new Document("path", "$products")),
                        new Document("$group",
                                new Document("_id", "$name")
                                        .append("count",
                                                new Document("$sum", 1L))),
                        new Document("$sort",
                                new Document("count", -1L))));

        printDocument(result);

    }

    public void getAveragePrice() {

        AggregateIterable<Document> result = collectionShops.aggregate(Arrays.asList(new Document("$lookup",
                        new Document("from", "products")
                                .append("localField", "products")
                                .append("foreignField", "name")
                                .append("as", "list")),
                new Document("$unwind",
                        new Document("path", "$list")),
                new Document("$group",
                        new Document("_id", "$name")
                                .append("avgPrice",
                                        new Document("$avg", "$list.price"))))
        );

        printDocument(result);


    }

    public void getCheapestItem() {
        AggregateIterable<Document> result = collectionShops.aggregate(Arrays.asList(new Document("$lookup",
                        new Document("from", "products")
                                .append("localField", "products")
                                .append("foreignField", "name")
                                .append("as", "list")),
                new Document("$unwind",
                        new Document("path", "$list")),
                new Document("$sort",
                        new Document("list.price", 1L)),
                new Document("$group",
                        new Document("_id", "$name")
                                .append("Name",
                                        new Document("$first", "$list.name"))
                                .append("Price",
                                        new Document("$first", "$list.price"))))
        );

        printDocument(result);
    }


    public void getMostExpensiveItem() {
        AggregateIterable<Document> result = collectionShops.aggregate(Arrays.asList(new Document("$lookup",
                        new Document("from", "products")
                                .append("localField", "products")
                                .append("foreignField", "name")
                                .append("as", "list")),
                new Document("$unwind",
                        new Document("path", "$list")),
                new Document("$sort",
                        new Document("list.price", -1L)),
                new Document("$group",
                        new Document("_id", "$name")
                                .append("Name",
                                        new Document("$first", "$list.name"))
                                .append("Price",
                                        new Document("$first", "$list.price"))))
        );

        printDocument(result);
    }


    public void getProductsCountLessThan(long value) {
        AggregateIterable<Document> result = collectionProducts.aggregate(Arrays.asList(
                new Document("$lookup", new Document("from", "shops")
                        .append("localField", "name")
                        .append("foreignField", "products")
                        .append("as", "shopsList")),
                new Document("$unwind", new Document("path", "$shopsList")),
                new Document("$match", new Document("price", new Document("$lt", value))),
                new Document("$group", new Document("_id", "$shopsList.name")
                        .append("lt" + value + "GoodsCount", new Document("$sum", 1L))),
                new Document("$sort", new Document("_id", -1L))));

        printDocument(result);
    }
}