package com.banmenit.demo.web.controller;

import cn.hutool.core.util.StrUtil;
import com.banmenit.libcore.common.utils.JsonUtils;
import com.banmenit.libcore.common.utils.RandomUtil;
import com.banmenit.libcore.web.annotation.WebLog;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.metadata.PredefinedScopeBeanMetaDataManager;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.SingleByte;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * @Descretion
 * @Author yi.yang
 * @Date 2024/10/10 10:12
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    private static String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

    private static String GATEWAY_ADDRESS = "http://localhost:8080/test/";
    Map<String, String> SHORT_URL_MAP = new HashMap<>();

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final Integer SHORT_URL_LENGTH = 8;
    @PostMapping("/short-url")
    @WebLog
    public String shortUrl(@RequestBody ShortUrlDTO dto) throws IOException {
        if (dto == null || StrUtil.isBlank(dto.getUrl())) {
            return "url is empty";
        }
        String shortUrl = getShortUrl();
        SHORT_URL_MAP.put(shortUrl,dto.getUrl());
        return GATEWAY_ADDRESS + shortUrl;
    }

    @GetMapping("/query")
    public String query(){
        return "query";
    }


    public static String getShortUrl() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            ret.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return ret.toString();
    }

    @GetMapping("/{url}")
    @WebLog
    public void sendRedirectUrl(@PathVariable String url, HttpServletResponse response) throws IOException {
        String JUMP_URL = SHORT_URL_MAP.get(url);
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location",JUMP_URL);
    }

    public static void main(String[] args) {
        String url = "https://aiot-dev.rd.chn-das.com/enterpriseadmin/iocvisual/drawingPreviewNoLogin?groupId=1272566143190122496&projectId=1268137555009052672&showLayout=false&token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZXJ2ZXIiOmZhbHNlLCJzdWIiOiIxODE4NDU4NzA5NDk2NTYxNjY1IiwiZmxhZyI6NiwiY2xpZW50VHlwZSI6bnVsbCwidXNlcl9uYW1lIjoiMTgwMDAwMDAwMjYiLCJzY29wZSI6WyJzZXZlciJdLCJuYW1lIjoiMTgwMDAwMDAwMjYiLCJleHAiOjE3Mjg1NTE5NDYsImp0aSI6ImI2MWExZjFhLTZjNDMtNDdmYS04Zjk4LTQyNzkzZmU4YjNiZiIsInRlbmFudCI6InRhaW90IiwiY2xpZW50X2lkIjoic3lzIn0.aORcNLcJVm879MgwZ1aYTvLF-90lB9TfWshYVHKBIwE";
        System.out.println(url.length());
        System.out.println(url.getBytes(StandardCharsets.UTF_8).length);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            getShortUrl();
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
