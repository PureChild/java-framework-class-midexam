package kr.ac.jejunu;

import java.sql.*;

public class ProductDao {
    private final JdbcContext jdbcContext;

    public ProductDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws SQLException {
        Long id1 = id;
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        return jdbcContext.JdbcContextFotGet(statementStrategy);
    }

    public Long insert(Product product) throws SQLException {
        Product product1 = product;
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into product (title, price) values (?,?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,product.getTitle());
            preparedStatement.setInt(2,product.getPrice());
            return preparedStatement;
        };
        return jdbcContext.JdbcContextForInsert(statementStrategy);
    }

    public void update(Product product) throws SQLException {
        Product product1 = product;
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET title = ?, price = ? WHERE id = ?");
            preparedStatement.setString(1,product.getTitle());
            preparedStatement.setInt(2,product.getPrice());
            preparedStatement.setLong(3,product.getId());
            return preparedStatement;
        };
        jdbcContext.JdbcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        Long id1 = id;
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        jdbcContext.JdbcContextForUpdate(statementStrategy);
    }
}
