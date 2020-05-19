import java.util.Scanner;

import entity.Product;

public final class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final MyProduct myProduct = new MyProduct();
        while (true) {
            System.out.println("Product List");
            System.out.println("1.Insert a new product");
            System.out.println("2.In-order traverse");
            System.out.println("3.Breadth first traverse");
            System.out.println("4.Search by product code");
            System.out.println("5.Delete by product code");
            System.out.println("6.Simple balancing");
            System.out.println("7.Count number of products");
            System.out.println("8.Print products that larger than input price");
            System.out.println("0.Exit");
            System.out.print("Your choice: ");
            final int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    scanner.close();
                    return;
                case 1:
                    System.out.print("Enter product code: ");
                    final String code = scanner.nextLine();
                    System.out.print("Enter product name: ");
                    final String name = scanner.nextLine();
                    System.out.print("Enter product quantity: ");
                    final int quantity = scanner.nextInt();
                    System.out.print("Enter product saled: ");
                    final int saled = scanner.nextInt();
                    System.out.print("Enter product price: ");
                    final double price = scanner.nextDouble();
                    myProduct.insert(new Product(code, name, quantity, saled, price));
                    break;
                case 2:
                    myProduct.inOrder();
                    break;
                case 3:
                    myProduct.BFT();
                    break;
                case 4:
                    System.out.print("Enter product code to search: ");
                    myProduct.search(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Enter product code to delete: ");
                    myProduct.delete(scanner.nextLine());
                    break;
                case 6:
                    myProduct.balance();
                    break;
                case 7:
                    System.out.println("Number of products " + myProduct.size());
                    break;
                case 8:
                    System.out.print("Enter price: ");
                    myProduct.showProductLargerThan(scanner.nextDouble());
                    break;
            }
        }
    }
}
