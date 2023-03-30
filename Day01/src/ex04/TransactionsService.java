package ex04;

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

    public Transaction[] checkValidTransactions() {
        TransactionsLinkedList transactionsLinkedList = getAllTransactions();

        TransactionsLinkedList transactionsLinkedListResult = new TransactionsLinkedList();
        Transaction[] arrTransactions = transactionsLinkedList.toArray();
        if (arrTransactions != null) {
            for (Transaction arrTransaction : arrTransactions) {
                int counter = 0;
                for (Transaction transaction : arrTransactions) {
                    if (arrTransaction.getID().equals(transaction.getID())) {
                        counter++;
                    }
                }
                if (counter != 2) {
                    transactionsLinkedListResult.addTrans(arrTransaction);
                }
            }
        }
        return transactionsLinkedListResult.toArray();
    }

    public Transaction[] getUserTransactions(int id) {
        return usersList.getUserById(id).getTransactionsList().toArray();
    }

    public void deleteTransaction(UUID transactionID, int id) {
        usersList.getUserById(id).getTransactionsList().deleteTrans(transactionID);
    }

    private TransactionsLinkedList getAllTransactions() {
        TransactionsLinkedList transactionsLinkedList = new TransactionsLinkedList();

        for (int i = 0; i < usersList.getUserCount(); i++) {
            User user = usersList.getUserByIndex(i);
            if (user != null) {
                int size = user.getTransactionsList().getSize();
                for (int j = 0; j < size; j++) {
                    transactionsLinkedList.addTrans(user.getTransactionsList().toArray()[j]);
                }
            }
        }
        return transactionsLinkedList;
    }
}
