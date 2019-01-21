package com.txp.repository;

import com.txp.source.PropertySource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.CompositePropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用程序上下文的所有属性
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/1
 */
public class DynamicPropertySourceRepository {

    private final ApplicationContext applicationContext;

    public DynamicPropertySourceRepository(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * @return 应用程序上下文的所有属性
     */
    public List<PropertySource> getAll() {
        List<PropertySource> result = new ArrayList<>();
        ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) applicationContext;
        for (org.springframework.core.env.PropertySource p : ctx.getEnvironment().getPropertySources()) {
            if (p instanceof PropertySource) {
                result.add((PropertySource) p);
            }
            else if (p instanceof CompositePropertySource) {
                collectNacosPropertySources((CompositePropertySource) p, result);
            }
        }
        return result;
    }

    private void collectNacosPropertySources(CompositePropertySource composite,
                                             List<PropertySource> result) {
        for (org.springframework.core.env.PropertySource p : composite.getPropertySources()) {
            if (p instanceof PropertySource) {
                result.add((PropertySource) p);
            }
            else if (p instanceof CompositePropertySource) {
                collectNacosPropertySources((CompositePropertySource) p, result);
            }
        }
    }

}
