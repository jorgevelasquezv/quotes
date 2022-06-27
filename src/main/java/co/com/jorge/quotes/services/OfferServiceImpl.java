package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Offer;
import co.com.jorge.quotes.repositories.OfferRepositoryImpl;
import co.com.jorge.quotes.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferServiceImpl implements OfferService{

    private Connection conn;

    private Repository<Offer> offerRepository;

    public OfferServiceImpl(Connection conn) {
        this.conn = conn;
        this.offerRepository = new OfferRepositoryImpl();
    }

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
