package controller;

import dao.ProductDao;
import dao.UserDao;
import dao.UserProductsDao;
import fabric.Fabric;
import table.Product;
import table.User;
import table.UserProducts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nikita on 30.05.16.
 */
@WebServlet("/EditProdQuantityInPO")
public class EditProdQuantityInPO extends Forward {
    private Fabric fabric = Fabric.getInstance();
    private ProductDao productDao = fabric.getProductDao();
    private UserProductsDao userProductsDao = fabric.getUserProductsDao();
    private UserDao userDao = fabric.getUserDao();
    private Product product;
    private UserProducts userProducts;
    private User user;
    private static String SHOW_PURCHASE = "/showPurchase.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productIdEditQuantityPurchase"));
        int prodPurchQuantityBeforeEdit = Integer.parseInt(request.getParameter("productPurchQuantity"));
        int prodPurchQuantityAfterEdit = Integer.parseInt(request.getParameter("productPurchQuantityForEdit"));
        int userProductsId = Integer.parseInt(request.getParameter("userProductsId"));
        try {
            product = productDao.getProduct(productId);
            userProducts = userProductsDao.getUserProducts(userProductsId);
            int productStock = product.getProductStock();
            int newProdStock = 0;
            int diffVar = 0;
            if (prodPurchQuantityBeforeEdit < prodPurchQuantityAfterEdit) {
                diffVar = prodPurchQuantityAfterEdit - prodPurchQuantityBeforeEdit;
                newProdStock = productStock - diffVar;
            } else if (prodPurchQuantityBeforeEdit > prodPurchQuantityAfterEdit) {
                diffVar = prodPurchQuantityBeforeEdit - prodPurchQuantityAfterEdit;
                newProdStock = productStock + diffVar;
            }

            product.setProductStock(newProdStock);
            productDao.editProduct(product);

            userProducts.setBoughtQuantity(prodPurchQuantityAfterEdit);
            userProductsDao.editUserProducts(userProducts);

            int userId = userProducts.getUser().getUserId();
            user = userDao.getUser(userId);

            List<UserProducts> userProducts = userProductsDao.getAllUsProdByRequiredUserId(user);
            request.setAttribute("userProductsesNew", userProducts);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //super.showRequireInfoAfterDeleteOrder(request);
        super.forward(SHOW_PURCHASE, request, response);

    }
}
