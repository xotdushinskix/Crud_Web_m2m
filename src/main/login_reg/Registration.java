package login_reg;

import controller.Forward;
import dao.UserDao;
import fabric.Fabric;
import table.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by FromxSoul on 25.06.2016.
 */
@WebServlet("/registration")
public class Registration extends Forward {
    private String REGISTRATION_PAGE = "/registration.jsp";
    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private User user;
    private String message;
    private String SUCCESS_REG = "/success_reg.jsp";
    private int shopExperience;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.forward(REGISTRATION_PAGE, request, response);
        response.sendRedirect("/registration");
    }


    private void emptyFieldsCannotBe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        message = "Any of fields can not be empty";
        request.setAttribute("messageEmpty", message);
        super.forward(REGISTRATION_PAGE, request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        try {
            shopExperience = Integer.parseInt(request.getParameter("shop_exp"));
        } catch (NumberFormatException e) {
            emptyFieldsCannotBe(request, response);
        }
        shopExperience = Integer.parseInt(request.getParameter("shop_exp"));
        String eMail = request.getParameter("email_password");
        String password = request.getParameter("password");


        if (firstName.length() == 0 || lastName.length() == 0 || eMail.length() == 0 || password.length() == 0) {
            emptyFieldsCannotBe(request, response);

        } else {
            try {
                user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setShopExperience(shopExperience);
                user.setLogin(eMail);
                user.setPassword(password);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            try {
                userDao.addUser(user);
                request.setAttribute("firstName", user.getFirstName());
                super.forward(SUCCESS_REG, request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
