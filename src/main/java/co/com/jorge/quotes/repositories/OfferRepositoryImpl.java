package co.com.jorge.quotes.repositories;

import co.com.jorge.quotes.models.Offer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OfferRepositoryImpl implements Repository<Offer> {

    @Inject
    @Named("conn")
    private Connection conn;

    public OfferRepositoryImpl() {
    }

    @Override
    public void setConnection(Connection conn) {
      this.conn = conn;
    }

    @Override
    public List<Offer> listAll( ) throws SQLException {
        List<Offer> list = new ArrayList<>();

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT o.id_offers, o.price, o.state, p.name as " +
                     "provider, p.id_providers, r.quantity, o.id_request, " +
                     "products.name as product, categories.name as category FROM offers o INNER JOIN providers p " +
                     "ON o.id_providers = p.id_providers INNER JOIN request r ON o.id_request = r.id_request " +
                     "INNER JOIN products ON products.id_products = r.id_products INNER JOIN categories ON " +
                     "products.id_categories = categories.id_category;");){
            while (resultSet.next()){
                list.add(createOfferView(resultSet));
            }
        }
        return list;
    }

    @Override
    public Offer findById(Offer offer) throws SQLException {
        Long id = offer.getIdOffer();
        Offer foundOffer = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM offers as o WHERE o.id_offers=?")){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    foundOffer= createOffer(resultSet);
                }
            }
        }
        return foundOffer;
    }

    @Override
    public Offer findByName(Offer offer) throws SQLException {
        return null;
    }

    @Override
    public Offer save(Offer offer) throws SQLException {
        String sql = null;
        if (offer.getIdOffer() != null && offer.getIdOffer() > 0){
            sql = "UPDATE offers SET price=?, id_providers=?, id_request=?, state=? WHERE id_offers=? ";
        }else {
            sql = "INSERT INTO offers(price, id_providers, id_request, state) VALUES(?, ?, ?, ?)";
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setLong(1, offer.getPrice());
            preparedStatement.setLong(2, offer.getIdProvider());
            preparedStatement.setLong(3, offer.getIdRequest());
            preparedStatement.setString(4, offer.getState());
            if (offer.getIdOffer() != null && offer.getIdOffer() > 0){
                preparedStatement.setLong(5, offer.getIdOffer());
            }
            preparedStatement.executeUpdate();
            if (offer.getIdOffer() == null){
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if (resultSet.next()){
                        offer.setIdOffer(resultSet.getLong(1));
                    }
                }
            }
        }
        return offer;
    }

    @Override
    public void delete(Offer offer) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM offers WHERE id_offers=?")){
            preparedStatement.setLong(1, offer.getIdOffer());
            preparedStatement.executeUpdate();
        }
    }

    private Offer createOffer(ResultSet resultSet) throws SQLException {
        Offer offer = new Offer();
        offer.setState("slope");
        offer.setIdOffer(resultSet.getLong("id_offers"));
        offer.setPrice(resultSet.getLong("price"));
        offer.setIdProvider(resultSet.getLong("id_providers"));
        offer.setIdRequest(resultSet.getLong("id_request"));
        return offer;
    }

    private Offer createOfferView(ResultSet resultSet) throws SQLException {
        Offer offer = new Offer();
        offer.setIdOffer(resultSet.getLong("id_offers"));
        offer.setPrice(resultSet.getLong("price"));
        offer.setState(resultSet.getString("state"));
        offer.setProvider(resultSet.getString("provider"));
        offer.setIdProvider(resultSet.getLong("id_providers"));
        offer.setIdRequest(resultSet.getLong("id_request"));
        offer.setProduct(resultSet.getString("product"));
        offer.setCategory(resultSet.getString("category"));
        offer.setQuantity(resultSet.getInt("quantity"));
        return offer;
    }

}
