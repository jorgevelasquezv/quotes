package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.RequestProduct;
import co.com.jorge.quotes.services.RequestProductService;
import co.com.jorge.quotes.services.RequestProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet({"/provider/request", "/admin/check-request"})
public class CheckRequestProductsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        RequestProductService requestProductService = new RequestProductServiceImpl(conn);
        List<RequestProduct> requestProductList = requestProductService.find();
        req.setAttribute("requests", requestProductList);

        getServletContext().getRequestDispatcher("/WEB-INF/pages/checkRequest.jsp").forward(req, resp);
    }
}
