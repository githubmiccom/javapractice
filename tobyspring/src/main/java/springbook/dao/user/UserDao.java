package springbook.dao.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import springbook.dao.SimpleConnectionMaker;

public class UserDao {
    private SimpleConnectionMaker connectionMaker;

    public void setConnectionMaker(SimpleConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.getConnection();

        PreparedStatement stmt = c.prepareStatement(
                "INSERT INTO user (id, name, password) VALUES( ?, ?, ?);"
        );

        stmt.setString(1, user.getId());
        stmt.setString(2, user.getName());
        stmt.setString(3, user.getPassword());

        stmt.executeUpdate();
        stmt.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.getConnection();

        PreparedStatement stmt = c.prepareStatement(
                "SELECT id, name, password FROM user WHERE id = ?;"
        );

        stmt.setString(1, id);

        ResultSet rs = stmt.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        stmt.close();
        c.close();

        return user;
    }
}
