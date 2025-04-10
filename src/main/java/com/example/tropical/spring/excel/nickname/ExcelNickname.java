package com.example.tropical.spring.excel.nickname;

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

        List<NicknamesEntity> allNicks = nicknamesMapper.findAll();

        XSSFSheet nicksPage = workbook.createSheet("NICKNAMES");
        allNicks.forEach(nick -> populateExcelNickname(nicksPage, nick));

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
        titulos.add("VENDEDOR");
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
        celula.setCellValue(obj.getNickname().toUpperCase());

        celula = linha.createCell(1);
        celula.setCellValue(obj.getCustomerBy().toUpperCase());

        celula = linha.createCell(2);
        celula.setCellValue(obj.getLojista().toUpperCase());
    }
}
