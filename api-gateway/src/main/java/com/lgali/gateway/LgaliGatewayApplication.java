package com.lgali.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class LgaliGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LgaliGatewayApplication.class, args);
    }


    @Bean
    DiscoveryClientRouteDefinitionLocator discoveryRoutes(ReactiveDiscoveryClient dc,
                                                          DiscoveryLocatorProperties dlp) {
        return new DiscoveryClientRouteDefinitionLocator(dc, dlp);
    }


}
