public abstract class User implements UserActions {
    private String username;
    private String password;
    private String role;  // Kullanıcı türünü tanımlayan rol

    // Constructor
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract String getRole();


}
