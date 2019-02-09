package springbook.config;

public final class YAMLConfig2 {
    public String name;
    public String environment;
    public class DBConnectionConfig{
        public String url;
        public String id;
        public String passwd;
    }

    public DBConnectionConfig database;
}
