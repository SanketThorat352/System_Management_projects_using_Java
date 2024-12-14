import java.util.*;

class Book {
    private String title;
    private String author;
    private int bookId;
    private boolean isIssued;

    public Book(String title, String author, int bookId) {
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public int getBookId() {
        return bookId;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        this.isIssued = true;
    }

    public void returnBook() {
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Issued: " + (isIssued ? "Yes" : "No");
    }
}

class Library {
    private Map<Integer, Book> books = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
        System.out.println("Book added successfully.");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    public void issueBook(int bookId) {
        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isIssued()) {
            System.out.println("Book is already issued.");
        } else {
            book.issueBook();
            System.out.println("Book issued successfully.");
        }
    }

    public void returnBook(int bookId) {
        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (!book.isIssued()) {
            System.out.println("Book is not issued.");
        } else {
            book.returnBook();
            System.out.println("Book returned successfully.");
        }
    }

    public void searchBook(String title) {
        boolean found = false;
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book with title '" + title + "' not found.");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book by Title");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();

                    Book book = new Book(title, author, bookId);
                    library.addBook(book);
                    break;

                case 2:
                    library.displayBooks();
                    break;

                case 3:
                    System.out.print("Enter book ID to issue: ");
                    int issueBookId = scanner.nextInt();
                    scanner.nextLine();
                    library.issueBook(issueBookId);
                    break;

                case 4:
                    System.out.print("Enter book ID to return: ");
                    int returnBookId = scanner.nextInt();
                    scanner.nextLine();
                    library.returnBook(returnBookId);
                    break;

                case 5:
                    System.out.print("Enter book title to search: ");
                    String searchTitle = scanner.nextLine();
                    library.searchBook(searchTitle);
                    break;

                case 6:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
