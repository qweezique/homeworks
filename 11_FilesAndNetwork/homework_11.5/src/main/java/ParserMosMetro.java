import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParserMosMetro {


    public static List<MetroLine> parseMetroline(Document document) {
        Elements lines = document.select("[class^=js-metro-line]");

        List<MetroLine> metroLines = lines.stream()
                .map(l -> new MetroLine(l.text(), l.attr("data-line")))
                .collect(Collectors.toList());

        addStationAndConnections(document.select("[class^=js-metro-stations]"), metroLines);

        return metroLines;
    }


    public static void addStationAndConnections(Elements stations, List<MetroLine> metroLines) {

        List<MetroLine> metroLinesWithStations = new ArrayList<>(metroLines);

        for (Element s : stations) {
            Elements nameStations = s.select("[class=name]");
            for (Element ns : nameStations) {
                MetroLine line = getLine(metroLinesWithStations, s.attr("data-line"));
                Station station = new Station(ns.text(), line);
                line.addStation(station);
            }
        }
        parseConnections(stations, metroLines);
    }

    public static void parseConnections(Elements stations, List<MetroLine> metroLines){

        for (Element s : stations) {
            Elements metroost = s.select("[data-metrost]");
            for (Element ms : metroost) {
                if(ms.children().size() > 2){

                    MetroLine line = getLine(metroLines, s.attr("data-line"));
                    Station station = getStation(ms.child(1).text(), line, metroLines);
                    for (int i=2;i<ms.children().size();i++){
                        Station connectionStation = parseStationNameInConnect(ms.child(i), metroLines);
                        station.setConnection(connectionStation);
                    }
                }
            }
        }

    }

    private static Station parseStationNameInConnect(Element element,List<MetroLine> metroLines) {
        String line = element.attr("class");
        String stationStr = element.attr("title");
        String ll = line.substring(line.lastIndexOf("-")+1);

        MetroLine line1 = getLine(metroLines, ll);
        int firstQuot = stationStr.lastIndexOf("«" );
        int lastQuot = stationStr.lastIndexOf("»");

        String stationName = stationStr.substring(firstQuot+1,lastQuot);
        return getStation(stationName,line1, metroLines);

    }


    private static MetroLine getLine(List<MetroLine> metroLines, String numberLine) {
        MetroLine line = metroLines.stream()
                .filter(metroLine -> metroLine.getNumber().equals(numberLine))
                .findFirst()
                .get();

        return line;
    }

    private static List<Station> getAllStations(List <MetroLine> linesWithStations){
        List<Station> allStations = linesWithStations.stream()
                .flatMap(line -> line.getStations().stream())
                .collect(Collectors.toList());
        return allStations;
    }

   private static Station getStation(String nameStation, MetroLine line, List<MetroLine> metroLines) {
        List<Station> allStations = getAllStations(metroLines);


        Station station = allStations.stream()
                .filter(s -> s.getLine().equals(line))
                .filter(s -> s.getName().equals(nameStation))
                .findAny()
                .get();

        return station;
    }


}
