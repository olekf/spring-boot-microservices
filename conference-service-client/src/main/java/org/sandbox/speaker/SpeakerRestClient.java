package org.sandbox.speaker;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@FeignClient("conference-service")
public interface SpeakerRestClient {

    @RequestMapping(value = "/speakers", method = RequestMethod.GET)
    Collection<Speaker> getSpeakers();
}
