package org.sandbox.speaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class SpeakerRestController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @RequestMapping("/speakers")
    Collection<Speaker> speakers() {
        return speakerRepository.findAll();
    }
}
