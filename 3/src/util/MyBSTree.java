package util;

import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class MyBSTree {

    Node<Product> root;
    int size;

    public MyBSTree() {
        root = null;
    }

    //visit a node of a tree -> output information of visited node
    public void visit(Node<Product> node) {
        System.out.println(node.info);
    }

    //return true if tree is empty otherwise return false
    public boolean isEmpty() {
        return size <= 0;
    }

    //inorder a tree
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<Product> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        visit(node);
        inOrder(node.right);
    }

    //count number of products
    public int count() {
        return size;
    }

    //breadth-first traverse a tree
    public void BFT() {
        final MyQueue myQueue = new MyQueue();
        myQueue.enqueue(root);
        while (!myQueue.isEmpty()) {
            final Node<Product> node = (Node<Product>) myQueue.dequeue();
            visit(node);
            if (node.left != null) {
                myQueue.enqueue(node.left);
            }
            if (node.right != null) {
                myQueue.enqueue(node.right);
            }
        }
    }

    //insert a new Product to a tree
    public void insert(Product product) {
        if (root == null) {
            root = new Node<>(product);
        } else {
            insert(product, root);
        }
        size++;
    }

    private static void insert(Product product, Node<Product> node) {
        if (product.getCode().compareTo(node.info.getCode()) < 0) {
            if (node.left == null) {
                node.left = new Node<>(product);
            } else {
                insert(product, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = new Node<>(product);
            } else {
                insert(product, node.right);
            }
        }
    }

    //balance a tree
    //step 1: traverse inorder tree and copy all item on tree to an arraylist
    //step 2: insert all items of list to a tree
    private static void buildArrayNodeLargerThanPrice(List<Node<Product>> list, Node<Product> p) {
        if (p == null) {
            return;
        }
        buildArrayNodeLargerThanPrice(list, p.left);
        list.add(p);
        buildArrayNodeLargerThanPrice(list, p.right);
    }

    //step 2:
    private void balance(List<Node<Product>> list, int f, int l) {
        if (f > l) {
            return;
        }
        final int mid = f + (l - f) / 2;
        insert(list.get(mid).info);
        balance(list, f, mid - 1);
        balance(list, mid + 1, l);
    }

    public void balance() {
        final List<Node<Product>> list = new ArrayList<>(size);
        buildArrayNodeLargerThanPrice(list, root);
        final MyBSTree myBSTree = new MyBSTree();
        myBSTree.balance(list, 0, list.size() - 1);
        root = myBSTree.root;
    }

    //search a Node of tree by product code
    //return null if given code does not exists
    public Node<Product> search(String code) {
        if (isEmpty()) {
            return null;
        }
        Node<Product> node = root;
        while (node != null) {
            if (node.info.getCode().equalsIgnoreCase(code)) {
                break;
            } else if (node.info.getCode().compareTo(code) < 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return node;
    }

    //delete a node by a given product code
    public void delete(String code) {
        final List<Node<Product>> list = new ArrayList<>(size);
        buildArrayNodeLargerThanPrice(list, root);
        for (Node<Product> n : list) {
            if (n.info.getCode().equalsIgnoreCase(code)) {
                list.remove(n);
                break;
            }
        }
        final MyBSTree myBSTree = new MyBSTree();
        myBSTree.balance(list, 0, list.size() - 1);
        root = myBSTree.root;
        size--;
    }

    public void findLargerNode(double price) {
        final List<Node<Product>> list = new ArrayList<>(size);
        final MyBSTree myBSTree = new MyBSTree();
        buildArrayNodeLargerThanPrice(list, root, price);

        if (list.isEmpty()) {
            System.out.println("Not found any product that larger than price " + price);
            return;
        }

        myBSTree.balance(list, 0, list.size() - 1);
        Product.printHeader();
        myBSTree.inOrder(myBSTree.root);
        System.out.println("Tree height is " + getHeight(myBSTree.root));
    }

    private static void buildArrayNodeLargerThanPrice(
            List<Node<Product>> list, Node<Product> node, double price) {
        if (node == null) {
            return;
        }
        buildArrayNodeLargerThanPrice(list, node.left, price);
        if (node.info.getPrice() >= price) {
            list.add(node);
        }
        buildArrayNodeLargerThanPrice(list, node.right, price);
    }

    private static int getHeight(Node<Product> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
}
