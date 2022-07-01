package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.Product;
import co.com.jorge.quotes.models.RequestProduct;
import co.com.jorge.quotes.services.*;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/admin/request")
public class SendRequestServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Inject
    private RequestProductService requestProductService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestProduct requestProduct = new RequestProduct();
        Product product;

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

        try {
            requestProduct.setInitialDate(formatDate.parse(req.getParameter("initialDate")));
            requestProduct.setFinalDate(formatDate.parse(req.getParameter("finalDate")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        requestProduct.setState(true);
        requestProduct.setQuantity(Integer.parseInt(req.getParameter("request")));

        product = productService.findByName(req.getParameter("name"));

        requestProduct.setProduct(product);

        requestProductService.save(requestProduct);

        getServletContext().getRequestDispatcher("/admin/check-request").forward(req, resp);

    }
}
