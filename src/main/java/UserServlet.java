import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "asuspro15");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"user\"");

            while (rs.next()) {
                String uname = rs.getString("uname");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int age = rs.getInt("age");

                users.add(new User(uname, password, email, phone, age));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("users", users);
        System.out.println(users);
        request.getRequestDispatcher("userDisplay.jsp").forward(request, response);
    }
}
