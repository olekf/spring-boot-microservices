package org.sandbox.speaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/speakers")
public class SpeakerNamesRestController {

    @Autowired
    private SpeakerIntegration speakerIntegration;

    @RequestMapping("/names")
    Collection<String> names() {
        return speakerIntegration.getSpeakerNames();
    }
}
