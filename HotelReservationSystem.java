import java.util.*;

class Room {
    private int roomNumber;
    private String roomType; // Example: Single, Double, Suite
    private boolean isAvailable;
    private double pricePerNight;

    public Room(int roomNumber, String roomType, boolean isAvailable, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
        this.pricePerNight = pricePerNight;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ") - " + (isAvailable ? "Available" : "Booked") + ", Price: $" + pricePerNight + "/night";
    }
}

class Booking {
    private String customerName;
    private Room room;
    private int nights;
    private double totalCost;

    public Booking(String customerName, Room room, int nights) {
        this.customerName = customerName;
        this.room = room;
        this.nights = nights;
        this.totalCost = room.getPricePerNight() * nights;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Room getRoom() {
        return room;
    }

    public int getNights() {
        return nights;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Booking for " + customerName + ": Room " + room.getRoomNumber() + " (" + room.getRoomType() + "), Nights: " + nights + ", Total Cost: $" + totalCost;
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Booking> bookings;

    public Hotel() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        // Sample rooms, in a real system these would be stored in a database
        rooms.add(new Room(101, "Single", true, 50));
        rooms.add(new Room(102, "Double", true, 80));
        rooms.add(new Room(103, "Suite", true, 150));
        rooms.add(new Room(104, "Single", true, 50));
        rooms.add(new Room(105, "Double", false, 80)); // Booked room
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public void makeReservation(String customerName, int roomNumber, int nights) {
        Room room = findRoomByNumber(roomNumber);
        if (room != null && room.isAvailable()) {
            Booking booking = new Booking(customerName, room, nights);
            bookings.add(booking);
            room.setAvailable(false);
            System.out.println("Reservation successful!");
            System.out.println(booking);
        } else {
            System.out.println("Room is not available for booking.");
        }
    }

    public void viewAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings made yet.");
        } else {
            System.out.println("All Bookings:");
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }
}

public class HotelReservationSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n---- Hotel Reservation System ----");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View All Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String name = scanner.next();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter number of nights: ");
                    int nights = scanner.nextInt();
                    hotel.makeReservation(name, roomNumber, nights);
                    break;
                case 3:
                    hotel.viewAllBookings();
                    break;
                case 4:
                    System.out.println("Exiting the system. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
