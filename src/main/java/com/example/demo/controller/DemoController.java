package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DemoController {
    private List<String> testList = Arrays.asList("aaa", "bbb", "ccc");

    @GetMapping("/public")
    public String publicAccess() {
        return "公开接口，谁都能访问";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminOnly() {
        return "只有ADMIN角色能访问";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userOnly() {
        return "只有USER角色能访问";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/both")
    public String bothRoles() {
        return "ADMIN和USER都能访问";
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/test")
    public String test() {
        Collections.shuffle(testList);
        System.out.println(testList.get(0));
        return testList.get(0);
    }
}
