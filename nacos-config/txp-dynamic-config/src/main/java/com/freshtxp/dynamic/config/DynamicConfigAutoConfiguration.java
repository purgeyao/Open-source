package com.freshtxp.dynamic.config;

import com.freshtxp.dynamic.refresh.DynamicContextRefresher;
import com.freshtxp.dynamic.refresh.DynamicRefreshProperties;
import com.freshtxp.dynamic.repository.DynamicPropertySourceRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置bean
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/1
 */
@Configuration
@EnableConfigurationProperties(DynamicConfigProperties.class)
public class DynamicConfigAutoConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private DynamicRefreshProperties nacosRefreshProperties;

    @Bean
    public DynamicPropertySourceRepository nacosPropertySourceRepository() {
        return new DynamicPropertySourceRepository(applicationContext);
    }

    @Bean
    public DynamicRefreshProperties nacosRefreshProperties() {
        return new DynamicRefreshProperties();
    }

    @Bean
    public DynamicContextRefresher nacosContextRefresher(ContextRefresher contextRefresher,
                                                         DynamicConfigProperties nacosConfigProperties,
                                                         DynamicPropertySourceRepository propertySourceRepository) {

        return new DynamicContextRefresher(contextRefresher, nacosConfigProperties,
                nacosRefreshProperties, propertySourceRepository,
                nacosConfigProperties.configServiceInstance());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
