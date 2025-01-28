package com.example.quizzzin.controllers.view;

import com.example.quizzzin.models.dto.other.ModeratorEnrollmentDTO;
import com.example.quizzzin.models.entities.ModeratorEnrollmentRequest;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.services.ModeratorEnrollmentRequestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/moderators")
@SessionAttributes("moderatorDTO")
@Slf4j
@AllArgsConstructor
public class ModeratorEnrollmentController {
    private final ModeratorEnrollmentRequestService moderatorEnrollmentRequestService;

    @GetMapping("/rules")
    public String getRules(Model model) {
        model.addAttribute("moderatorDTO", new ModeratorEnrollmentDTO());
        return "moderator/enrollment/rules";
    }

    @PostMapping("/rules")
    public String acceptRules(@Validated(ModeratorEnrollmentDTO.StepOne.class) @ModelAttribute("moderatorDTO") ModeratorEnrollmentDTO moderatorEnrollmentDTO,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "moderator/enrollment/rules";

        moderatorEnrollmentDTO.setStepOneCompleted(true);

        return "redirect:/moderators/personal-info";
    }

    @GetMapping("/personal-info")
    public String getPersonalInfo(@ModelAttribute("moderatorDTO") ModeratorEnrollmentDTO moderatorEnrollmentDTO) {
        if (moderatorEnrollmentDTO == null ||
                !moderatorEnrollmentDTO.isStepOneCompleted())
            return "redirect:/moderators/rules";

        return "moderator/enrollment/personal-info";
    }

    @PostMapping("/personal-info")
    public String processPersonalInfo(@Validated(ModeratorEnrollmentDTO.StepTwo.class) @ModelAttribute("moderatorDTO") ModeratorEnrollmentDTO moderatorEnrollmentDTO,
                                      @AuthenticationPrincipal User user,
                                      BindingResult bindingResult,
                                      SessionStatus sessionStatus) {
        if (bindingResult.hasErrors())
            return "moderator/enrollment/personal-info";

        ModeratorEnrollmentRequest moderatorEnrollmentRequest = moderatorEnrollmentRequestService.saveRequest(moderatorEnrollmentDTO, user.getEmail());

        log.info("Moderator request was saved: {}", moderatorEnrollmentRequest);

        sessionStatus.setComplete();

        return "redirect:/moderators/requests/" + moderatorEnrollmentRequest.getId();
    }

    @GetMapping("/requests/{id}")
    public String viewRequest(@PathVariable Long id) {
        return "moderator/request";
    }
}