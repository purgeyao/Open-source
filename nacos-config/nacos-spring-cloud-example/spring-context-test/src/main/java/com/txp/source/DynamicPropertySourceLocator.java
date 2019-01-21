package com.txp.source;

import com.alibaba.nacos.api.config.ConfigService;
import com.txp.config.DynamicConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * 扩展 {@link org.springframework.core.env.PropertySource} {@link PropertySourceLocator} 实现
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/1
 */
@Slf4j
@Order(0)
public class DynamicPropertySourceLocator implements PropertySourceLocator {

    private static final String NACOS_PROPERTY_SOURCE_NAME = "NACOS";
    private static final String SEP1 = "-";
    private static final String DOT = ".";

    @Autowired
    private DynamicConfigProperties nacosConfigProperties;

    private PropertySourceBuilder nacosPropertySourceBuilder;

    @Override
    public org.springframework.core.env.PropertySource locate(Environment environment) {

        // 获取阿里配置
        ConfigService configService = nacosConfigProperties.configServiceInstance();
        if (null == configService) {
            log.warn("[PropertySourceLocator] 找不到配置服务实例，无法从配置中心加载配置");
            return null;
        }

        // 构建
        long timeout = nacosConfigProperties.getTimeout();
        nacosPropertySourceBuilder = new PropertySourceBuilder(configService, timeout);

        // 获取属性
        String name = nacosConfigProperties.getName();
        String fileExtension = nacosConfigProperties.getFileExtension();
        String nacosGroup = nacosConfigProperties.getGroup();
        String dataIdPrefix = nacosConfigProperties.getPrefix();
        if (StringUtils.isEmpty(dataIdPrefix)) {
            dataIdPrefix = name;
        }

        CompositePropertySource composite = new CompositePropertySource(NACOS_PROPERTY_SOURCE_NAME);

//        Map<String, Object> source = new HashMap<String, Object>();
//        source.put("user.name", "123");
//
//        MapPropertySource mapPropertySource = new MapPropertySource("application.properties", source);
//        composite.addFirstPropertySource(mapPropertySource);

        loadApplicationConfiguration(composite, nacosGroup, dataIdPrefix, fileExtension);

        return composite;
    }


    private void loadApplicationConfiguration(CompositePropertySource compositePropertySource, String nacosGroup,
                                              String dataIdPrefix, String fileExtension) {
        loadNacosDataIfPresent(compositePropertySource, dataIdPrefix + DOT
                + fileExtension, nacosGroup, fileExtension);
        // 加载集合
        for (String profile : nacosConfigProperties.getNameLists()){
            loadNacosDataIfPresent(compositePropertySource, profile + DOT
                    + fileExtension, nacosGroup, fileExtension);
        }

        for (String profile : nacosConfigProperties.getActiveProfiles()) {
            String dataId = dataIdPrefix + SEP1 + profile + DOT + fileExtension;
            loadNacosDataIfPresent(compositePropertySource, dataId, nacosGroup, fileExtension);
        }
    }

    private void loadNacosDataIfPresent(final CompositePropertySource composite, final String dataId,
                                        final String group, String fileExtension) {

        PropertySource ps = nacosPropertySourceBuilder.build(dataId, group, fileExtension);
//        PropertySource ps1 = nacosPropertySourceBuilder.build("test.properties", group, fileExtension);
        if (ps != null) {
            composite.addFirstPropertySource(ps);
        }
//        if (ps1!=null){
//            composite.addFirstPropertySource(ps1);
//        }
    }
}
