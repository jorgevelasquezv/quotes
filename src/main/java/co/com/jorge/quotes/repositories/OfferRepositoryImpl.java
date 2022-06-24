package co.com.jorge.quotes.repositories;

import co.com.jorge.quotes.models.Offer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferRepositoryImpl implements Repository<Offer> {

    private Connection conn;

    public OfferRepositoryImpl() {
    }

    public OfferRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void setConnection(Connection conn) {
      this.conn = conn;
    }

    @Override
    public List<Offer> listAll() throws SQLException {
        List<Offer> list = new ArrayList<>();

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT o.price, p.name as provider, r.quantity, " +
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
    public Offer find(Offer offer) throws SQLException {
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
            sql = "UPDATE offers SET price=?, id_providers=?, id_request=? WHERE id_offers=? ";
        }else {
            sql = "INSERT INTO offers(price, id_providers, id_request) VALUES(?, ?, ?)";
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setLong(1, offer.getPrice());
            preparedStatement.setLong(2, offer.getIdProvider());
            preparedStatement.setLong(3, offer.getIdRequest());
            if (offer.getIdOffer() != null && offer.getIdOffer() > 0){
                preparedStatement.setLong(4, offer.getIdOffer());
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
    public void deleted(Offer offer) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM offers WHERE id_offers=?")){
            preparedStatement.setLong(1, offer.getIdOffer());
            preparedStatement.executeUpdate();
        }
    }

    private Offer createOffer(ResultSet resultSet) throws SQLException {
        Offer offer = new Offer();
        offer.setIdOffer(resultSet.getLong("id_offers"));
        offer.setPrice(resultSet.getLong("price"));
        offer.setIdProvider(resultSet.getLong("id_providers"));
        offer.setIdRequest(resultSet.getLong("id_request"));
        return offer;
    }

    private Offer createOfferView(ResultSet resultSet) throws SQLException {
        Offer offer = new Offer();
        offer.setPrice(resultSet.getLong("price"));
        offer.setProvider(resultSet.getString("provider"));
        offer.setProduct(resultSet.getString("product"));
        offer.setCategory(resultSet.getString("category"));
        offer.setQuantity(resultSet.getInt("quantity"));
        return offer;
    }

}
