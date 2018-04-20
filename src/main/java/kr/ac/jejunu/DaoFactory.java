package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public ProductDao getProductDao() {
        return new ProductDao(getConnectionMaker());
    }

    @Bean
    public JejuConnectionMaker getConnectionMaker() {
        return new JejuConnectionMaker();
    }
}
