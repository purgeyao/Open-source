package com.txp.controller;

import com.txp.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/2
 */
@RefreshScope
@RestController
public class TestControler {

    @Value("${local.name}")
    private String localName;

    @Value("${user.name}")
    private String userName;

    @Autowired
    private TestUser testUser;

    @Value("${test.user.name:xxx}")
    private String testName;

    @GetMapping("test")
    public Object test(){

        return "test: " + testName +
                " app: " + userName +
                " local" + localName;
    }

    @GetMapping("testUser")
    public Object testUser(){
        return testUser.toString();
    }
}
