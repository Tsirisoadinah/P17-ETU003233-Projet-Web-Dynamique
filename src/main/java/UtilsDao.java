package src.main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilsDao {
    //private static final String URL = "jdbc:postgresql://localhost:5432/testlogin";
   // private static final String USER = "postgres";
   // private static final String PASSWORD = " ";

    private static final String URL = "jdbc:mysql://172.80.237.54:3306/db_s2_ETU003233";
    private static final String USER = "ETU003233";
    private static final String PASSWORD = "mxi0ie80";

    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        //Class.forName("org.postgresql.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}

/*
 * Emp.jsp : liste deroulante (tsy mahazo miantso direct data)
 * formEmpServlet : maka liste departement (get ihany no atao) - form.jsp
 * -EmpServlet : doPost, save, miverina form.jsp avy ao no misy FindAll
 */