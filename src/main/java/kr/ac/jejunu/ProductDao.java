package kr.ac.jejunu;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product get(Long id) throws SQLException {
        String sql = "select * from product where id = ?";
        Object[] params = new Object[]{id};
        try {
            return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setTitle(rs.getString("title"));
                product.setPrice(rs.getInt("price"));
                return product;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Long insert(Product product) throws SQLException {
        String sql = "insert into product (title, price) values (?,?);";
        Object[] params = new Object[]{product.getTitle(), product.getPrice()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for(int i = 0; i < params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void update(Product product) throws SQLException {
        String sql = "UPDATE product SET title = ?, price = ? WHERE id = ?";
        Object[] params = new Object[]{product.getTitle(), product.getPrice(), product.getId()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }

}
