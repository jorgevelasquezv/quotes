package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.*;
import co.com.jorge.quotes.services.*;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet({ "/login", "/login/admin", "/login/provider"})
public class LoginServlet extends HttpServlet {

    @Inject
    private AdminService adminService;

    @Inject
    private ProviderService providerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");

        session.setAttribute("username", username );
        session.setAttribute("password", password );

        String path = req.getServletPath();
        Boolean isAdmin = path.endsWith("/admin");
        if (isAdmin){
            Admin admin;
            admin = adminService.findByUsername(username);
            if (admin != null && admin.getPassword().equals(password) ){
                session.setAttribute("rol", "admin");
                getServletContext().getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
            }else {
                resp.sendRedirect(getServletContext().getContextPath() + "/login-form?login=Admin");
            }
        }
        Boolean isProvider = path.endsWith("/provider");
        if(isProvider){
            Provider provider = providerService.findByName(username);
            if (provider != null && provider.getPassword().equals(password)){
                session.setAttribute("rol", "provider");
                session.setAttribute("id", provider.getIdProvider());
                getServletContext().getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
            }else {
                resp.sendRedirect(getServletContext().getContextPath() + "/login-form?login=Provider");
            }
        }
    }
}
