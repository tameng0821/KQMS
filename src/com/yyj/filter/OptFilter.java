package com.yyj.filter;
/**
 * @author Yanjiang
 * @create 2019-07-29 10:30
 */

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *@ClassName OptFilter
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/29 10:30
 *@Version 1.0
 **/
public class OptFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURL = ((HttpServletRequest)request).getRequestURL().toString();
        String[] split = requestURL.split("/");
        String opt = split[split.length - 1];
        request.setAttribute("opt",opt);
        chain.doFilter(request,response);
    }
    @Override
    public void destroy() {

    }
}
