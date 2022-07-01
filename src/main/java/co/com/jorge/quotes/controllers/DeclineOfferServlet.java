package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.Offer;
import co.com.jorge.quotes.models.RequestProduct;
import co.com.jorge.quotes.services.OfferService;
import co.com.jorge.quotes.services.OfferServiceImpl;
import co.com.jorge.quotes.services.RequestProductService;
import co.com.jorge.quotes.services.RequestProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/admin/decline")
public class DeclineOfferServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        Long idOffer = Long.valueOf(req.getParameterNames().nextElement());

        OfferService offerService = new OfferServiceImpl(conn);
        Offer offer = offerService.findById(idOffer);
        offer.setState("declined");
        offerService.save(offer);

        RequestProductService requestProductService = new RequestProductServiceImpl(conn);
        RequestProduct requestProduct = requestProductService.findById(offer.getIdRequest());
        requestProduct.setState(false);

        requestProductService.save(requestProduct);

        getServletContext().getRequestDispatcher("/admin/offers").forward(req, resp);
    }
}
