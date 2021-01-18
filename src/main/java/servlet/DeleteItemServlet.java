package servlet;

import manager.ItemManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteItem")
public class DeleteItemServlet extends HttpServlet {

    private ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser != null && currentUser.getUserType() == UserType.ADMIN) {
            String id = req.getParameter("id");
            itemManager.deleteItemById(Integer.parseInt(id));
            resp.sendRedirect("/adminHome");
        } else if (currentUser != null && currentUser.getUserType() == UserType.USER) {
            String id = req.getParameter("id");
            itemManager.deleteItemByItemIdAndUserId(Integer.parseInt(id), currentUser.getId());
            resp.sendRedirect("/userHome");
        }

    }
}
