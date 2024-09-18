import java.util.UUID;

public class TransactionsService {

  TransactionsService() {
    userArray_ = new UsersArrayList();
    transactionsList_ = new TransactionsLinkedList();
  }

  public void addUser(User user) {
    userArray_.addUser(user);
  }

  public int getBalance(int id) throws UserNotFoundException {
    return userArray_.getUser(id).getBalance();
  }

  public void makeTransaction(int id_recipient, int id_sender, int transf_amount) throws UserNotFoundException, IllegalTransactionException {
    if (transf_amount < 0) {
      throw new IllegalTransactionException();
    }

    User sender = userArray_.getUser(id_sender);
    if (sender.getBalance() - transf_amount < 0) {
      throw new IllegalTransactionException();
    }

    User recipient = userArray_.getUser(id_recipient);
    Transaction debit = new Transaction(recipient, sender, transf_amount);
    Transaction credit = new Transaction(debit.getId(), recipient, sender, transf_amount * -1);

    transactionsList_.addTransaction(debit);
    transactionsList_.addTransaction(credit);

    recipient.addTransaction(debit);
    sender.addTransaction(credit);
  }

  public void deleteTransaction(UUID id_transf, int id_user) throws UserNotFoundException,
                                                             TransactionNotFoundException
  {
    userArray_.getUser(id_user).getTransactions().deleteTransaction(id_transf);
  }

  public Transaction[] getTransArray(User user) throws UserNotFoundException {
    return userArray_.getUser(user.getId()).getTransactions().toArray();
  }

  public Transaction[] checkValidArray() {
    Transaction[] arrayTransactions = transactionsList_.toArray();
    Transaction[] inValidTransactions = new Transaction[arrayTransactions.length];
    int i = 0;

    for (int j = 0; j < arrayTransactions.length; ++j) {
      if ((j + 1) == arrayTransactions.length) {
        inValidTransactions[i++] = arrayTransactions[j];
      }
      if (arrayTransactions[j].getId() == arrayTransactions[j + 1].getId() &&
              arrayTransactions[j].getTransfType() != arrayTransactions[j + 1].getTransfType()) {
        ++j;
      } else {
        inValidTransactions[i++] = arrayTransactions[j];
      }
    }

    return checkNull(inValidTransactions, i);
  }

  private Transaction[] checkNull(Transaction[] inValidTransactions, int size) {
    Transaction[] correctArray = new Transaction[size];

    for (int i = 0; i < size; ++i) {
      correctArray[i] = inValidTransactions[i];
    }

    return correctArray;
  }

  UsersArrayList userArray_;
  TransactionsLinkedList transactionsList_;
}