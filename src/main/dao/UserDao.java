package dao;

import table.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by FromxSoul on 17.05.2016.
 */
public interface UserDao {

    void addUser(User user) throws SQLException;
    void deleteUser(User user) throws SQLException;
    void editUser(User user) throws SQLException;
    User getUser(int userId) throws SQLException;
    List<User> getAllUsers() throws SQLException;

}
