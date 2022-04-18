import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {

        Document doc = null;
        try {

            doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines")
                    .maxBodySize(0)
                    .timeout(10000)
                    .get();

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<MetroLine> metroLines = ParserMosMetro.parseMetroline(doc);
        createJSONfileWithTwoObj(metroLines);

        countStationInLine("metroMap.json");
    }


    public static void createJSONfileWithTwoObj(List<MetroLine> metroLines) {
        Map<String, String> linesName = new TreeMap<>();
        metroLines.forEach(line -> linesName.put(line.getNumber(), line.getName()));

        Map<String, List<String>> stationsMap = new TreeMap<>();
        metroLines.forEach(line -> stationsMap.put(line.getNumber(), line.getStations()
                .stream()
                .map(Station::getName)
                .collect(Collectors.toList())));

        Set<Set<Station>> connections = new TreeSet<>((s, s1) -> {

            List<Station> c1 = new ArrayList<>(s);
            List<Station> c2 = new ArrayList<>(s1);
            if (c1.size() - c2.size() == 0) {
                for (int i = 0; i < c2.size(); i++) {
                    if (c1.get(i).compareTo(c2.get(i)) != 0) {
                        return c1.get(i).compareTo(c2.get(i));
                    }
                }
            }
            return c1.size() - c2.size();
        });


        metroLines.stream().flatMap(line -> line.getStations().stream())
                .filter(station -> station.isConnection()).map(station -> connections.add(station.getConnectionsList())).collect(Collectors.toSet());


        JSONObject jsonObject = new JSONObject();

        jsonObject.put("lines", linesName);
        jsonObject.put("stations", stationsMap);
        jsonObject.put("connections", connections);


        try (FileWriter file = new FileWriter("metroMap.json")) {
            file.write(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void countStationInLine(String path) {
        Path pathJ = Paths.get(path);
        StringBuilder builder = new StringBuilder();

        try {
            List<String> lines = Files.readAllLines(pathJ);
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(builder.toString());
            JSONObject stations = (JSONObject) jsonObject.get("stations");

            stations.keySet().forEach(lineNumberObject ->
            {
                String lineNumber = (String) lineNumberObject;
                JSONArray stationsArray = (JSONArray) stations.get(lineNumberObject);
                System.out.println("Количество станций на линии " + lineNumber + "  " + stationsArray.size());
            });

            JSONArray connections = (JSONArray) jsonObject.get("connections");

            System.out.println("Переходов метро  " + connections.size());


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
