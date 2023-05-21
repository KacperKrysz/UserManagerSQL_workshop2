package pl.coderslab.entity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

public class UserDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String EDIT_USER_QUERY = "update users set email = ?, username = ?, password = ? where id = ?";

    private static final String PRINT_USER = "select * from users where id = ?";
    private static final String DELETE_USER = "delete from users where id = ?";
    private static final String PRINT_ALL_USERS = "select * from users";



    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public User createUser(User user) {
        try (Connection conn = DbUtil.connectWorkshop2()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User readUser(int userId) {

        try (Connection conn = DbUtil.connectWorkshop2()) {
            PreparedStatement statement = conn.prepareStatement(PRINT_USER);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateUser(User user) {

        try (Connection conn = DbUtil.connectWorkshop2()) {
            PreparedStatement statement = conn.prepareStatement(EDIT_USER_QUERY,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4,user.getId());

            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void deleteUser(int userId) {

        try (Connection conn = DbUtil.connectWorkshop2()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER);
            statement.setInt(1, userId);

            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<User> findAll() {

        try (Connection conn = DbUtil.connectWorkshop2()) {
            ArrayList<User> arrayList = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(PRINT_ALL_USERS);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                arrayList.add(user);
            }
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }


}


