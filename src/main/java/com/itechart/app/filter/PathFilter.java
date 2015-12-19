package com.itechart.app.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Maxim on 11/29/2015.
 */
public class PathFilter implements Filter {
    private FilterConfig fc;
    final Logger LOGGER = LoggerFactory.getLogger(PathFilter.class);
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("init");
        fc = filterConfig;
    }
    private final String UPLOAD_AVATAR = "/upload_avatar";
    private final String UPLOAD = "/upload";
    private final String STATIC = "/static";
    private final String CONTACT_BOOK = "/ContactBook";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        LOGGER.info("path: {}, query: {}",req.getPathInfo(), req.getQueryString());
        String path = req.getRequestURI().substring(req.getContextPath().length());
        LOGGER.info("HTTP path: {}",path);
        /*if (path.startsWith(UPLOAD))req.getRequestDispatcher(path).forward(request,response);
        else*/ if(path.startsWith(STATIC))filterChain.doFilter(request,response);

           else request.getRequestDispatcher(CONTACT_BOOK+path).forward(request,response);
    }

    public void destroy() {

    }
}
