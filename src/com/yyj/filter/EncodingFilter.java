package com.yyj.filter;
/**
 * @author Yanjiang
 * @create 2019-07-29 10:30
 */

import javax.servlet.*;
import java.io.IOException;

/**
 *@ClassName OptFilter
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/29 10:30
 *@Version 1.0
 **/
public class EncodingFilter implements Filter{
    String encoding;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
