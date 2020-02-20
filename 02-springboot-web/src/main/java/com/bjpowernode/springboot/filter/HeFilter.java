package com.bjpowernode.springboot.filter;

import javax.servlet.*;
import java.io.IOException;

public class HeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("he Filter您已经进入filter过滤器，您请求正常，请继续遵守规则...");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
