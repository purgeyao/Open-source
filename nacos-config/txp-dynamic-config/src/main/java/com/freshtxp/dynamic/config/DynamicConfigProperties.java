package com.freshtxp.dynamic.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static com.alibaba.nacos.api.PropertyKeyConst.*;

/**
 *
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/1
 */
@Data
@Slf4j
@ToString
@ConfigurationProperties("txp.spring.cloud.dynamic.config")
public class DynamicConfigProperties {

    /**
     * Whether online configuration is enabled
     */
    private Boolean isSwitch = false;

    /**
     * nacos config server address
     */
    private String serverAddr;

    /**
     * encode for nacos config content.
     */
    private String encode;

    /**
     * nacos config group, group is config data meta info.
     */
    private String group = "DEFAULT_GROUP";

    /**
     * nacos config dataId prefix
     */
    private String prefix;
    /**
     * the suffix of nacos config dataId, also the file extension of config content.
     */
    private String fileExtension = "properties";

    /**
     * timeout for get config from nacos.
     */
    private int timeout = 3000;

    /**
     * endpoint for Nacos, the domain name of a service, through which the server address
     * can be dynamically obtained.
     */
    private String endpoint;

    /**
     * namespace, separation configuration of different environments.
     */
    private String namespace;

    /**
     * access key for namespace.
     */
    private String accessKey;

    /**
     * secret key for namespace.
     */
    private String secretKey;

    /**
     * context path for nacos config server.
     */
    private String contextPath;

    /**
     * nacos config cluster name
     */
    private String clusterName;

//    @Value("${spring.application.name}")
    private String name;

    private List<String> nameLists = new ArrayList<>();

    private String[] activeProfiles;

    private ConfigService configService;

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        this.activeProfiles = environment.getActiveProfiles();
    }

    /**
     * 获取ConfigService对象
     * @return ConfigService
     */
    public ConfigService configServiceInstance() {

        if (null != configService) {
            return configService;
        }

        //spring.application.name=application
        //
        //# 在命名空间详情处可以获取到 endpoint 和 namespace 的值
        //spring.cloud.nacos.config.endpoint=acm.aliyun.com
        //spring.cloud.nacos.config.namespace=832838ad-2e82-4734-84da-578e99f9904a
        //# 推荐使用 RAM 账户的 accessKey 和 secretKey
        //spring.cloud.nacos.config.access-key=LTAImY8S8cmswlqJ
        //spring.cloud.nacos.config.secret-key=FgJkmZd5tZUTE8TQ5Tiy80Pq9u2zEK
        //# 指定配置的后缀，支持 properties、yaml、yml，默认为 properties
        //spring.cloud.nacos.config.file-extension=properties

        Properties properties = new Properties();
        properties.put(SERVER_ADDR, Objects.toString(this.serverAddr, ""));
        properties.put(ENCODE, Objects.toString(this.encode, ""));
        properties.put(NAMESPACE, Objects.toString(this.namespace, "832838ad-2e82-4734-84da-578e99f9904a"));
        properties.put(ACCESS_KEY, Objects.toString(this.accessKey, "LTAImY8S8cmswlqJ"));
        properties.put(SECRET_KEY, Objects.toString(this.secretKey, "FgJkmZd5tZUTE8TQ5Tiy80Pq9u2zEK"));
        properties.put(CONTEXT_PATH, Objects.toString(this.contextPath, ""));
        properties.put(CLUSTER_NAME, Objects.toString(this.clusterName, ""));
        properties.put(ENDPOINT, Objects.toString(this.endpoint, "acm.aliyun.com"));
        try {
            configService = NacosFactory.createConfigService(properties);
            return configService;
        }
        catch (Exception e) {
            log.error("创建配置服务错误!properties={},e=,", this, e);
            return null;
        }
    }
}
