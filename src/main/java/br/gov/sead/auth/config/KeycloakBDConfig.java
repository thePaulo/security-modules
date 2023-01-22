package br.gov.sead.auth.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:application-dev.yml"})
@EnableJpaRepositories(
        basePackages = {"br.gov.sead.auth.externalDao"},
        entityManagerFactoryRef = "externalEntityManager",
        transactionManagerRef = "externalTransactionManager")
public class KeycloakBDConfig {

    //@Primary
    @Bean
    @ConfigurationProperties(prefix="pagrn-auth-data.datasource")
    public DataSource externalDataSource() {
        return DataSourceBuilder.create().build();
    }
    // userEntityManager bean

    @Bean(name = "externalEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){

        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(readOnlyDataSource());
        em.setPackagesToScan(new String[] { "br.gov.sead.auth.externalModel" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        //em.setJpaProperties(additionalProperties());

        return em;

    }

    @Bean
    public DataSource readOnlyDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/keycloak");
        config.setUsername("postgres");
        config.setPassword("123456");
        config.setReadOnly(true);
        config.setAutoCommit(false);
        return new HikariDataSource(config);

    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/keycloak");
        dataSource.setUsername( "postgres" );
        dataSource.setPassword( "123456" );
        return dataSource;
    }

    @Bean(name = "externalTransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    // userTransactionManager bean
}