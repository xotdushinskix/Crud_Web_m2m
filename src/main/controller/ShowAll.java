package controller;

import dao.ProductDao;
import dao.UserDao;
import fabric.Fabric;
import table.Product;
import table.User;
import util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by nikita on 18.05.16.
 */
@SuppressWarnings("Since15")
@WebServlet("/ShowAll")
public class ShowAll extends Forward {

    private static String SHOW_ALL = "/allUserProduct.jsp";
    private static String ADD_EDIT_USER_PAGE = "/editAndAddUser.jsp";
    private static String ADD_EDIT_PRODUCT_PAGE = "/editAndADDProduct.jsp";
    private static String MAKE_PURCHASE = "/makePurchase.jsp";
    private static String SHOW_PURCHASE = "/showPurchase.jsp";
    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private ProductDao productDao = fabric.getProductDao();
    private User user = new User();
    private Product product = new Product();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateUtil.getSessionFactory();
        String forwardString = null;
        String action = request.getParameter("action");
        if (action.equals("showAllUserAndProduct")) {
            try {
                super.requestAction(request);
                forwardString = SHOW_ALL;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("updateUser")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            try {
                user = userDao.getUser(userId);
                request.setAttribute("user", user);
                forwardString = ADD_EDIT_USER_PAGE;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("deleteUser")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            try {
                userDao.deleteUser(userDao.getUser(userId));
                super.requestAction(request);
                forwardString = SHOW_ALL;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("addUser")) {

            forwardString = ADD_EDIT_USER_PAGE; //if add new user

        } else if (action.equals("updateProduct")) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            try {
                product = productDao.getProduct(productId);
                request.setAttribute("product", product);
                forwardString = ADD_EDIT_PRODUCT_PAGE;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("deleteProduct")) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            try {
                productDao.deleteProduct(productDao.getProduct(productId));
                super.requestAction(request);
                forwardString = SHOW_ALL;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("addProduct")) {
            forwardString = ADD_EDIT_PRODUCT_PAGE;
        } else if (action.equals("purchase")){
            int productId = Integer.parseInt(request.getParameter("productId"));
            try {
                product = productDao.getProduct(productId);
                request.setAttribute("product", product);
                forwardString = MAKE_PURCHASE;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("watchUserPurchases")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            try {
                user = userDao.getUser(userId);
                request.setAttribute("user", user);
                request.setAttribute("userProducts", user.getUserProducts());
                forwardString = SHOW_PURCHASE;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        super.forward(forwardString, request, response);
    }
}

