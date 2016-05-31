package controller;

import dao.ProductDao;
import dao.UserDao;
import dao.UserProductsDao;
import fabric.Fabric;
import table.Product;
import table.User;
import table.UserProducts;
import util.HibernateUtil;

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
    private UserProductsDao userProductsDao = fabric.getUserProductsDao();
    private UserProducts userProducts;
    private Product product;
    private User user;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateUtil.getSessionFactory();
        String forwardString = null;

        if (request.getParameter("makePurchase") !=null) {
            int userId = Integer.parseInt(request.getParameter("userIDpurchase"));

            int productId = Integer.parseInt(request.getParameter("productIdForPurchase"));
            int productStock = Integer.parseInt(request.getParameter("productStockForPurchase"));

            try {
                user = userDao.getUser(userId);
                product = productDao.getProduct(productId);
                int stock = product.getProductStock() - productStock;
                product.setProductStock(stock);

                userProducts = new UserProducts();
                userProducts.setBoughtQuantity(productStock);
                userProducts.setProduct(product);
                userProducts.setUser(user);

                user.getUserProducts().add(userProducts);
                product.getUserProducts().add(userProducts);

                userDao.editUser(user);
                productDao.editProduct(product);

                super.requestAction(request);
                request.setAttribute("userProducts", userProductsDao.getUserProducts(userProducts.getUserProductsId()));
                forwardString = SHOW_ALL;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        super.forward(forwardString, request, response);

    }


}
