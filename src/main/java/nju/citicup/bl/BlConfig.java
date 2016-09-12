package nju.citicup.bl;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admin on 2016/9/11.
 */
@Configuration
@ComponentScan(basePackages = "nju.citicup.bl")
@EnableConfigurationProperties
public class BlConfig {
}
