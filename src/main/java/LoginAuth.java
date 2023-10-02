import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginAuth {
    private String dburl = "jdbc:postgresql://localhost:5432/postgres";
    private String dbuname = "postgres";
    private String dbpassword = "asuspro15";

    public void loadDriver(String dbDriver) {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dburl, dbuname, dbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

public boolean checkLogin(String uname, String password) {
    loadDriver("org.postgresql.Driver");
    try (Connection con = getConnection()) {
        String sql = "SELECT * FROM \"user\" WHERE uname=? AND password=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uname);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        }
    return false;
    }
}
