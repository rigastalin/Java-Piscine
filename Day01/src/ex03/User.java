package ex03;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;
    private TransactionsLinkedList transactionsList;

    public User() {
        this.identifier = UserIdsGenerator.getInstance().generatorID();
        this.balance = 0;
        this.transactionsList = new TransactionsLinkedList();
    }

    public User(Integer balance, String name) {
        this.identifier = UserIdsGenerator.getInstance().generatorID();
        this.name = name;
        if (balance > 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }
        this.transactionsList = new TransactionsLinkedList();
    }

    public User(Integer id, Integer balance, String name) {
        this.identifier = id;
        this.name = name;
        if (balance > 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }
    }



    public void setID(Integer id) {
        this.identifier = id;
    }

    public void setBalance(Integer balance) {
        if (balance > 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getID() { return identifier; }
    public String getName() { return name; }
    public Integer getBalance() { return balance; }
    public TransactionsLinkedList getTransactionsList() { return transactionsList; }

    public String PrintUser() {
        return "ID: " + getID() + "\n" + "Name: "  + getName() + "\n" + "Balance: " + getBalance();
    }

}
