package com.arc.config;

import com.arc.entity.Account;
import com.arc.entity.Client;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
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
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class DBConfig {

    @Value("${mysql.driver}")
    private String dbDriver;

    @Value("${mysql.url}")
    private String dbUrl;

    @Value("${mysql.user}")
    private String dbUser;

    @Value("${mysql.password}")
    private String dbPassword;

    @Value("${hibernate.dialect}")
    private String hDialect;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hHBM2dll;

    @Value("${hibernate.format_sql}")
    private String hFormatSql;

    @Value("${hibernate.show_sql}")
    private String hShowSql;

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DriverManagerDataSource(dbUrl, dbUser, dbPassword);
        ((DriverManagerDataSource) dataSource).setDriverClassName(dbDriver);
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());

        Properties hibernateProps = new Properties();
        hibernateProps.put(Environment.DIALECT, hDialect);
        hibernateProps.put(Environment.HBM2DDL_AUTO, hHBM2dll);
        hibernateProps.put(Environment.FORMAT_SQL, hFormatSql);
        hibernateProps.put(Environment.SHOW_SQL, hShowSql);

        builder.addProperties(hibernateProps);
        builder.addAnnotatedClasses(Account.class, Client.class);

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
