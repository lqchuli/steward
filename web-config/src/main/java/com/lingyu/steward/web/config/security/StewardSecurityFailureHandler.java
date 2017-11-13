package com.lingyu.steward.web.config.security;

import lombok.Setter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 权限校验失败处理器
 *
 * @author allan
 * @date 13/11/2017
 */
@Setter
public class StewardSecurityFailureHandler implements AuthenticationFailureHandler {
    private static final String USERNAME_NOT_FOUND = "该帐号不存在";
    private static final String BAD_CREDENTIALS_MSG = "用户名或者密码错误";
    private final String failureUrl;
    private boolean forwardToDestination = false;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public StewardSecurityFailureHandler(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        if (StringUtils.isEmpty(failureUrl)) {
            throw new ServletException("failure url is required");
        }

        this.handleException(request, exception);
        if (this.forwardToDestination) {
            request.getRequestDispatcher(this.failureUrl).forward(request, response);
        } else {
            this.redirectStrategy.sendRedirect(request, response, this.failureUrl);
        }
    }

    private void handleException(HttpServletRequest request, AuthenticationException exception) {
        String msg;
        if (exception instanceof UsernameNotFoundException) {
            msg = USERNAME_NOT_FOUND;
        } else {
            msg = BAD_CREDENTIALS_MSG;
        }
        if (forwardToDestination) {
            request.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", msg);
        } else {
            HttpSession session = request.getSession(false);
            if (session != null) {
                request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", msg);
            }
        }
    }
}
