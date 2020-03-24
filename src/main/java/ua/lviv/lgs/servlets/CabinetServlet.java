package ua.lviv.lgs.servlets;

import ua.lviv.lgs.entities.User;
import ua.lviv.lgs.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/cabinet")
public class CabinetServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String email = session.getAttribute("email").toString();

        Optional<User> user = userService.getByEmail(email);

        if (user.isPresent()){
            String firstName = user.get().getFirstName();
            String lastName = user.get().getLastName();
            String role = user.get().getRole();

            req.getSession().setAttribute("firstName", firstName);
            req.getSession().setAttribute("lastName", lastName);
            req.getSession().setAttribute("role", role);
        }

        req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
    }
}
