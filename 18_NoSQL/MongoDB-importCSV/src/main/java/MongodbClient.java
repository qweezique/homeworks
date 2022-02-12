import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.function.Consumer;


public class MongodbClient {

    private static final String DATABASE_NAME = "user"; // You should use your database name

    com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
    public MongoCollection<Document> booksCollection = database.getCollection("books");
    public MongoCollection<Document> studentsCollection = database.getCollection("students");

    public void printAllCollections() {
        Iterable<String> allCollectionsName = database.listCollectionNames();
        System.out.printf("Collections in MongoDB \"%s\":\n", database.getName());
        allCollectionsName.forEach(System.out::println);
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);

    }

    public void printCollection(MongoCollection<Document> mongoCollection) {
        mongoCollection.find().forEach((Consumer<Document>) System.out::println);
    }

    public void importCSVfile(String absolutePath) {
        Runtime r = Runtime.getRuntime();
        Process p = null;
        String command = "mongoimport " +
                "--db user " +
                "--collection students " +
                "--type csv " +
                "--file " + absolutePath +
                " --fields \"name,age,courses\"";

        try {
            p = r.exec(command);
            System.out.println("IMPORTING CSV FILE TO DATABASE AS COLLECTION \"students\"");

        } catch (Exception e) {
            System.out.println("Error executing " + command + e.toString());
        }
    }
}

