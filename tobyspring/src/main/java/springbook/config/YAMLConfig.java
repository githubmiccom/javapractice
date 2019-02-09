package springbook.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {
    public String name;
    public String environment;
    public class DBConnectionConfig{
        public String url;
        public String id;
        public String passwd;
    }

    public DBConnectionConfig database;
}
