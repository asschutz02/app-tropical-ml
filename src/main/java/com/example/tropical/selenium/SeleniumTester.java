package com.example.tropical.selenium;

import com.example.tropical.selenium.model.AdSalesMLResponse;
import com.example.tropical.spring.entity.nicknames.NicknamesEntity;
import com.example.tropical.spring.entity.products.ProductsEntity;
import com.example.tropical.spring.entity.seller.SellerEntity;
import com.example.tropical.spring.mapper.nicknames.NicknamesMapper;
import com.example.tropical.spring.mapper.products.ProductsMapper;
import com.example.tropical.spring.mapper.seller.SellerMapper;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javamoney.moneta.Money;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static com.example.tropical.selenium.decorator.AdSalesMLResponseDecorator.getNickName;
import static javax.money.Monetary.getCurrency;

@AllArgsConstructor
@Component
public class SeleniumTester {

    private final ProductsMapper productsMapper;
    private final SellerMapper sellerMapper;
    private final NicknamesMapper nicknamesMapper;

//    public static void main(String[] args) throws IOException {

//        String firstPage = searchProductByName();
//
//        List<String> links = linksPage(firstPage);
//
//        List<AdSalesMLResponse> relatorio = getProductsInfo(links);
//
//        System.out.println("relatório: " + relatorio);
//
//        createExcel(relatorio);
//
//        emailSender();
//    }

    public void executeSelenium() {
        System.out.println("Executando o programa");
        List<ProductsEntity> products = this.productsMapper.findAllProducts();
        System.out.println("products: " + products);
        List<AdSalesMLResponse> relatorio = new ArrayList<>();

        products.forEach(product -> {
            String firstPage = searchProductByName(product.getName());

            List<String> links = linksPage(firstPage);

            List<AdSalesMLResponse> relatorioIndividual = getProductsInfo(links, product);

            relatorio.addAll(relatorioIndividual);
        });

        System.out.println("relatório: " + relatorio);
        System.out.println("Tamanho do relatório: " + relatorio.size());

        try {
            createExcel(relatorio);
        } catch (IOException e) {
            e.printStackTrace();
        }

        emailSender();
    }

    private void createExcel(List<AdSalesMLResponse> relatorio) throws IOException {

        //percorrer a lista de anuncios e a lista de nicknames;
        //se o nickname for igual nas duas lista, pegar o index do nickname na sua lista;
        //pegar o eveline respectivo aquele nickname e comparar com a lista de vendedores;
        //se existir adicionar, se não criar aba desconhecido;

        XSSFWorkbook workbook = new XSSFWorkbook();

        List<SellerEntity> sellers = this.sellerMapper.findaAll();
        List<NicknamesEntity> nicknames = this.nicknamesMapper.findaAll();

//        sellers.forEach(seller -> {
//            nicknames.forEach(nickname -> {
                XSSFSheet eveline = workbook.createSheet("EVELINE");
                XSSFSheet maciel = workbook.createSheet("MACIEL");
                XSSFSheet paola = workbook.createSheet("PAOLA");
                XSSFSheet rodrigoReis = workbook.createSheet("RODIRGO REIS");
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

                relatorio.forEach(ad -> nicknames.forEach(nickname -> {
                    if(ad.getNickNameSeller().equals(nickname.getNickname())) {
                        Integer indexOfNickNameEqual = nicknames.indexOf(nickname);
                        String vendedorTropical = nicknames.get(indexOfNickNameEqual).getCustomerBy();

                        switch (vendedorTropical){
                            case "Eveline":
                                //logica
                                populateExcel(eveline, ad);
                                System.out.println("Eveline");
                                break;
                            case "Maciel":
                                populateExcel(maciel, ad);
                                System.out.println("Maciel");
                                break;
                            case "PAOLA":
                                populateExcel(paola, ad);
                                System.out.println("PAOLA");
                                break;
                            case "RODRIGO REIS":
                                populateExcel(rodrigoReis, ad);
                                System.out.println("RODRIGO REIS");
                                break;
                            case "ELEANDRO":
                                populateExcel(eleandro, ad);
                                System.out.println("ELEANDRO");
                                break;
                            case "THOMAS":
                                populateExcel(thomas, ad);
                                System.out.println("THOMAS");
                                break;
                            case "DIEGO":
                                populateExcel(diego, ad);
                                System.out.println("DIEGO");
                                break;
                            case "WILLIAN":
                                populateExcel(willian, ad);
                                System.out.println("WILLIAN");
                                break;
                            case "CÉSAR":
                                populateExcel(cesar, ad);
                                System.out.println("CÉSAR");
                                break;
                            case "PATRICK":
                                populateExcel(patrick, ad);
                                System.out.println("PATRICK");
                                break;
                            case "AUGUSTO":
                                populateExcel(augusto, ad);
                                System.out.println("AUGUSTO");
                                break;
                            case "CARLOS EDUARDO":
                                populateExcel(carlosEduardo, ad);
                                System.out.println("CARLOS EDUARDO");
                                break;
                            case "RAFAEL":
                                populateExcel(rafael, ad);
                                System.out.println("RAFAEL");
                        }
                    } else {
                        populateExcel(desconhecido, ad);
                        System.out.println("desconhecido");
                        //LÓGICA DO DESCONHECIDO
                    }
                }));



                FileOutputStream out = null;
                try {
                    out = new FileOutputStream("C:\\estudos_java\\teste.xlsx");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    workbook.write(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            });
//
//        });

    }

