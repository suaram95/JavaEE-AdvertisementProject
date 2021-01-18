package servlet;

import exception.DuplicateItemException;
import manager.ItemManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/addItem")
public class AddItemServlet extends HttpServlet {


    private ItemManager itemManager = new ItemManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        String category = req.getParameter("category");

        StringBuilder itemDetailMsg = new StringBuilder();
        if (title == null || title.equals("")) {
            itemDetailMsg.append("Title field is required <br>");
        }
        if (title == null || description.equals("")) {
            itemDetailMsg.append("Description field is required <br>");
        }
        if (price == null || price.equals("")) {
            itemDetailMsg.append("Price field is required <br>");
        }
        if (itemDetailMsg.toString().equals("")) {
            User currentUser = (User) req.getSession().getAttribute("currentUser");
            Item item = Item.builder()
                    .title(title)
                    .description(description)
                    .price(Double.parseDouble(price))
                    .category(Category.valueOf(category))
                    .createdDate(new Date())
                    .user(currentUser)
                    .build();
            try {
                itemManager.addItem(item);
                itemDetailMsg.append("Item has successfully added! <br>");


            } catch (DuplicateItemException e) {
                itemDetailMsg.append(e.getMessage());
            }
        }
        req.getSession().setAttribute("itemDetailMsg", itemDetailMsg.toString());
        resp.sendRedirect("/userHome");
    }
}
