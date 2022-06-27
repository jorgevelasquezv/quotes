package co.com.jorge.quotes.models;

import java.util.Date;

public class RequestProduct{

    private Long idRequest;

    private boolean state;

    private int quantity;

    private Product product;

    private Category category;

    private Date initialDate;

    private Date finalDate;

    public RequestProduct() {
    }

    public RequestProduct(Long idRequest, boolean state, int quantity, Product product, Category category, Date initialDate, Date finalDate) {
        this.idRequest = idRequest;
        this.state = state;
        this.quantity = quantity;
        this.product = product;
        this.category = category;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    @Override
    public String toString() {
        return "RequestProduct{" +
                "idRequest=" + idRequest +
                ", state=" + state +
                ", quantity=" + quantity +
                ", product=" + product.getName() +
                ", category=" + category.getName() +
                ", initialDate=" + initialDate +
                ", finalDate=" + finalDate +
                '}';
    }
}
