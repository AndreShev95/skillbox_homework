import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    StationIndex stationIndexTest = new StationIndex();

    List<Station> route, route2;

    @Override
    protected void setUp() throws Exception {
        List<Station> connectionStations1 = new ArrayList<>();
        List<Station> connectionStations2 = new ArrayList<>();
        route2 = new ArrayList<>();

        Line line1 = new Line(1,"Кировско-Выборгская");
        Line line2 = new Line(2,"Фрунзенско-Приморская");
        Line line3 = new Line(3,"Невско-Василеостровская");

        Station station1 = new Station("Чернышевская", line1);
        Station station2 = new Station("Площадь Восстания", line1);
        Station station3 = new Station("Владимирская", line1);
        Station station4 = new Station("Пушкинская", line1);
        Station station5 = new Station("Технологический институт", line1);
        Station station6 = new Station("Садовая", line2);
        Station station7 = new Station("Звенигородская", line2);
        Station station8 = new Station("Обводный канал", line2);
        Station station9 = new Station("Гостиный двор", line3);
        Station station10 = new Station("Маяковская", line3);
        Station station11 = new Station("Площадь Александра Невского", line3);

        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line1.addStation(station4);
        line1.addStation(station5);
        line2.addStation(station6);
        line2.addStation(station7);
        line2.addStation(station8);
        line3.addStation(station9);
        line3.addStation(station10);
        line3.addStation(station11);

        stationIndexTest.addLine(line1);
        stationIndexTest.addLine(line2);
        stationIndexTest.addLine(line3);

        stationIndexTest.addStation(station1);
        stationIndexTest.addStation(station2);
        stationIndexTest.addStation(station3);
        stationIndexTest.addStation(station4);
        stationIndexTest.addStation(station5);

        stationIndexTest.addStation(station6);
        stationIndexTest.addStation(station7);
        stationIndexTest.addStation(station8);

        stationIndexTest.addStation(station9);
        stationIndexTest.addStation(station10);
        stationIndexTest.addStation(station11);

        connectionStations1.add(stationIndexTest.getStation("Площадь Восстания"));
        connectionStations1.add(stationIndexTest.getStation("Маяковская",3));
        stationIndexTest.addConnection(connectionStations1);

        connectionStations2.add(stationIndexTest.getStation("Пушкинская"));
        connectionStations2.add(stationIndexTest.getStation("Звенигородская"));
        stationIndexTest.addConnection(connectionStations2);

        RouteCalculator routeCalculatorTest = new RouteCalculator(stationIndexTest);
        route = routeCalculatorTest.getShortestRoute(station6, station9);

        route2.add(station6);
        route2.add(station7);
        route2.add(station4);
        route2.add(station3);
        route2.add(station2);
        route2.add(station10);
        route2.add(station9);
    }

    public void testGetShortestRoute() {
        List<Station> actual = route;
        List<Station> expected = route2;
        assertEquals(expected, actual);
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 17.0d;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {
    }
}
