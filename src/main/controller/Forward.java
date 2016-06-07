package controller;

import dao.ProductDao;
import dao.UserDao;
import dao.UserProductsDao;
import fabric.Fabric;
import table.UserProducts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 18.05.16.
 */
public class Forward extends HttpServlet {
    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private ProductDao productDao = fabric.getProductDao();
    private UserProductsDao userProductsDao = fabric.getUserProductsDao();

    protected void forward(String to, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(to);
        requestDispatcher.forward(request, response);
    }

    protected void requestAction(HttpServletRequest request) throws ServletException, IOException, SQLException {
        request.setAttribute("users", userDao.getAllUsers());
        request.setAttribute("products", productDao.getAllProducts());
    }


//    protected void showRequireInfoAfterDeleteOrder(HttpServletRequest request) throws ServletException {
//        int userId = Integer.parseInt(request.getParameter("userId"));
//        List<UserProducts> userProductsesAll = null;
//        try {
//            userProductsesAll = userProductsDao.getAllUserProducts();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        List<UserProducts> userProductsesNew = new ArrayList<UserProducts>();
//        for (UserProducts userProducts : userProductsesAll) {
//            int userIdInUserProductsList = userProducts.getUser().getUserId();
//            if (userId == userIdInUserProductsList) {
//                int userProdID = userProducts.getUserProductsId();
//                UserProducts userProducts1 = null;
//                try {
//                    userProducts1 = userProductsDao.getUserProducts(userProdID);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                userProductsesNew.add(userProducts1);
//            }
//        }
//        request.setAttribute("userProductsesNew", userProductsesNew);
//    }



}
