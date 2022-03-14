package com.wwdy.gateway.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wwdy
 * @date 2022/2/23 16:37
 */
@Configuration
public class OssRouter {

    @Bean
    public RouteLocator ossRoute(RouteLocatorBuilder builder) {
       return builder.routes()
                .route(predicateSpec ->
                        predicateSpec.path("/api/oss/**")
                                .filters(gatewayFilterSpec ->
                                        gatewayFilterSpec.rewritePath("/api/oss/?(?<segment>.*)", "/api/$\\{segment}"))
                                .uri("lb://online-shopping-oss"))
                .build();
    }
}
