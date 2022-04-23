package com.example.tropical.spring.service.nicknames.email;

import com.example.tropical.selenium.excel.nickname.ExcelNickname;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.tropical.selenium.email.nickname.EmailJavaNicknameSender.emailJavaNicknameSender;

@Service
@AllArgsConstructor
public class NicknameEmailService {

    private final ExcelNickname excelNickname;

    public void nicknameEmailRelatorio() {
        excelNickname.createExcelNickname();
        emailJavaNicknameSender();
    }
}
