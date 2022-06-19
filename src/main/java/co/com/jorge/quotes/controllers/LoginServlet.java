package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.Admin;
import co.com.jorge.quotes.services.CatalogService;
import co.com.jorge.quotes.services.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/login-admin", "/login-provider"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Service service = new CatalogService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");

        String path = req.getServletPath();
        Boolean isAdmin = path.endsWith("/login-admin");
        if (isAdmin){
            resp.sendRedirect(req.getContextPath() + "/login.html");
//            getServletContext().getRequestDispatcher("/login.html");
//            Admin admin = service.adminFindByUsername(username);
//            if (admin.getPassword().equals(password) ){
//                getServletContext().getRequestDispatcher("/request").forward(req, resp);
//            }else {
//                resp.sendRedirect(req.getContextPath() + "/login.html");
//            }
        }
        Boolean isProvider = path.endsWith("/login-provider");
        if(isProvider){
            resp.sendRedirect(req.getContextPath());

        }


    }
    {}}
