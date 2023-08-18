package k8dockerkeycloak.gatewayserver;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "k8dockerkeycloak.gatewayserver.security")
public class GatewayserverApplication {
    @Autowired
    private TokenRelayGatewayFilterFactory tokenRelayGatewayFilterFactory;
    @Autowired
    private EurekaClient discoveryClient;
    @Autowired
    private TokenRelayGatewayFilterFactory filterFactory;

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    public String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("K8DOCKERKEYCLOAK", false);
        return instance.getHomePageUrl();
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/app/**")
                        .filters(f -> f.filters(tokenRelayGatewayFilterFactory.apply()).rewritePath("/app/(?<segment>.*)", "/api/${segment}")
                                .removeRequestHeader("Cookies"))
                        .uri(serviceUrl())).build();
    }
}