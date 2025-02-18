import java.util.*;

class TicketBookingSystem {
    private final boolean[] seats;

    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats]; // false means available, true means booked
    }
//Selim Jahangir 22bcs13878
    // Synchronized method to prevent double booking
    public synchronized boolean bookSeat(int seatNumber, String user) {
        if (seatNumber < 0 || seatNumber >= seats.length) {
            System.out.println(user + " tried to book an invalid seat: " + seatNumber);
            return false;
        }
        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(user + " successfully booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(user + " attempted to book an already booked seat " + seatNumber);
            return false;
        }
    }

    public void displayAvailableSeats() {
        System.out.print("Available seats: ");
        for (int i = 0; i < seats.length; i++) {
            if (!seats[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}

class TicketBookingThread extends Thread {
    private final TicketBookingSystem system;
    private final String user;
    private final int seatNumber;

    public TicketBookingThread(TicketBookingSystem system, String user, int seatNumber, int priority) {
        this.system = system;
        this.user = user;
        this.seatNumber = seatNumber;
        setPriority(priority); // Set thread priority
    }

    public void run() {
        system.bookSeat(seatNumber, user);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(10); // 10 seats available

        // Create booking threads with different priorities
        TicketBookingThread user1 = new TicketBookingThread(system, "User1", 3, Thread.MIN_PRIORITY);
        TicketBookingThread vipUser = new TicketBookingThread(system, "VIPUser", 3, Thread.MAX_PRIORITY);
        TicketBookingThread user2 = new TicketBookingThread(system, "User2", 5, Thread.NORM_PRIORITY);
        TicketBookingThread vipUser2 = new TicketBookingThread(system, "VIPUser2", 5, Thread.MAX_PRIORITY);

        // Display available seats before booking
        system.displayAvailableSeats();

        // Start threads
        vipUser.start(); // VIP should book first
        user1.start();
        vipUser2.start();
        user2.start();

        // Ensure all threads finish execution
        try {
            vipUser.join();
            user1.join();
            vipUser2.join();
            user2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display available seats after booking
        system.displayAvailableSeats();
    }
}
