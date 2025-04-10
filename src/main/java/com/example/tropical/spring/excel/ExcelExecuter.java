package com.example.tropical.spring.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.example.tropical.spring.excel.helper.ExcelHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.example.tropical.spring.model.AdSalesMLResponse;
import com.example.tropical.spring.entity.nicknames.NicknamesEntity;
import com.example.tropical.spring.mapper.nicknames.NicknamesMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public final class ExcelExecuter {

	private final NicknamesMapper nicknamesMapper;

	public void createExcel(List<AdSalesMLResponse> relatorio) {
		XSSFWorkbook workbook = new XSSFWorkbook();

		List<NicknamesEntity> nicknames = this.nicknamesMapper.findAll();

		XSSFSheet geral = workbook.createSheet("GERAL");

		relatorio.forEach(ad -> {

			ExcelHelper.validateNickname(ad);

			List<NicknamesEntity> objFiltrado = nicknames.stream()
					.filter(nickname -> ad.getNickNameSeller().toLowerCase().equals(nickname.getNickname()))
					.collect(Collectors.toList());

			if (!objFiltrado.isEmpty()) {

				int indexOfNickNameEqual = nicknames.indexOf(objFiltrado.get(0));
				String vendedorTropical = nicknames.get(indexOfNickNameEqual).getCustomerBy();
				String lojista = nicknames.get(indexOfNickNameEqual).getLojista();

				System.out.println(vendedorTropical);

				ExcelHelper.populateExcel(geral, ad, lojista, vendedorTropical.toUpperCase());
				objFiltrado.clear();
			} else {
				ExcelHelper.populateExcel(geral, ad, null, null);
			}
		});

		FileOutputStream out = null;
		try {
			out = new FileOutputStream("OceanTech-ML.xlsx");
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
