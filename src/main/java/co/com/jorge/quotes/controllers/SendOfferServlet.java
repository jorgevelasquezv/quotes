package co.com.jorge.quotes.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/provider/send-offer")
public class SendOfferServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        getServletContext().getRequestDispatcher("/WEB-INF/pages/checkRequest.jsp").forward(req, resp);

    }
}
