package com.alibaba.nacos.example.spring.cloud.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.example.spring.cloud.config.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/config")
@RefreshScope
@EnableConfigurationProperties(User.class)
public class ConfigController {

    @Value("${useLocalCache}")
    private boolean useLocalCache;

    @NacosValue(value = "${name}", autoRefreshed = true)
    private String name;

    @Value("${age}")
    private String age;

    @Resource
    private User user;

    @RequestMapping("/get")
    public String get() {
        return useLocalCache + "age=" + age + name;
    }

    @GetMapping("user")
    public User getUser(){
        return user;
    }
}