package com.example.act_non.user.jwt.config;

import com.example.act_non.user.jwt.CustomAccessDeniedHandler;
import com.example.act_non.user.jwt.JwtAuthenticationTokenFilter;
import com.example.act_non.user.jwt.RestAuthenticationEntryPoint;
import com.example.act_non.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//cung cấp cấu hình bảo mật cho ứng dụng web sử dụng Spring Security. Nó cấu hình xác thực người dùng,
// mã hóa mật khẩu và cấu hình các quy tắc bảo mật.
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

//    @Bean
//    public JwtAuthenticationTokenFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationTokenFilter();
//    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder(10));
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    protected void configure(HttpSecurity http) throws Exception {
        // Disable crsf cho đường dẫn /api/**
        http.csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers(
                        "/api/login",
                        "/api/home",
                        "/api/product",
                        "/api/product/**",
                        "/api/cartDetail",
                        "/api/cartDetail/**",
                        "/api/orders",
                        "/api/orders/**",
                        "/api/orderDetail",
                        "/api/orderDetail/**"

                        )
                .permitAll()
                .antMatchers(
                        "/api/carts/detail",
                        "/api/customer/**"
                )
                .hasAnyAuthority("ADMIN")
                .antMatchers("/api/home/**")
                .hasAnyAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
