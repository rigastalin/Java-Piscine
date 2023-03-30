package ex04;

import java.util.UUID;

public class Transaction {
    private UUID identifier_;
    private User recipient_;
    private User sender_;
    private  Integer transAmount_ ;
    private Cat transCategory_;

    enum Cat {
        CR, DT;
    }

    public Transaction(User rec_, User sen_, Integer trAm_, Cat trCat_) {
        this.identifier_ = UUID.randomUUID();
        this.recipient_ = rec_;
        this.sender_ = sen_;
        this.transAmount_ = trAm_;
        this.transCategory_ = trCat_;
    }

    public void setRecipient(User rec_) { this.recipient_ = rec_; }
    public void setSender(User sen_) { this.sender_ = sen_; }
    public void setID(UUID identifier_) { this.identifier_ = identifier_; }

    public UUID getID() { return identifier_; }
    public User getRecipient() { return recipient_; }
    public User getSender() { return sender_; }
    public Integer getTransAmount() { return transAmount_; }
    public Cat getTransCategory() { return transCategory_; }

    public void ChangeBalance(Integer transAmount_) {
        if (transCategory_ == Cat.CR) {
            sender_.setBalance(sender_.getBalance() + transAmount_);
            recipient_.setBalance(recipient_.getBalance() - transAmount_);
        } else if (transCategory_ == Cat.DT) {
            sender_.setBalance(sender_.getBalance() - transAmount_);
            recipient_.setBalance(recipient_.getBalance() + transAmount_);
        }
    }

    public void Transaction(Integer trAm_) {
        if ((trAm_ > 0) && (sender_.getBalance() > trAm_) && (getTransCategory() == Cat.CR)) {
            this.transAmount_ = trAm_;
        } else if ((trAm_ < 0) && (sender_.getBalance() > trAm_) && (getTransCategory() == Cat.DT)) {
            this.transAmount_ = trAm_;
        } else {
            this.transAmount_ = 0;
        }
    }

    public String PrintTransaction() {
        return "Transaction: ID: " + getID() + "\n" + "recipient: " + getRecipient() + "\n" + "sender: " + getSender() + "\n" + "transferAmount: " + getTransAmount() + "\n" + "transferCategory: " + getTransCategory();
    }

}
