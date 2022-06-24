package az.spring.jdbc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "az.spring.jdbc")
public class SpringJdbcConfig {

    @Bean
    public DataSource dataSoruce(DataBaseConfig dataBaseConfig) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(dataBaseConfig.getDriverClassName());
        driverManagerDataSource.setUrl(dataBaseConfig.getUrl());
        driverManagerDataSource.setUsername(dataBaseConfig.getUsername());
        driverManagerDataSource.setPassword(dataBaseConfig.getPassword());

        return driverManagerDataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
