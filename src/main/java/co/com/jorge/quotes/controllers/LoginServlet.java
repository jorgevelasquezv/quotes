package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.Admin;
import co.com.jorge.quotes.models.Provider;
import co.com.jorge.quotes.services.CatalogService;
import co.com.jorge.quotes.services.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/login-admin", "/login-provider"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        Service service = new CatalogService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");

        session.setAttribute("username", username );
        session.setAttribute("password", password );

        String path = req.getServletPath();
        Boolean isAdmin = path.endsWith("/login-admin");
        if (isAdmin){
            Admin admin = service.adminFindByUsername(username);
            if (admin.getPassword().equals(password) ){
                session.setAttribute("rol", "admin");
                resp.sendRedirect(req.getContextPath() + "/admin.jsp");
            }else {
                resp.sendRedirect(req.getContextPath() + "/login-admin");
            }
        }
        Boolean isProvider = path.endsWith("/login-provider");
        if(isProvider){
            Provider provider = service.providerFindByUsername(username);
            if (provider.getPassword().equals(password)){
                session.setAttribute("rol", "provider");
                resp.sendRedirect(req.getContextPath() + "/admin.jsp");
            }else {
                resp.sendRedirect(req.getContextPath() + "/login-provider");
            }
        }


    }
    {}}
