package com.example.tropical.selenium.excel;

import static com.example.tropical.selenium.excel.helper.ExcelHelper.populateExcel;
import static com.example.tropical.selenium.excel.helper.ExcelHelper.validateNickname;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.example.tropical.selenium.model.AdSalesMLResponse;
import com.example.tropical.spring.entity.nicknames.NicknamesEntity;
import com.example.tropical.spring.entity.seller.SellerEntity;
import com.example.tropical.spring.mapper.nicknames.NicknamesMapper;
import com.example.tropical.spring.mapper.seller.SellerMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public final class ExcelExecuter {

	private final NicknamesMapper nicknamesMapper;

	public void createExcel(List<AdSalesMLResponse> relatorio) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();

		List<NicknamesEntity> nicknames = this.nicknamesMapper.findaAll();
		//		List<SellerEntity> sellers = this.sellerMapper.findaAll();
		//
		//		sellers.forEach(seller -> workbook.createSheet(seller.getName()));

		//		XSSFSheet desconhecido = workbook.createSheet("DESCONHECIDO");
		XSSFSheet geral = workbook.createSheet("GERAL");
		//		XSSFSheet inativo = workbook.createSheet("INATIVO");
		//		XSSFSheet augusto = workbook.createSheet("AUGUSTO");
		//		XSSFSheet carlosEduardo = workbook.createSheet("CARLOS EDUARDO");
		//		XSSFSheet cesar = workbook.createSheet("CÉSAR");
		//		XSSFSheet debora = workbook.createSheet("DÉBORA");
		//		XSSFSheet diego = workbook.createSheet("DIEGO");
		//		XSSFSheet eleandro = workbook.createSheet("ELEANDRO");
		//		XSSFSheet eveline = workbook.createSheet("EVELINE");
		//		XSSFSheet gabriela = workbook.createSheet("GABRIELA");
		//		XSSFSheet maciel = workbook.createSheet("MACIEL");
		//		XSSFSheet oziel = workbook.createSheet("OZIEL");
		//		XSSFSheet paola = workbook.createSheet("PAOLA");
		//		XSSFSheet patrick = workbook.createSheet("PATRICK");
		//		XSSFSheet rafael = workbook.createSheet("RAFAEL");
		//		XSSFSheet rodrigoReis = workbook.createSheet("REIS");
		//		XSSFSheet thomas = workbook.createSheet("TOMAS");
		//		XSSFSheet willian = workbook.createSheet("WILLIAN");
		//		XSSFSheet wwOlist = workbook.createSheet("WW OLIST");
		//		XSSFSheet wwwRafael = workbook.createSheet("WWW RAFAEL");
		//		XSSFSheet wwwReis = workbook.createSheet("WWW REIS");

		relatorio.forEach(ad -> {

			validateNickname(ad);

			List<NicknamesEntity> objFiltrado = nicknames.stream()
					.filter(nickname -> ad.getNickNameSeller().toLowerCase().equals(nickname.getNickname()))
					.collect(Collectors.toList());

			if (!objFiltrado.isEmpty()) {

				int indexOfNickNameEqual = nicknames.indexOf(objFiltrado.get(0));
				String vendedorTropical = nicknames.get(indexOfNickNameEqual).getCustomerBy();
				String lojista = nicknames.get(indexOfNickNameEqual).getLojista();

				System.out.println(vendedorTropical);

				//				switch (vendedorTropical) {
				//					case "eveline":
				//						populateExcel(eveline, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "EVELINE");
				//						break;
				//					case "maciel":
				//						populateExcel(maciel, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "MACIEL");
				//						break;
				//					case "paola":
				//						populateExcel(paola, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "PAOLA");
				//						break;
				//					case "reis":
				//						populateExcel(rodrigoReis, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "REIS");
				//						break;
				//					case "eleandro":
				//						populateExcel(eleandro, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "ELEANDRO");
				//						break;
				//					case "thomas":
				//						populateExcel(thomas, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "THOMAS");
				//						break;
				//					case "diego":
				//						populateExcel(diego, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "DIEGO");
				//						break;
				//					case "willian":
				//						populateExcel(willian, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "WILLIAN");
				//						break;
				//					case "cesar":
				//						populateExcel(cesar, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "CÉSAR");
				//						break;
				//					case "patrick":
				//						populateExcel(patrick, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "PATRICK");
				//						break;
				//					case "carlos eduardo":
				//						populateExcel(carlosEduardo, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "CARLOS EDUARDO");
				//						break;
				//					case "rafael":
				//						populateExcel(rafael, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "RAFAEL");
				//						break;
				//					case "oziel":
				//						populateExcel(oziel, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "OZIEL");
				//						break;
				//					case "augusto":
				//						populateExcel(augusto, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "AUGUSTO");
				//						break;
				//					case "debora":
				//						populateExcel(debora, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "DEBORA");
				//						break;
				//					case "gabriela":
				//						populateExcel(gabriela, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "GABRIELA");
				//						break;
				//					case "wwOlist":
				//						populateExcel(wwOlist, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "WW OLIST");
				//						break;
				//					case "wwwRafael":
				//						populateExcel(wwwRafael, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "WWW RAFAEL");
				//						break;
				//					case "wwwReis":
				//						populateExcel(wwwReis, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "WWW REIS");
				//						break;
				//					case "inativo":
				//						populateExcel(inativo, ad, lojista, null);
				//						populateExcel(geral, ad, lojista, "INATIVO");
				populateExcel(geral, ad, lojista, vendedorTropical.toUpperCase());
				//				}
				objFiltrado.clear();
			} else {
				//				populateExcel(desconhecido, ad, null, null);
				populateExcel(geral, ad, null, null);
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
