package login_reg;

import controller.Forward;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by nikita on 23.06.16.
 */
@WebServlet("/logout")
public class LogOut extends Forward {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.getAttribute("userLogin");
        session.invalidate();
        response.sendRedirect("/products");
    }
}
