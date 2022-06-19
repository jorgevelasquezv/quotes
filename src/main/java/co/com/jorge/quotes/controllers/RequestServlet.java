package co.com.jorge.quotes.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (PrintWriter out = resp.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <meta charset='UTF-8'/>");
            out.println("       <title>Login Correcto</title> ");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Login Correcto!</h1>");
            out.println("       <h3>User " + username+ "</h3>");
            out.println("       <h3>Password " + password + "</h3>");
            out.println("   </body>" );
            out.println("</htnl>" );
        }
    }
}
