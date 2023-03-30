package ex00;

public class Program {
    public static void main(String[] args) {
        User person1 = new User(1, 500, "Mother");
        User person2 = new User(2, 1200, "Fucker");

        System.out.println("USER 1");
        System.out.println(person1.PrintUser());

        System.out.println();
        System.out.println("USER 2");
        System.out.println(person2.PrintUser());

        System.out.println();

        Transaction trans1 = new Transaction(person1, person2, 250, Transaction.Cat.DT);
        Transaction trans2 = new Transaction(person1, person2, -500, Transaction.Cat.CR);

        System.out.println(trans1.PrintTransaction());
        System.out.println();
        System.out.println(trans2.PrintTransaction());
    }
}
