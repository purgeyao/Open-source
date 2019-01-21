package com.txp.config;

import com.txp.source.DynamicPropertySourceLocator;
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
    public DynamicPropertySourceLocator myPropertySourceLocator() {
        return new DynamicPropertySourceLocator();
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicConfigProperties nacosConfigProperties() {
        return new DynamicConfigProperties();
    }
}
