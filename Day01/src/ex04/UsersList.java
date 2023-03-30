package ex04;

public interface UsersList {
    void addUser(User newUser_);
    User getUserById(int id_);
    User getUserByIndex(int index_);
    int getUserCount();
}
