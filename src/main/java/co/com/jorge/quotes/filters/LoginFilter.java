package co.com.jorge.quotes.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/admin/*", "/provider/*"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession(true);
        String  rol = (String) session.getAttribute("rol");
        if (rol == null){
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "You are not authorized to access this page!");
        }else {
            chain.doFilter(request, response);
        }

    }
}
