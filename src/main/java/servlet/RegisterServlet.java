package servlet;

import exception.DuplicateUserException;
import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class RegisterServlet extends HttpServlet {

    private UserManager userManager=new UserManager();

    private final String UPLOAD_PATH="D:\\Aram\\IT Space LLC\\Projects 2021\\WEB Java EE (Servlet-Jsp)\\JavaEE-AdvertisementProject\\upload";

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
            for (Part part : req.getParts()) {
                if (getFileName(part)!=null) {
                    String fileName =System.currentTimeMillis()+ getFileName(part);
                    String fullFilename = UPLOAD_PATH + File.separator + fileName;
                    part.write(fullFilename);
                    user.setPictureUrl(fileName);
                }
            }

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

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }
}
