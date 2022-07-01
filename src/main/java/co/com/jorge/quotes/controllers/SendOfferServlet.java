package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.Offer;
import co.com.jorge.quotes.services.OfferService;
import co.com.jorge.quotes.services.OfferServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/provider/send-offer")
public class SendOfferServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Long idProvider = (Long) session.getAttribute("id");

        Connection conn = (Connection) req.getAttribute("conn");

        Long price = Long.valueOf(req.getParameter("price"));
        Long idRequest = Long.valueOf(req.getParameter("idRequest"));

        OfferService offerService = new OfferServiceImpl(conn);

        Offer offer = new Offer();
        offer.setIdProvider(idProvider);
        offer.setIdRequest(idRequest);
        offer.setPrice(price);
        offer.setState("slope");

        offerService.save(offer);

        getServletContext().getRequestDispatcher("/provider/request").forward(req, resp);

    }
}
