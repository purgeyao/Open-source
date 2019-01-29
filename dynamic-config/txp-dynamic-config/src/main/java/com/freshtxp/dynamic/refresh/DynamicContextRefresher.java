package com.freshtxp.dynamic.refresh;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.freshtxp.dynamic.config.DynamicConfigProperties;
import com.freshtxp.dynamic.repository.DynamicPropertySourceRepository;
import com.freshtxp.dynamic.source.PropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ApplicationListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * 实现 {@link ApplicationListener} 监听更新配置
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/1
 */
@Slf4j
public class DynamicContextRefresher implements ApplicationListener<ApplicationReadyEvent> {

    private final ContextRefresher contextRefresher;

    private final DynamicConfigProperties properties;

    private final DynamicRefreshProperties refreshProperties;

    private final DynamicPropertySourceRepository dynamicPropertySourceRepository;

    private final ConfigService configService;

    private Map<String, Listener> listenerMap = new ConcurrentHashMap<>(16);

    public DynamicContextRefresher(ContextRefresher contextRefresher, DynamicConfigProperties properties,
                                   DynamicRefreshProperties refreshProperties,
                                   DynamicPropertySourceRepository dynamicPropertySourceRepository,
                                   ConfigService configService) {
        this.contextRefresher = contextRefresher;
        this.properties = properties;
        this.refreshProperties = refreshProperties;
        this.dynamicPropertySourceRepository = dynamicPropertySourceRepository;
        this.configService = configService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        this.registerNacosListenersForApplications();
    }

    /**
     * 更新配置
     */
    private void registerNacosListenersForApplications() {
        if (refreshProperties.isEnabled()) {
            for (PropertySource nacosPropertySource : dynamicPropertySourceRepository.getAll()) {
                String dataId = nacosPropertySource.getDataId();
                registerNacosListener(dataId);
            }
        }
    }

    /**
     * 更新配置
     * @param dataId 配置文件名称
     */
    private void registerNacosListener(final String dataId) {

        Listener listener = listenerMap.computeIfAbsent(dataId, i -> new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                // 更新配置
                contextRefresher.refresh();
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });

        try {
            configService.addListener(dataId, properties.getGroup(), listener);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

}
