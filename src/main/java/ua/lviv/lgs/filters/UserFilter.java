package ua.lviv.lgs.filters;

import ua.lviv.lgs.entities.UserRole;
import ua.lviv.lgs.services.FilterService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;

@WebFilter({"/products", "/bucket", "/single-product", "/cabinet"})
public class UserFilter implements Filter {
    private FilterService filterService = FilterService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterService.doFilterValidation(servletRequest, servletResponse, filterChain, Arrays.asList(UserRole.USER, UserRole.ADMIN));
    }

    @Override
    public void destroy() {

    }
}
