import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class MongoDBConnection {

    private final MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    private final MongoDatabase database = mongoClient.getDatabase("optimizationLearning");
    private final MongoCollection<Document> collectionVoters = database.getCollection("voters");

    public MongoDBConnection() {
        collectionVoters.drop();
    }

    public void addVoters(String name, String birthDay) {
        Bson voterValidation = collectionVoters.find(and(eq("name", name), eq("birthDay", birthDay))).first();

        if (voterValidation == null) {
            Document doc = new Document();
            doc.put("name", name);
            doc.put("birthDay", birthDay);
            doc.put("visitCounts", 1);
            collectionVoters.insertOne(doc);

        } else {
            Bson updateValue = new Document("visitCounts", 1);
            Bson updateOp = new Document("$inc", updateValue);
            collectionVoters.updateOne(voterValidation, updateOp);
        }
    }

    public void printVoterCounts() {
        AggregateIterable<Document> result = collectionVoters
                .aggregate(Arrays.asList(new Document("$project",
                                new Document("_id", 0L)),
                        new Document("$match",
                                new Document("visitCounts",
                                        new Document("$gt", 1L)))));

        for (Document d : result) {
            System.out.println(d);
        }
    }
}
