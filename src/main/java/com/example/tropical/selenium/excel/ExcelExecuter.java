package com.example.tropical.selenium.excel;

import com.example.tropical.selenium.model.AdSalesMLResponse;
import com.example.tropical.spring.entity.nicknames.NicknamesEntity;
import com.example.tropical.spring.entity.products.ProductsEntity;
import com.example.tropical.spring.mapper.nicknames.NicknamesMapper;
import com.example.tropical.spring.mapper.products.ProductsMapper;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.tropical.selenium.excel.helper.ExcelHelper.populateExcel;
import static com.example.tropical.selenium.excel.product.ExcelProduct.populateExcelProduct;

@AllArgsConstructor
@Component
public final class ExcelExecuter {

    private final NicknamesMapper nicknamesMapper;
    private final ProductsMapper productsMapper;

    public void createExcel(List<AdSalesMLResponse> relatorio) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();

        List<NicknamesEntity> nicknames = this.nicknamesMapper.findaAll();

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
        XSSFSheet desconhecido = workbook.createSheet("DESCONHECIDO");
        XSSFSheet pms = workbook.createSheet("PMS");

        relatorio.forEach(ad ->  {

            List<NicknamesEntity> objFiltrado = nicknames.stream()
                    .filter(nickname -> ad.getNickNameSeller().equals(nickname.getNickname()))
                    .collect(Collectors.toList());

            if (!objFiltrado.isEmpty()) {

                int indexOfNickNameEqual = nicknames.indexOf(objFiltrado.get(0));
                String vendedorTropical = nicknames.get(indexOfNickNameEqual).getCustomerBy();
                String lojista = nicknames.get(indexOfNickNameEqual).getLojista();

                System.out.println(vendedorTropical);

                switch (vendedorTropical) {
                    case "eveline":
                        populateExcel(eveline, ad, lojista);
                        break;
                    case "maciel":
                        populateExcel(maciel, ad, lojista);
                        break;
                    case "paola":
                        populateExcel(paola, ad, lojista);
                        break;
                    case "rodrigo reis":
                        populateExcel(rodrigoReis, ad, lojista);
                        break;
                    case "eleandro":
                        populateExcel(eleandro, ad, lojista);
                        break;
                    case "thomas":
                        populateExcel(thomas, ad, lojista);
                        break;
                    case "diego":
                        populateExcel(diego, ad, lojista);
                        break;
                    case "willian":
                        populateExcel(willian, ad, lojista);
                        break;
                    case "cesar":
                        populateExcel(cesar, ad, lojista);
                        break;
                    case "patrick":
                        populateExcel(patrick, ad, lojista);
                        break;
                    case "augusto":
                        populateExcel(augusto, ad, lojista);
                        break;
                    case "carlos eduardo":
                        populateExcel(carlosEduardo, ad, lojista);
                        break;
                    case "rafael":
                        populateExcel(rafael, ad, lojista);
                }
                objFiltrado.clear();
            } else  {
                populateExcel(desconhecido, ad, null);
            }
        });

        List<ProductsEntity> products = productsMapper.findAllProducts();

        products.forEach(prod -> populateExcelProduct(pms, prod));

        FileOutputStream out = null;
        try {
            out = new FileOutputStream("tropical-ml.xlsx");
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
}
