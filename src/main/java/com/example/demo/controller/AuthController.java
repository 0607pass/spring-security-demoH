package com.example.demo.controller;

import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.TokenBlacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenBlacklist tokenBlacklist;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {

        // 进行身份验证，如果认证失败，会走 CustomAuthenticationEntryPoint 接口
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        //认证成功，获取用户信息
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        String token = jwtUtils.generateToken(user.getUsername(), user.getAuthorities().iterator().next().getAuthority());

        return Map.of("token", token);
    }

    /**
     * @author xfff
     * @date 2025-10-03
     * @description 使得jwt临牌失效 退出登录
     */
    @PostMapping("/logout")
    public Map<String, String> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklist.blacklistToken(token);
        }
        return Map.of("message", "Logged out successfully");
    }
}
