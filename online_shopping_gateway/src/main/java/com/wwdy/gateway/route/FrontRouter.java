package com.wwdy.gateway.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wwdy
 * @date 2022/4/3 11:26
 */
@Configuration
public class FrontRouter{

    @Bean("front_module")
    public RouteLocator frontRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(predicateSpec ->
                    predicateSpec.path("/api/front/**")
                            .filters(gatewayFilterSpec ->
                                gatewayFilterSpec.rewritePath("/api/front/?(?<segment>.*)","/api/$\\{segment}")
                            )
                            .uri("lb://online-shopping-front")
                ).build();
    }
}
