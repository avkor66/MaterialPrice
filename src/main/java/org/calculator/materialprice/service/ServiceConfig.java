package org.calculator.materialprice.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.services")
@Data
public class ServiceConfig {
    private String application;
    private String user;
    private String materialPrice;
    private String costCalculation;
    private String frontend;
}
