package com.txp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019-01-28
 */
public class MyApplicationPreparedEvent extends ApplicationPreparedEvent {
    /**
     * Create a new {@link ApplicationPreparedEvent} instance.
     *
     * @param application the current application
     * @param args        the arguments the application is running with
     * @param context     the ApplicationContext about to be refreshed
     */
    public MyApplicationPreparedEvent(SpringApplication application, String[] args, ConfigurableApplicationContext context) {
        super(application, args, context);
    }
}
