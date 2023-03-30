package ex05;

import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        boolean dev = false;
        boolean user = false;
        if (args.length == 0) {
            dev = false;
        } else if (args[0].equals("--profile=dev") && (args.length > 0)) {
            dev = true;
        }

        Menu menu = new Menu(dev);
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int count = 0;
        TransactionsService transServ = new TransactionsService();
        while ((!str.contains("7") && dev == true) || (!str.contains("5"))) {
            if (str.equals("1")) {
                System.out.println("Enter a user name and a balance");
                str = scanner.nextLine();
                String[] arr = str.split(" ");
                int balance = Integer.parseInt(arr[1]);
                String name = arr[0];
                User user1 = new User(balance, name);
                transServ.addUser(user1);
                System.out.println("User with id = " + transServ.usersList.getUserByIndex(count).getID() + " is added");
                count += 1;
                System.out.println();
            } else if (str.equals("2")) {
                System.out.println("Enter a user ID");
                str = scanner.nextLine();
                System.out.println(transServ.usersList.getUserById(Integer.parseInt(str)).getName() + " - " +
                        transServ.usersList.getUserById(Integer.parseInt(str)).getBalance());
                System.out.println();
            } else if (str.equals("3")) {
                System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
                str = scanner.nextLine();
                String[] arr = str.split(" ");
                int sender = Integer.parseInt(arr[0]);
                int recipient = Integer.parseInt(arr[1]);
                int sum = Integer.parseInt(arr[2]);
                try {
                    transServ.makeTransaction(sender, recipient, sum);
                } catch (IllegalTransactionException e) {
                    System.out.println(e);
                }
                System.out.println("Transfer is completed");
                System.out.println();
            } else if (str.equals("4")) {
                System.out.println("Enter a user ID");
                str = scanner.nextLine();
                int userID = Integer.parseInt(str);
                var auto = transServ.getUserTransactions(userID);
                for (var i : auto) {
                    System.out.println("To " + i.getRecipient().getName() + "(id = " + i.getRecipient().getID() + ") -" + i.getTransAmount() + " with id = " + i.getID());
                }
                System.out.println();
            } else if (str.equals("5") && dev) {
                System.out.println("Enter a user ID and a transfer ID");
                str = scanner.nextLine();
                String[] arr = str.split(" ");
                int userID = Integer.parseInt(arr[0]);
                UUID transID = UUID.fromString(arr[1]);
                try {
                    transServ.deleteTransaction(transID, userID);
                } catch (TransactionNotFoundException e) {
                    System.out.println(e);
                }
                var auto = transServ.getUserTransactions(userID);
                System.out.println("Operation is completed");
                System.out.println();
            } else if (str.equals("6") && dev) {
                System.out.println("Check results:");
                var auto = transServ.getAllTransactions();
                for (var i : auto.toArray()) {
                    System.out.println(i.getRecipient().getName() + "(id = " + i.getRecipient().getID() + ") has an unacknowledged transfer id = " +
                            i.getID() + " from " + i.getSender() + i.getSender().getName() + "(id = " + i.getSender().getID() + " for " + i.getTransAmount());
                }
                System.out.println();
            } else if (str.equals("7") && dev) {
                System.exit(0);
            }
            str = scanner.nextLine();
        }
        scanner.close();
        System.exit(0);
    }
}
















