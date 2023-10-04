public class User
{
    private String uname;
    private String password;
    private String email;
    private String phone;
    private Integer age;

    public User() {
        super();
    }
    public User(String uname, String password, String email, String phone, Integer age) {
        super();
        this.uname = uname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

}