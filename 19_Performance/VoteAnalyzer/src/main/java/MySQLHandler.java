import java.sql.SQLException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class MySQLHandler extends DefaultHandler {

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("voter")) {
            try {
                String birthDay = attributes.getValue("birthDay");
                String name = attributes.getValue("name");
                MySQLConnection.countVoter(name, birthDay);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void printDuplicatedVoters() throws SQLException {
        System.out.println("\n\nMySQL:\n");
        MySQLConnection.printVoterCounts();
    }
}
