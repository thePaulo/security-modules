package br.gov.sead.pagrn;

import br.gov.sead.auth.config.KeycloakConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
@EnableWebMvc
@EnableSwagger2
@EntityScan(basePackages = {"br.gov.sead.pagrn.domain", "br.gov.sead.motorDeCalculo.model","br.gov.sead.auth.external"})
/*@EnableJpaRepositories(
        basePackages = {"br.gov.sead.pagrn.repository","br.gov.sead.motorDeCalculo.repository"},
        entityManagerFactoryRef = "internalEntityManager",
        transactionManagerRef = "internalTransactionManager")*/
@ComponentScan(basePackages = {"br.gov.sead.pagrn","br.gov.sead.motorDeCalculo", "br.gov.sead.auth"})
public class PagRnApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(PagRnApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}