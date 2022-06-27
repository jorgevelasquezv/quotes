package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.*;
import co.com.jorge.quotes.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet({ "/login", "/login/admin", "/login/provider"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        Connection conn = (Connection) req.getAttribute("conn");


        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");

        session.setAttribute("username", username );
        session.setAttribute("password", password );

        String path = req.getServletPath();
        Boolean isAdmin = path.endsWith("/admin");
        if (isAdmin){
            AdminService adminService = new AdminServiceImpl(conn);
            Admin admin;
            admin = adminService.findByUsername(username);
            if (admin.getPassword().equals(password) ){
                session.setAttribute("rol", "admin");
                getServletContext().getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
            }else {
                resp.sendRedirect(req.getContextPath() + "/admin");
            }
        }
        Boolean isProvider = path.endsWith("/provider");
        if(isProvider){
            ProviderService providerService = new ProviderServiceImpl(conn);
            Provider provider = providerService.findByName(username);
            if (provider.getPassword().equals(password)){
                session.setAttribute("rol", "provider");
                getServletContext().getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
            }else {
                resp.sendRedirect(req.getContextPath() + "/provider");
            }
        }
    }
}
