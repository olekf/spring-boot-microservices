package org.sandbox;

import org.sandbox.speaker.Speaker;
import org.sandbox.speaker.SpeakerRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
@EnableZuulProxy
public class ConferenceServiceClientApplication {

    public static void main(String... args) {
        SpringApplication.run(ConferenceServiceClientApplication.class, args);
    }

    @Bean
    CommandLineRunner serviceDiscovery(final DiscoveryClient discoveryClient) {
        return args -> discoveryClient.getInstances("conference-service")
                .forEach(serviceInstance -> System.out.println(serviceInstance.getServiceId() + " " +
                        serviceInstance.getHost() + ":" +
                        serviceInstance.getPort()));
    }

    @Bean
    CommandLineRunner restTemplate(RestTemplate restTemplate) {
        return args -> {
            ParameterizedTypeReference<List<Speaker>> parameterizedTypeReference =
                    new ParameterizedTypeReference<List<Speaker>>() {
                    };
            List<Speaker> speakers =
                    restTemplate.exchange("http://conference-service/speakers",
                            HttpMethod.GET, null, parameterizedTypeReference).getBody();
            speakers.forEach(System.out::println);
        };
    }

    @Bean
    CommandLineRunner restClient(SpeakerRestClient speakerRestClient) {
        return args -> speakerRestClient.getSpeakers().forEach(System.out::println);
    }
}
