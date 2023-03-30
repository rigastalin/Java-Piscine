package edu.school21.app;

import edu.school21.models.Car;
import edu.school21.models.User;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.SQLException;

public class Program {
    private EmbeddedDatabase dataSource;
    private OrmManager manager;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Program program = new Program();
        program.dataSource = new EmbeddedDatabaseBuilder().build();
        program.manager = new OrmManager("edu.school21.models.", program.dataSource);
        program.manager.init();

        System.out.println();
        System.out.println("Saving");
        User user1 = new User(1L, "Parina", "Petuhina", 29);
        User user2 = new User(2L, "Pedrina", "Pedruhina", 29);
        program.manager.save(user1);
        program.manager.save(user2);

        Car car1 = new Car(1L, "Zapor", 100000.12, 90, true);
        Car car2 = new Car(2L, "Zapor AMG", 30000.00, 240, false);
        program.manager.save(car1);
        program.manager.save(car2);

        System.out.println();
        System.out.println("Finding stuff");
        User user;
        if ((user = program.manager.findById(2L, User.class)) != null) {
            System.out.println(user);
        }
        Car car;
        if ((car = program.manager.findById(1L, Car.class)) != null) {
            System.out.println(car);
        }

        System.out.println();
        System.out.println("Updating stuff");
        User user3 = new User();
        System.out.println("BEFORE ===> " + user3);
        user3 = new User(3L, "PEDRINKA", "PEDRICHINKA", 88);
        program.manager.update(user3);
        System.out.println("AFTER ===> " + user3);

        System.out.println();
        Car car3 = new Car();
        System.out.println("BEFORE ===> " + car3);
        car3 = new Car(3L, "ZAPOR 3XLLLL", 3333.33, 1200, true);
        program.manager.update(car3);
        System.out.println("AFTER ===> " + car3);

        program.dataSource.shutdown();
    }


}
