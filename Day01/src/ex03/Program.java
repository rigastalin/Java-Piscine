package ex03;

public class Program {
    public static void main(String[] args) {
            User person1 = new User(500, "Petuhina");
            System.out.println(person1.PrintUser() + "\n");

            User person2 = new User(500, "Valera");
            System.out.println(person2.PrintUser() + "\n");

            User person3 = new User(3, 118, "Igor");
            System.out.println(person3.PrintUser());

            System.out.println();
            System.out.println();

            TransactionsLinkedList listTransaction = new TransactionsLinkedList();
            Transaction transaction1 = new Transaction(person1, person2, -120, Transaction.Cat.CR);
            Transaction transaction2 = new Transaction(person1, person2, 333, Transaction.Cat.DT);

            listTransaction.addTrans(transaction1);
            listTransaction.addTrans(transaction2);
            listTransaction.deleteTrans(transaction1.getID());
            Transaction[] trArr = listTransaction.toArray();


            TransactionsLinkedList listTransaction1 = new TransactionsLinkedList();
            Transaction transaction3 = new Transaction(person2, person1, -42, Transaction.Cat.CR);
            Transaction transaction4 = new Transaction(person2, person1, 87, Transaction.Cat.DT);
            listTransaction1.addTrans(transaction1);
            listTransaction1.addTrans(transaction2);
            listTransaction1.addTrans(transaction3);
            listTransaction1.addTrans(transaction4);

            System.out.println("List of transactions: ");
            Transaction[] toArr1 = listTransaction1.toArray();

            for (Transaction transaction : toArr1)
                    System.out.println(transaction.getID());
            System.out.println();

            try {
                    listTransaction1.deleteTrans(transaction2.getID());
                    System.out.println("Transaction deleted successfully");
            } catch (TransactionNotFoundException e) {
                    System.out.println("There is no such transaction");
            }

            System.out.println();
            System.out.println("List of transactions after delete: ");
            Transaction[] toArr2 = listTransaction1.toArray();
            for (Transaction transaction : toArr2)
                    System.out.println(transaction.getID());
    }
}
