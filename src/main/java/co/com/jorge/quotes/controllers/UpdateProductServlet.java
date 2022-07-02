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

@WebServlet("/admin/stock/update")
public class UpdateProductServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer stock = Integer.valueOf(req.getParameter("stock"));
        Long price = Long.valueOf(req.getParameter("price"));
        Long id = Long.valueOf(req.getParameter("id"));
        Product product = productService.findById(id);
        product.setStock(stock);
        product.setPrice(price);
        System.out.println(product.toString());
        productService.save(product);

        getServletContext().getRequestDispatcher("/admin/stock").forward(req, resp);
    }
}
