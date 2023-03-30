package ex02;

import ex01.UserIdsGenerator;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    public User() {
        this.identifier = UserIdsGenerator.getInstance().generatorID();
        this.balance = 0;
    }

    public User(Integer balance, String n) {
        this.identifier = UserIdsGenerator.getInstance().generatorID();
        this.name = n;
        if (balance > 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }
    }

    public User(Integer identifier, Integer balance, String name) {
        this.identifier = identifier;
        this.name = name;
        if (balance > 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }
    }



    public void setID(Integer identifier) {
        this.identifier = identifier;
    }

    public void setBalance(Integer balance) {
        if (balance > 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }
    }

    public void setName(String n_) {
        this.name = name;
    }

    public Integer getID() {
        return identifier;
    }
    public String getName() {
        return name;
    }
    public Integer getBalance() {
        return balance;
    }

    public String PrintUser() {
        return "ID: " + getID() + "\n" + "Name: "  + getName() + "\n" + "Balance: " + getBalance();
    }

}
