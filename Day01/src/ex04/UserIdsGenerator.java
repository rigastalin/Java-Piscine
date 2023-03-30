package ex04;

public class UserIdsGenerator {
    private static UserIdsGenerator instance;
    private static Integer id_ = 0;

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public Integer generatorID() {
        return ++id_;
    }

    public static Integer getID() {
        return id_;
    }
}
