import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Loader {
    public static final String XML_02M = "res/data-0.2M.xml";
    public static final String XML_1M = "res/data-1M.xml";
    public static final String XML_18M = "res/data-18M.xml";
    public static final String XML_1572M = "res/data-1572M.xml";
    public static final long BYTE_DIVIDER = 1024L * 1024L;
    public static final SAXParserFactory factory = SAXParserFactory.newInstance();
    public static SAXParser parser;

    static {
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        parseUsingMemory(XML_1M);
//        parseUsingMongoDB(XML_1M);
        parseUsingMySQL(XML_1M);
    }

    public static void parseUsingMongoDB(String filePath) throws IOException, SAXException {
        MongoDBHandler mongoDBHandler = new MongoDBHandler();
        long start = System.currentTimeMillis();
        parser.parse(new File(filePath), mongoDBHandler);
        mongoDBHandler.printDuplicatedVoters();
        long end = System.currentTimeMillis();
        System.out.printf("\n\nВыполение программы за %d, мс\n", end - start);
    }

    public static void parseUsingMySQL(String filePath) throws IOException, SAXException, SQLException {
        MySQLHandler MySQLHandler = new MySQLHandler();
        long start = System.currentTimeMillis();
        parser.parse(new File(filePath), MySQLHandler);
        MySQLHandler.printDuplicatedVoters();
        long end = System.currentTimeMillis();
        System.out.printf("\n\nВыполение программы за %d, мс\n", end - start);
    }

    public static void parseUsingMemory(String filePath) throws IOException, SAXException {
        XMLHandler handler = new XMLHandler();
        long start = System.currentTimeMillis();
        long memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        parser.parse(new File(filePath), handler);
        handler.printDuplicatedVoters();
        memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - memoryUsage;
        long end = System.currentTimeMillis();
        System.out.printf("\n\nВыполение программы за %d, мс\nКоличество потребляемой памяти %d, mb\n", end - start, memoryUsage / (1024 * 1024));
    }
}