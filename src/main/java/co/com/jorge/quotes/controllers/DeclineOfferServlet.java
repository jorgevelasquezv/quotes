package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.Offer;
import co.com.jorge.quotes.services.OfferService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/decline")
public class DeclineOfferServlet extends HttpServlet {

    @Inject
    private OfferService offerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long idOffer = Long.valueOf(req.getParameterNames().nextElement());

        Offer offer = offerService.findById(idOffer);
        offer.setState("declined");
        offerService.save(offer);

        getServletContext().getRequestDispatcher("/admin/offers").forward(req, resp);
    }
}
