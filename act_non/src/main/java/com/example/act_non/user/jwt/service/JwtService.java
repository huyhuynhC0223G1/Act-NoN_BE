package com.example.act_non.user.jwt.service;


import com.example.act_non.user.model.UserPrinciple;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;


//  cung cấp các phương thức để tạo và xác thực token JWT trong ứng dụng. Nó sử dụng thư viện io.jsonwebtoken
//  để xử lý việc tạo và xác thực token, và cũng định nghĩa các hằng số và phương thức hỗ trợ liên quan.
@Service
public class JwtService {

    private static final String SECRET_KEY = "123456789987654321123456789987654321123456789";
    private static final long EXPIRE_TIME =  21600000000L;//(6h).

    public String generateTokenLogin(Authentication authentication) {
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))//Thiết lập chủ đề (subject) của token là tên người dùng.
                .setIssuedAt(new Date(System.currentTimeMillis()))//Thiết lập thời gian phát hành (issuedAt) là thời gian hiện tại.
                .setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME * 1000))//Thiết lập thời gian hết hạn (expiration) là thời gian hiện tại cộng với thời gian hết hạn của token.
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)//Ký và compact token bằng cách sử dụng khóa ký (signing key) lấy từ getSignInKey().
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);//Đọc khóa bí mật từ chuỗi SECRET_KEY và chuyển đổi nó thành mảng byte.
        return Keys.hmacShaKeyFor(keyBytes);//tạo khóa ký từ mảng byte.
    }

    public boolean validateJwtToken(String authToken) {  // Nhận một chuỗi token JWT và kiểm tra tính hợp lệ của nó:
        try {
            Jwts.parserBuilder()                 //  tạo một trình phân tích token.
                    .setSigningKey(SECRET_KEY) //Thiết lập khóa ký (signing key) từ SECRET_KEY.
                    .build()
                    .parse(authToken); //  phân tích cú pháp và kiểm tra tính hợp lệ của token.
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token -> Message: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token -> Message: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token -> Message: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty -> Message: " + e.getMessage());
        }
        return false;
    }

    public String getUsernameFromJwtToken(String token) {   // Nhận một chuỗi token JWT và trích xuất tên người dùng từ token:
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)         // phân tích cú pháp và trích xuất các khẳng định từ token.
                .getBody()
                .getSubject();                //Lấy chủ đề (subject) từ các khẳng định và trả về tên người dùng.
    }
}
