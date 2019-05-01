package model;

public class User {
    public String login;
    public String password;
    public String roleId;

    public User(String login, String password, String roleId) {
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
