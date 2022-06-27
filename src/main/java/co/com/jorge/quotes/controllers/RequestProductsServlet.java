package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.Product;
import co.com.jorge.quotes.services.ProductService;
import co.com.jorge.quotes.services.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/admin/request-form")
public class RequestProductsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");

        ProductService productService = new ProductServiceImpl(conn);
        List<Product> productList = productService.find();
        req.setAttribute("stock", productList);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/requestProducts.jsp").forward(req, resp);

    }
}
