
package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import entity.Book;

public class MyList {

    Node<Book> head, tail;
    int size;

    public MyList() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null || size < 1;
    }

    public void addLast(Book b) {
        if (head == null || tail == null) {
            head = tail = new Node<>(b, null);
        } else {
            tail = tail.next = new Node<>(b, null);
        }
        size++;
    }

    public void addFirst(Book b) {
        if (head == null || tail == null) {
            head = tail = new Node<>(b, null);
        } else {
            head = new Node<>(b, head);
        }
        size++;
    }

    public void traverse() {
        Node<Book> node = head;
        while (node != null) {
            System.out.println(node.info);
            node = node.next;
        }
    }

    public int size() {
        return size;
    }

    public Node<Book> getNode(int k) {
        if (k >= size || k < 0) {
            System.out.println("Invalid position");
            return null;
        }
        Node<Book> node = head;
        for (int i = 0; i < k; i++) {
            node = node.next;
        }
        return node;
    }

    public void addAfter(Book b, int k) {
        if (k >= size || k < 0) {
            System.out.println("Invalid position");
            return;
        }
        final Node<Book> node = getNode(k);
        node.next = new Node<>(b, node.next);
        if (size - 1 == k) {
            tail = node.next;
        }
        size++;
    }

    public void deleteAt(int k) {
        if (k >= size || k < 0) {
            System.out.println("Invalid position");
            return;
        }
        final Node<Book> node = getNode(k - 1);
        if (node != null) {
            Node<Book> temp = node.next;
            node.next = node.next.next;
            temp = null; // free to garbage collectors
        } else {
            Node<Book> temp = head;
            head = temp.next;
            temp = null; // free to garbage collectors
        }
        size--;
    }

    public Node<Book> search(String bCode) {
        Node<Book> node = head;
        while (node != null) {
            if (node.info.getbCode().equalsIgnoreCase(bCode)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public void sortByPrice() {
        final List<Book> books = toList();
        books.sort(Comparator.comparingDouble(Book::getPrice));
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void sortByBookCode() {
        final List<Book> books = toList();
        books.sort(Comparator.comparing(Book::getbCode));
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private List<Book> toList() {
        final List<Book> books = new ArrayList<>(size);
        Node<Book> node = head;
        while (node != null) {
            books.add(node.info);
            node = node.next;
        }
        return books;
    }
}