    private static void populateExcel(XSSFSheet paginaDoExcel, AdSalesMLResponse adSalesMLResponse){
        List<String> titulos = new ArrayList<>();
                titulos.add("PRODUTO");
                titulos.add("LINK");
                titulos.add("VALOR ANÚNCIO");
                titulos.add("NICKNAME");
                titulos.add("LINK ANUNCIANTE");
                titulos.add("LOJA");

            int numLinha = 0; // representa o número da linha da planilha

    // primeiro criamos a linha com os títulos
        Row linha = paginaDoExcel.createRow(numLinha);
        numLinha++;

        for(int i = 0; i < titulos.size(); i++){
            // cria uma nova célula
            Cell celula = linha.createCell(i);
            celula.setCellValue(titulos.get(i));
        }

//        numLinha++;

        linha = paginaDoExcel.createRow(numLinha);

        Cell celula = linha.createCell(0);
        celula.setCellValue(adSalesMLResponse.getProductName());

        celula = linha.createCell(1);
        celula.setCellValue(adSalesMLResponse.getLinkAd());

        celula = linha.createCell(2);
        if(!Objects.isNull(adSalesMLResponse.getPrice())){
            celula.setCellValue(adSalesMLResponse.getPrice());
        }

        celula = linha.createCell(3);
        celula.setCellValue(adSalesMLResponse.getNickNameSeller());

        celula = linha.createCell(4);
        celula.setCellValue(adSalesMLResponse.getLinkSeller());
    }

//    List<String> titulos = new ArrayList<>();
//                titulos.add("PRODUTO");
//                titulos.add("LINK");
//                titulos.add("VALOR ANÚNCIO");
//                titulos.add("NICKNAME");
//                titulos.add("LINK ANUNCIANTE");
//                titulos.add("LOJA");
//
//    int numLinha = 0; // representa o número da linha da planilha
//
//    // primeiro criamos a linha com os títulos
//    Row linha = eveline.createRow(numLinha);
//
//                for(int i = 0; i < titulos.size(); i++){
//        // cria uma nova célula
//        Cell celula = linha.createCell(i);
//        celula.setCellValue(titulos.get(i));
//    }
//
//    numLinha++;
//
//                for (AdSalesMLResponse adSalesMLResponse : relatorio) {
////                    if(adSalesMLResponse.getNickNameSeller().equals(nickname.getNickname()))
//        linha = eveline.createRow(numLinha);
//
//        Cell celula = linha.createCell(0);
//        celula.setCellValue(adSalesMLResponse.getProductName());
//
//        celula = linha.createCell(1);
//        celula.setCellValue(adSalesMLResponse.getLinkAd());
//
//        celula = linha.createCell(2);
//        if(!Objects.isNull(adSalesMLResponse.getPrice())){
//            celula.setCellValue(adSalesMLResponse.getPrice());
//        }
//
//        celula = linha.createCell(3);
//        celula.setCellValue(adSalesMLResponse.getNickNameSeller());
//
//        celula = linha.createCell(4);
//        celula.setCellValue(adSalesMLResponse.getLinkSeller());
//
//        numLinha++;
//    }

