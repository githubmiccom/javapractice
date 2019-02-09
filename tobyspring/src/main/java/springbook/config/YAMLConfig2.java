package springbook.config;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.io.InputStream;
import org.yaml.snakeyaml.Yaml;


public final class YAMLConfig2 {
    public class ConfigPerEnv {
        public class DBConnectionConfig {
            public String url;
            public String id;
            public String passwd;

            public String getUrl() {
                return url;
            }

            public String getId() {
                return id;
            }

            public String getPasswd() {
                return passwd;
            }

            public DBConnectionConfig(String url, String id, String passwd){
                this.url = url;
                this.id = id;
                this.passwd = passwd;
            }
        }
        public String name;
        public String environment;
        private DBConnectionConfig database;

        public ConfigPerEnv(String name, String environment, String dbUrl, String dbId, String dbPwd) {
            this.name = name;
            this.environment = environment;
            this.database = new DBConnectionConfig(dbUrl, dbId, dbPwd);
        }

        public String getName() {
            return name;
        }

        public String getEnvironment() {
            return environment;
        }

        public DBConnectionConfig getDatabase() {
            return database;
        }
    }
    private Map<String, ConfigPerEnv> configs;
    private String defaultProfile;

    public YAMLConfig2() {
        configs = new LinkedHashMap<>();

        InputStream cfgStream = YAMLConfig2.class.getResourceAsStream("/application.yml");
        Yaml yaml = new Yaml();
        for( Object o : yaml.loadAll(cfgStream) ){
            Map<String, Object> m = (Map<String, Object>)o;
            Map<String, String> dbCfgMap = (Map<String, String>)m.get("database");

            ConfigPerEnv cfg = new ConfigPerEnv(
                    (String)m.get("name"), (String)m.get("environment"),
                    dbCfgMap.get("url"), dbCfgMap.get("id"), dbCfgMap.get("passwd"));
            String profile = ((Map<String, String>)m.get("spring")).get("profiles");
            configs.put(profile, cfg);
        }
    }

    public Map<String, ConfigPerEnv> getConfigs() {
        return configs;
    }

    public ConfigPerEnv getConfig() {
        return getConfigs().get(defaultProfile);
    }

    public String getDefaultProfile() {
        return defaultProfile;
    }

    public void setDefaultProfile(String defaultProfile) {
        this.defaultProfile = defaultProfile;
    }
}
