package org.sandbox;

import org.sandbox.speaker.Speaker;
import org.sandbox.speaker.SpeakerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import static java.util.Arrays.asList;

@SpringBootApplication
@EnableDiscoveryClient
public class ConferenceServiceApplication {

    public static void main(String... args) {
        SpringApplication.run(ConferenceServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(SpeakerRepository speakerRepository) {
        return args -> {
            asList("Josh Long,Mikalai Alimenkou,Yakov Fain,Vladimir Tsukur".split(","))
                    .forEach(name -> speakerRepository.save(new Speaker(name)));

            speakerRepository.findAll().forEach(System.out::println);
        };
    }

    @Bean
    HealthIndicator hello() {
        return () -> Health.status("Hello!").build();
    }
}
