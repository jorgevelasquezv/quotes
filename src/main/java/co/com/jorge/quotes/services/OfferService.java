package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Offer;

import java.util.List;

public interface OfferService {

    Offer findById(Long id);

    List<Offer> find();

    void save(Offer offer);

    void delete(Offer offer);
}
