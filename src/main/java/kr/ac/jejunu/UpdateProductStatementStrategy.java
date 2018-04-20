package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProductStatementStrategy implements StatementStrategy {
    private Product product;
    public UpdateProductStatementStrategy(Product product) {
        this.product = product;
    }

    @Override
    public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET title = ?, price = ? WHERE id = ?");
        preparedStatement.setString(1,product.getTitle());
        preparedStatement.setInt(2,product.getPrice());
        preparedStatement.setLong(3,product.getId());
        return preparedStatement;
    }
}
