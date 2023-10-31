package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    Connection connection = null;

    public UserRepository() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gerenciamento-estoque",
                    "root",
                    "root");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.connection = con;
    }

    public User getUserByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM usuario WHERE email = '" + email + "';";

        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {

                user = new User(rs.getString("email"), rs.getString("senha"));

            }   

        } catch (Exception e) {

            e.printStackTrace();
                
        }
        return user;
    }

}
