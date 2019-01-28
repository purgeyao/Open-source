package com.txp.config;

import com.freshtxp.dynamic.repository.DynamicPropertySourceRepository;
import com.freshtxp.dynamic.source.PropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019-01-28
 */
@Slf4j
@Component
public class ListenProperties implements ApplicationContextAware {

    private volatile static Properties properties = new Properties();

    private static volatile Map<String, String> preValue = new ConcurrentHashMap<>();

    @Resource
    private DynamicPropertySourceRepository nacosPropertySourceRepository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        List<PropertySource> all = nacosPropertySourceRepository.getAll();
        log.info("[ListenProperties] upd properties");
    }

}
