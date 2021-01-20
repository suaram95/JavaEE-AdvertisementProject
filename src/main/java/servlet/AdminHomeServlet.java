package servlet;

import manager.ItemManager;
import manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/adminHome")
public class AdminHomeServlet extends HttpServlet {

    private UserManager userManager = new UserManager();
    private ItemManager itemManager = new ItemManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("users", userManager.getAllUsers());
        req.setAttribute("items", itemManager.getAllItems());
        req.getRequestDispatcher("/adminHome.jsp").forward(req, resp);

    }
}
