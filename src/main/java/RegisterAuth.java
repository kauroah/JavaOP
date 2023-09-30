import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class RegisterAuth {
    private String dburl = "jdbc:postgresql://localhost:5432/postgres";
    private String dbuname = "postgres";
    private String dbpassword = "asuspro15";
    private String dbdriver = "org.postgresql.Driver";
    public void loadDriver(String dbDriver)
    {
        try {
            Class.forName("org.postgresql.Driver");
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
    public String insert(User user) {
        loadDriver("org.postgresql.Driver");
        Connection con = getConnection();
        String sql = "insert into \"user\" values(?,?,?,?)";
        String result="Data Entered Successfully";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUname());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            result="Data Not Entered Successfully";
            e.printStackTrace();
        }
        return result;
    }

}