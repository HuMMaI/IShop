package ua.lviv.lgs.servlets;

import org.apache.commons.lang3.ObjectUtils;
import ua.lviv.lgs.entities.User;
import ua.lviv.lgs.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (!ObjectUtils.allNotNull(email, password)){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

            Optional<User> user = userService.getByEmailAndPassword(email, password);

        if (user.isPresent()){
            req.getSession().setAttribute("email", email);
            resp.setStatus(HttpServletResponse.SC_OK);

            return;
        }
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
