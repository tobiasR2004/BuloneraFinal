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
        HttpSession session = request.getSession(false);
        
        String path = request.getRequestURI();
        // Excluís recursos estáticos
        if (path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".jpg")
                || path.endsWith(".jpeg") || path.endsWith(".woff2") || path.endsWith(".ttf")
                || path.contains("index.jsp") || path.contains("svLogin")) {

            chain.doFilter(request, response);
            return;
        }
        
        //Logica de que si la sesion es nula y no hay usuario logeado entonces me rediriga al inicio de sesion
        boolean logueado = (session != null && session.getAttribute("usuarioLogueado") != null);
        String loginURI = request.getContextPath() + "/index.jsp";
        String loginServletURI = request.getContextPath() + "/svLogin";

        // Permitir acceso libre a login y recursos estáticos
        boolean loggedIn = (session != null && session.getAttribute("usValid") != null);
        boolean loginRequest = request.getRequestURI().equals(loginURI) || request.getRequestURI().equals(loginServletURI);

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response); // usuario válido o accediendo al login
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {}
    public void destroy() {}
    
}
