package com.banmenit.libcore.web.interceptor;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.banmenit.libcore.common.dto.GlobalRequestDTO;
import com.banmenit.libcore.common.utils.AuthorizationDataUtil;
import com.banmenit.libcore.common.utils.SnowflakeIdFactory;
import com.banmenit.libcore.web.utils.IpUtils;
import com.banmenit.libcore.web.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.banmenit.libcore.common.constant.GlobalRequestConstant.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;


/**
 * 将jwt内容解析出来放到request,放到ThreadLocal涉及到销毁
 *
 * @author tangsq
 */
@Slf4j
public class JwtInterceptor extends HandlerInterceptorAdapter {

    private static final SnowflakeIdFactory SNOW_FLAKE_ID_FACTORY
            = new SnowflakeIdFactory((LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() % 1024), 0);
    public static final Map<String, String> PROJECT_CODE_ID_MAP = new ConcurrentHashMap<>();

    /**
     * 请求前置拦截处理：读取jwt, queryparam以及head统一设置到request里
     *
     * @param request
     * @param response
     * @param handler
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //跨域预检放行
        if (request.getMethod().toUpperCase().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }

        String jwt = request.getHeader(AUTHORIZATION);
        // 2023-05-19 优先header 其次 query上access_token也可传递token
        if (StringUtils.isEmpty(jwt)) {
            jwt = request.getParameter(ACCESS_TOKEN);
        }
        if (StringUtils.isEmpty(jwt)) {
            // 尝试从cookie中获取令牌信息
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > BigDecimal.ZERO.intValue()) {
                Cookie tokenCookie = Arrays.stream(cookies).filter(cookie -> cookie.getName().equalsIgnoreCase(ACCESS_TOKEN)).findFirst().orElse(null);
                if (tokenCookie != null) {
                    jwt = tokenCookie.getValue();
                }
            }
        }
        boolean notProd = (SpringUtils.isLocal());
        if (StringUtils.isEmpty(jwt) && notProd) {
            jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                    ".eyJuYW1lIjoiYWRtaW4iLCJqdGkiOiIxIiwic3ViIjoiMSIsIm5pY2tuYW1lIjoi6LaF57qn566h55CG5ZGYIiwidGVuYW50IjoicHVibGljIiwibmJmIjoxNjcyMjIwODk2LCJleHAiOjE2NzIyMjQ0OTYsImlzcyI6IlBlcm1pc3Npb24uQVBJIiwiYXVkIjoiQUlvVCJ9.XiZxdMANszoEorPnSwXO7asXplbKKX-oYoHKqUGUZqE";
        }
        //去掉前面的Bearer 申明
        String newToken = Optional.ofNullable(jwt).map(e -> e.replace("Bearer ", "")).orElse(null);
        if (StringUtils.isEmpty(newToken)) {
            log.info("[Jwt组件] jwt is null");
            return true;
        }
        try {
            JWTPayload payload = JWTUtil.parseToken(newToken).getPayload();
            String tenant = (String) payload.getClaim(TENANT);
            String sub = (String) payload.getClaim(SUB);
            Boolean server = (Boolean) payload.getClaim(SERVER);
            String name = (String) payload.getClaim(NAME);
            Integer flag = (Integer) payload.getClaim(FLAG);
            String projectMapStr = (String)payload.getClaim(PROJECT_MAP);
            String instance = request.getParameter(INSTANCE);
            String urlProject = request.getParameter(PROJECT);
            String project = urlProject;
            // 兼容项目编码与id的转换,服务与开放接口
            if (!StringUtils.isEmpty(projectMapStr) && !StringUtils.isEmpty(urlProject)) {
                if (PROJECT_CODE_ID_MAP.containsKey(urlProject)) {
                    // 当前上下文 已包含 项目编码与id的关系 则直接转换
                    project = PROJECT_CODE_ID_MAP.get(urlProject);
                } else {
                    // 从jwt中直接获取映射关系并放置在内存中,同时转换项目信息
                    Map<String, String> projectMap = JSONUtil.toBean(projectMapStr, Map.class);
                    PROJECT_CODE_ID_MAP.putAll(projectMap);
                    project = projectMap.getOrDefault(urlProject, urlProject);
                }
            }
            String traceId = Optional.ofNullable(request.getHeader(TRACE_ID)).orElse(String.valueOf(SNOW_FLAKE_ID_FACTORY.nextId()));
            String timezone = request.getHeader(TIMEZONE);
            GlobalRequestDTO globalRequestParam = GlobalRequestDTO.builder()
                    .project(project)
                    .tenant(tenant)
                    .sub(sub)
                    .server(server)
                    .flag(flag)
                    .name(name)
                    .instance(instance)
                    .traceId(traceId)
                    .timezone(timezone)
                    .jwt(jwt)
                    .ip(IpUtils.getIpAddr(request))
                    .build();
            if (log.isDebugEnabled()) {
                log.debug("[Jwt组件] 全局参数信息:{}", globalRequestParam.toString());
            }
            request.setAttribute(JWT_INFO, globalRequestParam);
            AuthorizationDataUtil.setRequestData(globalRequestParam);
        } catch (Exception exception) {
            log.error("[Jwt组件] jwt解析错误", exception);
        }
        return true;
    }


    /**
     * 无论控制器方法执行时是否出现异常，afterCompletion方法都会执行
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        //清除,避免线程池复用导致数据错乱
        AuthorizationDataUtil.removeRequestData();
    }
}
