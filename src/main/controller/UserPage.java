package controller;

import dao.UserDao;
import fabric.Fabric;
import table.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by nikita on 24.06.16.
 */
@WebServlet("/user_page")
public class UserPage extends Forward {

    private String userLogin;
    private User user;
    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private String USER_PAGE = "/userPage.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        userLogin = session.getAttribute("userLogin").toString();
        try {
            user = userDao.getUserByLogin(userLogin);
            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("secondName", user.getLastName());
            request.setAttribute("userShopExperience", user.getShopExperience());
            request.setAttribute("userPassword", user.getPassword());
            super.forward(USER_PAGE, request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
