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

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.HOUR, 2);
        Date dateTwoHoursLater = calendar.getTime();

        return airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE)
                        && f.getDate().after(now)
                        && f.getDate().before(dateTwoHoursLater))
                .collect(Collectors.toList());
    }
}