package com.freshtxp.dynamic.refresh;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/1
 */
@Data
@Component
public class DynamicRefreshProperties {

    @Value("${txp.spring.cloud.dynamic.config.refresh.enabled:true}")
    private boolean enabled = true;
}
