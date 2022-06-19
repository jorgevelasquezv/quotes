package co.com.jorge.quotes.repositories;

import co.com.jorge.quotes.models.Offert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferRepositoryImpl implements Repository<Offert> {

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
    public List<Offert> listAll() throws SQLException {
        List<Offert> list = new ArrayList<>();

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM offers");){
            while (resultSet.next()){
                list.add(createOffer(resultSet));
            }
        }
        return list;
    }

    @Override
    public Offert find(Offert offert) throws SQLException {
        Long id = offert.getIdOffer();
        Offert foundOffer = null;
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
    public Offert save(Offert offert) throws SQLException {
        String sql = null;
        if (offert.getIdOffer() != null && offert.getIdOffer() > 0){
            sql = "UPDATE offers SET price=?, id_providers=?, id_request=? WHERE id_offers=? ";
        }else {
            sql = "INSERT INTO offers(price, id_providers, id_request) VALUES(?, ?, ?)";
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setLong(1, offert.getPrice());
            preparedStatement.setLong(2, offert.getIdProvider());
            preparedStatement.setLong(3, offert.getIdRequest());
            if (offert.getIdOffer() != null && offert.getIdOffer() > 0){
                preparedStatement.setLong(4, offert.getIdOffer());
            }
            preparedStatement.executeUpdate();
            if (offert.getIdOffer() == null){
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if (resultSet.next()){
                        offert.setIdOffer(resultSet.getLong("id_offers"));
                    }
                }
            }
        }
        return offert;
    }

    @Override
    public void deleted(Offert offert) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM offers WHERE id_offers=?")){
            preparedStatement.setLong(1, offert.getIdOffer());
            preparedStatement.executeUpdate();
        }
    }

    private Offert createOffer(ResultSet resultSet) throws SQLException {
        Offert offert = new Offert();
        offert.setIdOffer(resultSet.getLong("id_offers"));
        offert.setPrice(resultSet.getLong("price"));
        offert.setIdProvider(resultSet.getLong("id_providers"));
        offert.setIdRequest(resultSet.getLong("id_request"));
        return offert;
    }
}
