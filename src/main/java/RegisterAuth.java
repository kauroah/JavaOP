import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<User> getAllUsers() {
        loadDriver("org.postgresql.Driver");
        Connection con = getConnection();
        String sql = "SELECT * FROM \"user\"";
        List<User> userList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String uname = rs.getString("uname");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                User user = new User(uname, password, email, phone);
                userList.add(user);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


}
