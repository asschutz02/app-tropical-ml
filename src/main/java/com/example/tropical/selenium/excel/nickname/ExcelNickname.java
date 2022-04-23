package com.example.tropical.selenium.excel.nickname;

import com.example.tropical.spring.entity.nicknames.NicknamesEntity;
import com.example.tropical.spring.mapper.nicknames.NicknamesMapper;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ExcelNickname {

    private final NicknamesMapper nicknamesMapper;

    public void createExcelNickname() {
        XSSFWorkbook workbook = new XSSFWorkbook();

        List<NicknamesEntity> allNicks = nicknamesMapper.findaAll();

        XSSFSheet eveline = workbook.createSheet("EVELINE");
        XSSFSheet maciel = workbook.createSheet("MACIEL");
        XSSFSheet paola = workbook.createSheet("PAOLA");
        XSSFSheet rodrigoReis = workbook.createSheet("RODRIGO REIS");
        XSSFSheet eleandro = workbook.createSheet("ELEANDRO");
        XSSFSheet thomas = workbook.createSheet("THOMAS");
        XSSFSheet diego = workbook.createSheet("DIEGO");
        XSSFSheet willian = workbook.createSheet("WILLIAN");
        XSSFSheet cesar = workbook.createSheet("CÉSAR");
        XSSFSheet patrick = workbook.createSheet("PATRICK");
        XSSFSheet augusto = workbook.createSheet("AUGUSTO");
        XSSFSheet carlosEduardo = workbook.createSheet("CARLOS EDUARDO");
        XSSFSheet rafael = workbook.createSheet("RAFAEL");

        allNicks.forEach(nick -> {
            switch (nick.getCustomerBy().toLowerCase()) {
                case "eveline":
                    populateExcelNickname(eveline, nick);
                    break;
                case "maciel":
                    populateExcelNickname(maciel, nick);
                    break;
                case "paola":
                    populateExcelNickname(paola, nick);
                    break;
                case "rodrigo reis":
                    populateExcelNickname(rodrigoReis, nick);
                    break;
                case "eleandro":
                    populateExcelNickname(eleandro, nick);
                    break;
                case "thomas":
                    populateExcelNickname(thomas, nick);
                    break;
                case "diego":
                    populateExcelNickname(diego, nick);
                    break;
                case "willian":
                    populateExcelNickname(willian, nick);
                    break;
                case "cesar":
                    populateExcelNickname(cesar, nick);
                    break;
                case "patrick":
                    populateExcelNickname(patrick, nick);
                    break;
                case "augusto":
                    populateExcelNickname(augusto, nick);
                    break;
                case "carlos eduardo":
                    populateExcelNickname(carlosEduardo, nick);
                    break;
                case "rafael":
                    populateExcelNickname(rafael, nick);
                    break;
            }
        });
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("nicknames-tropical-ml.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert out != null;
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void populateExcelNickname(XSSFSheet paginaDoExcel, NicknamesEntity obj){
        List<String> titulos = new ArrayList<>();
        titulos.add("NICKNAME");
        titulos.add("LOJISTA");

        int tamanhoDaPagina = paginaDoExcel.getPhysicalNumberOfRows();

        int numLinha;

        if(tamanhoDaPagina == 0 ){
            numLinha = 0;
        } else {
            numLinha = tamanhoDaPagina + 1;
        }

        Row linha;

        if(tamanhoDaPagina == 0 ) {
            linha = paginaDoExcel.createRow(numLinha);
            numLinha++;

            for (int i = 0; i < titulos.size(); i++) {
                Cell celula = linha.createCell(i);
                celula.setCellValue(titulos.get(i));
            }
        }

        linha = paginaDoExcel.createRow(numLinha);

        Cell celula = linha.createCell(0);
        celula.setCellValue(obj.getNickname());

        celula = linha.createCell(1);
        celula.setCellValue(obj.getLojista());
    }
}
