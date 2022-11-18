import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Question2 {

  enum Priority {
    NORMAL,
    MINOR,
    MAJOR,
    CRITICAL,
    UNKNOWN,
  }
  // In case we want to have the most critical on top
  // enum Priority {
  // CRITICAL,
  // MAJOR,
  // MINOR,
  // NORMAL,
  // UNKNOWN,
  // }

  static private Priority getPriority(String val) {
    switch (val) {
      case "normal":
        return Priority.NORMAL;
      case "minor":
        return Priority.MINOR;
      case "major":
        return Priority.MAJOR;
      case "critical":
        return Priority.CRITICAL;
      default:
        return Priority.UNKNOWN;
    }

  }

  public static void main(String[] args) {
    PriorityQueue<Priority, String> hpq = new HeapPriorityQueue<>();

    try {
      File file = new File("log.txt");
      Scanner fileReader = new Scanner(file);
      while (fileReader.hasNextLine()) {
        String data = fileReader.nextLine().toLowerCase();
        String[] vals = data.split(":");
        // we can skip adding UNKNOWN keys if we don't want to have them in heap
        hpq.insert(getPriority(vals[1].strip()), vals[0]);
      }
      fileReader.close();
      System.out.println("Data imported into HeapPriorityQueue.\n");
    } catch (FileNotFoundException e) {
      System.out.println("<log.txt> could not found!");
    }

    String bar = "------------------------------------------";
    hpq.listHeap();
    System.out.println(bar);
    hpq.removeMin();

    hpq.listHeap();
    System.out.println(bar);
    hpq.removeMin();

    hpq.listHeap();
    System.out.println(bar);
    hpq.removeMin();

    hpq.listHeap();
    System.out.println(bar);
    hpq.removeMin();

    hpq.listHeap();
    System.out.println(bar);
    hpq.removeMin();

    hpq.listHeap();
    System.out.println(bar);
    hpq.removeMin();

    hpq.listHeap();
    System.out.println(bar);
    hpq.removeMin();

    hpq.listHeap();
    System.out.println(bar);
    hpq.removeMin();

    hpq.listHeap();
  }

}
