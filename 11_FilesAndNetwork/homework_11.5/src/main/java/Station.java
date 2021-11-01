import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Station implements Comparable<Station> {

    private String name;
    private MetroLine line;
    private boolean isConnection;
    private List<Station> connectionsList;


    public Station(String name, MetroLine line) {
        this.name = name;
        this.line = line;
        connectionsList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getNameInquotation() {
        return "\"" + name + "\"";
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetroLine getLine() {
        return line;
    }

    public void setLine(MetroLine line) {
        this.line = line;
    }

    public boolean isConnection() {
        return isConnection;
    }

    public void setConnection(Station station) {
        isConnection = true;
        connectionsList.add(station);
    }

    public Set<Station> getConnectionsList() {
        Set<Station> connections = new TreeSet<>(connectionsList);
        connections.add(this);
        return connections;
    }

    public String getStringConnection() {
        return "\"" + name + "\"," + connectionsList;
    }


    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\", \"line\": \"" + line.getNumber() + "\"}";
    }

    @Override
    public int compareTo(Station station) {
        int isOneLine = this.getLine().getNumber().compareTo(station.getLine().getNumber());

        if (isOneLine != 0) {
            return isOneLine;
        }
        return name.compareToIgnoreCase(station.getName());
    }
}
