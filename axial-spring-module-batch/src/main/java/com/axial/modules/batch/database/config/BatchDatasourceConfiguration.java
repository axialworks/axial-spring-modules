package com.axial.modules.batch.database.config;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BatchDatasourceConfiguration {

    @Bean(name = "dataSourcePropertiesBatch")
    @ConfigurationProperties("db-config.batch")
    public DataSourceProperties batchDataSourceProperties() {
        return new DataSourceProperties();
    }

    @BatchDataSource
    @Bean(name = "dataSourceBatch")
    public DataSource batchDataSource() {

        /*
            Another Solution:

             return DataSourceBuilder.create()
                .url("jdbc:h2:mem:db1")
                .driverClassName("org.h2.Driver")
                .username("sa")
                .password("")
                .build();

         */

        return batchDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

}
