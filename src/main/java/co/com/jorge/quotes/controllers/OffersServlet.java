package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.Offer;
import co.com.jorge.quotes.services.OfferService;
import co.com.jorge.quotes.services.OfferServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/admin/offers")
public class OffersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        OfferService offerService = new OfferServiceImpl(conn);
        List<Offer> offerList = offerService.find();
        req.setAttribute("offers", offerList);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/checkOffers.jsp").forward(req, resp);
    }
}
