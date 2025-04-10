package com.example.tropical.spring.service.nicknames.email;

import com.example.tropical.spring.excel.nickname.ExcelNickname;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.tropical.spring.service.nicknames.email.EmailJavaNicknameSender.emailJavaNicknameSender;

@Service
@AllArgsConstructor
public class NicknameEmailService {

    private final ExcelNickname excelNickname;

    public void nicknameEmailRelatorio() {
        excelNickname.createExcelNickname();
        emailJavaNicknameSender();
    }
}
