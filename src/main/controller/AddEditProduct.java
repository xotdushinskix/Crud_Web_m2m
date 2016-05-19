package controller;

import dao.ProductDao;
import dao.UserDao;
import fabric.Fabric;
import table.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by nikita on 19.05.16.
 */
@SuppressWarnings("Since15")
public class AddEditProduct extends Forward{
    private static String SHOW_ALL = "/allUserProduct.jsp";
    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private ProductDao productDao = fabric.getProductDao();
    private Product product = new Product();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productBrand = request.getParameter("productbrand");
        String productModel = request.getParameter("productmodel");
        int productMPN = Integer.parseInt(request.getParameter("productmpn"));

        product.setProductBrand(productBrand);
        product.setProductModel(productModel);
        product.setProductMPN(productMPN);

        String productId = request.getParameter("productIdAddEditPage");
        if (productId.isEmpty()) {
            try {
                productDao.addProduct(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            product.setProductId(Integer.parseInt(productId));
            try {
                productDao.editProduct(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            request.setAttribute("products", productDao.getAllProducts());
            request.setAttribute("users", userDao.getAllUsers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.forward(SHOW_ALL, request, response);
    }

}
