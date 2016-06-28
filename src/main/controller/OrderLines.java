package controller;

import dao.UserDao;
import fabric.Fabric;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import table.UserProducts;
import util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nikita on 27.06.16.
 */
@WebServlet("/cart/order_lines")
public class OrderLines extends Forward {

    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private String forwardString = "/orderLines.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userLogin = session.getAttribute("userLogin").toString();
        try {
            String userName = userDao.getUserByLogin(userLogin).getFirstName();
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Criteria criteria = HibernateUtil.getSessionFactory().openSession().createCriteria(UserProducts.class)
                    .add(Restrictions.eq("order.orderId", orderId));
            List<UserProducts> orderLines = criteria.list();
            request.setAttribute("orderLines", orderLines);
            session.setAttribute("userName", userName);
            super.forward(forwardString, request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
