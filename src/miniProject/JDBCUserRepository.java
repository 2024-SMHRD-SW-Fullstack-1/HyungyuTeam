package miniProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class JDBCUserRepository implements UserRepository {
    private static final String SELECT_USER_BY_ID = "SELECT * FROM QUIZ WHERE NICKNAME = ?";
    private static final String INSERT_USER = "INSERT INTO QUIZ (NICKNAME, SCORE) VALUES (?, ?)";
    private static final String UPDATE_USER_SCORE = "UPDATE QUIZ SET SCORE = ? WHERE NICKNAME = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM QUIZ ORDER BY SCORE DESC";

    private Connection connection;

    public JDBCUserRepository(String url, String username, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "데이터베이스 연결 실패", "오류", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public User getUserByUserId(String userId) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setString(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String id = resultSet.getString("NICKNAME");
                    int score = resultSet.getInt("SCORE");
                    return new User(id, score);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getId());
            statement.setInt(2, user.getScore());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserScore(User user, int score) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SCORE)) {
            statement.setInt(1, score);
            statement.setString(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("NICKNAME");
                int score = resultSet.getInt("SCORE");
                users.add(new User(id, score));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}