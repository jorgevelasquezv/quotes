package co.com.jorge.quotes.models;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@SessionScoped
@Named
public class Offer implements Serializable {

    private Long idOffer;

    private Long price;

    private Long idRequest;

    private Long idProvider;

    private String state;

    private String provider;

    private String product;

    private String category;

    private Integer quantity;

    public Offer() {
    }

    public Offer(Long idOffer, Long price, Long idRequest, Long idProvider, String provider, String product, String category, Integer quantity, String state) {
        this.idOffer = idOffer;
        this.price = price;
        this.idRequest = idRequest;
        this.idProvider = idProvider;
        this.provider = provider;
        this.product = product;
        this.category = category;
        this.quantity = quantity;
    }

    public Long getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(Long idOffer) {
        this.idOffer = idOffer;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Long idRequest) {
        this.idRequest = idRequest;
    }

    public Long getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Long idProvider) {
        this.idProvider = idProvider;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "idOffer=" + idOffer +
                ", price=" + price +
                ", idRequest=" + idRequest +
                ", idProvider=" + idProvider +
                ", state='" + state + '\'' +
                ", provider='" + provider + '\'' +
                ", product='" + product + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
