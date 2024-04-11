package com.axial.modules.batch.database.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/*
    Example Usage:

        @Service
        public class ProductService {

            private final JdbcTemplate jdbcTemplate;

            @Autowired
            public ProductService(@Qualifier("jdbcTemplate1") JdbcTemplate jdbcTemplate) {
                this.jdbcTemplate = jdbcTemplate;
            }

            // Service methods (e.g., getAllProducts, addProduct, updateProduct, deleteProduct)
        }

 */
@Configuration
public class BatchJdbcConfiguration {

    /*
     * Example Usage:

         @Autowired
         @Qualifier("jdbcTemplateBatch")
         JdbcTemplate jdbcTemplate;

     */
    @Bean(name = "jdbcTemplateBatch")
    public JdbcTemplate batchJdbcTemplate(@Qualifier("dataSourceBatch") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
