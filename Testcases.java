import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TravelPackageTest {

    private TravelPackage europeTour;
    private Destination london;
    private Destination paris;

    @BeforeEach
    void setUp() {
        europeTour = new TravelPackage("Europe Tour", 20);

        london = new Destination("London");
        london.addActivity(new Activity("Sightseeing", "Visit famous landmarks", 50.0, 15));
        london.addActivity(new Activity("Theatre Show", "Watch a musical", 100.0, 10));

        paris = new Destination("Paris");
        paris.addActivity(new Activity("Eiffel Tower Tour", "Visit the iconic tower", 80.0, 20));
        paris.addActivity(new Activity("Louvre Museum", "Explore famous artworks", 60.0, 15));

        europeTour.setItinerary(List.of(london, paris));
    }

    @Test
    void testAddPassenger() {
        Passenger passenger = new StandardPassenger("John", 1, 200.0);
        europeTour.addPassenger(passenger);
        assertEquals(1, europeTour.getPassengers().size());
    }

    @Test
    void testRemovePassenger() {
        Passenger passenger = new StandardPassenger("John", 1, 200.0);
        europeTour.addPassenger(passenger);
        europeTour.removePassenger(passenger);
        assertEquals(0, europeTour.getPassengers().size());
    }

    @Test
    void testSignUpForActivity() {
        Passenger passenger = new StandardPassenger("John", 1, 200.0);
        europeTour.addPassenger(passenger);
        passenger.signUpForActivity(london.getActivities().get(0));
        assertEquals(1, london.getActivities().get(0).getBookedCount());
    }

    @Test
    void testPrintItinerary() {
        // Assuming the printItinerary method simply prints to console, we can't directly test output here.
        // So, we'll just test that it doesn't throw any exceptions.
        assertDoesNotThrow(() -> europeTour.printItinerary());
    }

    @Test
    void testPrintPassengerList() {
        // Similar to printItinerary, we can't directly test output here.
        assertDoesNotThrow(() -> europeTour.printPassengerList());
    }

    @Test
    void testPrintAvailableActivities() {
        // Similar to printItinerary, we can't directly test output here.
        assertDoesNotThrow(() -> europeTour.printAvailableActivities());
    }
}
