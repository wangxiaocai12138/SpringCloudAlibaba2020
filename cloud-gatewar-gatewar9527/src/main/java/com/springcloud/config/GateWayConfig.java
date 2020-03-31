package com.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class GateWayConfig {
    //每一个转发需要配置一个新的映射
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route1",
                            r -> r.path("/guonei")  //访问地址
                                    .uri("http://news.baidu.com/guonei")).build();  //转发地址
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route2",
                r -> r.path("/guoji")  //访问地址
                        .uri("http://news.baidu.com/guoji")).build();  //转发地址
        return routes.build();
    }
}
