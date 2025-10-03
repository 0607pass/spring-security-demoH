package com.example.demo.entity;

import lombok.Data;

/**
 * @author xfff
 * @date 2025-10-03
 * @description User实体类
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String role;
}
