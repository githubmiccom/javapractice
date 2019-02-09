package springbook.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface SimpleConnectionMaker {
    Connection getConnection() throws ClassNotFoundException, SQLException;
}
