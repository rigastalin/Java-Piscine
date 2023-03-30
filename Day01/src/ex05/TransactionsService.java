package ex05;

import java.util.UUID;

public class TransactionsService {
    UsersList usersList = new UsersArrayList();
    TransactionsList transactionList = new TransactionsLinkedList();

    public void addUser(User user) {
        this.usersList.addUser(user);
    }

    public int getBalanceUser(User user) {
        int id = user.getID();
        for (int i = 0; i < usersList.getUserCount(); ++i) {
            if (usersList.getUserByIndex(i).getID().equals(id)) return user.getBalance();
        }
        throw new UserNotFoundException("There is no such user");
    }

    public void makeTransaction(int recipientID, int senderId, int sum) {
        User recipient = usersList.getUserById(recipientID);
        User sender = usersList.getUserById(senderId);

        if (sender.getBalance() < sum) throw new IllegalTransactionException("Not enough money...");
        if (sum < 0) throw new IllegalTransactionException("Money too low for that");

        Transaction transactionCR = new Transaction(recipient, sender, sum, Transaction.Cat.CR);
        Transaction transactionDT = new Transaction(recipient, sender, sum, Transaction.Cat.DT);

        sender.getTransactionsList().addTrans(transactionCR);
        sender.setBalance(sender.getBalance() - sum);

        recipient.getTransactionsList().addTrans(transactionDT);
        recipient.setBalance(recipient.getBalance() + sum);
    }

    public Transaction[] getUserTransactions(int id) {
        return usersList.getUserById(id).getTransactionsList().toArray();
    }

    public TransactionsList getAllTransactions() {
        return transactionList;
    }

    public Transaction getTransactionByID(UUID id) {
        for (Transaction trans : transactionList.toArray()) {
            if (trans.getID().equals(id)) return trans;
        }
        return getAllTransactions().toArray()[0];
    }

    public void deleteTransaction(UUID transactionID, int id) {
        usersList.getUserById(id).getTransactionsList().deleteTrans(transactionID);
    }
}
