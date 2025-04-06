import java.util.Scanner;

class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next, prev;

    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
    }
}

class Library {
    private Book head;
    private Book tail;

    public void addAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    public void addAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    public void addAtPosition(int pos, String title, String author, String genre, int bookId, boolean isAvailable) {
        if (pos <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos == 1) {
            addAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        Book temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            addAtEnd(title, author, genre, bookId, isAvailable);
        } else {
            newBook.next = temp.next;
            newBook.prev = temp;
            temp.next.prev = newBook;
            temp.next = newBook;
        }
    }

    public void removeByBookId(int bookId) {
        Book temp = head;
        while (temp != null && temp.bookId != bookId) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Book not found.");
            return;
        }
        if (temp == head) {
            head = temp.next;
            if (head != null)
                head.prev = null;
            if (temp == tail)
                tail = null;
        } else if (temp == tail) {
            tail = temp.prev;
            tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }

    public void searchByTitle(String title) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                displayBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

    public void searchByAuthor(String author) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                displayBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No books found by this author.");
        }
    }

    public void updateAvailability(int bookId, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = status;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    public void displayForward() {
        Book temp = head;
        if (temp == null) {
            System.out.println("Library is empty.");
            return;
        }
        while (temp != null) {
            displayBook(temp);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        Book temp = tail;
        if (temp == null) {
            System.out.println("Library is empty.");
            return;
        }
        while (temp != null) {
            displayBook(temp);
            temp = temp.prev;
        }
    }

    public void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total number of books: " + count);
    }

    private void displayBook(Book book) {
        System.out.println("ID: " + book.bookId + ", Title: " + book.title + ", Author: " + book.author +
                ", Genre: " + book.genre + ", Available: " + (book.isAvailable ? "Yes" : "No"));
    }
}

public class LibraryManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int choice;

        do {
            System.out.println("\nLibrary Management Menu");
            System.out.println("1. Add Book at Beginning");
            System.out.println("2. Add Book at End");
            System.out.println("3. Add Book at Position");
            System.out.println("4. Remove Book by ID");
            System.out.println("5. Search Book by Title");
            System.out.println("6. Search Book by Author");
            System.out.println("7. Update Availability");
            System.out.println("8. Display Books Forward");
            System.out.println("9. Display Books Reverse");
            System.out.println("10. Count Total Books");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            int id, pos;
            String title, author, genre;
            boolean status;

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Book Title: ");
                    title = sc.nextLine();
                    System.out.print("Author: ");
                    author = sc.nextLine();
                    System.out.print("Genre: ");
                    genre = sc.nextLine();
                    System.out.print("Book ID: ");
                    id = sc.nextInt();
                    System.out.print("Is Available (true/false): ");
                    status = sc.nextBoolean();
                    if (choice == 1)
                        library.addAtBeginning(title, author, genre, id, status);
                    else if (choice == 2)
                        library.addAtEnd(title, author, genre, id, status);
                    else {
                        System.out.print("Enter position: ");
                        pos = sc.nextInt();
                        library.addAtPosition(pos, title, author, genre, id, status);
                    }
                    break;
                case 4:
                    System.out.print("Enter Book ID to remove: ");
                    id = sc.nextInt();
                    library.removeByBookId(id);
                    break;
                case 5:
                    System.out.print("Enter Book Title: ");
                    title = sc.nextLine();
                    library.searchByTitle(title);
                    break;
                case 6:
                    System.out.print("Enter Author Name: ");
                    author = sc.nextLine();
                    library.searchByAuthor(author);
                    break;
                case 7:
                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt();
                    System.out.print("New Availability (true/false): ");
                    status = sc.nextBoolean();
                    library.updateAvailability(id, status);
                    break;
                case 8:
                    library.displayForward();
                    break;
                case 9:
                    library.displayReverse();
                    break;
                case 10:
                    library.countBooks();
                    break;
                case 0:
                    System.out.println("Exiting Library System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
