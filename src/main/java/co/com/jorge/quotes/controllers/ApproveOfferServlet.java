package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.Offer;
import co.com.jorge.quotes.models.Product;
import co.com.jorge.quotes.models.RequestProduct;
import co.com.jorge.quotes.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/admin/approve")
public class ApproveOfferServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        Long idOffer = Long.valueOf(req.getParameterNames().nextElement());

        OfferService offerService = new OfferServiceImpl(conn);
        Offer offer = offerService.findById(idOffer);
        offer.setState("approved");
        offerService.save(offer);

        RequestProductService requestProductService = new RequestProductServiceImpl(conn);
        RequestProduct requestProduct = requestProductService.findById(offer.getIdRequest());
        requestProduct.setState(false);
        requestProductService.save(requestProduct);

        ProductService productService = new ProductServiceImpl(conn);
        Product product = productService.findById(requestProduct.getProduct().getIdProduct());
        product.setStock(product.getStock() + requestProduct.getQuantity());
        productService.save(product);

        getServletContext().getRequestDispatcher("/admin/offers").forward(req, resp);

    }
}
