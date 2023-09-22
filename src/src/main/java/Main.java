import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import models.User;
import repositories.UserRepository;
import repositories.impl.UserRepositoryJdbcImpl;


public class Main {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("asuspro15");
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);

        User student = User.builder()
                .firstName("John")
                .lastName("Wick")
                .age(31)
                .build();

        System.out.println(student);
        userRepository.save(student);
        System.out.println(student);
        userRepository.update(3, "Raed", "John", "30");
        userRepository.delete(4);
        System.out.println(userRepository.findAll());
        System.out.println(userRepository.findAllByAge(31));


    }
}

