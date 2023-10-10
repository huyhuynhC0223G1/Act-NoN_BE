package com.example.act_non.user.controller;


import com.example.act_non.user.jwt.service.JwtResponse;
import com.example.act_non.user.jwt.service.JwtService;
import com.example.act_non.user.model.Users;
import com.example.act_non.user.model.dto.UserDTO;
import com.example.act_non.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    /* ---------------- GET ALL USER ------------------------ */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    /* ---------------- GET USER BY ID ------------------------ */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found Users", HttpStatus.NO_CONTENT);
    }

    /* ---------------- CREATE NEW USER ------------------------ */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody Users user) {
        if (userService.add(user)) {
            return new ResponseEntity<>("Created!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Users Existed!", HttpStatus.BAD_REQUEST);
        }
    }

    /* ---------------- DELETE USER ------------------------ */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        //Sử dụng authenticationManager để xác thực người dùng dựa trên tên người dùng và mật khẩu được truyền
        // vào qua yêu cầu. Đây là một bước xác thực thông qua UsernamePasswordAuthenticationToken.

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Đặt thông tin xác thực của người dùng vào SecurityContextHolder để giữ cho người dùng đã xác thực
        // trong phiên làm việc hiện tại.

        String jwt = jwtService.generateTokenLogin(authentication);
        // Tạo chuỗi token JWT bằng cách gọi phương thức generateTokenLogin của jwtService.
        // Token JWT này sẽ chứa thông tin xác thực của người dùng đã đăng nhập.

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // Trích xuất thông tin chi tiết về người dùng đã xác thực từ đối tượng Authentication.

        Users userInfo = userService.findByUsername(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(userInfo.getId(), jwt,
                userInfo.getUsername(), userInfo.getUsername(), userDetails.getAuthorities()));
    }
}
