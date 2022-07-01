package co.com.jorge.quotes.services;

import co.com.jorge.quotes.config.ConnectionMySQL;
import co.com.jorge.quotes.models.Offer;
import co.com.jorge.quotes.repositories.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OfferServiceImpl implements OfferService{

    @Inject
    private Repository<Offer> offerRepository;

    @Override
    public Offer findById(Long id) {
        Offer offer = new Offer();
        offer.setIdOffer(id);

        try {
            offer = offerRepository.findById(offer);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return offer;
    }

    @Override
    public List<Offer> find() {
        List<Offer> offerList = new ArrayList<>();

        try {
            offerList = offerRepository.listAll();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return offerList;
    }

    @Override
    public void save(Offer offer) {
        try {
            offerRepository.save(offer);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void delete(Offer offer) {
        try {
            offerRepository.delete(offer);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
