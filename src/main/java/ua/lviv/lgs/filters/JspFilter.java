package ua.lviv.lgs.filters;

import ua.lviv.lgs.services.FilterService;

import javax.servlet.*;
import java.io.IOException;

public class JspFilter implements Filter {
    private FilterService filterService = FilterService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
