package br.gov.sead.auth.config;

//import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
        import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
        import org.springframework.transaction.PlatformTransactionManager;

        import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource({"classpath:application-dev.yml"})
/*@EnableJpaRepositories(
        basePackages = {"br.gov.sead.pagrn.repository","br.gov.sead.motorDeCalculo.repository"},
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager")*/
@EnableJpaRepositories(
        basePackages = {"br.gov.sead.motorDeCalculo.repository","br.gov.sead.pagrn.repository"},
        entityManagerFactoryRef = "internalEntityManager",
        transactionManagerRef = "internalTransactionManager")
public class PagrnBDconfig {


    @Bean
    @Primary
    //@ConfigurationProperties("spring.datasource")
    public DataSourceProperties internalDataSourceProperties(){
        return new DataSourceProperties();
    }


    @Bean
    @Primary
    //@ConfigurationProperties("spring.datasource")
    public DataSource internalDataSource(){
        //return internalDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/pagrn");
        dataSource.setUsername( "postgres" );
        dataSource.setPassword( "123456" );
        return dataSource;
        //return DataSourceBuilder.create().build();
    }

    @Bean("internalEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean internalEntityManagerFactory(EntityManagerFactoryBuilder builder){
        Map<String,String> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
        props.put("hibernate.hbm2ddl.auto", "create-drop");
        return builder.dataSource(internalDataSource())
                .packages("br.gov.sead.pagrn.domain","br.gov.sead.motorDeCalculo.model")
                .properties(props)
                .build();
    }

    @Bean("internalTransactionManager")
    @Primary
    public PlatformTransactionManager internalTransactionManager(final @Qualifier("internalEntityManager")LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean){
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }

}