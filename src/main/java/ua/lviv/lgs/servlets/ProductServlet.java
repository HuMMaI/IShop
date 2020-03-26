package ua.lviv.lgs.servlets;

import ua.lviv.lgs.entities.Product;
import ua.lviv.lgs.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    ProductService productService = ProductService.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");
        Optional<Product> product = productService.getById(Integer.parseInt(productId));

        if (product.isPresent()){
            req.setAttribute("productName", product.get().getName());
            req.setAttribute("productDescription", product.get().getDescription());
            req.setAttribute("productPrice", product.get().getPrice());
            req.setAttribute("productId", product.get().getId());

            req.getRequestDispatcher("single_product.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("product_page.jsp").forward(req, resp);
    }
}
