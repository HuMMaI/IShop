package ua.lviv.lgs.servlets.controllers;

import com.google.gson.Gson;
import ua.lviv.lgs.dtos.BucketProductDto;
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
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet("/api/buckets")
public class BucketController extends HttpServlet {
    BucketService bucketService = BucketService.getInstance();
    ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");

        List<Bucket> buckets = bucketService.getAllByUserId(userId);

        Set<Integer> productIds = buckets.stream()
                .map(Bucket::getProductId)
                .collect(Collectors.toSet());

        Map<Integer, Product> productsGroupById = productService.getByIds(productIds).stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        List<BucketProductDto> result = buckets.stream()
                .map(bucket -> {
                    BucketProductDto bucketProductDto = new BucketProductDto();
                    bucketProductDto.id = bucket.getId();
                    bucketProductDto.purchaseDate = bucket.getPurchaseDate();

                    int productId = bucket.getProductId();

                    bucketProductDto.product = productsGroupById.get(productId);

                    return bucketProductDto;
                })
                .collect(Collectors.toList());

        String jsonArray = new Gson().toJson(result);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonArray);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        int userId = (int) req.getSession().getAttribute("userId");

        bucketService.insert(userId, productId, new Date());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bucketId = Integer.parseInt(req.getParameter("bucketId"));
        int userId = (int)req.getSession().getAttribute("userId");
        Bucket bucket = bucketService.getById(bucketId).get();

        if (bucket.getUserId() == userId){
            bucketService.delete(bucketId);
        } else {
            resp.getWriter().write("Error!!!");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
