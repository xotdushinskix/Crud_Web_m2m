package controller;

import dao.ProductDao;
import dao.UserDao;
import dao.UserProductsDao;
import fabric.Fabric;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
import java.util.List;

/**
 * Created by nikita on 18.05.16.
 */
@SuppressWarnings("Since15")
@WebServlet("/cart")
public class Cart extends Forward {

    private static String SHOW_ALL = "/allUserProduct.jsp";
    private static String ADD_EDIT_USER_PAGE = "/editAndAddUser.jsp";
    private static String ADD_EDIT_PRODUCT_PAGE = "/editAndADDProduct.jsp";
    private static String MAKE_PURCHASE = "/makePurchase.jsp";
    private static String SHOW_PURCHASE = "/showPurchase.jsp";
    private static String CHANGE_ORDER = "/changeProdQuantityInOrder.jsp";
    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private ProductDao productDao = fabric.getProductDao();
    private UserProductsDao userProductsDao = fabric.getUserProductsDao();
    private User user = new User();
    private Product product = new Product();
    private UserProducts userProducts = new UserProducts();


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
            List<UserProducts>userProducts = criteria.list();
            request.setAttribute("userProductsesNew", userProducts);
            request.setAttribute("userId", userId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.forward(SHOW_PURCHASE, request, response);







//        if (action.equals("purchase")){
//            int productId = Integer.parseInt(request.getParameter("productId"));
//            try {
//                product = productDao.getProduct(productId);
//                request.setAttribute("product", product);
//                forwardString = MAKE_PURCHASE;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        } else if (action.equals("watchUserPurchases")) {
//            int userId = Integer.parseInt(request.getParameter("userId"));
//            try {
//                user = userDao.getUser(userId);
//                Criteria criteria = HibernateUtil.getSessionFactory().openSession().createCriteria(UserProducts.class)
//                        .add(Restrictions.eq("user.userId", userId))
//                        .add(Restrictions.isNull("order.orderId"));
//                List<UserProducts>userProducts = criteria.list();
//                request.setAttribute("userProductsesNew", userProducts);
//                request.setAttribute("userId", userId);
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            forwardString = SHOW_PURCHASE;
//
//        } else if (action.equals("deletePurchQuantity")) {
//            int userProductsId = Integer.parseInt(request.getParameter("userProductsId"));
//
//            int boughtQuantityForDelete = 0;
//            try {
//                boughtQuantityForDelete = userProductsDao.getUserProducts(userProductsId).getBoughtQuantity();
//                int productIdForEdit = userProductsDao.getUserProducts(userProductsId).getProduct().getProductId();
//                product = productDao.getProduct(productIdForEdit);
//                int stock = product.getProductStock();
//                product.setProductStock(stock + boughtQuantityForDelete);
//                productDao.editProduct(product);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                userProducts = userProductsDao.getUserProducts(userProductsId);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            int userId = userProducts.getUser().getUserId();
//            int prodId = userProducts.getProduct().getProductId();
//            try {
//                user = userDao.getUser(userId);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                product = productDao.getProduct(prodId);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            user.getUserProducts().clear();
//            product.getUserProducts().clear();
//            try {
//                userDao.editUser(user);
//                productDao.editProduct(product);
//                userProductsDao.deleteUserProducts(userProducts);
//                List<UserProducts>userProducts = userProductsDao.getAllUsProdByRequiredUserId(user);
//                request.setAttribute("userProductsesNew", userProducts);
//                forwardString = SHOW_PURCHASE;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } else if(action.equals("updatePurchQuantity")) {
//            int userProductsId = Integer.parseInt(request.getParameter("userProductsId"));
//            try {
//                int productIdForEditQuantity = userProductsDao.getUserProducts(userProductsId).getProduct().getProductId();
//                product = productDao.getProduct(productIdForEditQuantity);
//                int prodQuantityInOrderBeforeEdit = userProductsDao.getUserProducts(userProductsId).getBoughtQuantity();
//                request.setAttribute("product", product);
//                request.setAttribute("prodQuantityInOrderBeforeEdit", prodQuantityInOrderBeforeEdit);
//                request.setAttribute("userId", userProductsDao.getUserProducts(userProductsId).getUser().getUserId());
//                request.setAttribute("userProductsId", userProductsId);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            forwardString = CHANGE_ORDER;
//        }
//
//        super.forward(forwardString, request, response);
    }

}

