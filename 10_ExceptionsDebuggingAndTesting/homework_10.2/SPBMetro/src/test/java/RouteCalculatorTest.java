import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndex;
    RouteCalculator routeCalculator;

    public void setUp() {
        Line line1 = new Line(1, "Первая линия");
        Line line2 = new Line(2, "Вторая линия");
        Line line3 = new Line(3, "Третья линия");
        Line line4 = new Line(4, "Четвертая линия");

        ArrayList<Station> stations = new ArrayList<>();

        Station station1_1 = new Station("Академическая_1", line1);
        Station station1_2 = new Station("Академическая_2", line1);
        Station station1_3 = new Station("Академическая_3", line1);
        stations.add(station1_1);
        stations.add(station1_2);
        stations.add(station1_3);

        Station station2_1 = new Station("Ботаническая_1", line2);
        Station station2_2 = new Station("Ботаническая_2", line2);
        Station station2_3 = new Station("Ботаническая_3", line2);
        stations.add(station2_1);
        stations.add(station2_2);
        stations.add(station2_3);


        Station station3_1 = new Station("Вокзальная_1", line3);
        Station station3_2 = new Station("Вокзальная_2", line3);
        Station station3_3 = new Station("Вокзальная_3", line3);
        stations.add(station3_1);
        stations.add(station3_2);
        stations.add(station3_3);


        Station station4_1 = new Station("Уралмаш_1", line4);
        Station station4_2 = new Station("Уралмаш_2", line4);
        Station station4_3 = new Station("Уралмаш_3", line4);
        stations.add(station4_1);
        stations.add(station4_2);
        stations.add(station4_3);


        stationIndex = new StationIndex();

        line1.addStation(station1_1);
        line1.addStation(station1_2);
        line1.addStation(station1_3);

        line2.addStation(station2_1);
        line2.addStation(station2_2);
        line2.addStation(station2_3);

        line3.addStation(station3_1);
        line3.addStation(station3_2);
        line3.addStation(station3_3);

        line4.addStation(station4_1);
        line4.addStation(station4_2);
        line4.addStation(station4_3);


        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addLine(line4);

        stationIndex.addStation(station1_1);
        stationIndex.addStation(station1_2);
        stationIndex.addStation(station1_3);

        stationIndex.addStation(station2_1);
        stationIndex.addStation(station2_2);
        stationIndex.addStation(station2_3);

        stationIndex.addStation(station3_1);
        stationIndex.addStation(station3_2);
        stationIndex.addStation(station3_3);

        stationIndex.addStation(station4_1);
        stationIndex.addStation(station4_2);
        stationIndex.addStation(station4_3);

        //Пересадка с 1-ой на 2-ую линию
        List<Station> line1_line2 = new ArrayList<>();
        line1_line2.add(station1_1);
        line1_line2.add(station2_1);
        stationIndex.addConnection(line1_line2);

        //Пересадка со 2-ой на 3-юю линию
        List<Station> line2_line3 = new ArrayList<>();
        line2_line3.add(station2_2);
        line2_line3.add(station3_1);
        stationIndex.addConnection(line2_line3);

        //Пересадка с 3-ей на 4-юю линию
        List<Station> line3_line4 = new ArrayList<>();
        line3_line4.add(station3_3);
        line3_line4.add(station4_1);
        stationIndex.addConnection(line3_line4);

        routeCalculator = new RouteCalculator(stationIndex);

    }

    public void testGetRouteOnTheLine() {
        List<Station> route = new ArrayList<>();
        route.add(stationIndex.getStation("Академическая_1", 1));
        route.add(stationIndex.getStation("Академическая_2", 1));
        route.add(stationIndex.getStation("Академическая_3", 1));


        List<Station> actualRoute = routeCalculator.getShortestRoute(stationIndex.getStation("Академическая_1"),
                stationIndex.getStation("Академическая_3"));

        assertEquals(route, actualRoute);
    }

    public void testGetRouteWithOneConnection() {
        List<Station> route = new ArrayList<>();
        route.add(stationIndex.getStation("Ботаническая_1", 2));
        route.add(stationIndex.getStation("Ботаническая_2", 2));
        route.add(stationIndex.getStation("Вокзальная_1", 3));
        route.add(stationIndex.getStation("Вокзальная_2", 3));
        route.add(stationIndex.getStation("Вокзальная_3", 3));

        List<Station> actualRoute = routeCalculator.getShortestRoute(stationIndex.getStation("Ботаническая_1"),
                stationIndex.getStation("Вокзальная_3"));

        assertEquals(route, actualRoute);
    }

    public void testGetRouteViaConnectedLine() {
        List<Station> route = new ArrayList<>();
        route.add(stationIndex.getStation("Ботаническая_1", 2));
        route.add(stationIndex.getStation("Ботаническая_2", 2));
        route.add(stationIndex.getStation("Ботаническая_3", 2));

        List<Station> actualRoute = routeCalculator.getShortestRoute(stationIndex.getStation("Ботаническая_1"),
                stationIndex.getStation("Ботаническая_3"));

        assertEquals(route, actualRoute);
    }

    public void testGetRouteWithTwoConnections() {
        List<Station> route = new ArrayList<>();
        route.add(stationIndex.getStation("Академическая_3", 1));
        route.add(stationIndex.getStation("Академическая_2", 1));
        route.add(stationIndex.getStation("Академическая_1", 1));
        route.add(stationIndex.getStation("Ботаническая_1", 2));
        route.add(stationIndex.getStation("Ботаническая_2", 2));
        route.add(stationIndex.getStation("Вокзальная_1", 3));
        route.add(stationIndex.getStation("Вокзальная_2", 3));
        route.add(stationIndex.getStation("Вокзальная_3", 3));

        List<Station> actualRoute = routeCalculator.getShortestRoute(stationIndex.getStation("Академическая_3",1),
                stationIndex.getStation("Вокзальная_3",3));

        assertEquals(route, actualRoute);
    }

    public void testCalculateDuration() {
        List<Station> route = new ArrayList<>();
        route.add(stationIndex.getStation("Академическая_3", 1));
        route.add(stationIndex.getStation("Академическая_2", 1)); //25
        route.add(stationIndex.getStation("Академическая_1", 1)); //25
        route.add(stationIndex.getStation("Ботаническая_1", 2)); //35
        route.add(stationIndex.getStation("Ботаническая_2", 2)); //25
        route.add(stationIndex.getStation("Вокзальная_1", 3)); //3.5
        route.add(stationIndex.getStation("Вокзальная_2", 3)); //2.5
        route.add(stationIndex.getStation("Вокзальная_3", 3)); //2.5
        route.add(stationIndex.getStation("Уралмаш_1", 4)); //35
        route.add(stationIndex.getStation("Уралмаш_2", 4)); //25


        double actual = RouteCalculator.calculateDuration(route);
        double expected = 2.5 + 2.5 + 3.5 + 2.5 + 3.5 + 2.5 + 2.5 + 3.5 + 2.5;

        assertEquals(expected, actual);
    }
}