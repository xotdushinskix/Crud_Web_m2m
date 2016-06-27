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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by nikita on 27.06.16.
 */
@WebServlet("/cart/change")
public class CartChange extends Forward {

    private Fabric fabric = Fabric.getInstance();
    private UserProductsDao userProductsDao = fabric.getUserProductsDao();
    private UserProducts userProducts;
    private String CHANGE_QUANTITY = "/changeProdQuantityInOrder.jsp";
    private String forward;
    private Product product;
    private ProductDao productDao = fabric.getProductDao();
    private int orderLineId;
    private String DELETE_LINE = "/deleteOrderLine.jsp";
    private User user;
    private UserDao userDao = fabric.getUserDao();


    private void orderRequest(HttpServletRequest request) {
        request.setAttribute("productId", userProducts.getProduct().getProductId());
        request.setAttribute("productBrand", userProducts.getProduct().getProductBrand());
        request.setAttribute("productModel", userProducts.getProduct().getProductModel());
        request.setAttribute("productStock", userProducts.getProduct().getProductStock());
        request.setAttribute("quantityBeforeEdit", userProducts.getBoughtQuantity());
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userLogin = session.getAttribute("userLogin").toString();
        try {
            user = userDao.getUserByLogin(userLogin);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        orderLineId = Integer.parseInt(request.getParameter("order_line_id"));
            request.setAttribute("orderLineId", orderLineId);
            try {
                userProducts = userProductsDao.getUserProducts(orderLineId);
                request.setAttribute("orderLineId", orderLineId);
                orderRequest(request);
                request.setAttribute("userName", user.getFirstName());
                if (request.getParameter("action").equals("update")) {
                    forward = CHANGE_QUANTITY;
                } else if (request.getParameter("action").equals("delete")) {
                    forward = DELETE_LINE;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        super.forward(forward, request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("EditProdQuantityInPO") != null) {

            int productId = Integer.parseInt(request.getParameter("productIdEditQuantityPurchase"));
            int prodPurchQuantityBeforeEdit = Integer.parseInt(request.getParameter("quantityBeforeEdit"));
            int prodPurchQuantityAfterEdit = Integer.parseInt(request.getParameter("productPurchQuantityForEdit"));
            int orderLineId = Integer.parseInt(request.getParameter("orderLineId"));
            try {
                product = productDao.getProduct(productId);
                userProducts = userProductsDao.getUserProducts(orderLineId);
                orderLineId = userProducts.getUserProductsId();
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

                String message = "Purchase quantity successfully updated";
                request.setAttribute("message", message);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                userProducts = userProductsDao.getUserProducts(orderLineId);
                request.setAttribute("orderLineId", orderLineId);
                orderRequest(request);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            super.forward(CHANGE_QUANTITY, request, response);


        } else if (request.getParameter("deleteOrderLine") != null) {
            int productId = Integer.parseInt(request.getParameter("productIdEditQuantityPurchase"));
            int prodPurchQuantityBeforeEdit = Integer.parseInt(request.getParameter("quantityBeforeEdit"));
            int orderLineId = Integer.parseInt(request.getParameter("orderLineId"));
            try {
                userProducts = userProductsDao.getUserProducts(orderLineId);
                product = productDao.getProduct(productId);
                int newStock = product.getProductStock() + prodPurchQuantityBeforeEdit;
                product.setProductStock(newStock);
                productDao.editProduct(product);
                userProductsDao.deleteUserProducts(userProducts);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("/cart");
        }

    }
}
