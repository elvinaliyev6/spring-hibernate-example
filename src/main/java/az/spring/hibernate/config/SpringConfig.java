package az.spring.hibernate.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "az.spring.hibernate")
@EnableTransactionManagement
public class SpringConfig {

    @Bean
    public DataSource dataSource(DatabaseConfig databaseConfig) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(databaseConfig.getDriverClassName());
        driverManagerDataSource.setUrl(databaseConfig.getUrl());
        driverManagerDataSource.setUsername(databaseConfig.getUsername());
        driverManagerDataSource.setPassword(databaseConfig.getPassword());

        return driverManagerDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sesionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("az.spring.hibernate.model");
        sessionFactoryBean.setHibernateProperties(properties());

        return sessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(
                "hibernate.hbm2ddl.auto", "none");

        properties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        return properties;
    }

}
