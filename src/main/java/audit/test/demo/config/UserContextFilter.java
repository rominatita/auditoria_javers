package audit.test.demo.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class UserContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String userIdFromHeaders = httpServletRequest.getHeader("X-User-Id");
        String userEmailFromHeaders = httpServletRequest.getHeader("Ip-Address");
        UserContextHolder.getContext().setUserName(userIdFromHeaders);
        UserContextHolder.getContext().setIpAddress(userEmailFromHeaders);

        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
