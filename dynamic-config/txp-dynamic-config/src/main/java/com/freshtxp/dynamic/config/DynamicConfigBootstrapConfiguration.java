package com.freshtxp.dynamic.config;

import com.freshtxp.dynamic.source.DynamicPropertySourceLocator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置管理
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/1
 */
@Configuration
public class DynamicConfigBootstrapConfiguration {

    @Bean
    public DynamicPropertySourceLocator dynamicPropertySourceLocator() {
        return new DynamicPropertySourceLocator();
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicConfigProperties nacosConfigProperties() {
        return new DynamicConfigProperties();
    }
}
