package springbook;

import springbook.config.YAMLConfig2;
import springbook.dao.MariadbConnectionMaker;
import springbook.dao.user.User;
import springbook.dao.user.UserDao;

import java.nio.channels.ClosedSelectorException;
import java.sql.SQLException;

public class SpringBookMain {
    public static void main(String []args){
        YAMLConfig2 cfg2 = new YAMLConfig2();
        cfg2.setDefaultProfile("test");

        MariadbConnectionMaker cMaker = new MariadbConnectionMaker(cfg2.getConfig());
        UserDao dao = new UserDao();
        dao.setConnectionMaker(cMaker);

        User user1= new User();
        user1.setId("1");
        user1.setName("user1");
        user1.setPassword("password1");

        try {
            //dao.add(user1);
            User fetchedUser = dao.get("1");
            System.out.println("fetchedUser " + user1.equals(fetchedUser));
        }catch(ClassNotFoundException ce){
            System.err.println("Class not found "+ ce);
        }catch(SQLException se) {
            System.err.println("SQL error " + se);
        }
    }
}
