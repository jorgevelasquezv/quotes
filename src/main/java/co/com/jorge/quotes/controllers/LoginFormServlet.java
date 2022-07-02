package co.com.jorge.quotes.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login-form")
public class LoginFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login =  req.getParameter("login");
        if (req.getAttribute("login") == null ) {
            req.setAttribute("login", login);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login =  req.getParameter("login");
        if (req.getAttribute("login") == null ) {
            req.setAttribute("login", login);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

}
