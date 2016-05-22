package controller;

import dao.ProductDao;
import dao.UserDao;
import fabric.Fabric;
import table.Product;
import table.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by FromxSoul on 21.05.2016.
 */
@WebServlet("/MakePurchase")
public class MakePurchase extends Forward {
    private static String SHOW_ALL = "/allUserProduct.jsp";
    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private ProductDao productDao = fabric.getProductDao();
    private Product product;
    private User user;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardString = null;

        int userId = Integer.parseInt(request.getParameter("userIDpurchase"));

        int productId = Integer.parseInt(request.getParameter("productIdForPurchase"));
        int productStock = Integer.parseInt(request.getParameter("productStockForPurchase"));

        System.out.println(userId);
        System.out.println(productId);
        System.out.println(productStock);

        try {
            user = userDao.getUser(userId);
            product = productDao.getProduct(productId);
            int stock = product.getProductStock() - productStock;
            product.setProductStock(stock);

            user.getProducts().add(product);
            product.getUsers().add(user);

            userDao.editUser(user);
            productDao.editProduct(product);

            super.requestAction(request);
            forwardString = SHOW_ALL;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        super.forward(forwardString, request, response);

    }
}
