package com.kaishengit.servlet.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by bayllech on 2016/12/16.
 */
public class EncodingFilter extends AbstractFilter {
    private String encoding = "utf-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encoding = filterConfig.getInitParameter("encoding");
        if (StringUtils.isNotEmpty(encoding)) {
            this.encoding=encoding;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
    }
}
