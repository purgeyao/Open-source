package com.txp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 *
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019/1/1
 */
@SpringBootApplication
public class TXPSpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TXPSpringApplication.class, args);

        ConfigurableEnvironment environment = run.getEnvironment();

        String property = environment.getProperty("user.name");
        System.out.println(property);

        MutablePropertySources propertySources = environment.getPropertySources();
        System.out.println(propertySources.toString());
        System.out.println("++++++++MutablePropertySources propertySources+++++++");
        for (PropertySource<?> bootstrapPropertie:propertySources){

            System.out.println(bootstrapPropertie.getName());
        }

        PropertySource<?> bootstrapProperties = propertySources.get("bootstrapProperties");


        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println(activeProfiles);
    }
}
