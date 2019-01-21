package com.freshtxp.dynamic.source;

import lombok.Getter;
import org.springframework.core.env.MapPropertySource;

import java.util.Date;
import java.util.Map;

/**
 * 配置文件对象 {@link MapPropertySource}
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/1
 */
@Getter
public class PropertySource extends MapPropertySource {

    /**
     * Nacos dataID
     */
    private final String dataId;

    /**
     * timestamp the property get
     */
    private final Date timestamp;


    PropertySource(String dataId, Map<String, Object> source, Date timestamp) {
        super(dataId, source);
        this.dataId = dataId;
        this.timestamp = timestamp;
    }
}
