package ua.lviv.lgs.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session != null){
            session.invalidate();
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
