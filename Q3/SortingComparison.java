// because of  java.lang.StackOverflowError exception we need to compile the code and run it at terminal with following command
// java -Xss40m SortingComparison 
// basically increasing the stack size for recursion algorithims

/* Results
Running insertionSort with 10000 values took 11ms
Running mergeSortRecursive with 10000 values took 1ms
Running mergeSortIterative with 10000 values took 1ms
Running quickSortRecursive with 10000 values took 59ms
Running quickSortIterative with 10000 values took 26ms
Running insertionSort with 50000 values took 132ms
Running mergeSortRecursive with 50000 values took 1ms
Running mergeSortIterative with 50000 values took 4ms
Running quickSortRecursive with 50000 values took 633ms
Running quickSortIterative with 50000 values took 634ms
Running insertionSort with 100000 values took 461ms
Running mergeSortRecursive with 100000 values took 5ms
Running mergeSortIterative with 100000 values took 3ms
Running quickSortRecursive with 100000 values took 2444ms
Running quickSortIterative with 100000 values took 2446ms
Running insertionSort with 500000 values took 11503ms
Running mergeSortRecursive with 500000 values took 21ms
Running mergeSortIterative with 500000 values took 33ms
Running quickSortRecursive with 500000 values took 50522ms
Running quickSortIterative with 500000 values took 50545ms
Running insertionSort with 1000000 values took 47465ms
Running mergeSortRecursive with 1000000 values took 46ms
Running mergeSortIterative with 1000000 values took 41ms
Running quickSortRecursive with 1000000 values took 165573ms
Running quickSortIterative with 1000000 values took 165333ms
 */

import java.util.Random;

public class SortingComparison {

  static void info(String name, int size, long time) {
    System.out
        .println("Running " + name + " with " + Integer.toString(size) + " values took " + Long.toString(time / 1000000)
            + "ms");
  }

  public static void main(String[] args) {
    Random random = new Random();
    int[] sizes = { 10000, 50000, 100000, 500000, 1000000 };
    String[] names = { "insertionSort", "mergeSortRecursive", "mergeSortIterative", "quickSortRecursive",
        "quickSortIterative" };
    int start = 0;

    for (int i = 0; i < sizes.length; i++) {
      int rs = 10;
      int re = 1000000;
      int[] data = random.ints(sizes[i], rs, re).toArray();

      long startTime = System.nanoTime();
      SortingAlgorithms.insertionSort(data);
      long elapsedTime = System.nanoTime() - startTime;
      info(names[0], sizes[i], elapsedTime);

      startTime = System.nanoTime();
      SortingAlgorithms.mergeSortRecursive(data, start, sizes[i] - 1);
      elapsedTime = System.nanoTime() - startTime;
      info(names[1], sizes[i], elapsedTime);

      startTime = System.nanoTime();
      SortingAlgorithms.mergeSortIterative(data, sizes[i]);
      elapsedTime = System.nanoTime() - startTime;
      info(names[2], sizes[i], elapsedTime);

      startTime = System.nanoTime();
      SortingAlgorithms.quickSortRecursive(data, start, sizes[i] - 1);
      elapsedTime = System.nanoTime() - startTime;
      info(names[3], sizes[i], elapsedTime);

      startTime = System.nanoTime();
      SortingAlgorithms.quickSortIterative(data, start, sizes[i] - 1);
      elapsedTime = System.nanoTime() - startTime;
      info(names[4], sizes[i], elapsedTime);
    }

  }

}
