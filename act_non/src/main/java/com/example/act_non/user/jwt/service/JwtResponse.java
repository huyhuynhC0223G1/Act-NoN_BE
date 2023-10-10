package com.example.act_non.user.jwt.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

// lớp JwtResponse chứa thông tin cần thiết về người dùng đã xác thực và token JWT đã tạo.
// Khi một yêu cầu xác thực thành công, đối tượng JwtResponse sẽ được tạo và trả về như một phản hồi
// từ phía server cho client để sử dụng token JWT trong các yêu cầu tiếp theo.
public class JwtResponse {

    private Long id;
    private String token;
    private String type = "Bearer";
    private String username;
    private String name;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(Long id, String token, String username, String name, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.name = name;
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
