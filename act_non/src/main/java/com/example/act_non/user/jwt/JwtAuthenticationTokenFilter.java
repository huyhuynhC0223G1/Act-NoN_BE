package com.example.act_non.user.jwt;


import com.example.act_non.user.jwt.service.JwtService;
import com.example.act_non.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// là một lớp lọc (filter) trong Spring Security được sử dụng để xác thực
// và phân quyền người dùng dựa trên token JWT.
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request); //Lấy token JWT từ yêu cầu bằng cách gọi phương thức
            if (jwt != null && jwtService.validateJwtToken(jwt)) {
                String username = jwtService.getUsernameFromJwtToken(jwt); // Nếu token hợp lệ, trích xuất tên người dùng từ token bằng cách gọi phương thức getUsernameFromJwtToken của jwtService.
                UserDetails userDetails = userService.loadUserByUsername(username); //Tải thông tin người dùng từ cơ sở dữ liệu bằng cách gọi phương thức loadUserByUsername của userService.
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication); // Đặt thông tin xác thực người dùng vào SecurityContextHolder.
            }
        } catch (Exception e) {
            logger.error("Can NOT set user authentication -> Message: {}", e);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) { // Kiểm tra xem giá trị tiêu đề có bắt đầu bằng "Bearer " không.
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}
