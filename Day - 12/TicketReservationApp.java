class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head;
    private Ticket tail;
    private int ticketCount;

    public TicketReservationSystem() {
        this.head = null;
        this.tail = null;
        this.ticketCount = 0;
    }

    public void addTicket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            tail = newTicket;
            tail.next = head; // Circular link
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head; // Circular link
        }
        ticketCount++;
        System.out.println("Ticket " + ticketId + " booked for " + customerName + ".");
    }

    public void removeTicket(int ticketId) {
        if (head == null) {
            System.out.println("No tickets to remove.");
            return;
        }

        Ticket current = head;
        Ticket previous = null;
        do {
            if (current.ticketId == ticketId) {
                if (previous == null) {
                    if (head == tail) {
                        head = tail = null;
                    } else {
                        head = current.next;
                        tail.next = head;
                    }
                } else {
                    previous.next = current.next;
                    if (current == tail) {
                        tail = previous;
                    }
                }
                ticketCount--;
                System.out.println("Ticket " + ticketId + " removed.");
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);

        System.out.println("Ticket " + ticketId + " not found.");
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        do {
            System.out.println("Ticket ID: " + current.ticketId + ", Customer: " + current.customerName +
                    ", Movie: " + current.movieName + ", Seat: " + current.seatNumber +
                    ", Booking Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }

    public void searchTicketByCustomerName(String customerName) {
        if (head == null) {
            System.out.println("No tickets found.");
            return;
        }

        Ticket current = head;
        do {
            if (current.customerName.equalsIgnoreCase(customerName)) {
                System.out.println("Ticket ID: " + current.ticketId + ", Customer: " + current.customerName +
                        ", Movie: " + current.movieName + ", Seat: " + current.seatNumber +
                        ", Booking Time: " + current.bookingTime);
                return;
            }
            current = current.next;
        } while (current != head);

        System.out.println("No ticket found for customer: " + customerName);
    }

    public void searchTicketByMovieName(String movieName) {
        if (head == null) {
            System.out.println("No tickets found.");
            return;
        }

        Ticket current = head;
        do {
            if (current.movieName.equalsIgnoreCase(movieName)) {
                System.out.println("Ticket ID: " + current.ticketId + ", Customer: " + current.customerName +
                        ", Movie: " + current.movieName + ", Seat: " + current.seatNumber +
                        ", Booking Time: " + current.bookingTime);
                return;
            }
            current = current.next;
        } while (current != head);

        System.out.println("No ticket found for movie: " + movieName);
    }

    public int getTotalBookedTickets() {
        return ticketCount;
    }
}

public class TicketReservationApp {
    public static void main(String[] args) {
        TicketReservationSystem reservationSystem = new TicketReservationSystem();

        reservationSystem.addTicket(101, "Alice", "The Matrix", "A1", "10:00 AM");
        reservationSystem.addTicket(102, "Bob", "Inception", "B2", "11:30 AM");
        reservationSystem.addTicket(103, "Charlie", "The Matrix", "C3", "12:30 PM");

        reservationSystem.displayTickets();

        reservationSystem.searchTicketByCustomerName("Alice");
        reservationSystem.searchTicketByMovieName("The Matrix");

        reservationSystem.removeTicket(102);
        reservationSystem.displayTickets();

        System.out.println("Total booked tickets: " + reservationSystem.getTotalBookedTickets());
    }
}
