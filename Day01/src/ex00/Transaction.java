package ex00;

import java.util.UUID;

public class Transaction {
    private final UUID identifier;
    private final User recipient;
    private final User sender;
    private  Integer transAmount;
    private final Cat transCategory;

    enum Cat {
        CR, DT
    }

    public Transaction(User recipient, User sender, Integer transAmount, Cat transCategory) {
        this.identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transAmount = transAmount;
        this.transCategory = transCategory;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Integer getTransAmount() {
        return transAmount;
    }

    public Cat getTransCategory() {
        return transCategory;
    }

    public void ChangeBalance(Integer transAmount_) {
        if (transCategory == Cat.CR) {
            sender.setBalance(sender.getBalance() + transAmount_);
            recipient.setBalance(recipient.getBalance() - transAmount_);
        } else if (transCategory == Cat.DT) {
            sender.setBalance(sender.getBalance() - transAmount_);
            recipient.setBalance(recipient.getBalance() + transAmount_);
        }
    }

    public void TransactionFunc(Integer trAm_) {
        if ((trAm_ > 0) && (sender.getBalance() > trAm_) && (getTransCategory() == Cat.CR)) {
            this.transAmount = trAm_;
        } else if ((trAm_ < 0) && (sender.getBalance() > trAm_) && (getTransCategory() == Cat.DT)) {
            this.transAmount = trAm_;
        } else {
            this.transAmount = 0;
        }
    }

    public String PrintTransaction() {
        return "Transaction: " + "\n" + "ID: " + getIdentifier() + "\n" + "recipient: " + getRecipient() + "\n" + "sender: " + getSender() + "\n" + "transferAmount: " + getTransAmount() + "\n" + "transferCategory: " + getTransCategory();
    }
}
