package com.banmenit.libcore.web.filter;

import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.banmenit.libcore.common.constant.GlobalRequestConstant.TRACE_ID;


/**
 * @author lichen
 * @since 2023-12-21 20:20:50
 **/
public class TraceFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String traceId = httpServletRequest.getHeader(TRACE_ID);
        if (StrUtil.isNotBlank(traceId)) {
            MDC.put(TRACE_ID, traceId);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        MDC.remove(TRACE_ID);
        Filter.super.destroy();
    }
}