    private static void emailSender(){
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("arthurschutzdasilva@gmail.com",
                                "arthurschutz2002");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("arthurschutzdasilva@gmail.com"));

            Address[] toUser = InternetAddress
                    .parse("arthur.schutz123@gmail.com");

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Enviando email com JavaMail");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File("C:\\estudos_java\\teste.xlsx"));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static List<String> linksPage(String firstPage) {

        WebDriver webDriver = new ChromeDriver();

        webDriver.navigate().to(firstPage);

        WebElement pageNumber = webDriver.findElement(By.xpath("//li[contains(@class, 'andes-pagination__page-count')]"));

        int numberPage = getNumberOfPageResults(pageNumber);

        List<String> pageLinks = new ArrayList<>();

        pageLinks.add(firstPage);

        for (int i = 0; i <= numberPage; i++) {
            if (i == 0) {
                WebElement btnNextLink = webDriver.findElement(By.xpath("//a[contains(@class, 'andes-pagination__link ui-search-link')][1]"));
                String hrefNextBtn = btnNextLink.getAttribute("href");
                pageLinks.add(hrefNextBtn);
                webDriver.navigate().to(hrefNextBtn);
            } else {
                List<WebElement> btnNextLink = webDriver.findElements(By.xpath("//a[contains(@class, 'andes-pagination__link ui-search-link')]"));
                btnNextLink.forEach(btnT -> {
                    if (btnT.getText().equals("Seguinte")) {
                        String hrefNextBtn = btnT.getAttribute("href");
                        pageLinks.add(hrefNextBtn);
                        webDriver.navigate().to(hrefNextBtn);
                    }
                });
            }
        }

        webDriver.close();

