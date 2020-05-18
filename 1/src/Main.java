import java.io.IOException;
import java.util.Scanner;

public final class Main {

    public static void main(final String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        final FileManager fm = new FileManager();

        while (true) {
            System.out.println("Menu");
            System.out.println("1.Load files");
            System.out.println("2.Sort files");
            System.out.println("3.Search files");
            System.out.println("4.Print file");
            System.out.println("0.Exit");
            System.out.print("Enter your choice: ");
            final int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    scanner.close();
                    return;
                case 1:
                    loadFiles(scanner, fm);
                    break;
                case 2:
                    sortFiles(scanner, fm);
                    break;
                case 3:
                    searchFiles(scanner, fm);
                    break;
                case 4:
                    printFile(scanner, fm);
                    break;
                default:
                    System.out.println("Please choose number [0, 1, 2, 3, 4].");
                    break;
            }
        }
    }

    private static void loadFiles(final Scanner scanner, final FileManager fm) {
        System.out.print("Enter a folder: ");
        fm.loadFiles(scanner.nextLine());
    }

    private static void sortFiles(final Scanner scanner, final FileManager fm) {
        System.out.println("Sort the list of files by using:");
        System.out.println("1.Selection sort");
        System.out.println("2.Insertion sort");
        System.out.println("3.Quick sort");
        System.out.print("Enter your choice: ");
        final int sortType = scanner.nextInt();
        switch (sortType) {
            case 1:
                fm.sort(SortType.SELECTIONSORT);
                break;
            case 2:
                fm.sort(SortType.INSERTTIONSORT);
                break;
            case 3:
                fm.sort(SortType.QUICKSORT);
                break;
            default:
                System.out.println("Please choose number [1, 2, 3].");
                break;
        }
    }

    private static void searchFiles(final Scanner scanner, final FileManager fm) throws IOException {
        System.out.print("Enter any keyword to search: ");
        fm.searchFile(scanner.nextLine());
    }

    private static void printFile(final Scanner scanner, final FileManager fm) throws IOException {
        System.out.print("Enter file name to print: ");
        fm.printFile(scanner.nextLine());
    }
}
