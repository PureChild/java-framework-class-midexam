package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest {

    private ProductDao productDao;
    private ProductDao hallaProductDao;

    @Before
    public void setup() {
        productDao = new JejuProductDao();
        hallaProductDao = new HallaProductDao();
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setTitle("상품추가");
        product.setPrice(1000);

        Long id = productDao.insert(product);

        Product insertedProduct = productDao.get(id);

        assertEquals(insertedProduct.getId(), id);
        assertEquals(insertedProduct.getTitle(), product.getTitle());
        assertEquals(insertedProduct.getPrice(), product.getPrice());
    }


    @Test
    public void  halla_get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = hallaProductDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void halla_add() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setTitle("상품추가");
        product.setPrice(1000);

        Long id = hallaProductDao.insert(product);

        Product insertedProduct = hallaProductDao.get(id);

        assertEquals(insertedProduct.getId(), id);
        assertEquals(insertedProduct.getTitle(), product.getTitle());
        assertEquals(insertedProduct.getPrice(), product.getPrice());
    }
}
