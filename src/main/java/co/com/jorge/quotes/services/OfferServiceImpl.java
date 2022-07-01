package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Offer;
import co.com.jorge.quotes.repositories.OfferRepositoryImpl;
import co.com.jorge.quotes.repositories.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OfferServiceImpl implements OfferService{

    @Inject
    @Named("conn")
    private Connection conn;

    @Inject
    private Repository<Offer> offerRepository;

    @Override
    public Offer findById(Long id) {
        Offer offer = new Offer();
        offer.setIdOffer(id);

        offerRepository.setConnection(conn);
        try {
            offer = offerRepository.findById(offer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return offer;
    }

    @Override
    public List<Offer> find() {
        List<Offer> offerList = new ArrayList<>();

        offerRepository.setConnection(conn);
        try {
            offerList = offerRepository.listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offerList;
    }

    @Override
    public void save(Offer offer) {
        offerRepository.setConnection(conn);
        try {
            offerRepository.save(offer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Offer offer) {
        offerRepository.setConnection(conn);
        try {
            offerRepository.delete(offer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
