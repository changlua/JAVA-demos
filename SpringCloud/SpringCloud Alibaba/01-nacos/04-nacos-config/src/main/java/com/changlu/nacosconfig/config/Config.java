package com.changlu.nacosconfig.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: changlu
 * @Date: 10:12 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
//@RefreshScope
@ConfigurationProperties(prefix = "config")
public class Config {

//    @Value("${config.name}")
    private String name;

//    @Value("${config.type}")
    private String type;

    private String envSharedValue;

    private String a;

    private String b;

}
