package entity;

public class User {
    private Integer id;
    private String login;
    private String password;
    private String role;

    public User(Integer id, String login, String password, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getLogin() {

        return login;
    }

    public String setLogin() {

        this.login = login;
        return null;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String levelUSer) {

        this.role = levelUSer;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", levelUSer='" + role + '\'' +
                '}';
    }
}
