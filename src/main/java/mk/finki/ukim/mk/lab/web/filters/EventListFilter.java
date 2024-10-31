package mk.finki.ukim.mk.lab.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.EventBooking;

import java.io.IOException;


@WebFilter(filterName = "auth-filter", urlPatterns = "/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        initParams = {
                @WebInitParam(name = "ignore-path", value = ""),
        })

public class EventListFilter implements Filter {
    private String ignorePath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ignorePath = filterConfig.getInitParameter("ignore-path");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        EventBooking booking = (EventBooking) req.getSession().getAttribute("eventBooking");

        String path = req.getServletPath();

        if (path.startsWith(ignorePath) || booking != null || path.equals("")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.sendRedirect("/");
        }

    }
}
