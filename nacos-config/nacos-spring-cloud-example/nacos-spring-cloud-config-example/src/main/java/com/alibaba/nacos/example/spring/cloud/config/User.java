package com.alibaba.nacos.example.spring.cloud.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/1
 */
@Data
@ConfigurationProperties(prefix = "user")
@ToString
public class User {

    private String name;

    private String pwd;

    private Integer age;
}
