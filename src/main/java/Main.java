import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            
            DbHandler dbHandler = DbHandler.getInstance();
            
            List<User> users = dbHandler.getAllUsers();
            for (User user : users) {
                System.out.println(user.toString());
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

