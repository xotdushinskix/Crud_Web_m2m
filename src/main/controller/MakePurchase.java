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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by FromxSoul on 21.05.2016.
 */
@WebServlet("/make_purchase")
public class MakePurchase extends Forward {
    private String IMPSBL_PURCH = "/impossiblePurchaseBeforeLogin.jsp";
    private String MAKE_PURCHASE = "/makePurchase.jsp";
    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private ProductDao productDao = fabric.getProductDao();
    private UserProducts userProducts;
    private Product product;
    private User user;
    private String message;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userLogin") == null) {
            super.forward(IMPSBL_PURCH, request, response);
        } else {
            try {
                String userLogin = session.getAttribute("userLogin").toString();
                user = userDao.getUserByLogin(userLogin);
                String firstName = user.getFirstName();
                int productId = Integer.parseInt(request.getParameter("productId"));
                product = productDao.getProduct(productId);
                request.setAttribute("productId", productId);
                request.setAttribute("productBrand", product.getProductBrand());
                request.setAttribute("productModel", product.getProductModel());
                request.setAttribute("productStock", product.getProductStock());
                request.setAttribute("productMPN", product.getProductMPN());
                session.setAttribute("firstName", firstName);
                super.forward(MAKE_PURCHASE, request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateUtil.getSessionFactory();

        HttpSession session = request.getSession();
        String userLogin = session.getAttribute("userLogin").toString();

        int productId = Integer.parseInt(request.getParameter("productIdForPurchase"));
        int productStock = Integer.parseInt(request.getParameter("productStockForPurchase"));

        try {
            user = userDao.getUserByLogin(userLogin);
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

            message = "Product successfully added to the cart";
            request.setAttribute("messageToCart", message);
            super.forward(MAKE_PURCHASE, request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
