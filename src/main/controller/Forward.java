package controller;

import dao.ProductDao;
import dao.UserDao;
import dao.UserProductsDao;
import fabric.Fabric;
import table.User;
import table.UserProducts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 18.05.16.
 */
public class Forward extends HttpServlet {
    private Fabric fabric = Fabric.getInstance();
    private ProductDao productDao = fabric.getProductDao();
    protected User user;

    protected void forward(String to, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(to);
        requestDispatcher.forward(request, response);
    }


    protected void requestAction(HttpServletRequest request) throws ServletException, IOException, SQLException {
        request.setAttribute("products", productDao.getAllProducts());
    }

}
