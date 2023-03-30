package ex03;

import java.util.UUID;

public class Transaction {
    private final UUID identifier;
    private User recipient;
    private User sender;
    private  Integer transAmount ;
    private final Cat transCategory;

    enum Cat {
        CR, DT
    }

    public Transaction(User rec, User sen, Integer trAm, Cat trCat) {
        this.identifier = UUID.randomUUID();
        this.recipient = rec;
        this.sender = sen;
        this.transAmount = trAm;
        this.transCategory = trCat;
    }

    public void setRecipient(User rec) { this.recipient = rec; }
    public void setSender(User sen) { this.sender = sen; }

    public UUID getID() { return identifier; }
    public User getRecipient() { return recipient; }
    public User getSender() { return sender; }
    public Integer getTransAmount() { return transAmount; }
    public Cat getTransCategory() { return transCategory; }

    public void ChangeBalance(Integer transAmount) {
        if (transCategory == Cat.CR) {
            sender.setBalance(sender.getBalance() + transAmount);
            recipient.setBalance(recipient.getBalance() - transAmount);
        } else if (transCategory == Cat.DT) {
            sender.setBalance(sender.getBalance() - transAmount);
            recipient.setBalance(recipient.getBalance() + transAmount);
        }
    }

    public void TransactionFunc(Integer trAm) {
        if ((trAm > 0) && (sender.getBalance() > trAm) && (getTransCategory() == Cat.CR)) {
            this.transAmount = trAm;
        } else if ((trAm < 0) && (sender.getBalance() > trAm) && (getTransCategory() == Cat.DT)) {
            this.transAmount = trAm;
        } else {
            this.transAmount = 0;
        }
    }

    public String PrintTransaction() {
        return "Transaction: ID: " + getID() + "\n" + "recipient: " + getRecipient() + "\n" + "sender: " + getSender() + "\n" + "transferAmount: " + getTransAmount() + "\n" + "transferCategory: " + getTransCategory();
    }

}
