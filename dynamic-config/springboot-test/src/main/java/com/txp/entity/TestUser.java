package com.txp.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/2
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "test.user")
public class TestUser {

    private String name;

    private String id;

    private String age;

    private String sex;
}
