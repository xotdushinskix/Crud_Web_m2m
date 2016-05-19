package controller;

import dao.ProductDao;
import dao.UserDao;
import fabric.Fabric;
import table.User;
import util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by nikita on 19.05.16.
 */
@SuppressWarnings("Since15")
public class AddEditUser extends Forward {

    private static String SHOW_ALL = "/allUserProduct.jsp";
    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private ProductDao productDao = fabric.getProductDao();
    private User user = new User();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateUtil.getSessionFactory();

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        int shopExperience = Integer.parseInt(request.getParameter("shopexperience"));

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setShopExperience(shopExperience);

        String userId = request.getParameter("userIdAddEditPage");
        if (userId.isEmpty()) {
            try {
                userDao.addUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            user.setUserId(Integer.parseInt(userId));
            try {
                userDao.editUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            request.setAttribute("users", userDao.getAllUsers());
            request.setAttribute("products", productDao.getAllProducts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.forward(SHOW_ALL, request, response);
    }
}
