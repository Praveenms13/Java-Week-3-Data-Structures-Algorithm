import java.util.Scanner;

class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }
}

class MovieDoublyLinkedList {
    private Movie head;
    private Movie tail;

    public void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    public void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    public void addAtPosition(int pos, String title, String director, int year, double rating) {
        if (pos <= 0) {
            System.out.println("Invalid position");
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        if (pos == 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            addAtEnd(title, director, year, rating);
        } else {
            newMovie.next = temp.next;
            newMovie.prev = temp;
            temp.next.prev = newMovie;
            temp.next = newMovie;
        }
    }

    public void deleteByTitle(String title) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                if (temp == head) {
                    head = temp.next;
                    if (head != null)
                        head.prev = null;
                    else
                        tail = null;
                } else if (temp == tail) {
                    tail = temp.prev;
                    if (tail != null)
                        tail.next = null;
                    else
                        head = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                System.out.println("Movie deleted.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found.");
    }

    public void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year
                        + ", Rating: " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found)
            System.out.println("No movies found by this director.");
    }

    public void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year
                        + ", Rating: " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found)
            System.out.println("No movies found with this rating.");
    }

    public void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Rating updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found.");
    }

    public void displayForward() {
        if (head == null) {
            System.out.println("No movies to display.");
            return;
        }
        Movie temp = head;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year
                    + ", Rating: " + temp.rating);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        if (tail == null) {
            System.out.println("No movies to display.");
            return;
        }
        Movie temp = tail;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year
                    + ", Rating: " + temp.rating);
            temp = temp.prev;
        }
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieDoublyLinkedList list = new MovieDoublyLinkedList();
        int choice;

        do {
            System.out.println("\nMovie Management System");
            System.out.println("1. Add Movie at Beginning");
            System.out.println("2. Add Movie at End");
            System.out.println("3. Add Movie at Position");
            System.out.println("4. Delete Movie by Title");
            System.out.println("5. Search by Director");
            System.out.println("6. Search by Rating");
            System.out.println("7. Update Movie Rating");
            System.out.println("8. Display All (Forward)");
            System.out.println("9. Display All (Reverse)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Director: ");
                    String director = sc.nextLine();
                    System.out.print("Enter Year of Release: ");
                    int year = sc.nextInt();
                    System.out.print("Enter Rating: ");
                    double rating = sc.nextDouble();
                    if (choice == 1)
                        list.addAtBeginning(title, director, year, rating);
                    else if (choice == 2)
                        list.addAtEnd(title, director, year, rating);
                    else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        list.addAtPosition(pos, title, director, year, rating);
                    }
                    break;
                case 4:
                    System.out.print("Enter Title to Delete: ");
                    String delTitle = sc.nextLine();
                    list.deleteByTitle(delTitle);
                    break;
                case 5:
                    System.out.print("Enter Director to Search: ");
                    String searchDirector = sc.nextLine();
                    list.searchByDirector(searchDirector);
                    break;
                case 6:
                    System.out.print("Enter Rating to Search: ");
                    double searchRating = sc.nextDouble();
                    list.searchByRating(searchRating);
                    break;
                case 7:
                    System.out.print("Enter Title to Update Rating: ");
                    String updateTitle = sc.nextLine();
                    System.out.print("Enter New Rating: ");
                    double newRating = sc.nextDouble();
                    list.updateRating(updateTitle, newRating);
                    break;
                case 8:
                    list.displayForward();
                    break;
                case 9:
                    list.displayReverse();
                    break;
                case 0:
                    System.out.println("Exiting Program.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