        return pageLinks;
    }

    private static String searchProductByName(String productName) {

        WebDriver webDriver = new ChromeDriver();

        String baseUrl = "https://www.mercadolivre.com.br/";

        webDriver.get(baseUrl);

        WebElement input = webDriver.findElement(By.className("nav-search-input"));

//        input.sendKeys("bomba ac 20000 ocean");
//        input.sendKeys("Bomba ac 6000 Ocean Tech");
        input.sendKeys(productName);
//        input.sendKeys("Nano ring bacterial 1 kg");

        WebElement btn = webDriver.findElement(By.className("nav-search-btn"));

        btn.click();

        String firstPage = webDriver.getCurrentUrl();

        webDriver.close();

        return firstPage;

    }

    private static List<AdSalesMLResponse> getProductsInfo(List<String> pageLinks, ProductsEntity products) {

        List<AdSalesMLResponse> finalList = new ArrayList<>();

        pageLinks.forEach(pgLink -> {
            WebDriver webDriver = new ChromeDriver();
            webDriver.manage().deleteAllCookies();
            webDriver.navigate().to(pgLink);

            try {
                List<String> linksResult = getProductInfo(webDriver, products);
                linksResult.forEach(link -> {
                    webDriver.navigate().to(link); //aqui pego link do anuncio

                    WebElement href = webDriver.findElement(By.xpath("//a[contains(@class, 'ui-pdp-media__action ui-box-component__action')]"));
                    String linkSeller = href.getAttribute("href"); //aqui pego link do vendedor

                    String productName = webDriver.findElement(By.className("ui-pdp-title")).getText();

                    AdSalesMLResponse adResponse = new AdSalesMLResponse();
                    adResponse.setProductName(productName);
                    adResponse.setLinkAd(link);
                    adResponse.setLinkSeller(linkSeller);
                    adResponse.setNickNameSeller(getNickName(linkSeller));
                    adResponse.setActualDate(LocalDate.now());

                    List<WebElement> prices = webDriver.findElements(By.className("andes-money-amount__fraction"));

                    prices.forEach(price -> {
                        String fontSize = price.getCssValue("font-size");
                        if(fontSize.equals("36px")){
                            Double bigPrice = Double.valueOf(price.getText()); //aqui pego preço
                            adResponse.setPrice(bigPrice);
                        }
                    });

                    finalList.add(adResponse);
                });
            } catch (StaleElementReferenceException ex) {
                List<String> linksResult = getProductInfo(webDriver, products);
                linksResult.forEach(link -> {
                    webDriver.navigate().to(link); //aqui pego link do anuncio

                    WebElement href = webDriver.findElement(By.xpath("//a[contains(@class, 'ui-pdp-media__action ui-box-component__action')]"));
                    String linkSeller = href.getAttribute("href"); //aqui pego link do vendedor

                    String productName = webDriver.findElement(By.className("ui-pdp-title")).getText();

                    AdSalesMLResponse adResponse = new AdSalesMLResponse();
                    adResponse.setProductName(productName);
                    adResponse.setLinkAd(link);
                    adResponse.setLinkSeller(linkSeller);
                    adResponse.setNickNameSeller(getNickName(linkSeller));
                    adResponse.setActualDate(LocalDate.now());

                    WebElement price = webDriver.findElement(By.className("andes-money-amount__fraction"));
                    String fontSize = price.getCssValue("font-size");
                    if(fontSize.equals("36px")){
                        Double bigPrice = Double.valueOf(price.getText()); //aqui pego preço
                        adResponse.setPrice(bigPrice);
                    }

                    finalList.add(adResponse);
                });
            }
            webDriver.close();
        });

        return finalList;
    }


    private static List<String> getProductInfo(WebDriver webDriver, ProductsEntity products) {
        List<String> links = new ArrayList<>();

        List<WebElement> productLinksGrid = webDriver.findElements(By.xpath("//a[contains(@class, 'ui-search-result__content ui-search-link')]"));

        List<WebElement> productLinksLine = webDriver.findElements(By.xpath("//a[contains(@class, 'ui-search-item__group__element ui-search-link')]"));

        if(productLinksGrid.size() > 0){
            filterPrices(productLinksGrid, webDriver, links, products);
        } else {
            filterPrices(productLinksLine, webDriver, links, products);
        }
        return links;
    }

    private static void filterPrices(List<WebElement> productLinks, WebDriver webDriver, List<String> links, ProductsEntity products){
        List<WebElement> allPrices = webDriver.findElements(By.xpath("//span[contains(@class, 'price-tag-fraction')]"));


        allPrices.removeIf(price -> !price.getCssValue("font-size").equals("24px"));

        System.out.println("Tamanho link prices: " + allPrices.size());

        allPrices.forEach(priceML -> {
            CurrencyUnit real = getCurrency("BRL");
            String priceInt = priceML.getText().replace(".", "");
            String priceFinal = priceInt.replace(".", "");
            Integer price = Integer.valueOf(priceFinal);

            MonetaryAmount money = Money.of(price, real);

            if(comparePrice(money, real, products.getPrice())){
                int indexPrice = allPrices.indexOf(priceML);

                if(productLinks.get(indexPrice).getAttribute("href").contains("MLB")) {
                    links.add(productLinks.get(indexPrice).getAttribute("href"));
                }
            }
        });
    }

    private static Boolean comparePrice(MonetaryAmount moneyML, CurrencyUnit real, Double realPrice){

//        Double realPrice = 837.0;
//        Double realPrice = 837.0;
//        Double realPrice = 78.00;
//        Double realPrice = 1412.00;
        MonetaryAmount moneyReal = Money.of(realPrice, real);

        MonetaryAmount halfMoney = moneyReal.divide(2L);

        MonetaryAmount quarterMoney = halfMoney.divide(2L);

        MonetaryAmount threeQuarter = halfMoney.add(quarterMoney);

        return moneyML.isLessThan(moneyReal) && moneyML.isGreaterThan(threeQuarter);
    }

    private static Integer getNumberOfPageResults(WebElement pageNumber) {
        String onlyNumber = pageNumber.getText().replace(" ", "");

        if (onlyNumber.length() == 3) {
            String justTheNumber = onlyNumber.substring(2);
            return Integer.valueOf(justTheNumber);
        } else {
            String justTheNumber = onlyNumber.substring(2, 4);
            return Integer.valueOf(justTheNumber);
        }
    }
}