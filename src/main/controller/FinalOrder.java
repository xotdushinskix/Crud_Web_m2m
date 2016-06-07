package controller;

import dao.OrderDao;
import dao.UserProductsDao;
import fabric.Fabric;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import table.Order;
import table.UserProducts;
import util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by FromxSoul on 06.06.2016.
 */
@WebServlet("/FinalOrder")
public class FinalOrder extends Forward{
    private Order order;
    private String SHOW_ORDER = "/showOrder.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateUtil.getSessionFactory();

        Fabric fabric = Fabric.getInstance();
        UserProductsDao userProductsDao = fabric.getUserProductsDao();
        OrderDao orderDao = fabric.getOrderDao();

        int userId = Integer.parseInt(request.getParameter("userId"));
        try {
            Criteria criteria = HibernateUtil.getSessionFactory().openSession().createCriteria(UserProducts.class)
                    .add(Restrictions.eq("user.userId", userId))
                    .add(Restrictions.isNull("order.orderId"));
            List<UserProducts>userProducts = criteria.list();
            order = new Order();
            order.setUserProducts(userProducts);
            orderDao.addOrder(order);
            for (UserProducts userProducts1 : userProducts) {
                userProducts1.setOrder(order);
                userProductsDao.editUserProducts(userProducts1);
            }
            int orderId = order.getOrderId();
            System.out.println(orderId);
            request.setAttribute("orderId", orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.forward(SHOW_ORDER, request, response);

    }
}
