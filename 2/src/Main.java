import java.util.Scanner;

import entity.Book;

public final class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final BookList bookList = new BookList();

        while (true) {
            System.out.println("Book List");
            System.out.println("1.Input Book and add to the end");
            System.out.println("2.Display books");
            System.out.println("3.Search by code");
            System.out.println("4.Input Book and add to the beginning");
            System.out.println("5.Add Book after position k");
            System.out.println("6.Delete Book after position k");
            System.out.println("7.Sort book by price");
            System.out.println("8.Sort book by book code");
            System.out.println("0.Exit");
            System.out.print("Your choice: ");
            final int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    scanner.close();
                    return;
                case 1:
                    bookList.addLast(getBook(scanner, bookList));
                    break;
                case 2:
                    bookList.list();
                    break;
                case 3:
                    System.out.print("Enter Book Code: ");
                    bookList.search(scanner.nextLine());
                    break;
                case 4:
                    bookList.addFirst(getBook(scanner, bookList));
                    break;
                case 5:
                    bookList.addAfter(getBook(scanner, bookList), getPosition(scanner));
                    break;
                case 6:
                    bookList.deleteAt(getPosition(scanner));
                    break;
                case 7:
                    bookList.sortByPrice();
                    break;
                case 8:
                    bookList.sortByBookCode();
                    break;
                default:
                    break;
            }
        }
    }

    private static Book getBook(Scanner scanner, BookList bookList) {
        System.out.println("Enter information of a Book");

        String bCode;
        do {
            System.out.print("Book Code: ");
            bCode = scanner.nextLine();
        } while (!bookList.checkBCode(bCode));

        String title;
        do {
            System.out.print("Book Title: ");
            title = scanner.nextLine();
        } while (!bookList.checkTitle(title));

        System.out.print("Book Quantity: ");
        final int quantity = scanner.nextInt();

        System.out.print("Book Lended: ");
        final int lended = scanner.nextInt();

        System.out.print("Book Price: ");
        final double price = scanner.nextDouble();

        return new Book(bCode, title, quantity, lended, price);
    }

    private static int getPosition(Scanner scanner) {
        System.out.print("Enter Position: ");
        return scanner.nextInt();
    }
}
