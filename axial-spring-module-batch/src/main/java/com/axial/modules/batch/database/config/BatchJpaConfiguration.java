package com.axial.modules.batch.database.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/*
    Example Usage:

        @Repository
        @Transactional(transactionManager = "batchTransactionManager")
        public interface Entity1Repository extends JpaRepository<Entity1, Long> {
            // Custom query methods
        }

 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.axial.modules.batch.database.repository",
        entityManagerFactoryRef = "entityManagerFactoryBatch",
        transactionManagerRef = "transactionManagerBatch"
)
public class BatchJpaConfiguration {

    @Bean(name = "entityManagerFactoryBatch")
    public LocalContainerEntityManagerFactoryBean batchEntityManagerFactory(
            @Qualifier("dataSourceBatch") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.axial.modules.batch.database.entity")
                .build();
    }

    @Bean(name = "transactionManagerBatch")
    public PlatformTransactionManager batchTransactionManager(
            @Qualifier("entityManagerFactoryBatch")
            EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
