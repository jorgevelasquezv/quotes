package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.Product;
import co.com.jorge.quotes.models.RequestProduct;
import co.com.jorge.quotes.services.CatalogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestProduct requestProductProducts = new RequestProduct();

        Connection conn = (Connection) req.getAttribute("conn");
        CatalogService service = new CatalogService(conn);

        SimpleDateFormat formatDate= new SimpleDateFormat("yyyy-MM-dd");

        try {
            requestProductProducts.setInitialDate(formatDate.parse(req.getParameter("initialDate")));
            requestProductProducts.setFinalDate(formatDate.parse(req.getParameter("finalDate")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        requestProductProducts.setState(true);
        requestProductProducts.setQuantity(Integer.parseInt(req.getParameter("request")));

        Product product = new Product();

        product = service.productFindByName(req.getParameter("name"));

        requestProductProducts.setProduct(product);

        service.requestProduct(requestProductProducts);

        resp.sendRedirect(req.getContextPath() + "/request.jsp");

    }
}
