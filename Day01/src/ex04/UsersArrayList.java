package ex04;

public class UsersArrayList implements UsersList {
    private int defaultVal  = 10;
    private User[] users = new User[defaultVal];
    private int counter = 0;

    @Override
    public void addUser(User newUser) {
        if (this.counter >= defaultVal) {
            User[] doubleUsers = new User[defaultVal * 2];
            for (int i = 0; i < this.defaultVal; ++i) {
                doubleUsers[i] = this.users[i];
            }
            this.defaultVal *= 2;
            this.users = doubleUsers;
        }
        this.users[counter++] = newUser;
    }

    @Override
    public User getUserById(int id) {
        for (int i = 0; i < this.counter; ++i) {
            if (users[i].getID() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public User getUserByIndex(int index) {
        return users[index];
    }

    @Override
    public int getUserCount() {
        return this.counter;
    }

    public void PrintList() {
        for (int i = 0; i < this.counter; ++i) {
            System.out.println(i + ". Name: " + users[i].getName() + "\n" + "Balance: " + users[i].getBalance() + "\n" + "ID: " + users[i].getID() + "\n");
        }
    }
}
