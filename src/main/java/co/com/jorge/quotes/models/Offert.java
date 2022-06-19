package co.com.jorge.quotes.models;

import java.io.Serializable;

public class Offert implements Serializable {

    private Long idOffer;

    private Long price;

    private Long idRequest;

    private Long idProvider;

    public Offert() {
    }

    public Offert(Long idOffer, Long price, Long idQuotes, Long idProvider) {
        this.idOffer = idOffer;
        this.price = price;
        this.idRequest = idQuotes;
        this.idProvider = idProvider;
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

    @Override
    public String toString() {
        return "Offert{" +
                "idOffer=" + idOffer +
                ", price=" + price +
                ", idQuotes=" + idRequest +
                ", idProvider=" + idProvider +
                '}';
    }
}
