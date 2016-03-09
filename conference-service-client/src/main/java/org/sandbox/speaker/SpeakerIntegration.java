package org.sandbox.speaker;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

import static java.util.stream.Collectors.toList;

@Component
public class SpeakerIntegration {

    @Autowired
    private SpeakerRestClient speakerRestClient;

    Collection<String> getSpeakerNamesFallback() {
        return Collections.emptyList();
    }

    @HystrixCommand(fallbackMethod = "getSpeakerNamesFallback")
    Collection<String> getSpeakerNames() {
        return speakerRestClient.getSpeakers().stream()
                .map(Speaker::getName)
                .collect(toList());
    }
}
