package com.txp.source;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.StringUtils;

import java.io.StringReader;
import java.util.*;

/**
 * 获取配置中心文件
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/1
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class PropertySourceBuilder {

    private ConfigService configService;

    /**
     * 超时时间
     */
    private long timeout;

    PropertySource build(String dataId, String group, String fileExtension) {
        // 获取线上配置
        Properties p = loadNacosData(dataId, group, fileExtension);
        if (p == null) {
            return null;
        }
        // 转换map返回
        return new PropertySource(dataId, propertiesToMap(p), new Date());
    }

    /**
     * 获取Properties对象
     *
     * @param dataId 文件名称
     * @param group 分组
     * @param fileExtension 文件后缀名
     * @return Properties
     */
    private Properties loadNacosData(String dataId, String group, String fileExtension) {
        String data = null;
        try {
            data = configService.getConfig(dataId, group, timeout);
            if (!StringUtils.isEmpty(data)) {
                log.info(String.format("Loading nacos data, dataId: '%s', group: '%s'", dataId, group));

                // 转换为properties格式文件
                if (fileExtension.equalsIgnoreCase("properties")) {
                    Properties properties = new Properties();

                    properties.load(new StringReader(data));
                    return properties;
                }
                // 转换为yaml或yml格式文件
                else if (fileExtension.equalsIgnoreCase("yaml")
                        || fileExtension.equalsIgnoreCase("yml")) {
                    YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
                    yamlFactory.setResources(new ByteArrayResource(data.getBytes()));
                    return yamlFactory.getObject();
                }

            }
        }
        catch (NacosException e) {
            log.error("get data from Nacos error,dataId:{}, ", dataId, e);
        }
        catch (Exception e) {
            log.error("parse data from Nacos error,dataId:{},data:{},", dataId, data,
                    e);
        }
        return null;
    }

    /**
     * 转换Map<String, Object>
     *
     * @param properties 文件
     * @return Map<String,Object>
     */
    private Map<String, Object> propertiesToMap(Properties properties) {
        Map<String, Object> result = new HashMap<>(16);
        Enumeration<String> keys = (Enumeration<String>) properties.propertyNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Object value = properties.getProperty(key);
            if (value != null) {
                result.put(key, ((String) value).trim());
            }
            else {
                result.put(key, null);
            }
        }
        return result;
    }

}
