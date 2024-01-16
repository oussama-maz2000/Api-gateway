package com.lgali.gateway;

import java.util.Set;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.lgali.gateway.filter.ConfigFilter;
import com.lgali.gateway.filter.UserFilter;

@Component
public class GatewayPathConfig {

    private static final String MS_REQUEST  = "MS-REQUEST-SERVICE";
    private static final String MS_RESPONSE = "MS-RESPONSE-SERVICE";
    private static final String MS_ACCOUNT  = "MS-ACCOUNT-SERVICE";

    private static final String PREFIX_URI = "lb://";

    /**
     * Routing to Request microservice
     */
    @Bean
    public RouteLocator gatewayRequestRoutes(final RouteLocatorBuilder builder) {

        final String REQUEST = "/api/request/**";
        final Set<String> urls = Set.of(REQUEST);
        return buildRoutes(builder.routes(), urls, MS_REQUEST);
    }

    /**
     * Routing to Request microservice
     */
    @Bean
    public RouteLocator gatewayResponseRoutes(final RouteLocatorBuilder builder) {

        final String RESPONSE = "/api/response/**";
        final Set<String> urls = Set.of(RESPONSE);
        return buildRoutes(builder.routes(), urls, MS_RESPONSE);
    }

    /**
     * Routing to Request microservice
     */
    @Bean
    public RouteLocator gatewayAccountRoutes(final RouteLocatorBuilder builder) {

        final String ACCOUNT = "/api/account/**";
        final String PROFILE = "/api/profile/**";
        final String USER = "/api/user/**";
        final String COMPANY = "/api/company/**";
        final String ADDRESS = "/api/address/**";
        final Set<String> urls = Set.of(ACCOUNT, PROFILE, USER, COMPANY, ADDRESS);
        return buildRoutes(builder.routes(), urls, MS_ACCOUNT);
    }

    /**
     * Use builder to route given urls to the microservice
     */
    private RouteLocator buildRoutes(final RouteLocatorBuilder.Builder builder,
                                     final Set<String> urls,
                                     final String ms) {
        for (final String url : urls) {
            builder
              .route(r -> r.path(url)
                           .filters(f -> f.filter(new UserFilter().apply(new ConfigFilter())))
                           .uri(PREFIX_URI + ms));
        }
        return builder.build();
    }

}
