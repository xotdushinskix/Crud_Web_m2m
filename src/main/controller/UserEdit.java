package controller;

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
 * Created by nikita on 24.06.16.
 */
@WebServlet("/user_edit")
public class UserEdit extends Forward{

    private Fabric fabric = Fabric.getInstance();
    private UserDao userDao = fabric.getUserDao();
    private String USER_PAGE = "/userPage.jsp";
    private User user;
    private String userLogin;
    private String firstName;
    private String lastName;
    private String message;
    private int shopExperience;
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;



    private void userAttribute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("firstName", user.getFirstName());
        request.setAttribute("secondName", user.getLastName());
        request.setAttribute("userShopExperience", user.getShopExperience());
        request.setAttribute("userPassword", user.getPassword());
        super.forward(USER_PAGE, request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        userLogin = session.getAttribute("userLogin").toString();
        try {
            user = userDao.getUserByLogin(userLogin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (request.getParameter("newFN") != null) {
            firstName = request.getParameter("newFirstName");
            if (firstName.length() == 0) {
                message = "First name can not be empty";
                request.setAttribute("messageEmpty", message);
                userAttribute(request, response);
            } else {
                try {
                    user.setFirstName(firstName);
                    userDao.editUser(user);
                    session.setAttribute("userName", user.getFirstName());
                    message = "Name successfully updated";
                    request.setAttribute("message", message);
                    userAttribute(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else if (request.getParameter("newLN") != null) {
            lastName = request.getParameter("newLastName");
            if (lastName.length() == 0) {
                message = "Last name can not be empty";
                request.setAttribute("messageEmptyL", message);
                userAttribute(request, response);
            } else {
                try {
                    user.setLastName(lastName);
                    userDao.editUser(user);
                    message = "Last name successfully updated";
                    request.setAttribute("message1", message);
                    userAttribute(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else if (request.getParameter("newSE") != null) {
            try {
                shopExperience = Integer.parseInt(request.getParameter("newShopExperience"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                message = "Shopping experience can not be empty";
                request.setAttribute("messageSEE", message);
                userAttribute(request, response);
            }
            try {
                user.setShopExperience(shopExperience);
                userDao.editUser(user);
                message = "Shop experience successfully updated";
                request.setAttribute("message2", message);
                userAttribute(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (request.getParameter("newPassword") != null) {
            currentPassword = request.getParameter("currentPassword");
            if (user.getPassword().equals(currentPassword)) {
                newPassword = request.getParameter("newPassword");
                confirmNewPassword = request.getParameter("confirmNewPassword");
                if (newPassword.equals(confirmNewPassword)) {
                    user.setPassword(confirmNewPassword);
                    try {
                        userDao.editUser(user);
                        super.forward("/logout", request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    message = "Confirmed password doesn't equal new password";
                    request.setAttribute("message3", message);
                    userAttribute(request, response);
                }
            } else {
                message = "You entered invalid current password";
                request.setAttribute("message3", message);
                userAttribute(request, response);
            }
        }


    }
}
