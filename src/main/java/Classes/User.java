package Classes;

public class User {
    private String name;
    private String password;
    private int userID;
    public User(String name, String password, int userID) {
        this.name = name;
        this.password = password;
        this.userID = userID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void login();

    public void logout();
}
