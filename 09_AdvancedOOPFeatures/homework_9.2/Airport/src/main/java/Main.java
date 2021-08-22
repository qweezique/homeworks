import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {

        Calendar c = Calendar.getInstance();
        Date now = c.getTime();
        c.add(Calendar.HOUR, 2);
        Date date2hour = c.getTime();

        return airport.getTerminals().stream()
                .flatMap((terminal -> terminal.getFlights().stream()))
                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE)
                        && f.getDate().after(now)
                        && f.getDate().before(date2hour))
                .collect(Collectors.toList());
    }
}