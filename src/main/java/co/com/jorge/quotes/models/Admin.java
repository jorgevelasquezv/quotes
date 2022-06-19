package co.com.jorge.quotes.models;

import java.io.Serializable;

public class Admin implements Serializable {

    private Long idAdmin;

    private String username;

    private String password;

    public Admin() {
    }

    public Admin(Long idAdmins, String username, String password) {
        this.idAdmin = idAdmins;
        this.username = username;
        this.password = password;
    }

    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admins{" +
                "idAdmins=" + idAdmin +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
