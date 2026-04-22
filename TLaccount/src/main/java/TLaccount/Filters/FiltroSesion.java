package Bulonera.Filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class FiltroSesion implements Filter {
    //Metodo obligatorio de implementar en los filtros
   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    // Permitir preflight CORS
    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
        response.setStatus(HttpServletResponse.SC_OK);
        return; // No continuar con el filtro de sesión ni redireccionar
    }

    HttpSession session = request.getSession(false);

    String path = request.getRequestURI();
    // Excluir recursos estáticos
    if (path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".jpg")
            || path.endsWith(".jpeg") || path.endsWith(".woff2") || path.endsWith(".ttf")
            || path.contains("index.jsp") || path.contains("svLogin")) {

        chain.doFilter(request, response);
        return;
    }

    boolean loggedIn = (session != null && session.getAttribute("usValid") != null);
    String loginURI = request.getContextPath() + "/index.jsp";
    String loginServletURI = request.getContextPath() + "/svLogin";
    boolean loginRequest = request.getRequestURI().equals(loginURI) || request.getRequestURI().equals(loginServletURI);

    if (loggedIn || loginRequest) {
        chain.doFilter(request, response);
    } else {
        response.sendRedirect("index.jsp");
    }
}

    public void init(FilterConfig config) throws ServletException {}
    public void destroy() {}
    
}
