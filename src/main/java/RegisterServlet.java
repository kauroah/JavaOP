import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname=request.getParameter("uname");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
        Integer age= Integer.valueOf(request.getParameter("age"));
        User user = new User(uname, password, email, phone, age);
        RegisterAuth registerAuth=new RegisterAuth();
        String result=registerAuth.insert(user);

        System.out.println("Username: " + uname);
        System.out.println("Password: " + password);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Age " + age);
        response.getWriter().println(result);

        System.out.println();
//
//        Integer searchAge = 10;
//
//        User userByAge = (User) registerAuth.findUserByAge(searchAge);
//
//        if (userByAge != null) {
//            System.out.println("Users By Age:");
//            System.out.println("Username: " + userByAge.getUname());
//            System.out.println("Password: " + userByAge.getPassword());
//            System.out.println("Email: " + userByAge.getEmail());
//            System.out.println("Phone: " + userByAge.getPhone());
//            System.out.println("Age: " + userByAge.getAge());
//        } else {
//            System.out.println("User not found with email: " + searchAge);
//        }
//
//
//        System.out.println();

        String searchEmail = "fahman@example.com";
        User foundUser = registerAuth.findUserByEmail(searchEmail);

        if (foundUser != null) {
            System.out.println("User found:");
            System.out.println("Username: " + foundUser.getUname());
            System.out.println("Password: " + foundUser.getPassword());
            System.out.println("Email: " + foundUser.getEmail());
            System.out.println("Phone: " + foundUser.getPhone());
            System.out.println("Age: " + foundUser.getAge());
            System.out.println();
        } else {
            System.out.println("User not found with email: " + searchEmail);
        }
    }
}