package manager;

import db.DBConnectionProvider;
import exception.DuplicateUserException;
import model.User;
import model.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private Connection connection;

    public UserManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addUser(User user) throws DuplicateUserException {
        if (getUserByUsername(user.getUsername())!=null){
            throw new DuplicateUserException();
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO user(name,surname,phone_number,username,password,picture_url) " +
                            "VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getPictureUrl());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id=" + id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByUsername(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username=?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers(){
        List<User> userList=new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()){
                userList.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void deleteUserById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id=" + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .surname(resultSet.getString(3))
                .phoneNumber(resultSet.getString(4))
                .username(resultSet.getString(5))
                .password(resultSet.getString(6))
                .userType(UserType.valueOf(resultSet.getString(7)))
                .pictureUrl(resultSet.getString(8))
                .build();
    }

    public void updateUserData(int currentUserId, User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE user SET name=?,surname=?,phone_number=?,username=?,password=?,picture_url=? WHERE id=? ");
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getPictureUrl());
            statement.setInt(7, currentUserId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
