import com.skillbox.airport.Airport;

public class Main
{
    public int countAircraft;

    public static void main(String[] args)
    {
        Airport airport = Airport.getInstance();

        System.out.println("Список всех самолетов в аэропорту: " + airport.getAllAircrafts());

        int countAircraft = airport.getAllAircrafts().size();

        System.out.println("Количество самолетов в аэропорту: " + countAircraft + " шт.");
    }

}
