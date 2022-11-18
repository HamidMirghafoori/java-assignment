import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// WordNode class
class WordNode {
  private String word;
  // this attribute is redundant, we can just return size of lineNumbers List to
  // get the number of repitition
  private int counter;
  private List<Integer> lineNumers = new ArrayList<Integer>();

  public WordNode(String newWord, int lineNumber) {
    lineNumers.add(lineNumber);
    word = newWord;
    addCounter();
  }

  public void addToList(int lineNumber) {
    lineNumers.add(lineNumber);
    addCounter();
  }

  public void addCounter() {
    counter++;
  }

  public String getWord() {
    return word;
  }

  public int getCount() {
    return counter;
  }

  private String getListString() {
    String list = "";
    int max = lineNumers.size();
    for (int i = 0; i < max; i++) {
      list += lineNumers.get(i).toString();
      if (i < max - 1) {
        list += ", ";
      }
    }
    return list;
  }

  public String getList() {
    String list = "[" + getListString() + "]";
    return list;
  }

  public int compareTo(WordNode node) {
    return (word.compareTo(node.getWord()));
  }

  public String toString() {
    return word + " (" + Integer.toString(counter) + ") : " + getListString();
  }
}

// Node class
class Node {
  private Node leftNode, rightNode;
  private WordNode word;

  // constructor
  public Node() {
    leftNode = null;
    rightNode = null;
    word = null;
  }

  // constructor with data
  public Node(String newWord) {
    leftNode = null;
    rightNode = null;
    word = new WordNode(newWord, 0);
  }

  public Node(String newWord, int lineNumber) {
    leftNode = null;
    rightNode = null;
    word = new WordNode(newWord, lineNumber);
  }

  // when there is a repititive word in the list we keep track of new instances
  public void updateWord(int lineNumber) {
    word.addToList(lineNumber);
  }

  // leftNode setter
  public void setLeft(Node node) {
    leftNode = node;
  }

  // rightNode setter
  public void setRight(Node node) {
    rightNode = node;
  }

  // leftNode getter
  public Node getLeft() {
    return leftNode;
  }

  // rightNode getter
  public Node getRight() {
    return rightNode;
  }

  // word getter
  public String getWord() {
    return word.getWord();
  }

  public WordNode getWordNode() {
    return word;
  }

}

class BinarySearchTree {
  private Node rootNode;
  private int distinctWords;

  // constructor
  public BinarySearchTree() {
    rootNode = null;
    distinctWords = 0;
  }

  public int getDistinctWordsCount() {
    return distinctWords;
  }

  public WordNode findWord(String item) {
    Node found = findWord(rootNode, item);
    if (found == null) {
      return null;
    } else {
      return found.getWordNode();
    }
  }

  private Node findWord(Node node, String item) {
    if (node == null)
      return null;
    if (item.compareTo(node.getWord()) == 0) {
      return node;
    } else {
      if (item.compareTo(node.getWord()) < 0) {
        return findWord(node.getLeft(), item);
      } else {
        return findWord(node.getRight(), item);
      }
    }
  }

  // insert new word method
  public void insert(String newWord) {
    rootNode = insertNode(rootNode, newWord);
  }

  // insert new word method wit lineNumber
  public void insert(String newWord, int lineNumber) {
    rootNode = insertNode(rootNode, newWord, lineNumber);
  }

  // internal recursive method to set new word node position in the tree
  private Node insertNode(Node node, String word) {
    if (node == null)
      node = new Node(word);
    else {
      if (word.compareTo(node.getWord()) < 0)
        node.setLeft(insertNode(node.getLeft(), word));
      else
        node.setRight(insertNode(node.getRight(), word));
    }
    return node;
  }

  // internal recursive method to set new word node position in the tree
  // with lineNumber
  private Node insertNode(Node node, String word, int lineNumber) {
    if (node == null) {
      distinctWords++;
      node = new Node(word, lineNumber);
    } else if (node.getWord().compareTo(word) == 0) {
      // same word again
      node.updateWord(lineNumber);
    } else {
      if (word.compareTo(node.getWord()) < 0)
        node.setLeft(insertNode(node.getLeft(), word, lineNumber));
      else
        node.setRight(insertNode(node.getRight(), word, lineNumber));
    }
    return node;
  }

  public void printSorted() {
    printInOrder(rootNode);
    System.out.print("\n");
  }

  private void printInOrder(Node node) {
    if (node != null) {
      printInOrder(node.getLeft());
      System.out.print(node.getWord() + ", ");
      printInOrder(node.getRight());
    }
  }

  public void printAll() {
    if (rootNode.getWord() != "") {
      printAllNodes(rootNode);
    } else {
      System.out.println("Tree is empty!");
    }
    System.out.print("\n");
  }

  public void printMinimum(int min) {
    if (rootNode.getWord() != "") {
      if (min < 1) {
        System.out.println("Enter a number bigger than 0!");
      } else {
        printMinimumCount(rootNode, min);
      }
    } else {
      System.out.println("Tree is empty!");
    }
    System.out.print("\n");
  }

  public void printInBetween(String word1, String word2) {
    if (rootNode.getWord() != "") {
      printInBetween(rootNode, word1, word2);
    } else {
      System.out.println("Tree is empty!");
    }
    System.out.print("\n");
  }

  public void printAll(int number) {
    if (number > 0) {
      if (rootNode.getWord() != "") {
        printAllNodes(rootNode, 0, number);
      } else {
        System.out.println("Tree is empty!\n");
      }
    } else {
      System.out.println("Enter number bigger than 0!\n");
    }
    System.out.print("\n");
  }

  private void printAllNodes(Node node) {
    System.out.print(node.getWord() + ", ");
    if (node.getLeft() != null) {
      printAllNodes(node.getLeft());
    }
    if (node.getRight() != null) {
      printAllNodes(node.getRight());
    }
  }

