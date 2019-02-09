package springbook.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import springbook.config.YAMLConfig2;

public class MariadbConnectionMaker implements SimpleConnectionMaker {
    private YAMLConfig2.ConfigPerEnv config;

    public MariadbConnectionMaker(YAMLConfig2.ConfigPerEnv cfg) {
        this.config = cfg;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");

        return DriverManager.getConnection(
                config.getDatabase().getUrl(), config.getDatabase().getId(), config.getDatabase().getPasswd());
    }

}
