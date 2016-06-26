package controller;

import dao.OrderDao;
import dao.ProductDao;
import dao.UserDao;
import dao.UserProductsDao;
import fabric.Fabric;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import table.Order;
import table.Product;
import table.UserProducts;
import util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by FromxSoul on 06.06.2016.
 */
@WebServlet("/finalize_order")
public class FinalOrder extends Forward{
    private Order order;
    private String SHOW_ORDER = "/showOrder.jsp";
    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private UserProductsDao userProductsDao = fabric.getUserProductsDao();
    private ProductDao productDao = fabric.getProductDao();
    private Product product;
    private UserProducts userProducts;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateUtil.getSessionFactory();
        HttpSession session = request.getSession();
        String userLogin = session.getAttribute("userLogin").toString();

        Fabric fabric = Fabric.getInstance();
        UserProductsDao userProductsDao = fabric.getUserProductsDao();
        OrderDao orderDao = fabric.getOrderDao();

        if (request.getParameter("final_order") != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            try {
                user = userDao.getUserByLogin(userLogin);
                Criteria criteria = HibernateUtil.getSessionFactory().openSession().createCriteria(UserProducts.class)
                        .add(Restrictions.eq("user.userId", user.getUserId()))
                        .add(Restrictions.isNull("order.orderId"));
                List<UserProducts> userProducts = criteria.list();
                order = new Order();
                order.setUserProducts(userProducts);
                order.setShipStatus(false);
                order.setCurrentData(dateFormat.format(date));
                orderDao.addOrder(order);
                for (UserProducts userProducts1 : userProducts) {
                    userProducts1.setOrder(order);
                    userProductsDao.editUserProducts(userProducts1);
                }
                int orderId = order.getOrderId();
                request.setAttribute("orderId", orderId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            super.forward(SHOW_ORDER, request, response);


        } else if (request.getParameter("update_quantity") != null) {
            int prodPurchQuantityAfterEdit = Integer.parseInt(request.getParameter("b_quantity"));
            int orderLineId = Integer.parseInt(request.getParameter("orderLineId"));

            int newProdStock = 0;
            try {
                userProducts = userProductsDao.getUserProducts(orderLineId);
                System.out.println(userProducts.getUserProductsId());
                int productStock = userProducts.getProduct().getProductStock();
                UserProducts orderLine = userProductsDao.getUserProducts(orderLineId);
                int prodPurchQuantityBeforeEdit = orderLine.getBoughtQuantity();
                System.out.println(prodPurchQuantityBeforeEdit + " " + prodPurchQuantityAfterEdit);
                if (prodPurchQuantityBeforeEdit < prodPurchQuantityAfterEdit) {
                    int diffVar = prodPurchQuantityAfterEdit - prodPurchQuantityBeforeEdit;
                    newProdStock = productStock - diffVar;
                } else if (prodPurchQuantityBeforeEdit > prodPurchQuantityAfterEdit) {
                    int diffVar = prodPurchQuantityBeforeEdit - prodPurchQuantityAfterEdit;
                    newProdStock = productStock + diffVar;
                }
                System.out.println(newProdStock);
                int productId = userProducts.getProduct().getProductId();
                product = productDao.getProduct(productId);
                product.setProductStock(newProdStock);
                productDao.editProduct(product);

                userProducts.setBoughtQuantity(prodPurchQuantityAfterEdit);
                userProductsDao.editUserProducts(userProducts);



//                int userId = userProducts.getUser().getUserId();
//                user = userDao.getUser(userId);



//                List<UserProducts> userProducts = userProductsDao.getAllUsProdByRequiredUserId(user);
//                request.setAttribute("userProductsesNew", userProducts);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect("/cart");
        }

    }
}
