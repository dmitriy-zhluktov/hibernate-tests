package com.arc.config;

import com.arc.entity.Account;
import com.arc.entity.Client;
import com.arc.tracker.SqlQueryInterceptor;
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
    private String dialect;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2dll;

    @Value("${hibernate.format_sql}")
    private String formatSql;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.use_sql_comments}")
    private String useSqlComments;

    @Value("${hibernate.cache.use_second_level_cache}")
    private String userL2Cache;

    @Value("${hibernate.cache.use_query_cache}")
    private String useQueryCache;


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
        hibernateProps.put(Environment.DIALECT, dialect);
        hibernateProps.put(Environment.HBM2DDL_AUTO, hbm2dll);
        hibernateProps.put(Environment.FORMAT_SQL, formatSql);
        hibernateProps.put(Environment.SHOW_SQL, showSql);
        hibernateProps.put(Environment.USE_SQL_COMMENTS, useSqlComments);
        hibernateProps.put(Environment.USE_SECOND_LEVEL_CACHE, userL2Cache);
        hibernateProps.put(Environment.USE_QUERY_CACHE, useQueryCache);
        hibernateProps.put(Environment.GENERATE_STATISTICS, useQueryCache);

        builder.addProperties(hibernateProps);
        builder.addAnnotatedClasses(Account.class, Client.class);
        builder.setInterceptor(new SqlQueryInterceptor());

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
