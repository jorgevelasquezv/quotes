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
import java.util.List;

@WebServlet("/provider/approved")
public class ApprovedOffersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        HttpSession session = req.getSession(true);
        Long id = (Long) session.getAttribute("id");

        OfferService offerService = new OfferServiceImpl(conn);
        List<Offer> offerList = offerService.find().stream()
                .filter(offer -> offer.getState().equals("approved") && offer.getIdProvider() == id).toList();
        req.setAttribute("offers", offerList);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/checkApproved.jsp").forward(req, resp);
    }
}
