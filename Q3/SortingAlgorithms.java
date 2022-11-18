/**
 *
 * @author abdul.qayoom
 */
public class SortingAlgorithms {

  static void merge(int[] dataList, int left, int mid, int right) {

    int lengthLeft = mid - left + 1;
    int lengthRight = right - mid;

    int leftArray[] = new int[lengthLeft];
    int rightArray[] = new int[lengthRight];

    for (int i = 0; i < lengthLeft; i++)
      leftArray[i] = dataList[left + i];
    for (int i = 0; i < lengthRight; i++)
      rightArray[i] = dataList[mid + i + 1];

    int leftIndex = 0;
    int rightIndex = 0;

    for (int i = left; i < right + 1; i++) {
      if (leftIndex < lengthLeft && rightIndex < lengthRight) {
        if (leftArray[leftIndex] < rightArray[rightIndex]) {
          dataList[i] = leftArray[leftIndex];
          leftIndex++;
        } else {
          dataList[i] = rightArray[rightIndex];
          rightIndex++;
        }
      }

      else if (leftIndex < lengthLeft) {
        dataList[i] = leftArray[leftIndex];
        leftIndex++;
      }

      else if (rightIndex < lengthRight) {
        dataList[i] = rightArray[rightIndex];
        rightIndex++;
      }
    }
  }

  static int partition(int[] dataList, int begin, int end) {

    int pivot = end;
    int temp;
    int counter = begin;
    for (int i = begin; i < end; i++) {
      if (dataList[i] < dataList[pivot]) {
        temp = dataList[counter];
        dataList[counter] = dataList[i];
        dataList[i] = temp;
        counter++;
      }
    }
    temp = dataList[pivot];
    dataList[pivot] = dataList[counter];
    dataList[counter] = temp;

    return counter;
  }

  public static void insertionSort(int[] dataList) {
    int current, j;
    for (int i = 1; i < dataList.length; i++) {
      current = dataList[i];
      j = i - 1;
      while (j >= 0 && current < dataList[j]) {
        dataList[j + 1] = dataList[j];
        j--;
      }
      dataList[j + 1] = current;
    }
  }

  public static void mergeSortIterative(int dataList[], int n) {
    int curSize;
    int left_start;
    int mid, right_end;
    for (curSize = 1; curSize <= n - 1; curSize = 2 * curSize) {
      for (left_start = 0; left_start < n - 1; left_start += 2 * curSize) {
        mid = Math.min(left_start + curSize - 1, n - 1);

        right_end = Math.min(left_start
            + 2 * curSize - 1, n - 1);
        merge(dataList, left_start, mid, right_end);
      }
    }
  }

  public static void mergeSortRecursive(int[] dataList, int left, int right) {
    if (right <= left)
      return;
    int mid = (left + right) / 2;
    mergeSortRecursive(dataList, left, mid);
    mergeSortRecursive(dataList, mid + 1, right);
    merge(dataList, left, mid, right);
  }

  public static void quickSortIterative(int dataList[], int low, int high) {
    int[] stack = new int[high - low + 1];
    int top = -1;
    int p;
    stack[++top] = low;
    stack[++top] = high;
    while (top >= 0) {
      high = stack[top--];
      low = stack[top--];
      p = partition(dataList, low, high);
      if (p - 1 > low) {
        stack[++top] = low;
        stack[++top] = p - 1;
      }
      if (p + 1 < high) {
        stack[++top] = p + 1;
        stack[++top] = high;
      }
    }

  }

  public static int[] quickSortRecursive(int[] dataList, int begin, int end) {
    if (end <= begin)
      return null;
    int pivot = partition(dataList, begin, end);
    quickSortRecursive(dataList, begin, pivot - 1);
    quickSortRecursive(dataList, pivot + 1, end);

    return dataList;
  }

}