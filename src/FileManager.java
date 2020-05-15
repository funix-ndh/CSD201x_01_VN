import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FileManager {

    private MyFile[] files;

    /*
     * ======================================================================================================
     * LOAD FILES FUNCTION BLOCK
     * ======================================================================================================
     */
    public void loadFiles(final String folder) {
        final List<MyFile> listFiles = new ArrayList<>();
        final File dir = new File(folder);
        if (dir.exists()) {
            loadFiles(dir, listFiles);
            files = listFiles.toArray(new MyFile[0]);
            printFiles(files);
        } else {
            System.out.println(folder + " not found.");
        }
    }

    private void loadFiles(final File file, final List<MyFile> listFiles) {
        for (final File f : Objects.requireNonNull(file.listFiles())) {
            if (f.isDirectory()) {
                loadFiles(f, listFiles);
            } else {
                listFiles.add(new MyFile(f.getName(), f.length(), f.getAbsolutePath()));
            }
        }
    }

    /*
     * ======================================================================================================
     * SORT FILES FUNCTION BLOCK
     * ======================================================================================================
     */
    public void sort(final SortType st) {
        if (files != null && files.length > 0) {
            final MyFile[] txtFiles = Stream.of(files)
                    .filter(f -> f.getName().toLowerCase().endsWith(".txt"))
                    .toArray(MyFile[]::new);
            if (st == SortType.INSERTTIONSORT) {
                insertionSort(txtFiles);
            } else if (st == SortType.SELECTIONSORT) {
                selectionSort(txtFiles);
            } else {
                quickSort(txtFiles, 0, txtFiles.length - 1);
            }
            printFiles(txtFiles);
        } else {
            System.out.println("List of files is empty...");
        }
    }

    private void selectionSort(final MyFile[] files) {
        for (int i = 0; i < files.length; i ++) {
            int minIdx = i; // store index's min element
            for (int j = i + 1; j < files.length; j ++) {
                if (files[minIdx].getSize() > files[j].getSize()) {
                    minIdx = j; // update new min index
                }
            }
            // swap value
            final MyFile temp = files[minIdx];
            files[minIdx] = files[i];
            files[i] = temp;
        }
    }

    private void insertionSort(final MyFile[] files) {
        for (int i = 1; i < files.length; i ++) {
            final MyFile temp = files[i]; // store temp element
            int j = i - 1; // j move backward and check if value's j index larger than temp then swap
            while (j >= 0 && temp.getSize() < files[j].getSize()) {
                files[j + 1] = files[j];
                j --;
            }
            files[j + 1] = temp;
        }
    }

    private void quickSort(final MyFile[] files, final int left, final int right) {
        if (left >= right) {
            return;
        }
        final MyFile pivot = files[left + (right - left) / 2];
        final int index = partition(files, pivot, left, right);
        quickSort(files, left, index - 1);
        quickSort(files, index, right);
    }

    private int partition(final MyFile[] files, final MyFile pivot, int left, int right) {
        while (left <= right) {
            while (files[left].getSize() < pivot.getSize()) {
                left ++;
            }
            while (files[right].getSize() > pivot.getSize()) {
                right --;
            }
            if (left <= right) {
                final MyFile temp = files[left];
                files[left] = files[right];
                files[right] = temp;
                left ++;
                right --;
            }
        }
        return left;
    }

    /*
     * ======================================================================================================
     * SEARCH FILES FUNCTION BLOCK
     * ======================================================================================================
     */
    public void searchFile(final String keyword) throws IOException {
        if (files != null && files.length > 0) {
            //save all files which matched given keyword to the list and output the list
            final List<MyFile> listFiles = new ArrayList<>();
            for (final MyFile f : files) {
                if (searchFile(f, keyword)) {
                    listFiles.add(f);
                }
            }
            final MyFile[] foundFiles = listFiles.toArray(new MyFile[0]);
            if (foundFiles.length < 1) {
                System.out.println(keyword + " not found.");
                return;
            }
            quickSort(foundFiles, 0, foundFiles.length - 1);
            printFiles(foundFiles);
        } else {
            System.out.println("List of files is empty...");
        }
    }

    private boolean searchFile(final MyFile mf, final String keyword) throws IOException {
        if (!mf.getName().toLowerCase().endsWith(".txt")) {
            return false;
        }
        final LineNumberReader lnr = new LineNumberReader(new FileReader(new File(mf.getFullPath())));
        while (true) {
            final String line = lnr.readLine();
            if (line == null) {
                break;
            }
            if (line.toLowerCase().contains(keyword.toLowerCase())) {
                lnr.close();
                return true;
            }
        }
        lnr.close();
        return false;
    }

    public void printFile(final String fileName) throws IOException {
        if (files != null && files.length > 0) {
            for (final MyFile f : files) {
                if (f.getName().equalsIgnoreCase(fileName)) {
                    final LineNumberReader lnr = new LineNumberReader(
                            new FileReader(new File(f.getFullPath())));
                    while (true) {
                        final String line = lnr.readLine();
                        if (line == null) {
                            break;
                        }
                        System.out.println(line);
                    }
                    lnr.close();
                    return;
                }
            }
            System.out.println(fileName + " not found.");
        } else {
            System.out.println("List of files is empty...");
        }
    }

    /*
     * ======================================================================================================
     * PRINT FILES HELPER
     * ======================================================================================================
     */
    private void printFiles(final MyFile[] files) {
        if (files != null && files.length > 0) {
            System.out.println(String.format("%-20s%-10s", "Name", "Size(in byte)")); // print header
            for (final MyFile f : files) {
                System.out.println(f);
            }
        } else {
            System.out.println("List of files is empty...");
        }
    }
}
