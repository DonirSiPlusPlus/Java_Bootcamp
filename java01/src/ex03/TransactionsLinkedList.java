import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
  TransactionsLinkedList() {
    size = 0;
  }

  TransactionsLinkedList(Transaction tr) {
    first_node = new Node(tr);
    last_node = first_node;
    size = 1;
  }

  public void addTransaction(Transaction tr) {
    Node temp = new Node(tr);

    if (first_node == null) {
      first_node = temp;
    } else if (size == 1) {
      first_node.next_ = temp;
      temp.prev_ = first_node;
    } else {
      last_node.next_ = temp;
      temp.prev_ = last_node;
    }
    last_node = temp;

    ++size;
  }

  public void deleteTransaction(UUID id) throws TransactionNotFoundException {
    Node i = first_node;

    while (i != null && size > 0) {
      if (i.transaction_.getId() == id) {
        if (size-- == 1) {
          first_node = last_node = null;
        } else if (i == first_node){
          first_node = first_node.next_;
          first_node.prev_ = null;
        } else if (i == last_node) {
          last_node = last_node.prev_;
          last_node.next_ = null;
        } else {
          i.prev_.next_ = i.next_;
          i.next_.prev_ = i.prev_;
        }
        i.transaction_ = null;
        return;
      }
      i = i.next_;
    }

    throw new TransactionNotFoundException();
  }

  public Transaction[] toArray() {
    Transaction[] array = new Transaction[size];

    int j = 0;
    for (Node i = first_node; i != null; i = i.next_) {
      array[j++] = i.transaction_;
    }

    return array;
  }

  private static class Node {
    Node(Transaction transaction) {
      this.transaction_ = transaction;
      prev_ = next_ = null;
    }

    Transaction transaction_;
    Node prev_;
    Node next_;
  }

  private Node first_node;
  private Node last_node;

  private int size;
}