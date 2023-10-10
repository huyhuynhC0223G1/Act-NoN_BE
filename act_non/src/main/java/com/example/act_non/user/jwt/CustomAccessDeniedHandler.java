package com.example.act_non.user.jwt;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//xử lý các trường hợp truy cập bị từ chối (access denied) trong ứng dụng.
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException exc)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // (403 - Forbidden)
        response.getWriter().write("Access Denied!");
    }
}
