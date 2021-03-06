package com.arc.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class DBConfig {

    @Value("${db.driver}")
    private String dbDriver;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DriverManagerDataSource(dbUrl, dbUser, dbPassword);
        ((DriverManagerDataSource) dataSource).setDriverClassName(dbDriver);
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());

        builder.addAnnotatedClasses(com.arc.entity.Account.class, com.arc.entity.Client.class);
        builder.addAnnotatedClasses(com.arc.entity.npo.Account.class, com.arc.entity.npo.Client.class);
        builder.addAnnotatedClasses(com.arc.entity.subselect.Account.class, com.arc.entity.subselect.Client.class);
        builder.addAnnotatedClasses(com.arc.entity.batchfetch.Account.class, com.arc.entity.batchfetch.Client.class);
        builder.addAnnotatedClasses(com.arc.entity.graph.Account.class, com.arc.entity.graph.Client.class);

        return builder.buildSessionFactory();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new JpaTransactionManager();
    }
}
