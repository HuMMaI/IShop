package ua.lviv.lgs.servlets;

import org.apache.commons.lang3.ObjectUtils;
import ua.lviv.lgs.entities.User;
import ua.lviv.lgs.entities.UserRole;
import ua.lviv.lgs.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Optional<User> user = userService.getByEmail(email);

        if (ObjectUtils.allNotNull(firstName, lastName, email, password) && !user.isPresent()) {
            userService.insert(email, firstName, lastName, UserRole.USER.toString(), password);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            return;
        }

        resp.setContentType("text/plain");
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
