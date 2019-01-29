package com.txp.listener;

import com.txp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhaoyao
 * @version 1.0
 * @date 2019-01-29
 */
@Slf4j
public class ApplicationListenerStarted implements ApplicationListener<ApplicationStartedEvent> {


    /**
     * ApplicationStartedEvent ：spring boot启动开始时执行的事件
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        // No qualifying bean of type 'com.txp.entity.User' available
//        User user = applicationContext.getBean(User.class);
        log.info("[ApplicationListenerStarted] ApplicationListener onApplicationEvent");
    }
}
