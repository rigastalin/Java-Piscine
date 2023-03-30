package ex01;

public class Program {
    public static void main(String[] args) {
        User person1 = new User();
        System.out.println(person1.PrintUser() + "\n");

        User person2 = new User(18, "Valera");
        System.out.println(person2.PrintUser() + "\n");

        User person3 = new User(3, 118, "Igor");
        System.out.println(person3.PrintUser());
    }
}
