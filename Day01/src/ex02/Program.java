package ex02;

public class Program {
    public static void main(String[] args) {
        User person1 = new User();
        System.out.println(person1.PrintUser() + "\n");

        User person2 = new User(-500, "Valera");
        System.out.println(person2.PrintUser() + "\n");

        User person3 = new User(3, 118, "Igor");
        System.out.println(person3.PrintUser());

        System.out.println();
        System.out.println();
        UsersArrayList userList_ = new UsersArrayList();
        userList_.addUser(person1);
        userList_.addUser(person2);
        userList_.addUser(new User(33, -1000, "Parina"));
        userList_.addUser(new User(-30, "Petuha"));
        userList_.addUser(new User());

        userList_.PrintList();

        System.out.println("Looking for unreal user");
        try {
            userList_.getUserById(3333);
        } catch (UserNotFoundException e) {
            System.out.println("User is not found");
        }
    }
}
