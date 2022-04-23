package com.example.tropical.spring.controller.nicknames.email;

import com.example.tropical.spring.service.nicknames.email.NicknameEmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/tropical/nicknames/email")
@AllArgsConstructor
public class NicknameEmailController {

    private final NicknameEmailService nicknameEmailService;

    @GetMapping()
    public void nicknameEmailRelatorio() {
        nicknameEmailService.nicknameEmailRelatorio();
    }
}
