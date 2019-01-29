package com.txp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019-01-29
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;

    private String pwd;
}
