package manager;

import db.DBConnectionProvider;
import exception.DuplicateItemException;
import model.Category;
import model.Item;
import util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private Connection connection;

    private UserManager userManager = new UserManager();

    public ItemManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addItem(Item item) throws DuplicateItemException {
        if (getItemByUserIdAndTitle(item.getUser().getId(), item.getTitle()) != null) {
            throw new DuplicateItemException("Item with title " + item.getTitle() + " already exists");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO item(title,description,price,category,created_date,user_id) " +
                            "VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getDescription());
            statement.setDouble(3, item.getPrice());
            statement.setString(4, item.getCategory().name());
            statement.setString(5, DateUtil.getStringFromDate(item.getCreatedDate()));
            statement.setInt(6, item.getUser().getId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item getItemById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM item WHERE id=" + id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getItemsByUserId(int userId) {
        List<Item> itemList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM item WHERE user_id=" + userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                itemList.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public Item getItemByUserIdAndTitle(int userId, String title) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM item WHERE user_id=? AND title=?");
            statement.setInt(1, userId);
            statement.setString(2, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM item");
            while (resultSet.next()) {
                itemList.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public void deleteItemById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM item WHERE id=" + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItemByItemIdAndUserId(int itemId, int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM item WHERE id=? AND user_id=?");
            statement.setInt(1, itemId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
        return Item.builder()
                .id(resultSet.getInt(1))
                .title(resultSet.getString(2))
                .description(resultSet.getString(3))
                .price(Double.parseDouble(resultSet.getString(4)))
                .category(Category.valueOf(resultSet.getString(5)))
                .createdDate(DateUtil.getDateFromString(resultSet.getString(6)))
                .user(userManager.getUserById(Integer.parseInt(resultSet.getString(7))))
                .build();
    }


}
