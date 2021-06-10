public class User {
    
    public int id;
    
    public String user;
    
    public double password;
    
    
   
    public User(int id, String user, double pass) {
        this.id = id;
        this.user = user;
        this.password = pass;
        
    }
    
    
    @Override
    public String toString() {
        return String.format("ID: %s | Пользователь: %s | Пароль: %s ",
                this.id, this.user, this.password);
    }
}
