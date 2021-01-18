package servlet;

import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        StringBuilder loginErrorMessage = new StringBuilder();
        if (username == null || username.equals("")) {
            loginErrorMessage.append("Username field is required! <br>");
        }
        if (password == null || password.equals("")) {
            loginErrorMessage.append("Password field is required! <br>");
        }
        if (loginErrorMessage.toString().equals("")) {
            User currentUser = userManager.getUserByUsernameAndPassword(username, password);
            if (currentUser != null) {
                req.getSession().setAttribute("currentUser", currentUser);
                if (currentUser.getUserType() == UserType.ADMIN) {
                    resp.sendRedirect("/adminHome");
                } else {
                    resp.sendRedirect("/userHome");
                }
            } else {
                loginErrorMessage.append("Invalid Email or Password!");
                req.getSession().setAttribute("loginErrorMessage", loginErrorMessage.toString());
                resp.sendRedirect("/login.jsp");
            }
        } else {
            req.getSession().setAttribute("loginErrorMessage", loginErrorMessage.toString());
            resp.sendRedirect("/login.jsp");
        }

    }
}
