package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest {

    private ProductDao productDao;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        productDao = applicationContext.getBean(ProductDao.class);
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
        Product product = insertProduct();

        Long id = productDao.insert(product);

        Product insertedProduct = productDao.get(id);

        assertEquals(insertedProduct.getId(), id);
        assertEquals(insertedProduct.getTitle(), product.getTitle());
        assertEquals(insertedProduct.getPrice(), product.getPrice());
    }

    @Test
    public void update() throws SQLException {
        Product product = insertProduct();

        Long id = productDao.insert(product);

        product.setId(id);
        product.setTitle("상품수정");
        product.setPrice(5000);
        productDao.update(product);

        Product updatedProduct = productDao.get(id);
        assertEquals(updatedProduct.getId(), id);
        assertEquals(updatedProduct.getTitle(), product.getTitle());
        assertEquals(updatedProduct.getPrice(), product.getPrice());
    }

    @Test
    public void delete() throws SQLException {
        Product product = insertProduct();

        Long id = productDao.insert(product);

        productDao.delete(id);

        Product deletedProduct = productDao.get(id);
        assertEquals(deletedProduct, null);
    }

    private Product insertProduct() {
        Product product = new Product();
        product.setTitle("상품추가");
        product.setPrice(1000);
        return product;
    }
}
