package com.example.act_non.user.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// xử lý các trường hợp xác thực không thành công (unauthorized) trong ứng dụng REST.
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // (response) là HttpServletResponse.SC_UNAUTHORIZED (401 - Unauthorized).
        response.getWriter().write("Unauthorized");
    }
}
