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
 * Created by nikita on 16.06.16.
 */
@WebServlet("/products")
public class Start extends Forward{

    private String GUEST_STR = "Guest";
    private User user;
    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            super.requestAction(request);
            String forwardString = "/allUserProduct.jsp";
            super.forward(forwardString, request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
