package co.com.jorge.quotes.models;

import java.io.Serializable;
import java.util.Date;

public class Request implements Serializable {

    private Long idRequest;

    private boolean state;

    private int quantity;

    private Long idProduct;

    private Date registryDate;

    public Request() {
    }

    public Request(Long idRequest, boolean state, int quantity, Long idProduct, Date registryDate) {
        this.idRequest = idRequest;
        this.state = state;
        this.quantity = quantity;
        this.idProduct = idProduct;
        this.registryDate = registryDate;
    }

    public Long getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Long idRequest) {
        this.idRequest = idRequest;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Date getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(Date registryDate) {
        this.registryDate = registryDate;
    }

    @Override
    public String toString() {
        return "Request{" +
                "idRequest=" + idRequest +
                ", state=" + state +
                ", quantity=" + quantity +
                ", idProduct=" + idProduct +
                '}';
    }
}
