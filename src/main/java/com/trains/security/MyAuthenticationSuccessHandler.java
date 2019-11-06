package com.trains.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class MyAuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            if (authority.getAuthority().equals("passenger")) {
                try {
                    redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/pass/");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (authority.getAuthority().equals("employee")) {
                try {
                    redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/empl/");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
