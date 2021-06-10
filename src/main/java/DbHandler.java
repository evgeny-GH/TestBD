import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class DbHandler {
    
    private static final String CON_STR = "jdbc:sqlite:D:/myfin.db";
    
    private static DbHandler instance = null;
    
    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }
    
    private Connection connection;
    
    private DbHandler() throws SQLException {
        
        DriverManager.registerDriver(new JDBC());
        
        this.connection = DriverManager.getConnection(CON_STR);
    }
    
    public List<User> getAllUsers() {
        
        
        try (Statement statement = this.connection.createStatement()) {
            
            List<User> users = new ArrayList<User>();
            
            ResultSet resultSet = statement.executeQuery("SELECT id, user, pass FROM users");
            
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),
                        resultSet.getString("user"),
                        resultSet.getDouble("pass")));
            }
            
            return users;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return Collections.emptyList();
        }
    }
    
    
    public void addUser(User user) {
        
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO users(`user`,`pass`) VALUES(?, ?)")) {
            statement.setObject(1, user.user);
            statement.setObject(2, user.password);
            
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void deleteUser(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM users WHERE id = ?")) {
            statement.setObject(1, id);
            
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
