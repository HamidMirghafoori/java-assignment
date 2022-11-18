public interface PriorityQueue<K, V> {
  int size();

  void listHeap();

  boolean isEmpty();

  Entry<K, V> insert(K key, V value) throws IllegalArgumentException;

  Entry<K, V> min();

  Entry<K, V> removeMin();
}