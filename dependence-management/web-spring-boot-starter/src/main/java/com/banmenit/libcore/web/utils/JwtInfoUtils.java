package com.banmenit.libcore.web.utils;

import com.banmenit.libcore.common.dto.GlobalRequestDTO;
import com.banmenit.libcore.common.exception.BizException;
import com.banmenit.libcore.common.utils.AuthorizationDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

import static com.banmenit.libcore.common.constant.GlobalRequestConstant.JWT_INFO;


/**
 * jwt工具类
 *
 * @author tangsq
 * @date 2022/12/12
 */
@Slf4j
public class JwtInfoUtils {

    /**
     * 默认为0
     */
    public static final String DEFAULT_SUB = "0";

    /**
     * 默认服务调用
     */
    public static final String DEFAULT_NAME = "服务调用";


    /**
     * 获取全局请求参数
     * 当开启获取全局请求信息,获取没有直接抛异常
     *
     * @return {@link GlobalRequestDTO}
     */
    @Deprecated
    public static GlobalRequestDTO getJwtInfo() {
        HttpServletRequest httpServletRequest = ServletUtils.getRequest();
        GlobalRequestDTO globalRequestDTO = (GlobalRequestDTO) Optional.ofNullable(httpServletRequest)
                .map(e -> e.getAttribute(JWT_INFO)).orElse(null);
        if (null == globalRequestDTO) {
            globalRequestDTO = AuthorizationDataUtil.getRequestData();
            if (null == globalRequestDTO) {
                globalRequestDTO = new GlobalRequestDTO();
            }
        }
        return globalRequestDTO;
    }

    /**
     * 生成jwt builder,直接使用 JwtBuilder.builder()
     *
     * @return {@link JwtBuilder}
     */
    @Deprecated
    public static JwtBuilder builder() {
        return JwtBuilder.builder();
    }

    /**
     * 鉴权处理
     *
     * @return {@link JwtBuilder}
     */
    public static void checkToken(String token) {
        if (StringUtils.isEmpty(token)) {
            throw BizException.buildException("PARAM_FAILED", "缺少token");
        }
        Environment env = SpringUtils.getBean(Environment.class);
        if (env != null) {
            String property = env.getProperty("business.forward-auth.enable");
            if ("true".equalsIgnoreCase(property) || StringUtils.isEmpty(property)) {
                // 开启插件鉴权
                String url = env.getProperty("business.forward-auth.url");
                if (StringUtils.isEmpty(url)) {
                    throw BizException.buildException("PARAM_FAILED", "缺少鉴权接口");
                }
                HttpHeaders headers = new HttpHeaders();
                if (!token.contains("Bearer")) {
                    token = "Bearer " + token;
                }
                headers.add(HttpHeaders.AUTHORIZATION, token);
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, request, Map.class);
                HttpStatus statusCode = response.getStatusCode();
                if (HttpStatus.OK.value() != statusCode.value()) {
                    throw BizException.buildException("PARAM_FAILED", "非法连接");
                }
            }
        }
    }
}
