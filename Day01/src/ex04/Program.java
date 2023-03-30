package ex04;

public class Program {
    public static void main(String[] args) {
        TransactionsService tranServ = new TransactionsService();
        User person1 = new User(4000, "Parina");
        User person2 = new User(3000, "Petuhina");

        System.out.println("Make 2 users");
        System.out.println(person1.PrintUser());
        System.out.println(person2.PrintUser());

        tranServ.addUser(person1);
        tranServ.addUser(person2);

        System.out.println();
        System.out.println("Get balance of users");
        System.out.println(person1.getName() + " : " + tranServ.getBalanceUser(person1));
        System.out.println(person2.getName() + " : " +tranServ.getBalanceUser(person2));

        System.out.println();
        System.out.println("Make transactions and get balance again");
        tranServ.makeTransaction(person1.getID(), person2.getID(), 500);
        tranServ.makeTransaction(person1.getID(), person2.getID(), 200);
        tranServ.makeTransaction(person1.getID(), person2.getID(), 100);
        tranServ.makeTransaction(person1.getID(), person2.getID(), 1500);
        System.out.println(person1.getName() + " : " + tranServ.getBalanceUser(person1));
        System.out.println(person2.getName() + " : " +tranServ.getBalanceUser(person2));

        System.out.println();
        System.out.println("Trying to catch an exception");
        try {
            tranServ.makeTransaction(person1.getID(), person2.getID(), 150000);
        } catch (IllegalTransactionException err) {
            System.out.println(err);
        }
    }
}
