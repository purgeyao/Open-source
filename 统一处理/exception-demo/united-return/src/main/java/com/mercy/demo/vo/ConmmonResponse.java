package com.mercy.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019-01-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConmmonResponse<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    public ConmmonResponse(Integer code, String message){
        this.setCode(code);
        this.setMessage(message);
    }
}
