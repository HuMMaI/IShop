package ua.lviv.lgs.servlets.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.ObjectUtils;
import ua.lviv.lgs.entities.Bucket;
import ua.lviv.lgs.entities.Product;
import ua.lviv.lgs.services.BucketService;
import ua.lviv.lgs.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/api/product-catalog")
public class ProductCatalogController extends HttpServlet {
    private ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.getAll();

        if (products.isEmpty()){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < products.size(); i++){
            JsonObject obj = new JsonObject();

            obj.addProperty("id", products.get(i).getId());
            obj.addProperty("name", products.get(i).getName());
            obj.addProperty("description", products.get(i).getDescription());
            obj.addProperty("price", String.valueOf(products.get(i).getPrice()));

            jsonArray.add(obj);
        }

        String jsonElement = new Gson().toJsonTree(jsonArray).toString();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(jsonElement);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));

        if (ObjectUtils.allNotNull(name, description, price)){
            productService.insert(name, description, price);
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        resp.setContentType("text/plain");
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        Optional<Product> product = productService.getById(productId);

        if (product.isPresent()){
            BucketService bucketService = new BucketService();
            bucketService.deleteByProductId(productId);
            productService.delete(productId);
        } else {
            resp.getWriter().write("Error!!!");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
