import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(); // Create a hotel

        // Adding some sample rooms to the hotel
        hotel.addRoom(new Room("101", RoomType.SINGLE, 100));
        hotel.addRoom(new Room("102", RoomType.DOUBLE, 150));
        hotel.addRoom(new Room("103", RoomType.SUITE, 250));

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Search available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Available Rooms:");
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    String roomNumber = scanner.next();
                    System.out.print("Enter your name: ");
                    String guestName = scanner.next();
                    System.out.print("Enter number of nights: ");
                    int numNights = scanner.nextInt();
                    hotel.makeReservation(roomNumber, guestName, numNights);
                    break;
                case 3:
                    hotel.displayBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the hotel reservation system!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

class Hotel {
    private List<Room> rooms;
    private final List<Reservation> reservations;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void makeReservation(String roomNumber, String guestName, int numNights) {
        Room room = findRoom(roomNumber);
        if (room != null) {
            Reservation reservation = new Reservation(room, guestName, numNights);
            reservations.add(reservation);
            room.setReserved(true);
            System.out.println(STR."Reservation successful! Booking ID: \{reservation.getBookingId()}");
        } else {
            System.out.println("Room not found or already reserved!");
        }
    }

    public void displayAvailableRooms() {
        for (Room room : rooms) {
            if (room.isReserved()) {
                System.out.println(room);
            }
        }
    }

    public void displayBookings() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    private Room findRoom(String roomNumber) {
        for (Room room : rooms) {
            if (room.getNumber().equals(roomNumber) && room.isReserved()) {
                return room;
            }
        }
        return null;
    }
}

class Room {
    private final String number;
    private final RoomType type;
    private final double price;
    private boolean reserved;

    public Room(String number, RoomType type, double price) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.reserved = false;
    }

    public String getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isReserved() {
        return !reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return STR."Room Number: \{number}, Type: \{type}, Price: $\{price}";
    }
}

class Reservation {
    private static int nextBookingId = 1000;
    private final int bookingId;
    private final Room room;
    private final String guestName;
    private final int numNights;

    public Reservation(Room room, String guestName, int numNights) {
        this.bookingId = nextBookingId++;
        this.room = room;
        this.guestName = guestName;
        this.numNights = numNights;
    }

    public int getBookingId() {
        return bookingId;
    }

    @Override
    public String toString() {
        return STR."Booking ID: \{bookingId}, Guest Name: \{guestName}, Room: \{room.getNumber()}, Type: \{room.getType()}, Number of Nights: \{numNights}, Total Price: $\{room.getPrice() * numNights}";
    }
}

enum RoomType {
    SINGLE,
    DOUBLE,
    SUITE
}
