package com.txp.listener;

import com.txp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 基于 ApplicationPreparedEvent 扩展外部化配置属性源 加载顺序靠后
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019-01-29
 */
@Slf4j
public class ApplicationListenerPrepared implements ApplicationListener<ApplicationPreparedEvent> {

    /**
     * ApplicationPreparedEvent：spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        // has not been refreshed yet
//        User user = applicationContext.getBean(User.class);
        log.info("[ApplicationListenerPrepared] ApplicationPreparedEvent onApplicationEvent");
    }
}
