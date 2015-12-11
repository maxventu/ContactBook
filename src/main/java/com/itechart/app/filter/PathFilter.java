package com.itechart.app.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Maxim on 11/29/2015.
 */
public class PathFilter implements Filter {
    private FilterConfig fc;
    final Logger logger = LoggerFactory.getLogger(PathFilter.class);
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("init");
        fc = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }
}
