package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.Product;
import co.com.jorge.quotes.services.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/request-form")
public class RequestProductsServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> productList = productService.find();
        req.setAttribute("stock", productList);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/requestProducts.jsp").forward(req, resp);

    }
}
