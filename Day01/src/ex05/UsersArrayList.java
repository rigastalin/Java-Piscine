package ex05;

public class UsersArrayList implements UsersList {
    private int default_  = 10;
    private User[] users = new User[default_];
    private int counter = 0;

    @Override
    public void addUser(User newUser_) {
        if (this.counter >= default_) {
            User[] doubleUsers_ = new User[default_ * 2];
            for (int i = 0; i < this.default_; ++i) {
                doubleUsers_[i] = this.users[i];
            }
            this.default_ *= 2;
            this.users = doubleUsers_;
            this.users[counter++] = newUser_;
        } else {
            this.users[counter++] = newUser_;
        }
    }

    @Override
    public User getUserById(int id_) {
        for (int i = 0; i < this.counter; ++i) {
            if (users[i].getID() == id_) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public User getUserByIndex(int index_) {
        return users[index_];
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
