package com.abqdesibites.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;
import java.util.Map;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource() throws Exception {
        Map<String, String> env = System.getenv();
        HikariConfig config = new HikariConfig();

        String databaseUrl = env.get("DATABASE_URL");
        if (databaseUrl != null) {
            URI uri = new URI(databaseUrl);
            String host     = uri.getHost();
            int    port     = uri.getPort() == -1 ? 5432 : uri.getPort();
            String database = uri.getPath().substring(1);
            String[] info   = uri.getUserInfo().split(":", 2);
            String username = info[0];
            String password = info.length > 1 ? info[1] : "";

            config.setJdbcUrl("jdbc:postgresql://" + host + ":" + port + "/" + database);
            config.setUsername(username);
            config.setPassword(password);
        } else {
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/abqdesibites");
            config.setUsername(env.getOrDefault("PGUSER", "postgres"));
            config.setPassword(env.getOrDefault("DB_PASSWORD", "postgres123"));
        }

        config.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(config);
    }
}
