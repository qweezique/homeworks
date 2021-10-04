import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class MongoDBHandler extends DefaultHandler {
    final MongoDBConnection mongo = new MongoDBConnection();

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("voter")) {
            String name = attributes.getValue("name");
            String birthDay = attributes.getValue("birthDay");
            mongo.addVoters(name, birthDay);
        }
    }

    public void printDuplicatedVoters() {
        System.out.println("\n\nMongoDB: \n");
        mongo.printVoterCounts();
    }
}
