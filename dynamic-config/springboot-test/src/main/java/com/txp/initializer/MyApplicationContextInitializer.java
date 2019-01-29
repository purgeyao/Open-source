package com.txp.initializer;

import com.freshtxp.dynamic.repository.DynamicPropertySourceRepository;
import com.freshtxp.dynamic.source.PropertySource;
import com.txp.entity.TestUser;
import com.txp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.web.context.support.StaticWebApplicationContext;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaoyao
 * @version 1.0
 * @date 2019-01-28
 */
@Slf4j
//@Component
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private volatile static Properties properties = new Properties();

    private volatile static Map<String, Object> preValue = new ConcurrentHashMap<>();

    private DynamicPropertySourceRepository dynamicPropertySourceRepository;

//    @Resource
    private TestUser testUser;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

//        DynamicPropertySourceRepository bean = applicationContext.getBean(DynamicPropertySourceRepository.class);

//        User user = applicationContext.getBean(User.class);
//        TestUser testUser = applicationContext.getBean(TestUser.class);

        List<PropertySource> result = new ArrayList<>();
        for (org.springframework.core.env.PropertySource p : applicationContext.getEnvironment().getPropertySources()) {
            if (p instanceof PropertySource) {
                result.add((PropertySource) p);
            } else if (p instanceof CompositePropertySource) {
                collectNacosPropertySources((CompositePropertySource) p, result);
            }
        }
        // upd Properties and Map<String, Object>
        if (result.size() > 0) {
            for (PropertySource propertySource : result) {
                Map<String, Object> source = propertySource.getSource();
                preValue.putAll(source);
                properties.putAll(source);
            }
        }
        log.info("[MyApplicationContextInitializer] ApplicationContextInitializer initialize");
    }

    private void collectNacosPropertySources(CompositePropertySource composite,
                                             List<PropertySource> result) {
        for (org.springframework.core.env.PropertySource p : composite.getPropertySources()) {
            if (p instanceof PropertySource) {
                result.add((PropertySource) p);
            } else if (p instanceof CompositePropertySource) {
                collectNacosPropertySources((CompositePropertySource) p, result);
            }
        }
    }

}
