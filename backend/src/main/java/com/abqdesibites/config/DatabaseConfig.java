package com.abqdesibites.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        Map<String, String> env = System.getenv();

        String host     = env.getOrDefault("PGHOST",     "localhost");
        String port     = env.getOrDefault("PGPORT",     "5432");
        String database = env.getOrDefault("PGDATABASE", "abqdesibites");
        String username = env.getOrDefault("PGUSER",     "postgres");
        String password = env.getOrDefault("PGPASSWORD",
                          env.getOrDefault("DB_PASSWORD", "postgres123"));

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://" + host + ":" + port + "/" + database);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(config);
    }
}
