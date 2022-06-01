package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author HERNANDEZ Raul @devrulls
 */
public class DBConnection {
    public static final String URL =  "jdbc:mysql://localhost:3306/nesti_gestion?autoReconnet=true&useSSL=false";
    public static final String USER =  "root";
    public static final String PWD =  "root";


    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.
                    getConnection(URL, USER, PWD);
            System.out.println("Successful connection !");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e); //connexion échouée
        }
        return conn;
    }

}



