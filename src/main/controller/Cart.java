package controller;

import dao.UserDao;
import dao.UserProductsDao;
import fabric.Fabric;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
import java.util.List;

/**
 * Created by nikita on 18.05.16.
 */
@SuppressWarnings("Since15")
@WebServlet("/cart")
public class Cart extends Forward {

    private static String SHOW_PURCHASE = "/showPurchase.jsp";
    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private UserProductsDao userProductsDao = fabric.getUserProductsDao();
    private User user = new User();
    String forward;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateUtil.getSessionFactory();
        HttpSession session = request.getSession();

        String userLogin = session.getAttribute("userLogin").toString();
        try {
            user = userDao.getUserByLogin(userLogin);
            int userId = user.getUserId();
            Criteria criteria = HibernateUtil.getSessionFactory().openSession().createCriteria(UserProducts.class)
                    .add(Restrictions.eq("user.userId", userId))
                    .add(Restrictions.isNull("order.orderId"));
            List<UserProducts> userProducts = criteria.list();
            request.setAttribute("userProductsesNew", userProducts);
            session.setAttribute("userName", user.getFirstName());


            List<UserProducts> userProductsOrder = userProductsDao.getAllUsProdByRequiredUserId(user);
            request.setAttribute("userProductsOrder", userProductsOrder);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        forward = SHOW_PURCHASE;


        super.forward(forward, request, response);
    }

}

