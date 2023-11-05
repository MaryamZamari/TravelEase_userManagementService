package com.easytravel.userManagementService.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class RoleLoggingFilter extends GenericFilterBean {
/***
 * the purpose of this Filter is because I wanted to provide a better user experience, so if a user with wrong ROLE
 * tries the wrong endpoint, they would get some feedback so it won't take them too much time to look for what is
 * causing error.
 * In this case, the functionality is managed only for ADMIN Endpoints.
 */

    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */

    private static final Logger logger= LoggerFactory.getLogger(RoleLoggingFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        if (authentication != null) {
            String requestPath = httpRequest.getRequestURI();
            boolean hasAdminRole = httpRequest.isUserInRole("admin");
            //TODO: Check later why it is not showing in console
            logger.info("RoleLoggingFilter: Checking if the requesting user's role matches the role required by the Endpoint...");
            if (requestPath.contains("admin") && !hasAdminRole) {
                logger.info("Access denied: Role check failed. Only Admin Role can access this Endpoint!");
                throw new AccessDeniedException("Access denied: Role check failed for " + requestPath + ". Only Admin Role can access this Endpoint!");

            }
        }
        filterChain.doFilter(httpRequest, httpResponse);
    }
}