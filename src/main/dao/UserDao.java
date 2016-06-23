package dao;

import table.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by FromxSoul on 17.05.2016.
 */
public interface UserDao {

    void addUser(User user) throws SQLException;
    void deleteUser(User user) throws SQLException;
    void editUser(User user) throws SQLException;
    User getUser(int userId) throws SQLException;
    User getUserFirstName(String firstName) throws SQLException;
    User getUserLastName(String lastName) throws SQLException;
    User getUserByLogin(String login) throws SQLException;
    List<User> getAllUsers() throws SQLException;

}
