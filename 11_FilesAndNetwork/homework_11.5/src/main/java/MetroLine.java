
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MetroLine implements Comparable<MetroLine> {


    private String name;
    private String number;
    private List<Station> stations;
    private List<Station> connections;

    public MetroLine(String name, String number) {
        this.name = name;
        this.number = number;
        this.stations = new ArrayList<>();
        this.connections = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void addStation(Station station) {
        stations.add(station);
    }
    public void addConnection(Station station){
        connections.add(station);
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public List<Station> getConnections() {

        if(this.connections.size()==0 & this.connections==null){
            setConnections(stations);
        }
        return connections;

    }

    public void setConnections(List<Station> stations) {

        this.connections = stations.stream().filter(Station::isConnection).collect(Collectors.toList()); ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetroLine line = (MetroLine) o;

        if (!name.equals(line.name)) return false;
        return number.equals(line.number);
    }

    @Override
    public int hashCode() {
        return 0;
    }


    @Override
    public String toString() {
        setConnections(stations);

        if (stations != null & stations.size()!=0) {
            return "{\n\"number\":\"" + number + "\",\n\t\"name\": \"" + name + "\",\n" +
                    "\"stations\":" + stations.stream().map(s -> s.getNameInquotation())
                    .collect(Collectors.toList()) +", \"connections\":"
                    + connections.stream().map(s->s.getStringConnection()).collect(Collectors.toList()) + "}";
        } else {
            return "{\n\t\"number\":\"" + number + "\",\n\t\"name\": \"" + name + "\"\n}\n";
        }
    }

    @Override
    public int compareTo(MetroLine metroLine) {

        int isOneLine = this.getNumber().compareTo(metroLine.getNumber());

        return isOneLine;
    }
}

