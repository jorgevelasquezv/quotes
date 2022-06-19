package co.com.jorge.quotes.models;

import java.io.Serializable;

public class Provider implements Serializable {

    private Long idProvider;

    private String username;

    private String password;

    private String name;

    private String address;

    private String phone;

    private int nit;

    public Provider() {
    }

    public Provider(Long idProvider, String username, String password, String name, String address, String phone, int nit) {
        this.idProvider = idProvider;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.nit = nit;
    }

    public Long getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Long idProvider) {
        this.idProvider = idProvider;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "idProvider=" + idProvider +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", nit=" + nit +
                '}';
    }
}
