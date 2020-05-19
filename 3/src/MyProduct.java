import entity.Product;
import util.MyBSTree;
import util.Node;

public class MyProduct {

    MyBSTree tree;

    public MyProduct() {
        tree = new MyBSTree();
        tree.insert(new Product("3", "3", 3,3,3));
        tree.insert(new Product("1", "1", 1,1,1));
        tree.insert(new Product("4", "4", 4,4,4));
        tree.insert(new Product("2", "2", 2,2,2));
        tree.insert(new Product("6", "6", 6,6,6));
        tree.insert(new Product("5", "5", 5,5,5));
    }

    //1.1 input and insert a new product to tree
    public void insert(Product p) {
        tree.insert(p);
    }

    //1.2 in-order traverse
    public void inOrder() {
        Product.printHeader();
        tree.inOrder();
    }

    //1.3 BFT a tree
    public void BFT() {
        Product.printHeader();
        tree.BFT();
    }

    //1.4 search a product by product code
    public void search(String code) {
        final Node<Product> node = tree.search(code);
        if (node == null) {
            System.out.println(code + " not found.");
        } else {
            System.out.println(node.info);
        }
    }

    //1.5 delete a product by product code
    public void delete(String code) {
        final Node<Product> node = tree.search(code);
        if (node != null) {
            tree.delete(code);
            System.out.println("Product code " + code + " has been deleted");
        } else {
            System.out.println(code + " not found.");
        }
    }

    //1.6 simply balancing a tree
    public void balance() {
        tree.balance();
    }

    //1.7 count the number of products in the tree
    public int size() {
        return tree.count();
    }

    public void showProductLargerThan(double price) {
        tree.findLargerNode(price);
    }
}
