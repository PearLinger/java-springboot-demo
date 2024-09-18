package com.banmenit.libcore.web.filter;

import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tangsq
 * @date 2022/11/25
 */
public class BanmenitCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //避免对后面Filter或者拦截器有影响提前返回
        if (HttpMethod.OPTIONS.toString().equals(((HttpServletRequest) req).getMethod())) {
            return;
        }
        chain.doFilter(req, res);
    }
}
