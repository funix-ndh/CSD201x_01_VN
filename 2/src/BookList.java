import entity.Book;
import util.MyList;
import util.Node;

public class BookList {

    private final MyList books;

    public MyList getBooks() {
        return books;
    }

    public BookList() {
        books = new MyList();
        addLast(new Book("3", "Helper", 15, 3, 3.0));
        addFirst(new Book("2", "Intermediate", 10, 2, 2.0));
        addLast(new Book("4", "Tutorial", 20, 4, 4.0));
        addFirst(new Book("1", "Intro", 5, 1, 1.0));
        addAfter(new Book("5", "Practice", 25, 5, 5.0), 3);
        addAfter(new Book("6", "Testing", 30, 6, 6.0), 4);
    }

    public boolean checkBCode(String bCode) {
        if ("".equalsIgnoreCase(bCode)) {
            System.out.println("bCode must not be blank");
            return false;
        }
        if (books.search(bCode) != null) {
            System.out.println("bCode must be unique");
            return false;
        }
        return true;
    }

    public boolean checkTitle(String title) {
        if ("".equalsIgnoreCase(title)) {
            System.out.println("title must not be blank");
            return false;
        }
        return true;
    }

    //1.1 accept and add a new Book to the end of book list
    public void addLast(Book b) {
        if (b != null) {
            books.addLast(b);
        }
    }

    //1.2 output information of book list
    public void list() {
        books.traverse();
    }

    //1.3 search book by book code
    public void search(String bCode) {
        final Node<Book> node = books.search(bCode);
        if (node != null) {
            System.out.println(node.info);
        } else {
            System.out.println(bCode + " not found");
        }
    }

    //1.4 accept and add a new Book to the beginning of book list
    public void addFirst(Book b) {
        if (b != null) {
            books.addFirst(b);
        }
    }

    //1.5 Add a new Book after a position k
    public void addAfter(Book b, int k) {
        if (b != null) {
            books.addAfter(b, k);
        }
    }

    //1.6 Delete a Book at position k
    public void deleteAt(int k) {
        books.deleteAt(k);
    }

    public void sortByPrice() {
        books.sortByPrice();
    }

    public void sortByBookCode() {
        books.sortByBookCode();
    }
}
