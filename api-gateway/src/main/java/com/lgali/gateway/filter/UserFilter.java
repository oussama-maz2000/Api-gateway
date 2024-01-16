package com.lgali.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserFilter extends AbstractGatewayFilterFactory<ConfigFilter> {

    @Override
    public GatewayFilter apply(final ConfigFilter config) {
        return (exchange, chain) -> {

            final ServerHttpRequest modifiedRequest = exchange.getRequest()
                                                              .mutate()
                                                              .header("USER_ROLE", "ADMIN")
                                                              .header("USER_TOKEN", "TOKEN")
                                                              .build();
            return chain.filter(exchange.mutate().request(modifiedRequest).build());

        };
    }

}
