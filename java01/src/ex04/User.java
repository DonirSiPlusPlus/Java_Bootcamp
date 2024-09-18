public class User {
  User(String name, int balance) {
    this.name_ = name;
    this.balance_ = balance;
    this.id_ = userIdsGenerator_.getInstance().generateId();
    transactions_ = new TransactionsLinkedList();
  }

  public void setName(String name) { this.name_ = name; }

  public void setBalance(int balance) {
    this.balance_ = (this.balance_ > 0) ? balance : this.balance_;
  }

  public int getId() { return id_; }

  public String getName() { return name_; }

  public int getBalance() { return balance_; }

  public void addTransaction(Transaction transaction) {
    transactions_.addTransaction(transaction);
  }

  public TransactionsLinkedList getTransactions() {
    return transactions_;
  }

  private int id_;
  private String name_;
  private int balance_;
  private UserIdsGenerator userIdsGenerator_;
  private TransactionsLinkedList transactions_;
}
