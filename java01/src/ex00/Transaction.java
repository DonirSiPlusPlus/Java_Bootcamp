import java.util.UUID;

public class Transaction {
  Transaction(User recipient, User sender, int transf_amount) {
    conctructor(recipient, sender, transf_amount);
    id_ = UUID.randomUUID();
  }

  Transaction(UUID id, User recipient, User sender, int transf_amount) {
    conctructor(recipient, sender, transf_amount);
    id_ = id;
  }

  public UUID getId() { return id_; }

  public User getRecipient() { return recipient_; }

  public User getSender() { return sender_; }

  public TransfType getTransfType() { return transfType_; }

  public int getTransfAmount() { return transf_amount_; }

  private enum TransfType {
    DEBIT,
    CREDIT
  }

  private void conctructor(User recipient, User sender, int transf_amount) {
    this.recipient_ = recipient;
    this.sender_ = sender;
    this.transf_amount_ = transf_amount;

    transfType_ = (transf_amount < 0) ? TransfType.CREDIT : TransfType.DEBIT;

    if (sender.getBalance() * recipient.getBalance() > 0) {
      if (transfType_ == TransfType.DEBIT) {
        sender_.setBalance(sender_.getBalance() - transf_amount);
      } else {
        recipient_.setBalance(recipient_.getBalance() - transf_amount);
      }
    }
  }

  private UUID id_;
  private User recipient_;
  private User sender_;
  private TransfType transfType_;
  private int transf_amount_;
}
