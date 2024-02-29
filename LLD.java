public class TravelPackage {
    // Attributes
    private String name;
    private int passengerCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;
    
    // Methods
    public void addPassenger(Passenger passenger);
    public void removePassenger(Passenger passenger);
    public void printItinerary();
    public void printPassengerList();
    public void printPassengerDetails(Passenger passenger);
    public void printAvailableActivities();
}

public class Destination {
    // Attributes
    private String name;
    private List<Activity> activities;
    
    // Methods
    // Constructor, getters, setters
}

public class Activity {
    // Attributes
    private String name;
    private String description;
    private double cost;
    private int capacity;
    
    // Methods
    // Constructor, getters, setters
}

public abstract class Passenger {
    // Attributes
    private String name;
    private int passengerNumber;
    private PassengerType passengerType;
    private List<Activity> activitiesSignedUp;
    
    // Methods
    public abstract void signUpForActivity(Activity activity);
    public void printDetails();
}

public class StandardPassenger extends Passenger {
    // Attributes
    private double balance;
    
    // Methods
    // Constructor, signUpForActivity(), printDetails()
}

public class GoldPassenger extends Passenger {
    // Attributes
    private double balance;
    
    // Methods
    // Constructor, signUpForActivity(), printDetails()
}

public class PremiumPassenger extends Passenger {
    // Methods
    // Constructor, signUpForActivity(), printDetails()
}
