import java.util.*;

class Room {
    int number;
    String type;
    double price;
    boolean available;
    
    Room(int number, String type, double price) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.available = true;
    }
}

class Booking {
    static int idCounter = 1;
    int id;
    String name;
    String email;
    Room room;
    String checkIn;
    String checkOut;
    double total;
    String status;
    
    Booking(String name, String email, Room room, String checkIn, String checkOut, int days) {
        this.id = idCounter++;
        this.name = name;
        this.email = email;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.total = days * room.price;
        this.status = "Booked";
    }
}

public class HotelSystem {
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    public HotelSystem() {
        setupRooms();
    }
    
    void setupRooms() {
        // Standard rooms
        for (int i = 101; i <= 105; i++) {
            rooms.add(new Room(i, "Standard", 2500.0));
        }
        // Deluxe rooms
        for (int i = 201; i <= 203; i++) {
            rooms.add(new Room(i, "Deluxe", 4000.0));
        }
        // Suite rooms
        rooms.add(new Room(301, "Suite", 6500.0));
        rooms.add(new Room(302, "Suite", 6500.0));
    }
    
    void showMenu() {
        System.out.println("\n--- Hotel Booking System ---");
        System.out.println("1. View Rooms");
        System.out.println("2. Book Room");
        System.out.println("3. My Bookings");
        System.out.println("4. Cancel Booking");
        System.out.println("5. Make Payment");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
    }
    
    void viewRooms() {
        System.out.println("\nAvailable Rooms:");
        System.out.println("Room    Type      Price    Status");
        System.out.println("--------------------------------");
        
        for (Room r : rooms) {
            String status = r.available ? "Free" : "Taken";
            System.out.println(r.number + "    " + r.type + "     Rs." + r.price + "    " + status);
        }
    }
    
    void bookRoom() {
        System.out.println("\n--- Book a Room ---");
        viewRooms();
        
        System.out.print("Enter room number: ");
        int roomNum = sc.nextInt();
        
        Room selectedRoom = null;
        for (Room r : rooms) {
            if (r.number == roomNum && r.available) {
                selectedRoom = r;
                break;
            }
        }
        
        if (selectedRoom == null) {
            System.out.println("Room not available!");
            return;
        }
        
        sc.nextLine(); // clear buffer
        System.out.print("Your name: ");
        String name = sc.nextLine();
        
        System.out.print("Email: ");
        String email = sc.nextLine();
        
        System.out.print("Check-in date (DD/MM/YYYY): ");
        String checkIn = sc.nextLine();
        
        System.out.print("Check-out date (DD/MM/YYYY): ");
        String checkOut = sc.nextLine();
        
        System.out.print("Number of days: ");
        int days = sc.nextInt();
        
        Booking booking = new Booking(name, email, selectedRoom, checkIn, checkOut, days);
        bookings.add(booking);
        selectedRoom.available = false;
        
        System.out.println("\nBooking Confirmed!");
        System.out.println("Booking ID: " + booking.id);
        System.out.println("Room: " + selectedRoom.number);
        System.out.println("Total: Rs." + booking.total);
    }
    
    void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        
        System.out.println("\nAll Bookings:");
        System.out.println("ID   Name       Room   Total    Status");
        System.out.println("------------------------------------");
        
        for (Booking b : bookings) {
            System.out.println(b.id + "   " + b.name + "      " + b.room.number + 
                             "     Rs." + b.total + "   " + b.status);
        }
    }
    
    void cancelBooking() {
        viewBookings();
        
        System.out.print("Enter booking ID to cancel: ");
        int id = sc.nextInt();
        
        for (Booking b : bookings) {
            if (b.id == id && b.status.equals("Booked")) {
                b.status = "Cancelled";
                b.room.available = true;
                System.out.println("Booking cancelled!");
                return;
            }
        }
        System.out.println("Booking not found or already cancelled.");
    }
    
    void makePayment() {
        System.out.print("Enter booking ID: ");
        int id = sc.nextInt();
        
        Booking booking = null;
        for (Booking b : bookings) {
            if (b.id == id && b.status.equals("Booked")) {
                booking = b;
                break;
            }
        }
        
        if (booking == null) {
            System.out.println("Booking not found!");
            return;
        }
        
        System.out.println("\nPayment Details:");
        System.out.println("Guest: " + booking.name);
        System.out.println("Room: " + booking.room.number);
        System.out.println("Amount: Rs." + booking.total);
        
        System.out.println("\nPayment Options:");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash");
        System.out.print("Choose: ");
        int choice = sc.nextInt();
        
        String method = (choice == 1) ? "Credit Card" : "Cash";
        
        System.out.println("Processing payment...");
        System.out.println("Payment of Rs." + booking.total + " via " + method + " successful!");
        booking.status = "Paid";
    }
    
    void run() {
        System.out.println("Welcome to Hotel Booking System!");
        
        while (true) {
            showMenu();
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    makePayment();
                    break;
                case 6:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    public static void main(String[] args) {
        HotelSystem hotel = new HotelSystem();
        hotel.run();
    }
}
