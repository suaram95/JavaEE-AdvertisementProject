package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/editUserData")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class EditUserDataServlet extends HttpServlet {

    private UserManager userManager=new UserManager();

    private final String UPLOAD_PATH="D:\\Aram\\IT Space LLC\\Projects 2021\\WEB Java EE (Servlet-Jsp)\\JavaEE-AdvertisementProject\\upload";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String phoneNumber = req.getParameter("phoneNumber");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User currentUser = (User) req.getSession().getAttribute("currentUser");

        StringBuilder userEditMsg = new StringBuilder();
        if (name == null || name.equals("")) {
            userEditMsg.append("Name field is required! <br>");
        }
        if (surname == null || surname.equals("")) {
            userEditMsg.append("Surname field is required! <br>");
        }
        if (phoneNumber == null || phoneNumber.equals("")) {
            userEditMsg.append("Phone Number field is required! <br>");
        }
        if (username == null || username.equals("")) {
            userEditMsg.append("Username field is required! <br>");
        }
        if (password == null || password.equals("")) {
            userEditMsg.append("Password field is required! <br>");
        }
        if (userEditMsg.toString().equals("")){
            currentUser.setName(name);
            currentUser.setSurname(surname);
            currentUser.setPhoneNumber(phoneNumber);
            currentUser.setUsername(username);
            currentUser.setPassword(password);

            for (Part part : req.getParts()) {
                if (getFileName(part)!=null) {
                    String fileName =System.currentTimeMillis()+ getFileName(part);
                    String fullFilename = UPLOAD_PATH + File.separator + fileName;
                    part.write(fullFilename);
                    currentUser.setPictureUrl(fileName);
                }
            }

            userManager.updateUserData(currentUser.getId(), currentUser);
            userEditMsg.append("Data was edited!");
        }
        req.getSession().setAttribute("userEditMsg", userEditMsg.toString());
        resp.sendRedirect("/userDetails");
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }
}
