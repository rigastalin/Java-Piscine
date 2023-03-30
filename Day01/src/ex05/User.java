package ex05;

public class User {
    private Integer identifier_;
    private String name_;
    private Integer balance_;
    private TransactionsLinkedList transactionsList;

    public User() {
        this.identifier_ = UserIdsGenerator.getInstance().generatorID();
        this.balance_ = 0;
        this.transactionsList = new TransactionsLinkedList();
    }

    public User(Integer bal_, String n) {
        this.identifier_ = UserIdsGenerator.getInstance().generatorID();
        this.name_ = n;
        if (bal_ > 0) {
            this.balance_ = bal_;
        } else {
            this.balance_ = 0;
        }
        this.transactionsList = new TransactionsLinkedList();
    }

    public User(Integer id_, Integer bal_, String n) {
        this.identifier_ = id_;
        this.name_ = n;
        if (bal_ > 0) {
            this.balance_ = bal_;
        } else {
            this.balance_ = 0;
        }
    }



    public void setID(Integer id_) {
        this.identifier_ = id_;
    }

    public void setBalance(Integer bal_) {
        if (bal_ > 0) {
            this.balance_ = bal_;
        } else {
            this.balance_ = 0;
        }
    }

    public void setName(String n_) {
        this.name_ = n_;
    }

    public Integer getID() { return identifier_; }
    public String getName() { return name_; }
    public Integer getBalance() { return balance_; }
    public TransactionsLinkedList getTransactionsList() { return transactionsList; }

    public String PrintUser() {
        return "ID: " + identifier_ + "\n" + "Name: "  + name_ + "\n" + "Balance: " + balance_;
    }

}
