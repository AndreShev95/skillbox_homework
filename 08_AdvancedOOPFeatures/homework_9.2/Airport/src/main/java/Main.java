import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Airport airport = Airport.getInstance();
        findPlanesLeavingInTheNextTwoHours(airport);

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        List <Terminal> terminals = airport.getTerminals();
        LocalDateTime nowTime = LocalDateTime.now();
        List<Flight> flightList = terminals.stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE)
                .filter(flight -> {
                    Date input = flight.getDate();
                    LocalDateTime date = LocalDateTime.ofInstant(input.toInstant(), ZoneId.systemDefault());
                    return (nowTime.plusHours(2).isAfter(date) && nowTime.isBefore(date));
                })
                .collect(Collectors.toList());
        flightList.forEach(System.out::println);

        return flightList;
    }

}