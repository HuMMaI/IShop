package ua.lviv.lgs.servlets;

import ua.lviv.lgs.services.BucketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/api/buckets")
public class BucketController extends HttpServlet {
    BucketService bucketService = BucketService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        int userId = (int)req.getSession().getAttribute("userId");

        bucketService.insert(userId, productId, new Date());
    }
}
