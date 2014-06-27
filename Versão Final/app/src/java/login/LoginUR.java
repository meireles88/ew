package login;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managedBeans.UserSession;

@WebFilter("/faces/uregistado/*")
public class LoginUR implements Filter {

    // Constants ----------------------------------------------------------------------------------
    private static final String MANAGED_BEAN_NAME = "userSession";
    private static final String COOKIE_NAME = "LoginFilter.cookieId";
    private static final int COOKIE_MAX_AGE = 31536000; // 60*60*24*365 seconds; 1 year.

    @Override
    public void init(FilterConfig config) throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        if (session == null) {
            session = request.getSession(true);
        }

        UserSession sessaoUtilizador = (UserSession) session.getAttribute(MANAGED_BEAN_NAME);
        String cookieId = getCookieValue(request, COOKIE_NAME);

        if (cookieId == null) {
            cookieId = UUID.randomUUID().toString();
            setCookieValue(response, COOKIE_NAME, cookieId, COOKIE_MAX_AGE);
        }

        try {
            if (sessaoUtilizador.getCookieId() == null) {
                sessaoUtilizador.setCookieId(cookieId);
                session.setAttribute(MANAGED_BEAN_NAME, sessaoUtilizador);
            }
        } catch (java.lang.Exception e) {
        }
        
        if (sessaoUtilizador != null && sessaoUtilizador.isLoggedIn()) {
            
            if(sessaoUtilizador.getUser().getGroupOid().getGroupname().equals("URegistado"))
                chain.doFilter(req, res); // Logged-in user found, so just continue request.
            else
                response.sendRedirect(request.getContextPath() + "/faces/404");
            
            
        } else {

            if (sessaoUtilizador == null) {
                // No ID found in cookie, or no UserSession found in DB.
                // Create new UserSession.
                cookieId = UUID.randomUUID().toString();
                sessaoUtilizador = new UserSession(cookieId);

                // Put ID in cookie.
                setCookieValue(response, COOKIE_NAME, cookieId, COOKIE_MAX_AGE);
                session.setAttribute(MANAGED_BEAN_NAME, sessaoUtilizador);
            }

            response.sendRedirect(request.getContextPath() + "/faces/login.xhtml"); // No logged-in user found, so redirect to login page.
        }
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }

    // Helpers (may be refactored to some utility class) ------------------------------------------
    /**
     * Retrieve the cookie value from the given servlet request based on the
     * given cookie name.
     *
     * @param request The HttpServletRequest to be used.
     * @param name The cookie name to retrieve the value for.
     * @return The cookie value associated with the given cookie name.
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Set the cookie value in the given servlet response based on the given
     * cookie name and expiration interval.
     *
     * @param response The HttpServletResponse to be used.
     * @param name The cookie name to associate the cookie value with.
     * @param value The actual cookie value to be set in the given servlet
     * response.
     * @param maxAge The expiration interval in seconds. If this is set to 0,
     * then the cookie will immediately expire.
     */
    public static void setCookieValue(
            HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
}