  private void printMinimumCount(Node node, int min) {
    if (node.getLeft() != null) {
      printMinimumCount(node.getLeft(), min);
    }
    if (node.getWordNode().getCount() >= min) {
      System.out.print(node.getWord() + ", ");
    }
    if (node.getRight() != null) {
      printMinimumCount(node.getRight(), min);
    }
  }

  // putting print after getLeft makes it alphabetically in order, if we put
  // before that it will displayed as it is in database
  private void printInBetween(Node node, String word1, String word2) {
    String word = node.getWordNode().getWord();
    int comp1 = word.compareTo(word1);
    int comp2 = word.compareTo(word2);
    if (node.getLeft() != null) {
      printInBetween(node.getLeft(), word1, word2);
    }
    if (comp1 >= 0 && comp2 <= 0) {
      System.out.print(node.getWord() + "(" + Integer.toString(node.getWordNode().getCount()) + "), ");
    }
    if (node.getRight() != null) {
      printInBetween(node.getRight(), word1, word2);
    }
  }

  private void printAllNodes(Node node, int counter, int max) {
    counter++;
    if (counter > max)
      return;
    System.out.print(node.getWord() + ", ");
    if (node.getLeft() != null) {
      printAllNodes(node.getLeft(), counter, max);
    }
    counter++;
    if (counter > max)
      return;
    if (node.getRight() != null) {
      printAllNodes(node.getRight(), counter, max);
    }
  }

}

class DocAnalizer {
  BinarySearchTree BST = new BinarySearchTree();

  public DocAnalizer(String filename) {
    try {
      System.out.println("Filename <" + filename + "> found.\n");
      File file = new File(filename);
      Scanner fileReader = new Scanner(file);
      int lineCounter = 0;
      while (fileReader.hasNextLine()) {
        String data = fileReader.nextLine().toLowerCase();
        lineCounter++;
        BST.insert(data, lineCounter);
      }
      fileReader.close();
      System.out.println("Data imported into BTS.\n\n");
    } catch (FileNotFoundException e) {
      System.out.println("Filename <" + filename + "> could not found!\n");

    }
  }

  public void printAll() {
    BST.printAll();
  }

  public void printMinimum(int min) {
    BST.printMinimum(min);
  }

  public void printWordsDistinctNumber() {
    System.out.println("Number of distinct words = " + Integer.toString(BST.getDistinctWordsCount()));
  }

  public void printWordRepitition(String word) {
    WordNode found = BST.findWord(word);
    if (found != null) {
      System.out.println("<" + word + "> is repeated " + found.getCount() + " times.");
    } else {
      System.out.println("<" + word + "> not found!");
    }
  }

  public void printWordLineNumbers(String word) {
    WordNode found = BST.findWord(word);
    if (found != null) {
      System.out.println("<" + word + "> is appeared on lines " + found.getList() + " times.");
    } else {
      System.out.println("<" + word + "> not found!");
    }
  }

  public void printInBetween(String word1, String word2) {
    WordNode found = BST.findWord(word1);
    WordNode found2 = BST.findWord(word1);
    if (found == null) {
      System.out.println("<" + word1 + "> not found!");
    } else if (found2 == null) {
      System.out.println("<" + word2 + "> not found!");
    } else if (word1.compareTo(word2) > 0) {
      System.out.println("<" + word2 + "> should be 1st word!");
    } else {
      BST.printInBetween(word1, word2);
    }
  }

  public void getLineNumberList(String word) {
    WordNode found = BST.findWord(word);
    if (found != null) {
      System.out.println("<" + word + "> is repeated in lines : " + found.getList() + ".");
    } else {
      System.out.println("<" + word + "> not found!");
    }
  }
}

public class Main {
  static Scanner scanner = new Scanner(System.in);

  private static void showMenu() {
    System.out.println(
        "1: Print All  2: Distinct#  3: Word repeatition  4: WordCount > N  5: Between  6: Word Line #  7: Quit");
  }

  public static void main(String[] args) {

    System.out.println("Binary Search Tree by Ali Mirghafouri\n");
    System.out.println("=====================================\n");
    DocAnalizer DA = new DocAnalizer("words.txt");

    String command = "";
    int c = 0;
    do {
      showMenu();
      command = scanner.nextLine();
      try {
        c = Integer.parseInt(command);
        if ((c < 1 || c > 7)) {
          System.out.println("Wrong command! Enter 1-7\n");
        } else {
          switch (c) {
            case 1: {
              DA.printAll();
              break;
            }
            case 2: {
              DA.printWordsDistinctNumber();
              break;
            }
            case 3: {
              System.out.print("Enter the word: ");
              String word = "";
              word = scanner.nextLine();
              DA.printWordRepitition(word);
              break;
            }
            case 4: {
              System.out.print("Enter min number of repeat: ");
              int count = 0;
              try {
                count = Integer.parseInt(scanner.nextLine());
                DA.printMinimum(count);
              } catch (NumberFormatException e) {
                System.out.println("Only Digits are valid input.\n");
              }
              break;
            }
            case 5: {
              System.out.print("Enter 1st word: ");
              String word1 = scanner.nextLine();
              System.out.print("Enter 2nd word: ");
              String word2 = scanner.nextLine();
              DA.printInBetween(word1, word2);
              break;
            }
            case 6: {
              System.out.print("Enter the word: ");
              String word = "";
              word = scanner.nextLine();
              DA.getLineNumberList(word);
              break;
            }
          }
        }
      } catch (NumberFormatException e) {
        System.out.println("Only Digits are valid input.\n");
      }
    } while (c != 7);
    scanner.close();
  }
}