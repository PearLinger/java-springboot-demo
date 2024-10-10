package com.banmenit.demo.web.controller;

import com.banmenit.libcore.common.utils.JsonUtils;
import com.banmenit.libcore.web.annotation.WebLog;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.metadata.PredefinedScopeBeanMetaDataManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Descretion
 * @Author yi.yang
 * @Date 2024/10/10 10:12
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @RequestMapping("/query")
    @WebLog
    public void test(HttpServletResponse response,HttpServletRequest request) throws IOException {
        response.sendRedirect("http://localhost:8080/test/query1");
    }

    @RequestMapping("/query1")
    @WebLog
    public void query1(HttpServletRequest request) {
        log.info("sds");
    }
}
