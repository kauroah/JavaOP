package repositories.impl;
import models.User;
import repositories.UserRepository;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final static String SQL_SELECT_ALL =
            "select * from accounts";


    private final static String SQL_INSERT =
            "insert into accounts(first_name, last_name, age) values (?, ?, ?)";


    private static final String SQL_SELECT_BY_AGE = "SELECT * FROM accounts WHERE age = ?";

    private final static String SQL_UPDATE = "UPDATE accounts SET first_name=?, last_name=?, age=? WHERE ID=?";

    private final static String SQL_DELETE = "DELETE FROM accounts WHERE ID=?";

    private final DataSource dataSource;

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User model) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, model.getFirstName());
                statement.setString(2, model.getLastName());
                statement.setInt(3, model.getAge());

                int affectedRows = statement.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Cannot insert user");
                }

                try (ResultSet generatedIds = statement.getGeneratedKeys()){
                    if (generatedIds.next()) {
                        model.setId(generatedIds.getInt("id"));
                    } else {
                        throw new SQLException("Cannot retrieve id");
                    }
                }

            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users= new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
                while (resultSet.next()) {
                    User user = User.builder()
                            .id(resultSet.getInt("id"))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .age(resultSet.getInt("age"))
                            .build();

                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }

    public List<User> findAllByAge(int age) {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_AGE)) {

            statement.setInt(1, age);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = User.builder()
                            .id(resultSet.getInt("id"))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .age(resultSet.getInt("age"))
                            .build();

                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }


    @Override
    public void update(int ModelID, String first_name, String last_name, String age) {
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            try(Connection connection = dataSource.getConnection()){
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
                statement.setString(1, first_name);
                statement.setString(2, last_name);
                statement.setInt(3, Integer.parseInt(age));
                statement.setInt(4, ModelID);

                statement.executeUpdate();
            }
        }catch (IOException e) {
            throw new IllegalStateException(e);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(int ID) {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
                statement.setInt(1, ID);
                statement.executeUpdate();

            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
