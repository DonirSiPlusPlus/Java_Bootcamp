import java.util.UUID;

public interface TransactionsList {
  public void addTransaction(Transaction tr);

  public void deleteTransaction(UUID id)  throws TransactionNotFoundException;

  public Transaction[] toArray();
}