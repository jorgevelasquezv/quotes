package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.*;
import co.com.jorge.quotes.services.CatalogService;
import co.com.jorge.quotes.services.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/login-admin", "/login-provider"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        Connection conn = (Connection) req.getAttribute("conn");

        Service service = new CatalogService(conn);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");

        session.setAttribute("username", username );
        session.setAttribute("password", password );

        String path = req.getServletPath();
        Boolean isAdmin = path.endsWith("/login-admin");
        if (isAdmin){
            Admin admin = service.adminFindByUsername(username);
            List<Product> productList = service.productFindAll();
            List<Offer> offerList = service.offertFindAll();
            if (admin.getPassword().equals(password) ){
                session.setAttribute("rol", "admin");
                session.setAttribute("stock", productList);
                session.setAttribute("offers", offerList);
                resp.sendRedirect(req.getContextPath() + "/request.jsp");
            }else {
                resp.sendRedirect(req.getContextPath() + "/login-admin");
            }
        }
        Boolean isProvider = path.endsWith("/login-provider");
        if(isProvider){
            Provider provider = service.providerFindByUsername(username);
            if (provider.getPassword().equals(password)){
                session.setAttribute("rol", "provider");
                List<RequestProduct> requestProductList = service.requestProductFindAll();
                session.setAttribute("requests", requestProductList);
                resp.sendRedirect(req.getContextPath() + "/request.jsp");
            }else {
                resp.sendRedirect(req.getContextPath() + "/login-provider");
            }
        }


    }
}
