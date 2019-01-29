package com.txp.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 基于ApplicationEnvironmentPreparedEvent 扩展外部化配置属性源
 * // 2. from-ApplicationEnvironmentPreparedEvent : 9
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019-01-29
 */
@Slf4j
public class ApplicationListenerEnvironmentPrepared implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    /**
     * ApplicationEnvironmentPreparedEvent：spring boot 对应Enviroment已经准备完毕，但此时上下文context还没有创建。
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        log.info("[ApplicationListenerEnvironmentPrepared] ApplicationEnvironmentPreparedEvent onApplicationEvent");
    }
}
