package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Car1", 1234)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Car2", 2234)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Car3", 3543)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Car4", 4235)));

        List<User> users = userService.listUsers();
        users.forEach(System.out::println);

        try {
            System.out.println(userService.getUserByCar("Car2", 2234));
        } catch (NoResultException e) {
            System.out.println("Пользователь не найден");
        }
        context.close();
    }
}
