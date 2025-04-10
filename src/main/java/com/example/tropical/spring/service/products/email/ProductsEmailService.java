package com.example.tropical.spring.service.products.email;

import com.example.tropical.spring.excel.product.ExcelProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.tropical.spring.service.products.email.EmailJavaProductsSender.emailJavaProductSender;

@Service
@AllArgsConstructor
public class ProductsEmailService {

    private final ExcelProduct excelProduct;

    public void productEmailRelatorio(){
        excelProduct.createExcelProduct();
        emailJavaProductSender();
    }
}
