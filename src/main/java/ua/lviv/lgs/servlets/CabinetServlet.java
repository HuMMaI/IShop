package ua.lviv.lgs.servlets;

import org.apache.commons.lang3.ObjectUtils;
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
            int id = user.get().getId();
            String firstName = user.get().getFirstName();
            String lastName = user.get().getLastName();
            String role = user.get().getRole();
            String username = user.get().getUsername() == null ? "" : user.get().getUsername();
            String bio = user.get().getBio() == null ? "" : user.get().getBio();
            String phoneNumber = user.get().getPhoneNumber() == null ? "" : user.get().getPhoneNumber();
            String address = user.get().getAddress() == null ? "" : user.get().getAddress();
            String age = user.get().getAge() == 0 ? "" : String.valueOf(user.get().getAge());
            String gender = user.get().getGender() == null ? "" : user.get().getGender();
            String profession = user.get().getProfession() == null ? "" : user.get().getProfession();

            req.getSession().setAttribute("userId", id);
            req.getSession().setAttribute("firstName", firstName);
            req.getSession().setAttribute("lastName", lastName);
            req.getSession().setAttribute("role", role);
            req.getSession().setAttribute("username", username);
            req.getSession().setAttribute("bio", bio);
            req.getSession().setAttribute("phoneNumber", phoneNumber);
            req.getSession().setAttribute("address", address);
            req.getSession().setAttribute("age", age);
            req.getSession().setAttribute("gender", gender);
            req.getSession().setAttribute("profession", profession);
        }

        req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String username = req.getParameter("username");
        String bio = req.getParameter("bio");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String profession = req.getParameter("profession");

        Optional<User> user = userService.getByEmail(req.getSession().getAttribute("email").toString());

        if (!user.isPresent()){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int id = user.get().getId();

        if (ObjectUtils.allNotNull(firstName, lastName, username, bio, phoneNumber, address, email, age, gender, profession)){
            userService.update(id, firstName, lastName, username, bio, phoneNumber, address, email, age, gender, profession);
        }
    }
}
