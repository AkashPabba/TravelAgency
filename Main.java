import java.util.ArrayList;
import java.util.List;

class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;

    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public void addPassenger(Passenger passenger) {
        if (passengers.size() < passengerCapacity) {
            passengers.add(passenger);
        } else {
            System.out.println("Passenger capacity reached. Cannot add more passengers.");
        }
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
    }

    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        for (Destination destination : itinerary) {
            destination.printActivities();
        }
    }

    public void printPassengerList() {
        System.out.println("Travel Package: " + name);
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Number of Passengers Enrolled: " + passengers.size());
        for (Passenger passenger : passengers) {
            System.out.println(passenger.getName() + " - " + passenger.getPassengerNumber());
        }
    }

    public void printAvailableActivities() {
        for (Destination destination : itinerary) {
            destination.printAvailableActivities();
        }
    }

    public void setItinerary(List<Destination> itinerary) {
        this.itinerary = itinerary;
    }
}

class Destination {
    private String name;
    private List<Activity> activities;

    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void printActivities() {
        System.out.println("Destination: " + name);
        for (Activity activity : activities) {
            System.out.println("Activity: " + activity.getName() + ", Cost: " + activity.getCost() +
                    ", Capacity: " + activity.getCapacity());
        }
    }

    public void printAvailableActivities() {
        System.out.println("Destination: " + name);
        for (Activity activity : activities) {
            if (activity.getAvailableSpace() > 0) {
                System.out.println("Available Activity: " + activity.getName() + ", Cost: " + activity.getCost() +
                        ", Available Space: " + activity.getAvailableSpace());
            }
        }
    }
}

class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private int bookedCount;

    public Activity(String name, String description, double cost, int capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.bookedCount = 0;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSpace() {
        return capacity - bookedCount;
    }

    public void bookActivity() {
        if (bookedCount < capacity) {
            bookedCount++;
        }
    }
}

abstract class Passenger {
    private String name;
    private int passengerNumber;

    public Passenger(String name, int passengerNumber) {
        this.name = name;
        this.passengerNumber = passengerNumber;
    }

    public String getName() {
        return name;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public abstract void signUpForActivity(Activity activity);
}

class StandardPassenger extends Passenger {
    private double balance;

    public StandardPassenger(String name, int passengerNumber, double balance) {
        super(name, passengerNumber);
        this.balance = balance;
    }

    @Override
    public void signUpForActivity(Activity activity) {
        if (balance >= activity.getCost()) {
            balance -= activity.getCost();
            activity.bookActivity();
            System.out.println(getName() + " signed up for activity: " + activity.getName() + " at destination: " +
                    activity.getDestination().getName() + " for $" + activity.getCost());
        } else {
            System.out.println("Insufficient balance to sign up for activity: " + activity.getName());
        }
    }
}

class GoldPassenger extends Passenger {
    private double balance;

    public GoldPassenger(String name, int passengerNumber, double balance) {
        super(name, passengerNumber);
        this.balance = balance;
    }

    @Override
    public void signUpForActivity(Activity activity) {
        double discountedCost = activity.getCost() * 0.9; // 10% discount for gold passengers
        if (balance >= discountedCost) {
            balance -= discountedCost;
            activity.bookActivity();
            System.out.println(getName() + " signed up for activity: " + activity.getName() + " at destination: " +
                    activity.getDestination().getName() + " for $" + discountedCost + " (10% discount applied)");
        } else {
            System.out.println("Insufficient balance to sign up for activity: " + activity.getName());
        }
    }
}

class PremiumPassenger extends Passenger {
    public PremiumPassenger(String name, int passengerNumber) {
        super(name, passengerNumber);
    }

    @Override
    public void signUpForActivity(Activity activity) {
        activity.bookActivity();
        System.out.println(getName() + " signed up for activity: " + activity.getName() + " at destination: " +
                activity.getDestination().getName() + " (Premium passenger, no cost)");
    }
}

public class Main {
    public static void main(String[] args) {
        // Sample usage
        TravelPackage europeTour = new TravelPackage("Europe Tour", 20);

        Destination london = new Destination("London");
        london.addActivity(new Activity("Sightseeing", "Visit famous landmarks", 50.0, 15));
        london.addActivity(new Activity("Theatre Show", "Watch a musical", 100.0, 10));

        Destination paris = new Destination("Paris");
        paris.addActivity(new Activity("Eiffel Tower Tour", "Visit the iconic tower", 80.0, 20));
        paris.addActivity(new Activity("Louvre Museum", "Explore famous artworks", 60.0, 15));

        europeTour.setItinerary(List.of(london, paris));

        Passenger standardPassenger = new StandardPassenger("John", 1, 200.0);
        Passenger goldPassenger = new GoldPassenger("Alice", 2, 300.0);
        Passenger premiumPassenger = new PremiumPassenger("Bob", 3);

        europeTour.addPassenger(standardPassenger);
        europeTour.addPassenger(goldPassenger);
        europeTour.addPassenger(premiumPassenger);

        europeTour.printItinerary();
        europeTour.printPassengerList();
        europeTour.printAvailableActivities();

        standardPassenger.signUpForActivity(london.getActivities().get(0));
        goldPassenger.signUpForActivity(london.getActivities().get(1));
        premiumPassenger.signUpForActivity(par
