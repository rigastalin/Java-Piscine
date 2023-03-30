package ex05;

public class Menu {
    public Menu(boolean dev) {
        if (dev == true) {
            printDev();
        } else {
            printUser();
        }
    }

    private static void printDev() {
        System.out.println("""
           1. Add a user
           2. View user balances
           3. Perform a transfer
           4. View all transactions for a specific user
           5. DEV - remove a transfer by ID
           6. DEV - check transfer validity
           7. Finish execution
        """);
    }

    private static void printUser() {
        System.out.println("""
           1. Add a user
           2. View user balances
           3. Perform a transfer
           4. View all transactions for a specific user
           5. Finish execution
        """);
    }
}
