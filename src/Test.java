import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gerenciamento-estoque",
                "root",
                "root");
                System.out.println("Conectou");

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
    
}
