package com.txp.listener;

import com.txp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhaoyao
 * @version 1.0
 * @date 2019-01-29
 */
@Slf4j
public class ApplicationListenerFailed implements ApplicationListener<ApplicationFailedEvent> {

    /**
     * ApplicationFailedEvent：spring boot启动异常时执行事件
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();

//        User user = applicationContext.getBean(User.class);
        log.info("[ApplicationListenerFailed] ApplicationFailedEvent onApplicationEvent");
    }
}
