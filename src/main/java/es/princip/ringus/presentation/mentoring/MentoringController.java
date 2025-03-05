package es.princip.ringus.presentation.mentoring;

import es.princip.ringus.application.mentoring.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class MentoringController {

    private final MentoringService mentoringService;

}
