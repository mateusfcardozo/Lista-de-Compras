package Connection_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_DB {

    private static final String url = "jdbc:mysql://localhost:3306/DB_Lista_de_Compras";
    private static final String user = "root";
    private static final String pass = "senac2022";

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexão Realizada com sucesso!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Não foi possível se conectar");
            throw e;
        }
    }
}