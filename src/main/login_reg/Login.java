package login_reg;

import controller.Forward;
import dao.UserDao;
import fabric.Fabric;
import table.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by nikita on 23.06.16.
 */
@WebServlet("/products/login_error")
public class Login extends Forward {

    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private User user;
    private String LOGIN_PAGE = "/allUserProduct.jsp"; //"/login.jsp";
    private String LOGOUT_PAGE = "/logout.html";
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        String message;


        if (userLogin.length() == 0 & userPassword.length() == 0) {
            message = "Login and password fields cannot be empty";
            request.setAttribute("message", message);
            try {
                super.requestAction(request);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            super.forward(LOGIN_PAGE, request, response);
        }



        if (userLogin.length() == 0) {
            message = "Login can not be empty";
            request.setAttribute("message", message);
            try {
                super.requestAction(request);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            super.forward(LOGIN_PAGE, request, response);
        } else {
            if (userPassword.length() == 0) {
                message = "Password can not be empty";
                request.setAttribute("message", message);
                try {
                    super.requestAction(request);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                super.forward(LOGIN_PAGE, request, response);

            } else {
                try {
                    user = userDao.getUserByLogin(userLogin);
                    user.getLogin();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    message = "This customer doesn't register, login is invalid";
                    request.setAttribute("message", message);
                    try {
                        super.requestAction(request);
                    } catch (SQLException o) {
                        o.printStackTrace();
                    }

                    super.forward(LOGIN_PAGE, request, response);
                }

                try {
                    String customerPassword = user.getPassword();
                    if (customerPassword.equals(userPassword)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("userLogin", userLogin);
                        userLogin = session.getAttribute("userLogin").toString();
                        user = userDao.getUserByLogin(userLogin);
                        session.setAttribute("userName", user.getFirstName());
                        response.sendRedirect("/products");

                    } else {
                        message = "Your password is invalid";
                        request.setAttribute("message", message);
                        try {
                            super.requestAction(request);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        super.forward(LOGIN_PAGE, request, response);
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            super.requestAction(request);
            super.forward(LOGIN_PAGE, request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
