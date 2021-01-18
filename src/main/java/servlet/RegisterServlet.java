package servlet;

import exception.DuplicateUserException;
import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private UserManager userManager=new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String phoneNumber = req.getParameter("phoneNumber");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        StringBuilder msg=new StringBuilder();
        if (name==null|| name.equals("")){
            msg.append("Name field is required! <br>");
        }
        if (surname==null|| surname.equals("")){
            msg.append("Surname field is required! <br>");
        }
        if (phoneNumber==null|| phoneNumber.equals("")){
            msg.append("Phone Number field is required! <br>");
        }
        if (username==null|| username.equals("")){
            msg.append("Username field is required! <br>");
        }
        if (password==null|| password.equals("")){
            msg.append("Password field is required! <br>");
        }
        if (msg.toString().equals("")){
            User user=User.builder()
                    .name(name)
                    .surname(surname)
                    .phoneNumber(phoneNumber)
                    .username(username)
                    .password(password)
                    .userType(UserType.USER)
                    .build();
            try {
                userManager.addUser(user);
                msg.append("<p style=\"color: green\">You have successfully registered! Please login!</p>");
            } catch (DuplicateUserException e) {
                msg.append("User with username: "+username+" already exists!");
            }
        }
        req.getSession().setAttribute("message", msg.toString());
        resp.sendRedirect("/register.jsp");


    }
}
