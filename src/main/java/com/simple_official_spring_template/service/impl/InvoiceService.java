package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.InvoiceConverter;
import com.simple_official_spring_template.dto.InvoiceDTO;
import com.simple_official_spring_template.dto.others.*;
import com.simple_official_spring_template.dto.others.trackingweb.TrackingWebDTO;
import com.simple_official_spring_template.dto.support_dto.SupportDTO;
import com.simple_official_spring_template.entity.InvoiceEntity;
import com.simple_official_spring_template.entity.InvoiceOriLinkEntity;
import com.simple_official_spring_template.repository.IInvoiceOriLinkRepository;
import com.simple_official_spring_template.repository.IInvoiceRepository;
import com.simple_official_spring_template.service.IInvoiceService;
import com.simple_official_spring_template.utils.nativequery.GeneralNativeQuery;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

@Service
//@Transactional(rollbackFor = Exception.class)
public class InvoiceService implements IInvoiceService {

    @Value("${dinhvb.automode}")
    private String autoMode;

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceConverter invoiceConverter;

    @Autowired
    private IInvoiceOriLinkRepository invoiceOriLinkRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GeneralNativeQuery generalNativeQuery;

    @Autowired
    private ResourceLoader resourceLoader;

//    @Value("${customdata.server}")
//    private String customDataServer;


    @Value("${searchPartnerInfoServer}")
    private String searchPartnerInfoServer;

    @Value("${itgreentool.server}")
    private String itgreenToolServer;


    @Override
    public InvoiceDTO save(InvoiceDTO invoiceDTO) {
        InvoiceEntity invoiceEntity = invoiceConverter.toEntity(invoiceDTO, new InvoiceEntity());
        return invoiceConverter.toDTO(invoiceRepository.save(invoiceEntity));
    }

    @Override
    public InvoiceDTO update(InvoiceDTO invoiceDTO) {
        InvoiceEntity invoiceEntity = invoiceRepository.findOneById(invoiceDTO.getId());
        invoiceEntity = invoiceConverter.toEntity(invoiceDTO, invoiceEntity);
        return invoiceConverter.toDTO(invoiceRepository.save(invoiceEntity));
    }

    @Override
    public InvoiceDTO findOneById(String id) {
        return invoiceConverter.toDTO(invoiceRepository.findOneById(id));
    }

    @Override
    public List<InvoiceDTO> findAll() {
        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        for (InvoiceEntity invoiceEntity : invoiceRepository.findAll()) {

//            String[] lst = invoiceEntity.getFullDate().split("_");
//
//            invoiceEntity.setYear(lst[0]);
//            invoiceEntity.setMonth(lst[1]);
//            invoiceEntity.setDate(lst[2]);
//
//            invoiceRepository.save(invoiceEntity);

//            InvoiceOriLinkEntity invoiceOriLinkEntity = invoiceOriLinkRepository.findOneByLink(invoiceEntity.getOriginalLink());
//            invoiceEntity.setCustomerId(invoiceOriLinkEntity.getCustomerId());
//            invoiceEntity.setCustomerName(invoiceOriLinkEntity.getCustomerName());
//            invoiceRepository.save(invoiceEntity);


            invoiceDTOS.add(invoiceConverter.toDTO(invoiceEntity));
        }
        return invoiceDTOS;
    }

    @Override
    public void delete(InvoiceDTO invoiceDTO) {
        invoiceRepository.deleteByIds(invoiceDTO.getIds());
    }

    @Override
//    @Scheduled(initialDelay = 0, fixedDelay = 24* 60 * 60 * 1000)
//    @Scheduled(cron = "0 0 0 * * *")
    public void crawlInvoice() {

//        try {
//            Runtime.getRuntime().exec("pkill firefox");
//        } catch (Exception ignored) {
//        }


        // LOAD PROFILE

//        String firefoxBinary = "/opt/firefox/firefox";
//        String firefoxProfile = "/home/dinhvb/snap/firefox/common/.mozilla/firefox/5d1a0h9z.hoadon";
//
////        firefoxBinary = "/usr/bin/firefox"; // local
//
//
//        WebDriverManager.firefoxdriver().setup();
//
//
//        FirefoxOptions opt = new FirefoxOptions();
//        File pathToBinary = new File(firefoxBinary);
//        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
//        opt.setBinary(ffBinary);
////        opt.addArguments("-private");
//
//
////                        File profileDirectory = new File(firefoxProfile);
////                        FirefoxProfile profile = new FirefoxProfile(profileDirectory);
//        FirefoxProfile profile = new FirefoxProfile();
//        profile.setAcceptUntrustedCertificates(true);
//
//
//        opt.setProfile(profile);
//        WebDriver driver = new FirefoxDriver(opt);


        // FIREFOX


        WebDriverManager.firefoxdriver().setup();

//
//        // LOAD PROFILE
//
        String firefoxBinary = System.getenv("FIREFOX_BINARY");
        String firefoxProfile = System.getenv("HD_PROFILE");

//            firefoxBinary = "/usr/bin/firefox";
//            firefoxProfile = "/home/dinhvb/.mozilla/firefox/ep2ji49n.click_ads";

        FirefoxOptions opt = new FirefoxOptions();
        File pathToBinary = new File(firefoxBinary);
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        opt.setBinary(ffBinary);


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("acceptInsecureCerts", "true");

        opt.merge(capabilities);


        File profileDirectory = new File(firefoxProfile);
        FirefoxProfile profile = new FirefoxProfile(profileDirectory);
        profile.setAcceptUntrustedCertificates(true);

        opt.setProfile(profile);
        WebDriver driver = new FirefoxDriver(opt);


        driver.manage().window().setSize(new Dimension(1920, 1080));

//        driver.manage().window().setPosition(new Point(-2000, 0));


        // CHROME

//        WebDriverManager.chromiumdriver().driverVersion("108.0.5359.71").setup();
//
//
////            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//
//
//        ChromeOptions options = new ChromeOptions();
//
//
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("disable-infobars");
////        options.addArguments("--disable-extensions");
//        options.addArguments("--disable-gpu");
//
//
//        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//        options.addArguments("--no-sandbox"); // Bypass OS security model
//
//        options.addArguments("user-data-dir=" + "/home/dinhvb/snap/chromium/common/hoadon");
//        options.addArguments("--profile-directory=" + "Default");
//
//        options.setBinary("/snap/bin/chromium");
//
//        options.addArguments("ignore-certificate-errors");
//
//        WebDriver driver = new ChromeDriver(options);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofSeconds(10));
        WebDriverWait fastWait = new WebDriverWait(driver, Duration.ofSeconds(40), Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ArrayList<String> tabs = null;


        //// MY CONFIG

        boolean targetCustomerMode = true;

        List<String> targetCustomerIds = new ArrayList<>();
        targetCustomerIds.add("7667640907");
        targetCustomerIds.add("9472367449");


        // fetch new links

        if (false) {
            driver.get("https://ads.google.com/aw/budgets?ocid=194893325&euid=102741392&__u=7656001808&uscid=194893325&__c=9578557925");


            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-table-row")));

            List<String> removedSelectors = new ArrayList<>();
            removedSelectors.add(".customer-note-label");


            do {

                for (String removedSelector : removedSelectors) {
                    String query = "document.querySelectorAll('" + removedSelector + "').forEach(element => element.style.display='none');";
                    try {
                        js.executeScript(query);
                    } catch (Exception ignored) {

                    }
                }


                List<WebElement> customerRows = driver.findElements(By.cssSelector(".particle-table-row"));
//                System.out.println(customerRows.size());

                for (WebElement customerRow : customerRows) {


//            external-customer-id: id

//            account-cell-link: name (nếu external ở trên tồn tại)  /id (nếu external ở trên ko tồn tại) & link


                    String name = null, id = null, link = null;

                    WebElement accountCellLink = customerRow.findElement(By.cssSelector(".account-cell-link"));
                    link = accountCellLink.getAttribute("href").replaceAll("/overview", "/billing/documents");


                    try {
                        id = customerRow.findElement(By.cssSelector(".external-customer-id")).getText();
                        name = accountCellLink.getText();
                    } catch (Exception u) {
                        id = accountCellLink.getText();
                    }

                    id = id.replaceAll("-", "");

                    String formatCustomerId = "";

                    for (int i = 0; i < id.length(); i++) {
                        if (Character.isDigit(id.charAt(i))) {
                            formatCustomerId += id.charAt(i);
                        } else {
                            break;
                        }
                    }

                    id = formatCustomerId;


                    if (
                            name != null && (
                                    name.toLowerCase().contains("kc_") || name.toLowerCase().contains("td_")
                                            || name.toLowerCase().contains("root_") || name.toLowerCase().contains("ns_")
                                            || name.toLowerCase().contains("nsch_") || name.toLowerCase().contains("tg_")

                                            || name.toLowerCase().contains("kc-") || name.toLowerCase().contains("td-")
                                            || name.toLowerCase().contains("root-") || name.toLowerCase().contains("ns-")
                                            || name.toLowerCase().contains("nsch-") || name.toLowerCase().contains("tg-")

                            )

                    ) {
                        id = id.replaceAll("-", "");


                        if (invoiceOriLinkRepository.findOneByLink(link) == null) {
                            InvoiceOriLinkEntity invoiceOriLinkEntity = new InvoiceOriLinkEntity();
                            invoiceOriLinkEntity.setLink(link);
                            invoiceOriLinkEntity.setCustomerId(id);
                            if (name != null) {
                                invoiceOriLinkEntity.setCustomerName(name);
                            }
                            invoiceOriLinkRepository.save(invoiceOriLinkEntity);
                        }
                    }


//            String customerName = customerRow.findElement(By.cssSelector(".account-cell-link"));
                }


                // next page

                driver.findElement(By.cssSelector(".pager-buttons material-button.next")).click();

                try {
                    fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                    fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                } catch (Exception ignore) {

                }

            }
            while (driver.findElements(By.cssSelector(".pager-buttons material-button.next.is-disabled")).size() == 0);
        }


        List<InvoiceOriLinkEntity> invoiceOriLinkEntityList;

        invoiceOriLinkEntityList = invoiceOriLinkRepository.findAll();


        for (InvoiceOriLinkEntity invoiceOriLinkEntity : invoiceOriLinkEntityList) {

            boolean checkExist = false;
            for (String cid : targetCustomerIds) {
                if (cid.equals(invoiceOriLinkEntity.getCustomerId())) {
                    checkExist = true;
                    break;
                }
            }
            if (!checkExist) {
                continue;
            }


            try {


                String urlPage = invoiceOriLinkEntity.getLink();
                driver.get(urlPage);


                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe#post-signup-embedded-page-containerIframe")));

                String iframe = driver.findElement(By.cssSelector("iframe#post-signup-embedded-page-containerIframe")).getAttribute("src");

                driver.get(iframe);

                Thread.sleep(1000);


                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.b3id-widget-table")));

//        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));
                List<WebElement> rows = driver.findElements(By.cssSelector("table.b3id-widget-table tbody tr"));

                if (!rows.isEmpty()) {

                    int rowsNumber = rows.size();

                    int counter = 0;
                    for (WebElement row : rows) {

                        if (counter++ == rowsNumber - 1) {
                            continue;
                        }

                        List<WebElement> columnList = row.findElements(By.cssSelector("td"));


                        String invoiceId = columnList.get(3).getText();


                        if (invoiceRepository.findOneById(invoiceId) == null) {
                            String dateStr = columnList.get(1).getText();

                            String[] splitYear = dateStr.split(", ");
                            int year = Integer.parseInt(splitYear[1]);

                            String[] splitDateMonth = splitYear[0].split(" tháng ");
                            int month = Integer.parseInt(splitDateMonth[1]);
                            int date = Integer.parseInt(splitDateMonth[0]);


                            Double money = Double.parseDouble(columnList.get(4).getText().replaceAll(" ₫", "").replaceAll("\\.", "").replaceAll(",", "."));


                            String prefixPath = year + "_" + String.format("%02d", month) + "_" + String.format("%02d", date);


                            try {

                                row.findElement(By.linkText("Tải xuống")).sendKeys(Keys.CONTROL, Keys.ENTER);
                                Thread.sleep(1000);

                                //move file

                                File source = new File("/home/dinhvb/Downloads/" + invoiceId + ".pdf");
                                File target = new File("/home/dinhvb/mydata/hoa_don/uploads/" + prefixPath + "/" + invoiceId + ".pdf");

                                FileUtils.moveFile(source, target);

                                InvoiceEntity invoiceEntity = new InvoiceEntity();

                                invoiceEntity.setId(invoiceId);


                                invoiceEntity.setOriginalLink(urlPage);
                                invoiceEntity.setCustomerId(invoiceOriLinkEntity.getCustomerId());
                                invoiceEntity.setCustomerName(invoiceOriLinkEntity.getCustomerName());


                                invoiceEntity.setFullDate(prefixPath);

                                invoiceEntity.setYear(String.valueOf(year));
                                invoiceEntity.setMonth(String.format("%02d", month));
                                invoiceEntity.setDate(String.format("%02d", date));

                                invoiceEntity.setMoney(money);
                                invoiceEntity.setFileUrl(prefixPath + "/" + invoiceId + ".pdf");

                                invoiceRepository.save(invoiceEntity);


                            } catch (IOException e) {
//                                    driver.switchTo().window(tabs.get(1));
//                                    driver.close();
//                                    driver.switchTo().window(tabs.get(0));
                                e.printStackTrace();
//                                driver.close();
//                                driver.switchTo().window(tabs.get(0));
                            } finally {
                                tabs = new ArrayList<String>(driver.getWindowHandles());

                                for (int i = tabs.size() - 1; i >= 1; i--) {
                                    driver.switchTo().window(tabs.get(i)).close();
                                }

//                                driver.switchTo().window(tabs.get(1));
//                                driver.close();
                                driver.switchTo().window(tabs.get(0));
                            }
                        }

                    }
                }


            } catch (Exception e) {
                // continue

                tabs = new ArrayList<String>(driver.getWindowHandles());

                for (int i = tabs.size() - 1; i >= 1; i--) {
                    driver.switchTo().window(tabs.get(i)).close();
                }

//                                driver.switchTo().window(tabs.get(1));
//                                driver.close();
                driver.switchTo().window(tabs.get(0));

            }

        }

        driver.quit();
    }

    @Override
    public PageInvoiceDTO findPageInvoice(Pageable pageable) {

        PageInvoiceDTO pageInvoiceDTO = new PageInvoiceDTO();
        Double totalMoney = 0.0;

        Page<InvoiceEntity> invoiceEntityPage = invoiceRepository.findAll(pageable);

        pageInvoiceDTO.setPage(invoiceEntityPage.getTotalPages());
        pageInvoiceDTO.setTotalPages(invoiceEntityPage.getTotalPages());
        pageInvoiceDTO.setTotalItems(invoiceEntityPage.getTotalElements());


        invoiceEntityPage.getNumberOfElements();


        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        for (InvoiceEntity invoiceEntity : invoiceEntityPage.getContent()) {
            invoiceDTOS.add(invoiceConverter.toDTO(invoiceEntity));
            totalMoney += invoiceEntity.getMoney();
        }

        pageInvoiceDTO.setTotalMoney(totalMoney);
        pageInvoiceDTO.setListResult(invoiceDTOS);

        return pageInvoiceDTO;
    }

    @Override
    public PageInvoiceDTO findByYear(String year, Pageable pageable) {
        PageInvoiceDTO pageInvoiceDTO = new PageInvoiceDTO();
        Double totalMoney = 0.0;

        Page<InvoiceEntity> invoiceEntityPage = invoiceRepository.findByYear(year, pageable);

        pageInvoiceDTO.setPage(invoiceEntityPage.getTotalPages());
        pageInvoiceDTO.setTotalPages(invoiceEntityPage.getTotalPages());
        pageInvoiceDTO.setTotalItems(invoiceEntityPage.getTotalElements());


        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        for (InvoiceEntity invoiceEntity : invoiceEntityPage.getContent()) {
            invoiceDTOS.add(invoiceConverter.toDTO(invoiceEntity));
            totalMoney += invoiceEntity.getMoney();
        }

        pageInvoiceDTO.setTotalMoney(totalMoney);
        pageInvoiceDTO.setListResult(invoiceDTOS);

        return pageInvoiceDTO;
    }

    @Override
    public PageInvoiceDTO findByYearAndMonth(String year, String month, Pageable pageable) {
        PageInvoiceDTO pageInvoiceDTO = new PageInvoiceDTO();
        Double totalMoney = 0.0;

        Page<InvoiceEntity> invoiceEntityPage = invoiceRepository.findByYearAndMonth(year, month, pageable);

        pageInvoiceDTO.setPage(invoiceEntityPage.getTotalPages());
        pageInvoiceDTO.setTotalPages(invoiceEntityPage.getTotalPages());
        pageInvoiceDTO.setTotalItems(invoiceEntityPage.getTotalElements());


        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        for (InvoiceEntity invoiceEntity : invoiceEntityPage.getContent()) {
            invoiceDTOS.add(invoiceConverter.toDTO(invoiceEntity));
            totalMoney += invoiceEntity.getMoney();
        }

        pageInvoiceDTO.setTotalMoney(totalMoney);
        pageInvoiceDTO.setListResult(invoiceDTOS);

        return pageInvoiceDTO;
    }

    @Override
    public PageInvoiceDTO findByYearAndMonthAndDate(String year, String month, String date, Pageable pageable) {
        PageInvoiceDTO pageInvoiceDTO = new PageInvoiceDTO();
        Double totalMoney = 0.0;

        Page<InvoiceEntity> invoiceEntityPage = invoiceRepository.findByYearAndMonthAndDate(year, month, date, pageable);

        pageInvoiceDTO.setPage(invoiceEntityPage.getTotalPages());
        pageInvoiceDTO.setTotalPages(invoiceEntityPage.getTotalPages());
        pageInvoiceDTO.setTotalItems(invoiceEntityPage.getTotalElements());


        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        for (InvoiceEntity invoiceEntity : invoiceEntityPage.getContent()) {
            invoiceDTOS.add(invoiceConverter.toDTO(invoiceEntity));
            totalMoney += invoiceEntity.getMoney();
        }

        pageInvoiceDTO.setTotalMoney(totalMoney);
        pageInvoiceDTO.setListResult(invoiceDTOS);

        return pageInvoiceDTO;
    }


    @Override
    public PageInvoiceDTO findCustom(String year, String month, String date, String customerId, Pageable pageable) {
        PageInvoiceDTO pageInvoiceDTO = new PageInvoiceDTO();
        Double totalMoney = 0.0;

        Page<InvoiceEntity> invoiceEntityPage;


        if (year != null) {

            if (month == null) {
                if (customerId == null) {
                    invoiceEntityPage = invoiceRepository.findByYear(year, pageable);
                } else {
                    invoiceEntityPage = invoiceRepository.findByYearAndCustomerId(year, customerId, pageable);
                }

            } else {
                if (date == null) {
                    if (customerId == null) {
                        invoiceEntityPage = invoiceRepository.findByYearAndMonth(year, month, pageable);
                    } else {
                        invoiceEntityPage = invoiceRepository.findByYearAndMonthAndCustomerId(year, month, customerId, pageable);
                    }

                } else {
                    if (customerId == null) {
                        invoiceEntityPage = invoiceRepository.findByYearAndMonthAndDate(year, month, date, pageable);
                    } else {
                        invoiceEntityPage = invoiceRepository.findByYearAndMonthAndDateAndCustomerId(year, month, date, customerId, pageable);
                    }

                }
            }

        } else {
            if (customerId == null) {
                invoiceEntityPage = invoiceRepository.findAll(pageable);
            } else {
                invoiceEntityPage = invoiceRepository.findByCustomerId(customerId, pageable);
            }
        }


        pageInvoiceDTO.setPage(invoiceEntityPage.getTotalPages());
        pageInvoiceDTO.setTotalPages(invoiceEntityPage.getTotalPages());
        pageInvoiceDTO.setTotalItems(invoiceEntityPage.getTotalElements());


        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        for (InvoiceEntity invoiceEntity : invoiceEntityPage.getContent()) {
            invoiceDTOS.add(invoiceConverter.toDTO(invoiceEntity));
            totalMoney += invoiceEntity.getMoney();
        }

        pageInvoiceDTO.setTotalMoney(totalMoney);
        pageInvoiceDTO.setListResult(invoiceDTOS);

        return pageInvoiceDTO;
    }

    @Override
    public List<GroupInvoiceDTO> groupInvoice(String year, String month, String date) {

        List<GroupInvoiceDTO> groupInvoiceDTOList = new ArrayList<>();

        List<Object[]> groupInvoice = generalNativeQuery.groupInvoice(entityManager, year, month, date);

        for (Object[] o : groupInvoice) {
            GroupInvoiceDTO groupInvoiceDTO = new GroupInvoiceDTO(
                    String.valueOf(o[0]),
                    String.valueOf(o[1]),
                    String.valueOf(o[2]),
                    Double.valueOf(String.valueOf(o[3])),
                    Integer.parseInt(String.valueOf(o[4]))
            );

            groupInvoiceDTOList.add(groupInvoiceDTO);
        }

        return groupInvoiceDTOList;

    }

    @Override
    // first: current
//    @Scheduled(initialDelay = 0, fixedDelay = 2 * 60 * 1000)
//
////    @Scheduled(initialDelay = 0, fixedDelay = 30 * 60 * 1000)
//    // old
////    @Scheduled(cron = "0 0 0,12 * * *")
//    @Async


    @Scheduled(initialDelay = 0, fixedDelay = 5 * 60 * 1000)
    public void deleteRecommendAds() {

//        java -jar hoa_don-1.0-SNAPSHOT.jar --dinhvb.automode=only_check_list


        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.of("+7"));
//        ZonedDateTime utcYesterday = ZonedDateTime.now(ZoneOffset.of("+7")).minus(1, ChronoUnit.DAYS);

        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.of("+7"));


        // random string
//        byte[] array = new byte[7]; // length is bounded by 7
//        new Random().nextBytes(array);
//        String generatedString = new String(array, StandardCharsets.UTF_8);


//        String searchPartnerInfoServer = customDataServer;
//        searchPartnerInfoServer = "103.57.222.198:8082";


        RestTemplate restTemplate = new RestTemplate();


//        String keywordListStr43 = restTemplate.getForObject("http://103.57.222.198:8082/api-general/keyword_list", GeneralDTO.class).getValue();

//        GeneralDTO generalDTO2 = restTemplate.getForObject( "103.57.222.198:8082/api-general", GeneralDTO.class);

        List<String> keywordList = new ArrayList<>();
        if (false) {
//            List<String> keywordList = new ArrayList<>();
            try {
                String keywordListStr = restTemplate.getForObject(searchPartnerInfoServer + "/api-general/keyword_list", GeneralDTO.class).getValue();

                if (keywordListStr != null) {
                    for (String key : keywordListStr.split(",")) {
                        key = key.toLowerCase().trim();
                        if (!key.equals("") && !keywordList.contains(key)) {
                            keywordList.add(key);
                        }
                    }
                }
            } catch (Exception dinhvb) {
                System.out.println("Không lấy được dữ liệu database từ server " + searchPartnerInfoServer);
            }
        }


        int accountCounter = 0;


//        try {
//            Runtime.getRuntime().exec("pkill firefox");
//        } catch (Exception e) {
//            System.out.println("No current firefox processes are running");
//        }
//
////        // delete cache
////        System.out.println(java.time.LocalTime.now());
////
////        System.out.println("Kill old temp files");
////
////        String[] cmd = {"/bin/bash", "-c", "echo 9550 | sudo -S find /tmp -mmin +720 -delete"};
////        try {
////            Process pb = Runtime.getRuntime().exec(cmd);
////        } catch (IOException ignore) {
////        }
//
//
//        // FIREFOX
//
//
////        WebDriverManager.firefoxdriver().setup();
//
//
////        try {
////            String test = new ClassPathResource("data/geckodriver").getFile().getAbsolutePath();
////            System.out.println(test);
////        } catch (IOException e) {
////            e.printStackTrace();
////        };
//
//
////        Resource resource = resourceLoader.getResource("classpath:data/geckodriver");
//
//
////        InputStream inputStream;
////        try {
////            inputStream =  resource.getInputStream();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////
////        try {
////            System.out.println(resource.getFile().getAbsolutePath());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//
//        System.out.println(System.getenv("GECKODRIVER"));
//
//        System.out.println(System.getenv("FIREFOX_BINARY"));
//        System.out.println(System.getenv("HD_PROFILE"));
//
//
//        System.setProperty("webdriver.gecko.driver", System.getenv("GECKODRIVER"));
//
//
////
////        // LOAD PROFILE
////
//        String firefoxBinary = System.getenv("FIREFOX_BINARY");
//        String firefoxProfile = System.getenv("HD_PROFILE");
//
////        firefoxProfile = "C:/Users/DinhVB-P16S/AppData/Roaming/Mozilla/Firefox/Profiles/f1pid8zo.default-release";
//
//
//
//        // UBUNTU
////            firefoxBinary = "/usr/bin/firefox";
////            firefoxProfile = "/home/dinhvb/.mozilla/firefox/ep2ji49n.click_ads";
////
////        // WINDOWS
////        firefoxBinary = "C:/Program Files/Mozilla Firefox/firefox.exe";
//////        firefoxProfile = "C:/Users/bangd/AppData/Roaming/Mozilla/Firefox/Profiles/zo222cai.test";
////        firefoxProfile = "C:/Users/bangd/AppData/Roaming/Mozilla/Firefox/Profiles/gvtqcrwc.ads_test";
//
//        FirefoxOptions opt = new FirefoxOptions();
//        File pathToBinary = new File(firefoxBinary);
//        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
//        opt.setBinary(ffBinary);
//
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("acceptInsecureCerts", "true");
//
//        opt.merge(capabilities);
//
//
//        File profileDirectory = new File(firefoxProfile);
//        FirefoxProfile profile = new FirefoxProfile(profileDirectory);
//        profile.setAcceptUntrustedCertificates(true);
//
//        opt.setProfile(profile);
//        WebDriver driver = new FirefoxDriver(opt);
//
//
//        driver.manage().window().setSize(new Dimension(1920, 3000));
////        driver.manage().window().setSize(new Dimension(1250, 1500));
//
//
////        driver.get("https://support.google.com/google-ads/contact/pf_suspended?request_source=113&helpcenter=google-ads&hl=vi&psd=%7B%7D&page_type=CONTACT_FORM&id=pf_suspended&components=PCT_DYNAMIC_CONTENT&CPCT_GDPR_DIALOG&CPCT_METADATA&CPCT_NEED_MORE_HELP_MODULE&CPCT_NOTIFICATIONS&extra_params=js_request_id=render_api730443753&Capi_client=help_panel&Crender_inapp=1&Cauthuser=0&Cec=aw_overview&CisLoggedIn=true&ChelpPanelInapp=true&CenableSendFeedback=false&ChelpCenterContext=aw_overview&ChelpCenterDomain=support.google.com&ChelpCenterPath=/google-ads&ctx=5JbodJ0Pd7EzoSWBBWV#topic=10286612");
////
////        int icb = 0;
////        while (true){
////            driver.findElement(By.cssSelector(".submit-button")).click();
////            if (icb++ == 10){
////                break;
////            }
////        }
//
//
//
//
//
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofSeconds(10));
//        WebDriverWait fastWait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(10));
//        WebDriverWait superFastWait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofSeconds(10));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        ArrayList<String> tabs = null;
//
//        Actions action = new Actions(driver);
//
//
////        String[][] fetchLinks = new String[][] {
////                ["https://ads.google.com/aw/budgets?ocid=390375515&workspaceId=0&euid=102741392&__u=7656001808&uscid=390375515&__c=5390887235&ascid=390375515"],
////                ["https://ads.google.com/aw/budgets?ocid=196099850&ascid=196099850&euid=102741392&__u=7656001808&uscid=196099850&__c=4643022650"],
////                "https://ads.google.com/aw/budgets?ocid=194893325&euid=102741392&__u=7656001808&uscid=194893325&__c=9578557925",
////        };
//
//
//        List<String[]> fetchLinksList = new ArrayList<>();
//
//        fetchLinksList.add(new String[]{"https://ads.google.com/aw/budgets?ocid=194893325&euid=102741392&__u=7656001808&uscid=194893325&__c=9578557925&ascid=194893325&hl=en", "7835319390"});
//        fetchLinksList.add(new String[]{"https://ads.google.com/aw/budgets?ocid=196099850&ascid=196099850&euid=102741392&__u=7656001808&uscid=196099850&__c=4643022650&hl=en", "1052119864"});
//
//
//        // Chỉ quét lấy link tk, không mở tab mới
//        boolean onlyCheckLink = false;
//
//        if (autoMode.equals("only_check_list")){
//            onlyCheckLink = true;
//        }


//        String arg1 = args[0];

        List<String[]> fetchLinksList = new ArrayList<>();

//        fetchLinksList.add(new String[]{"https://ads.google.com/aw/budgets?ocid=194893325&euid=102741392&__u=7656001808&uscid=194893325&__c=9578557925&ascid=194893325&hl=en", "7835319390"});
//        fetchLinksList.add(new String[]{"https://ads.google.com/aw/budgets?ocid=196099850&ascid=196099850&euid=102741392&__u=7656001808&uscid=196099850&__c=4643022650&hl=en", "1052119864"});


//        Tài khoản duytrungacb

//        fetchLinksList.add(new String[]{"https://ads.google.com/aw/budgets?ocid=196099850&workspaceId=0&euid=203724696&__u=4146546904&uscid=196099850&__c=4643022650&authuser=0&ascid=196099850", "1052119864"});
//        fetchLinksList.add(new String[]{"https://ads.google.com/aw/budgets?ocid=194893325&workspaceId=0&euid=203724696&__u=4146546904&uscid=194893325&__c=9578557925&authuser=0&ascid=194893325", "7835319390"});


        // TK khang1@gmail.com
        fetchLinksList.add(new String[]{"https://ads.google.com/aw/budgets?ocid=194893325&workspaceId=0&euid=1381988833&__u=3048584217&uscid=194893325&__c=9578557925&ascid=194893325", "7835319390"});

        int scanAccountCounter = -1;


        try {
            Runtime.getRuntime().exec("pkill firefox");
        } catch (Exception e) {
            System.out.println("No current firefox processes are running");
        }

        // delete cache
        System.out.println(java.time.LocalTime.now());

        System.out.println("Kill old temp files");

        String[] cmd = {"/bin/bash", "-c", "echo 9550 | sudo -S find /tmp -mmin +720 -delete"};
        try {
            Process pb = Runtime.getRuntime().exec(cmd);
        } catch (IOException ignore) {
        }

        System.out.println(System.getenv("GECKODRIVER"));

        System.out.println(System.getenv("FIREFOX_BINARY"));
        System.out.println(System.getenv("HD_PROFILE"));


        System.setProperty("webdriver.gecko.driver", System.getenv("GECKODRIVER"));


//
//        // LOAD PROFILE
//
        String firefoxBinary = System.getenv("FIREFOX_BINARY");
        String firefoxProfile = System.getenv("HD_PROFILE");

//        firefoxProfile = "C:/Users/DinhVB-P16S/AppData/Roaming/Mozilla/Firefox/Profiles/f1pid8zo.default-release";


        // UBUNTU
//            firefoxBinary = "/usr/bin/firefox";
//            firefoxProfile = "/home/dinhvb/.mozilla/firefox/ep2ji49n.click_ads";
//
//        // WINDOWS
//        firefoxBinary = "C:/Program Files/Mozilla Firefox/firefox.exe";
////        firefoxProfile = "C:/Users/bangd/AppData/Roaming/Mozilla/Firefox/Profiles/zo222cai.test";
//        firefoxProfile = "C:/Users/bangd/AppData/Roaming/Mozilla/Firefox/Profiles/gvtqcrwc.ads_test";

        FirefoxOptions opt = new FirefoxOptions();
        File pathToBinary = new File(firefoxBinary);
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        opt.setBinary(ffBinary);


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("acceptInsecureCerts", "true");

        opt.merge(capabilities);


        File profileDirectory = new File(firefoxProfile);
        FirefoxProfile profile = new FirefoxProfile(profileDirectory);
        profile.setAcceptUntrustedCertificates(true);

        opt.setProfile(profile);
        WebDriver driver = new FirefoxDriver(opt);


        driver.manage().window().setSize(new Dimension(1920, 3000));
//        driver.manage().window().setSize(new Dimension(1250, 1500));


//        driver.get("https://support.google.com/google-ads/contact/pf_suspended?request_source=113&helpcenter=google-ads&hl=vi&psd=%7B%7D&page_type=CONTACT_FORM&id=pf_suspended&components=PCT_DYNAMIC_CONTENT&CPCT_GDPR_DIALOG&CPCT_METADATA&CPCT_NEED_MORE_HELP_MODULE&CPCT_NOTIFICATIONS&extra_params=js_request_id=render_api730443753&Capi_client=help_panel&Crender_inapp=1&Cauthuser=0&Cec=aw_overview&CisLoggedIn=true&ChelpPanelInapp=true&CenableSendFeedback=false&ChelpCenterContext=aw_overview&ChelpCenterDomain=support.google.com&ChelpCenterPath=/google-ads&ctx=5JbodJ0Pd7EzoSWBBWV#topic=10286612");
//
//        int icb = 0;
//        while (true){
//            driver.findElement(By.cssSelector(".submit-button")).click();
//            if (icb++ == 10){
//                break;
//            }
//        }


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofSeconds(10));
        WebDriverWait fastWait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(10));
        WebDriverWait superFastWait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ArrayList<String> tabs = null;

        Actions action = new Actions(driver);


//                List<String[]> fetchLinksList = new ArrayList<>();
//
//                fetchLinksList.add(new String[]{"https://ads.google.com/aw/budgets?ocid=194893325&euid=102741392&__u=7656001808&uscid=194893325&__c=9578557925&ascid=194893325&hl=en", "7835319390"});
//                fetchLinksList.add(new String[]{"https://ads.google.com/aw/budgets?ocid=196099850&ascid=196099850&euid=102741392&__u=7656001808&uscid=196099850&__c=4643022650&hl=en", "1052119864"});


        // Chỉ quét lấy link tk, không mở tab mới
        boolean onlyCheckLink = false;

        if (autoMode.equals("only_check_list")) {
            onlyCheckLink = true;
        }


        for (String[] fetchLinksItem : fetchLinksList) {


            // fetch new links
//            driver.get("https://ads.google.com/aw/budgets?ocid=194893325&euid=102741392&__u=7656001808&uscid=194893325&__c=9578557925");
            driver.get(fetchLinksItem[0]);

            try {
                fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
            } catch (Exception ignore) {

            }

//            try {
//                List<WebElement> deleteIconList = driver.findElements(By.cssSelector(".predicates-container material-chip .content-with-delete .delete-icon-container"));
//                for (WebElement icon: deleteIconList){
//                    icon.click();
//                    try {
//                        fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
//                        fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
//                    } catch (Exception ignore) {
//
//                    }
//                }
//            }
//            catch (Exception ignore){
//
//            }


            Long mainLoginCustomerId = Long.valueOf(fetchLinksItem[1]);


            boolean firstCustomerPage = true;

            //                     Chỉ lọc các tài khoản chưa có profile payment
            if (false) {
                ResponseEntity<List<AdsAccountDTO>> responseTmpMain = restTemplate.exchange(
                        itgreenToolServer + "/api-ads-account/payments-profile-not-null",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AdsAccountDTO>>() {
                        }
                );

                List<AdsAccountDTO> targetAdsAccountDTO1 = responseTmpMain.getBody();

                if (!targetAdsAccountDTO1.isEmpty()) {

                    String excludedAccountString = "";
                    for (AdsAccountDTO accountDTOItem : targetAdsAccountDTO1) {
                        if (!excludedAccountString.equals("")) {
                            excludedAccountString += "\n";
                        }
                        excludedAccountString += accountDTOItem.getId();
                    }


                    //                                    String tkHoatDongLink = "https://ads.google.com/aw/notifications?ocid=194893325&__u=7656001808&__c=9578557925&ascid=194893325&tableState=Eg8SDQoEdHlwZRADIgMI2w8%3D";


//                                System.out.println(tkHoatDongLink);

                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-table-row")));

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("awsm-child-content toolbelt-bar .extended-predicates-container material-button.add-filter-btn"))).click();
                    } catch (Exception ignore) {
                    }

                    try {
                        Thread.sleep(1000);
                        fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".material-popup-content focus-trap .popup-search-box-wrapper input.search-box"))).sendKeys("Mã khách hàng");
                        Thread.sleep(1000);
                        fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".suggestion-list material-select-item[aria-label=\"Mã khách hàng\"]"))).click();
                        Thread.sleep(1000);
//                                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("material-dropdown-select dropdown-button material-icon.icon"))).click();
                        fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("predicate-operator material-dropdown-select dropdown-button"))).click();
                        Thread.sleep(1000);
                        fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".material-popup-content material-list material-select-dropdown-item:nth-child(2) span"))).click();


                        Thread.sleep(1000);
//                        fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("focus-trap filter-editor-string material-input textarea"))).sendKeys(excludedAccountString);

                        WebElement element = driver.findElement(By.cssSelector("focus-trap filter-editor-string material-input textarea"));
                        js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                                element, excludedAccountString);


//                            fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("focus-trap filter-editor-string material-input textarea"))).click();
//                            // Send keys Kiểu khác: Dùng clipboard
//                            Toolkit toolkit = Toolkit.getDefaultToolkit();
//                            Clipboard clipboard = toolkit.getSystemClipboard();
//                            StringSelection strSel = new StringSelection(excludedAccountString);
//                            clipboard.setContents(strSel, null);
////
//                            action.keyDown(Keys.CONTROL).sendKeys("V").perform();


//                            Toolkit toolkit = Toolkit.getDefaultToolkit();
//                            Clipboard clipboard = toolkit.getSystemClipboard();
//                            StringSelection strSel = new StringSelection(excludedAccountString);
//                            clipboard.setContents(strSel, null);
//
//                            Robot robot = new Robot();
//                            robot.keyPress(KeyEvent.VK_CONTROL);
//                            robot.keyPress(KeyEvent.VK_V);
//                            robot.keyRelease(KeyEvent.VK_V);
//                            robot.keyRelease(KeyEvent.VK_CONTROL);
                        // END Kiểu khác: Dùng clipboard


                        Thread.sleep(2000);
                        try {
                            System.out.println("Chờ nhấn nút lọc");

//                            WebElement applyBtn = fastWait.until(
//                                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("focus-trap .footer material-button"))
//                            );
//                            fastWait.until(ExpectedConditions.elementToBeClickable(applyBtn)).click();


                            By applyBtn = By.cssSelector("focus-trap .footer material-button");
                            safeClickMaterial(driver, applyBtn);


                        } catch (Exception e2) {

                            System.out.println("Không click nút lọc được");
//                                                    e2.printStackTrace();
//
//                                                    fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("focus-trap .footer material-button material-ripple"))).click();
                            continue;
                        }

                        try {
                            fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                            fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                        } catch (Exception ignore) {

                        }
                        Thread.sleep(3000);

                        List<WebElement> mtCustomerRows = driver.findElements(By.cssSelector(".particle-table-row"));
                        if (mtCustomerRows.isEmpty()) {
                            continue;
                        }

                    } catch (Exception e) {
                        continue;
                    }


                }
            }


            do {

                if (firstCustomerPage) {
                    firstCustomerPage = false;
                } else {
                    // next page


                    try {
                        driver.findElement(By.cssSelector(".pager-buttons material-button.next")).click();
                        fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                        fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                    } catch (Exception ignore) {
                        break;
                    }
                }


                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-table-row")));
                } catch (Exception e2) {
                    break;
                }


//                Xóa bộ lọc kiểu 1
//                    boolean xoaKieu1 = false;
//                    try {
//
//
//                        while (true) {
//
//                            fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("predicate-editor .delete-icon-container .delete-button")));
//
//                            WebElement webRemoveElement = driver.findElement(By.cssSelector("predicate-editor .delete-icon-container .delete-button"));
////                        fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("predicate-editor .delete-icon-container .delete-button"))).click();
//
////                        action.moveToElement(webRemoveElement).build().perform();
//                            webRemoveElement.click();
//
////                        try {
////                            fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("predicate-editor .delete-icon-container .delete-button")));
////                        } catch (Exception ignore) {
////                            break;
////                        }
//
//                            xoaKieu1 = true;
//                        }
//
//
//                    } catch (Exception e) {
//
//                    }
//
//                    if (xoaKieu1 == false) {
//                        //                Xóa bộ lọc kiểu 2
//                        try {
//
//                            fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".extended-predicates-container .view-all-label"))).click();
//                            fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close-popup-btn"))).click();
//
//                        } catch (Exception ignore2) {
//
//                        }
//                    }


//                String query = "document.querySelector(\".base-root\").scrollTop=6000;";
                String query = "window.scrollTo({top: 3000, behavior: 'smooth'})";
                try {
                    js.executeScript(query);
                } catch (Exception ignored) {

                }


                List<WebElement> customerRows = driver.findElements(By.cssSelector(".particle-table-row"));

                for (WebElement customerRow : customerRows) {

                    scanAccountCounter += 1;

                    try {


                        WebElement accountCellLink = customerRow.findElement(By.cssSelector(".account-cell-link"));

                        String accountCellHref = accountCellLink.getAttribute("href");


                        AdsAccountDTO adsAccountDTO = new AdsAccountDTO();


                        String customerCellId = "";
                        try {
                            customerCellId = customerRow.findElement(By.cssSelector(".external-customer-id")).getText();
                        } catch (Exception u) {
                            customerCellId = accountCellLink.getText();
                        }

                        customerCellId = customerCellId.replaceAll("-", "");

                        String formatCustomerId = "";

                        for (int i = 0; i < customerCellId.length(); i++) {
                            if (Character.isDigit(customerCellId.charAt(i))) {
                                formatCustomerId += customerCellId.charAt(i);
                            } else {
                                break;
                            }
                        }

                        customerCellId = formatCustomerId;

                        System.out.println(customerCellId);

//                        System.out.println(customerCellId);
//                        System.out.println(accountCellLink.getText());
//
//                        if (Long.parseLong(customerCellId) == 2564259343L){
//                            System.out.println("Found: 2564259343");
//                        }
//                        else {
//                            continue;
//                        }


//                    if (!customerCellId.equals("1115041076")){
//                        continue;
//                    }


//                    System.out.println("CHECKING: " + customerCellId + " - " + accountCellLink.getText());


                        adsAccountDTO.setId(Long.parseLong(customerCellId));


                        // ******** KIỂM TRA TK TRÊN DB

                        // Tạo mới nếu chưa có

                        boolean isUpdateAccount = false;


                        AdsAccountDTO dbAdsAccountDTO = null;
                        try {


                            dbAdsAccountDTO = restTemplate.getForObject(itgreenToolServer + "/api-ads-account/" + adsAccountDTO.getId(), AdsAccountDTO.class);


                        } catch (Exception failed) {

                            System.out.println("Not found in database. May be server error");

                            continue;
                        }


                        if (dbAdsAccountDTO == null) {
                            isUpdateAccount = true;
                            adsAccountDTO.setDescriptiveName(accountCellLink.getText());
//                            Long loginCustomerId = 7835319390L;
                            adsAccountDTO.setLoginCustomerId(mainLoginCustomerId);

                            dbAdsAccountDTO = restTemplate.postForObject(itgreenToolServer + "/api-ads-account/", adsAccountDTO, AdsAccountDTO.class);
                        } else {
                            if (dbAdsAccountDTO.getDescriptiveName() == null || !dbAdsAccountDTO.getDescriptiveName().equals(accountCellLink.getText())) {
                                adsAccountDTO.setDescriptiveName(accountCellLink.getText());

                                isUpdateAccount = true;
                            }

                            if (dbAdsAccountDTO.getLoginCustomerId() == null || !dbAdsAccountDTO.getLoginCustomerId().equals(mainLoginCustomerId)) {
                                adsAccountDTO.setLoginCustomerId(mainLoginCustomerId);

                                isUpdateAccount = true;
                            }
                        }


//                        action.moveToElement(element).perform();


                        Map<String, List<String>> queryParams = getQueryParams(accountCellHref);


                        if (dbAdsAccountDTO.getOcid() == null) {
                            if (queryParams.get("ocid") != null) {
                                isUpdateAccount = true;
                                adsAccountDTO.setOcid(getQueryParams(accountCellHref).get("ocid").get(0));
                            }
                        }
                        if (dbAdsAccountDTO.getAscid() == null) {
                            if (queryParams.get("ascid") != null) {
                                isUpdateAccount = true;
                                adsAccountDTO.setAscid(getQueryParams(accountCellHref).get("ascid").get(0));
                            }
                        }


                        if (dbAdsAccountDTO.getC() == null) {
                            if (queryParams.get("__c") != null) {
                                isUpdateAccount = true;
                                adsAccountDTO.setC(getQueryParams(accountCellHref).get("__c").get(0));
                            }
                        }

                        adsAccountDTO.setToolCheckingTime(new Timestamp(System.currentTimeMillis()));
//
//                        String directManager = null;
//                        try {
//                            directManager = customerRow.findElement(By.cssSelector("direct-managers-cell .customer-cell-link")).getText();
//
//                        }
//                        catch (Exception e){
//                            try {
//                                directManager = customerRow.findElement(By.cssSelector("direct-managers-cell .customer-name-label")).getText();
//                            }
//                            catch (Exception ignore){}
//                        }
//
//                        if (directManager != null){
//                            adsAccountDTO.setDirectManager(directManager);
//                        }


                        try {
                            String paymentsProfile = customerRow.findElement(By.cssSelector("payments-customer-cell .payments-customer-name")).getText();
                            if (adsAccountDTO.getPaymentsProfile() == null && !paymentsProfile.isEmpty()) {
                                isUpdateAccount = true;
                                adsAccountDTO.setPaymentsProfile(paymentsProfile);
                            }
                        } catch (Exception ignore) {

                        }

                        try {
                            String paymentsNumber = customerRow.findElement(By.cssSelector("payments-customer-cell .payments-customer-number")).getText();
                            if (adsAccountDTO.getPaymentsNumber() == null && !paymentsNumber.isEmpty()) {
                                isUpdateAccount = true;
                                adsAccountDTO.setPaymentsNumber(paymentsNumber);
                            }
                        } catch (Exception ignore) {

                        }


                        if (onlyCheckLink == false) {
                            //
                            ((JavascriptExecutor) driver).executeScript("window.open()");
                            tabs = new ArrayList<String>(driver.getWindowHandles());
                            driver.switchTo().window(tabs.get(1));


                            // ******** LAY SO DU
                            if (false) {
                                String soDuLink = accountCellHref.replaceAll("/overview", "/billing/summary");
                                driver.get(soDuLink);

                                try {
                                    String totalBalance = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".total-balance"))).getText();
                                    String lastPaymentDate = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".last-payment-date"))).getText();
                                    String lastPaymentAmount = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".last-payment-amount"))).getText();


                                    Double totalBalanceValue = Double.valueOf(totalBalance.replace(" ₫", "").replace(".", ""));
                                    Double lastPaymentAmountValue = Double.valueOf(lastPaymentAmount.replace(" ₫", "").replace(".", ""));


                                    lastPaymentDate = lastPaymentDate.split(", ")[0];

                                    String[] lastPaymentDateList = lastPaymentDate.split(" thg ");
                                    String lastPaymentDateValue = "";
                                    if (lastPaymentDateList[0].length() == 2) {
                                        lastPaymentDateValue += lastPaymentDateList[0];
                                    } else {
                                        lastPaymentDateValue += "0" + lastPaymentDateList[0];
                                    }

                                    lastPaymentDateValue += "-";

                                    if (lastPaymentDateList[1].length() == 2) {
                                        lastPaymentDateValue += lastPaymentDateList[1];
                                    } else {
                                        lastPaymentDateValue += "0" + lastPaymentDateList[1];
                                    }

                                    utc = ZonedDateTime.now(ZoneOffset.of("+7"));

                                    adsAccountDTO.setTotalBalance(totalBalanceValue);
                                    adsAccountDTO.setLastPaymentAmount(lastPaymentAmountValue);
                                    adsAccountDTO.setLastPaymentDate(lastPaymentDateValue);
                                    adsAccountDTO.setPaymentCheckTime(utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


                                } catch (Exception ignore) {
                                }
                            }


                            if (false) {
                                //****** LAY TEN MIEN


                                //                        Thread.sleep(1000);

//                        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("date-picker dropdown-button")));
//                        System.out.println("Wait 1: ok");


//                        for (int dateIndex : new int[]{1, 7}) {

//                        "30 ngày qua"


//                        Ngày hiện tại

//                        ZonedDateTime now = ZonedDateTime.now();
//                        ZonedDateTime yesterday = now.minus(1, ChronoUnit.DAYS);


//                        String formattedYesterday = utcYesterday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));


                                List<String> checkedDateList = new ArrayList<>();


                                ///

//                        LocalDate startDate = LocalDate.parse("2023-11-18");
//                        LocalDate endDate = LocalDate.parse("2023-12-13");


                                String lastCheckDomainDate = "";
                                if (dbAdsAccountDTO.getLastCheckDomainDate() != null) {
                                    lastCheckDomainDate = dbAdsAccountDTO.getLastCheckDomainDate();
                                } else {
                                    // kiem tra truoc 3 ngay

                                    // Today: 2023-12-15 --> lastCheckDomainDate: 2023-12-11

                                    lastCheckDomainDate = ZonedDateTime.now(ZoneOffset.of("+7")).minus(5, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                }


//                    ***** TEST


//                    lastCheckDomainDate = "2023-12-18";

//                    ****** END TEST


                                // 2023-12-12 -> 2023-12-14
                                LocalDate startDate = LocalDate.parse(lastCheckDomainDate).plusDays(1);


                                utc = ZonedDateTime.now(ZoneOffset.of("+7"));
                                LocalDate endDate = LocalDate.parse(utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                                long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);

                                if (numOfDaysBetween > 0) {
                                    //                    System.out.println("Get domain...");
                                    String auctionInsightsLink = accountCellHref.replaceAll("/overview", "/adgroups/auctioninsights");

//                    //
//                    ((JavascriptExecutor) driver).executeScript("window.open()");
//                    tabs = new ArrayList<String>(driver.getWindowHandles());
//                    driver.switchTo().window(tabs.get(1));
                                    driver.get(auctionInsightsLink);


                                    try {


                                        if (numOfDaysBetween > 15) {
                                            startDate = endDate.minusDays(15);
                                            numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
                                        }

                                        LocalDate finalStartDate = startDate;
                                        List<LocalDate> localDateList = IntStream.iterate(0, i -> i + 1)
                                                .limit(numOfDaysBetween)
                                                .mapToObj(i -> finalStartDate.plusDays(i))
                                                .collect(Collectors.toList());

                                        for (LocalDate localDate : localDateList) {
                                            String localDateStr = localDate.format(formatters);
                                            checkedDateList.add(localDateStr);
                                        }

//                        System.out.println("CheckedDateList: " + checkedDateList.size());


                                        ///


                                        for (String dateSegment : checkedDateList) {

//                            System.out.println(dateSegment);

                                            String[] dateSegmentSplit = dateSegment.split("/");

                                            String formatDateSegmentInDatabase = dateSegmentSplit[2] + "-" + dateSegmentSplit[1] + "-" + dateSegmentSplit[0];


                                            try {
//                                System.out.println("Wait for date picker...");


                                                int datePickerCounter = 0;
                                                do {
                                                    datePickerCounter += 1;
                                                    try {
                                                        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("date-picker dropdown-button"))).click();
                                                        break;
                                                    } catch (Exception waitDatePicker) {
                                                        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("date-picker dropdown-button"))).click();
                                                    }
                                                }
                                                while (datePickerCounter == 5);


//                                try {
//                                    WebElement datePickerElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("date-picker dropdown-button")));
//                                    datePickerElement.click();
//                                }
//                                catch (Exception waitDatePicker){
//                                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("date-picker dropdown-button"))).click();
//                                }


//                                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("menu-item-groups material-select-item[data-item-index='" + dateIndex + "']"))).click();
//                                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("menu-item-groups .menu-item-row:nth-child(" + dateIndex + ") material-select-item"))).click();


//                                System.out.println("Finding begin - end date");
                                                WebElement beginDateInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("date-range-editor .right-column date-range-input .start.date-input input")));
                                                WebElement endDateInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("date-range-editor .right-column date-range-input .end.date-input input")));
//                                System.out.println("Found begin - end date");


//                                beginDateInput.clear();

                                                beginDateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));

                                                beginDateInput.sendKeys(dateSegment);


//                                endDateInput.clear();
                                                endDateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                                                endDateInput.sendKeys(dateSegment);

//                                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("date-range-editor .right-column date-range-input .start.date-input input"))).clear().sendKeys(Keys.chord(Keys.CONTROL, "a"), "4/12/2023");
//                                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("date-range-editor .right-column date-range-input .end.date-input input"))).sendKeys(Keys.chord(Keys.CONTROL, "a"),"4/12/2023");


                                                fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".apply-bar material-button.apply"))).click();

//                                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("menu-item-groups .menu-item-row material-select-item[aria-label='" + dateSegment + "']"))).click();
//                                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("menu-item-groups .menu-item-row material-select-item[aria-label='Hôm nay']"))).click();
                                                superFastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-table-row")));

                                                boolean firstDomainPage = true;
                                                do {

                                                    if (firstDomainPage) {
                                                        firstDomainPage = false;
                                                    } else {
                                                        // next page
                                                        driver.findElement(By.cssSelector(".pager-buttons material-button.next")).click();
                                                        try {
                                                            fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                                                            fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                                                        } catch (Exception ignore) {
//                                            System.out.println("Not found loading");
                                                        }
                                                    }

                                                    List<WebElement> domainRows = driver.findElements(By.cssSelector(".particle-table-row"));
                                                    for (WebElement domainItem : domainRows) {
                                                        String domain = domainItem.findElement(By.cssSelector("ess-cell")).getText();
                                                        if (domain.contains(".")) {

                                                            boolean hasDomainInDb = false;

                                                            // tim thay domain

                                                            String domainText = domainItem.findElement(By.cssSelector("ess-cell")).getText();


                                                            // lưu bảng TrackingWeb ở itgreenToolServer

                                                            TrackingWebDTO[] trackingWebDTOList = restTemplate.getForObject(itgreenToolServer + "/api-tracking-web?hostName=" + domainText + "&autoTool=true&date=" + formatDateSegmentInDatabase, TrackingWebDTO[].class);
                                                            assert trackingWebDTOList != null;
                                                            if (trackingWebDTOList.length == 0) {

                                                                TrackingWebDTO trackingWebDTO = new TrackingWebDTO();
                                                                trackingWebDTO.setHostName(domainText);
                                                                trackingWebDTO.setDate(formatDateSegmentInDatabase);
                                                                trackingWebDTO.setAutoTool(true);

                                                                restTemplate.postForObject(itgreenToolServer + "/api-tracking-web", trackingWebDTO, TrackingWebDTO.class);
                                                            }


                                                            PartnerInfoDTO partnerInfoDTO = new PartnerInfoDTO();
                                                            partnerInfoDTO.setId(domainText);
                                                            partnerInfoDTO.setNeedToCheck(true);
                                                            partnerInfoDTO.setSourceType("ads");


                                                            if (restTemplate.getForObject(searchPartnerInfoServer + "/api-partner-info/" + domainText, PartnerInfoDTO.class) == null) {
//                                        hasDomainInDb = true;
                                                                partnerInfoDTO = restTemplate.postForObject(searchPartnerInfoServer + "/api-partner-info", partnerInfoDTO, PartnerInfoDTO.class);

//                                        try {
//                                            partnerInfoDTO = restTemplate.getForObject(searchPartnerInfoServer + "/api-partner-info/" + domainText, PartnerInfoDTO.class);
//                                            hasDomainInDb = true;
//                                        }
//                                        catch (Exception ex){
//                                            partnerInfoDTO = restTemplate.postForObject(searchPartnerInfoServer + "/api-partner-info", partnerInfoDTO, PartnerInfoDTO.class);
//                                        }

                                                                assert partnerInfoDTO != null;
//                                                System.out.println(partnerInfoDTO.getId());


//                                        if (!hasDomainInDb){
//                                            System.out.println("ok");
//                                        }
                                                            }


                                                        }
                                                    }


                                                }
                                                while (driver.findElements(By.cssSelector(".pager-buttons material-button.next.is-disabled")).size() == 0);

                                            } catch (Exception e3) {
//                                System.out.println("Fail: " + dbAdsAccountDTO.getId() + " - " + dateSegment);
                                            }

                                            AdsAccountDTO adsAccountDTO1 = new AdsAccountDTO();
                                            adsAccountDTO1.setId(dbAdsAccountDTO.getId());
                                            adsAccountDTO1.setLastCheckDomainDate(formatDateSegmentInDatabase);
                                            restTemplate.put(itgreenToolServer + "/api-ads-account", adsAccountDTO1, AdsAccountDTO.class);


                                        }

                                    } catch (Exception ignore) {
//                        System.out.println("Fail: " + dbAdsAccountDTO.getId() + " - " + "Tên miền");
                                    }
                                }


                                // ********* END LAY TEN MIEN

                            }


                            if (false) {
                                // ********** LAY TU KHOA

//                if (accountCounter % 10 == 0){
//                    try {
//                        String keywordListStr = restTemplate.getForObject("http://192.168.2.22:8082/api-general/keyword_list", String.class);
//
//                        if (keywordListStr != null){
//                            for (String key: keywordListStr.split(",")){
//                                key = key.toLowerCase().trim();
//                                if (!keywordList.contains(key)){
//                                    keywordList.add(key);
//                                }
//                            }
//                        }
//                    }
//                    catch (Exception dinhvb){
//                        System.out.println("Không lấy được dữ liệu database từ server 192.168.2.22");
//                    }
//                }

                                String keywordSearchLink = accountCellHref.replaceAll("/overview", "/keywords/searchterms");


                                //
//                ((JavascriptExecutor) driver).executeScript("window.open()");
//                tabs = new ArrayList<String>(driver.getWindowHandles());
//                driver.switchTo().window(tabs.get(1));
                                driver.get(keywordSearchLink);


                                try {
                                    Thread.sleep(1000);

                                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("date-picker dropdown-button")));
                                    for (int dateIndex : new int[]{1, 7}) {
                                        try {
                                            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("date-picker dropdown-button"))).click();
                                            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("menu-item-groups material-select-item[data-item-index='" + dateIndex + "']"))).click();
                                            superFastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-table-row")));

                                            boolean firstDomainPage = true;
                                            do {

                                                if (firstDomainPage) {
                                                    firstDomainPage = false;
                                                } else {
                                                    // next page
                                                    driver.findElement(By.cssSelector(".pager-buttons material-button.next")).click();
                                                    try {
                                                        fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                                                        fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                                                    } catch (Exception ignore) {

                                                    }
                                                }

                                                List<WebElement> domainRows = driver.findElements(By.cssSelector(".particle-table-row"));
                                                boolean updateKeywordList = false;
                                                for (WebElement domainItem : domainRows) {
                                                    String key = domainItem.findElement(By.cssSelector("ess-cell")).getText();


                                                    if (!key.equals("") && !keywordList.contains(key)) {

                                                        keywordList.add(key);

                                                        updateKeywordList = true;


                                                    }
                                                }

                                                if (updateKeywordList) {
                                                    String resultKeywordListStr = "";
                                                    for (String key2 : keywordList) {
                                                        if (!resultKeywordListStr.equals("")) {
                                                            resultKeywordListStr += ",";
                                                        }
                                                        resultKeywordListStr += key2;
                                                    }

                                                    GeneralDTO generalDTO = new GeneralDTO();
                                                    generalDTO.setId("keyword_list");
                                                    generalDTO.setValue(resultKeywordListStr);

                                                    restTemplate.put(searchPartnerInfoServer + "/api-general", generalDTO, GeneralDTO.class);
//                                            System.out.println("ok");
                                                }


                                                break;

                                            }
                                            while (driver.findElements(By.cssSelector(".pager-buttons material-button.next.is-disabled")).size() == 0);

                                        } catch (Exception e3) {
                                            //
                                        }
                                    }

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }


                                /// END LAY TU KHOA


//                driver.close();
//                driver.switchTo().window(tabs.get(0));


//                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("date-picker dropdown-button"))).click();
//                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("menu-item-groups material-select-item[data-item-index=\"7\"]"))).click();
//
//
//
//
//                //End lay ten mien
                            }


                            // Tắt xóa đề xuất
                            if (false) {
                                // XOA DE XUAT
                                String recommendationLink = accountCellHref.replaceAll("/overview", "/recommendations");


                                //
//                ((JavascriptExecutor) driver).executeScript("window.open()");
//                tabs = new ArrayList<String>(driver.getWindowHandles());
//                driver.switchTo().window(tabs.get(1));

                                driver.get(recommendationLink);


                                try {
                                    fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".main-cards-view card")));


                                    while (true) {
                                        List<WebElement> cardList = driver.findElements(By.cssSelector(".main-cards-view card suggestion-card-menu"));

                                        if (cardList.size() == 0) {
                                            break;
                                        }

                                        WebElement suggestionCardMenu = cardList.get(0);

                                        try {
                                            Thread.sleep(2000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

//                WebElement suggestionCardMenu = card.findElement(By.cssSelector("suggestion-card-menu"));
                                        action.moveToElement(suggestionCardMenu).click().build().perform();
//            suggestionCardMenu.click();

                                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("relative-popup")));

                                        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("relative-popup material-list-item:last-of-type"))).click();

                                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dismiss-all-dialog")));


                                        WebElement dismissAllBtn = driver.findElement(By.xpath("//dismiss-all-dialog/material-dialog/focus-trap/div[2]/div/footer/div/material-yes-no-buttons/material-button[2]/material-ripple"));
                                        dismissAllBtn.click();


                                        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".dismiss-all-dialog")));


                                    }


                                } catch (Exception ignore) {
                                }
                            }


                        }


                        // ********* END LAY SO DU


                        // CAP NHAT TAI KHOAN

                        String requestUrl = itgreenToolServer + "/api-ads-account";


                        try {

//                                AdsAccountDTO currentAdsAccountDTO = restTemplate.getForObject(itgreenToolServer + "/api-ads-account/" + adsAccountDTO.getId(), AdsAccountDTO.class);

                            if (
//                                        currentAdsAccountDTO != null && isUpdateAccount == true
                                    isUpdateAccount == true
                            ) {
                                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                                HttpHeaders headers = new HttpHeaders();
                                Map<String, String> param = new HashMap<String, String>();
//                param.put("id","10");
                                HttpEntity<AdsAccountDTO> requestEntity = new HttpEntity<AdsAccountDTO>(adsAccountDTO, headers);
                                HttpEntity<AdsAccountDTO> response = restTemplate.exchange(requestUrl, HttpMethod.PUT, requestEntity, AdsAccountDTO.class, param);
                            }
                        } catch (Exception ignore) {

                        }

                        // END CAP NHAT TAI KHOAN


                    } catch (Exception ignore) {

                    }


                    if (onlyCheckLink == false) {
                        driver.close();
                        assert tabs != null;
                        driver.switchTo().window(tabs.get(0));
                    }


                }


            }
            while (driver.findElements(By.cssSelector(".pager-buttons material-button.next.is-disabled")).size() == 0);


//                driver.quit();
        }


        // Quét link hoạt động, tạm dừng
        if (onlyCheckLink == false) {


//                                    QUÉT DANH SÁCH TÀI KHOẢN HOẠT ĐÔNGK, TẠM DỪNG

            // Tạo list module danh sách
            List<ScanListDTO> scanListDTOList = new ArrayList<>();

            ScanListDTO scanActiveList = new ScanListDTO(
                    "https://ads.google.com/aw/notifications?ocid=194893325&euid=1381988833&__u=3048584217&uscid=194893325&__c=9578557925&authuser=0&subid=vn-vi-awhp-g-aw-c-home-signin%21o2&ascid=194893325&tableState=Eg8SDQoEdHlwZRADIgMI2w8%3D",
                    "active_list",
                    null,
                    7835319390L

            );
            ScanListDTO scanPausedList = new ScanListDTO(
                    "https://ads.google.com/aw/notifications?ocid=194893325&euid=1381988833&__u=3048584217&uscid=194893325&__c=9578557925&authuser=0&subid=vn-vi-awhp-g-aw-c-home-signin%21o2&tableState=EhUSEwoEdHlwZRADIgMI2Q8iBAiKmwQ%3D&ascid=194893325",
                    "paused_list",
                    null,
                    7835319390L
            );

            ScanListDTO scanPausedList2 = new ScanListDTO(
                    "https://ads.google.com/aw/notifications?ocid=194893325&euid=1381988833&__u=3048584217&uscid=194893325&__c=9578557925&authuser=0&subid=vn-vi-awhp-g-aw-c-home-signin%21o2&tableState=EhYSFAoEdHlwZRADIgQIkJsEIgQIkJsE&ascid=194893325",
                    "paused_list_2",
                    null,
                    7835319390L
            );


            scanListDTOList.add(scanActiveList);
            scanListDTOList.add(scanPausedList);
            scanListDTOList.add(scanPausedList2);


            for (ScanListDTO scanListDTO : scanListDTOList) {


                // ********** LẤY THÔNG TIN TK HOẠT ĐỘNG

// ACB
                String tkHoatDongLink = scanListDTO.getLink();
                driver.get(tkHoatDongLink);

                //                                    List<AdsAccountDTO> targetAdsAccountDTO = restTemplate.getForObject(itgreenToolServer + "/api-ads-account/list?list_type=active_list", List<AdsAccountDTO>.class);


                SupportDTO supportDTO = new SupportDTO();

                if (scanListDTO.getType().equals("active_list") || scanListDTO.getType().equals("paused_list")) {

                    String checkedType = scanListDTO.getType();
                    if (scanListDTO.getType().equals("paused_list")){
                        checkedType = "paused_list_exist_notification";
                    }


                    ResponseEntity<List<AdsAccountDTO>> responseTmp = restTemplate.exchange(
                            itgreenToolServer + "/api-ads-account/list?list_type=" + checkedType,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<AdsAccountDTO>>() {
                            }
                    );

                    List<AdsAccountDTO> targetAdsAccountDTO = responseTmp.getBody();

                    if (!targetAdsAccountDTO.isEmpty()) {

                        String excludedAccountString = "";
                        for (AdsAccountDTO accountDTOItem : targetAdsAccountDTO) {
                            if (!excludedAccountString.equals("")) {
                                excludedAccountString += "\n";
                            }
                            excludedAccountString += accountDTOItem.getId();
                        }


                        //                                    String tkHoatDongLink = "https://ads.google.com/aw/notifications?ocid=194893325&__u=7656001808&__c=9578557925&ascid=194893325&tableState=Eg8SDQoEdHlwZRADIgMI2w8%3D";


//                                System.out.println(tkHoatDongLink);

                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-table-row")));

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        try {
                            fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("awsm-child-content toolbelt-bar .extended-predicates-container material-button.add-filter-btn"))).click();
                        } catch (Exception ignore) {
                        }

                        try {
                            Thread.sleep(1000);
                            fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".material-popup-content focus-trap .popup-search-box-wrapper input.search-box"))).sendKeys("Tên tài khoản");
                            Thread.sleep(1000);
                            fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".suggestion-list material-select-item[aria-label=\"Tên tài khoản\"]"))).click();
                            Thread.sleep(1000);
//                                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("material-dropdown-select dropdown-button material-icon.icon"))).click();
                            fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("predicate-operator material-dropdown-select dropdown-button"))).click();
                            Thread.sleep(1000);
                            fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".material-popup-content material-list material-select-dropdown-item:nth-child(2) span"))).click();


//                                                Thread.sleep(1000);
//                                                WebElement applyBtn = fastWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("focus-trap .footer material-button")));
//
//                                                System.out.println("---");
//                                                System.out.println(applyBtn);


                            Thread.sleep(1000);
//                                                fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("focus-trap filter-editor-string material-input textarea"))).sendKeys(excludedAccountString);


                            WebElement element = driver.findElement(By.cssSelector("focus-trap filter-editor-string material-input textarea"));
                            js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                                    element, excludedAccountString);

                            Thread.sleep(2000);
                            try {
                                System.out.println("Chờ nhấn nút lọc");

//                                                    WebElement applyBtn = fastWait.until(
//                                                            ExpectedConditions.presenceOfElementLocated(By.cssSelector("focus-trap .footer material-button"))
//                                                    );
//                                                    fastWait.until(ExpectedConditions.elementToBeClickable(applyBtn)).click();

//                                                    safeClick(driver, By.cssSelector("focus-trap .footer material-button"));
//                                                    By applyBtn = By.cssSelector("material-button[aria-label='Áp dụng']");
                                By applyBtn = By.cssSelector("focus-trap .footer material-button");
                                safeClickMaterial(driver, applyBtn);

//                                                    fastWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[4]/div/div/div[2]/div[2]/focus-trap/div[2]/div[3]/material-button/div[2]"))).click();

//                                                    applyBtn = fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("focus-trap .footer material-button")));

//                                                    System.out.println("---");
//                                                    System.out.println(applyBtn);

//                                                    action.moveToElement(applyBtn).click().build().perform();

//                                                    fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("focus-trap .footer material-button material-ripple"))).click();
                            } catch (Exception e2) {

                                System.out.println("Không click nút lọc được");
//                                                    e2.printStackTrace();
//
//                                                    fastWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("focus-trap .footer material-button material-ripple"))).click();
                                continue;
                            }

                            try {
                                fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                                fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                            } catch (Exception ignore) {

                            }
                            Thread.sleep(3000);

                            List<WebElement> mtCustomerRows = driver.findElements(By.cssSelector(".particle-table-row"));
                            if (mtCustomerRows.isEmpty()) {
                                continue;
                            }

                        } catch (Exception e) {
                            continue;
                        }


                    }
                }


                try {


                    boolean firstDomainPage = true;
                    do {

                        List<Long> newCheckedCustomerIdList = new ArrayList<>();

                        if (firstDomainPage) {
                            firstDomainPage = false;
                        } else {
                            // next page
                            driver.findElement(By.cssSelector(".pager-buttons material-button.next")).click();
                            try {
                                fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                                fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                            } catch (Exception ignore) {

                            }
                        }


                        try {
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-table-row")));
                        } catch (Exception e3) {
                            break;
                        }


                        String query = "document.querySelector(\".base-root\").scrollTop=6000;";
                        try {
                            js.executeScript(query);
                        } catch (Exception ignored) {

                        }


                        List<WebElement> mtCustomerRows = driver.findElements(By.cssSelector(".particle-table-row"));
                        if (mtCustomerRows.size() > 0) {
                            System.out.println("Read row number: " + mtCustomerRows.size());


                            int rowIndex = 0;
                            for (WebElement mtCustomerRow : mtCustomerRows) {


                                try {

                                    rowIndex += 1;
                                    System.out.println("Row index: " + rowIndex);

                                    WebElement mtAccountCellLink = mtCustomerRow.findElement(By.cssSelector(".account-cell-link"));


                                    String mtCustomerCellId = "";
                                    try {
                                        mtCustomerCellId = mtCustomerRow.findElement(By.cssSelector(".external-customer-id")).getText();
                                    } catch (Exception u) {
                                        mtCustomerCellId = mtAccountCellLink.getText();
                                    }

                                    mtCustomerCellId = mtCustomerCellId.replaceAll("-", "");

                                    String formatCustomerId = "";

                                    for (int i = 0; i < mtCustomerCellId.length(); i++) {
                                        if (Character.isDigit(mtCustomerCellId.charAt(i))) {
                                            formatCustomerId += mtCustomerCellId.charAt(i);
                                        } else {
                                            break;
                                        }
                                    }

                                    System.out.println("Checking customer id: " + formatCustomerId);


                                    // Cap nhat thong bao, ly do bi ngung

                                    try {

                                        // CAP NHAT TAI KHOAN

                                        String requestUrl2 = itgreenToolServer + "/api-ads-account";

                                        AdsAccountDTO currentAdsAccountDTO = restTemplate.getForObject(itgreenToolServer + "/api-ads-account/" + formatCustomerId, AdsAccountDTO.class);

                                        if (currentAdsAccountDTO == null) {
                                            AdsAccountDTO newScanAdsAccount = new AdsAccountDTO();
                                            newScanAdsAccount.setId(Long.valueOf(formatCustomerId));
                                            newScanAdsAccount.setDescriptiveName("Default - " + formatCustomerId);
                                            newScanAdsAccount.setLoginCustomerId(scanListDTO.getLoginCustomerId());

                                            currentAdsAccountDTO = restTemplate.postForObject(itgreenToolServer + "/api-ads-account/", newScanAdsAccount, AdsAccountDTO.class);
                                        }


                                        if (currentAdsAccountDTO != null) {

                                            newCheckedCustomerIdList.add(currentAdsAccountDTO.getId());

                                            WebElement notification = mtCustomerRow.findElement(By.cssSelector("notification-type-text.notification-type-text .has-hover"));
                                            action.moveToElement(notification);
                                            notification.click();

//                                                    Thread.sleep(1000);
//                                                    String accNotification = driver.findElement(By.cssSelector(".relative-elements-container .particle-tooltip-container .particle-tooltip")).getText();
                                            String accNotification = fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".relative-elements-container .particle-tooltip-container .particle-tooltip"))).getText();


                                            AdsAccountDTO newAdsAccountDTO = new AdsAccountDTO();
                                            newAdsAccountDTO.setId(currentAdsAccountDTO.getId());

//                                                                if (scanListDTO.getType().equals("active_list")){
//                                                                    newAdsAccountDTO.setActiveList(true);
//                                                                    newAdsAccountDTO.setPausedList(false);
//                                                                    newAdsAccountDTO.setPausedList2(false);
//                                                                }
//                                                                else if (scanListDTO.getType().equals("paused_list")){
//                                                                    newAdsAccountDTO.setActiveList(false);
//                                                                    newAdsAccountDTO.setPausedList(true);
//                                                                }
//                                                                else if (scanListDTO.getType().equals("paused_list_2")){
//                                                                    newAdsAccountDTO.setActiveList(false);
//                                                                    newAdsAccountDTO.setPausedList2(true);
//                                                                }


                                            // Cập nhật notification
                                            if (currentAdsAccountDTO.getNotificationCheckingTime() == null || !currentAdsAccountDTO.getNotification().equals(accNotification)) {


                                                newAdsAccountDTO.setNotification(accNotification);
                                                newAdsAccountDTO.setNotificationCheckingTime(new Timestamp(System.currentTimeMillis()));


                                                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                                                HttpHeaders headers = new HttpHeaders();
                                                Map<String, String> param = new HashMap<String, String>();
//                param.put("id","10");
                                                HttpEntity<AdsAccountDTO> requestEntity = new HttpEntity<AdsAccountDTO>(newAdsAccountDTO, headers);
                                                HttpEntity<AdsAccountDTO> response = restTemplate.exchange(requestUrl2, HttpMethod.PUT, requestEntity, AdsAccountDTO.class, param);

                                            }


                                        }
                                    } catch (Exception ignore) {

                                    }

                                    // END CAP NHAT TAI KHOAN


                                } catch (Exception ignore) {
                                }
                            }


                            if (newCheckedCustomerIdList.size() > 0) {
                                //                                                Cật nhập trạng thái active_list, paused_list
                                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                                HttpHeaders headers = new HttpHeaders();
                                Map<String, String> param = new HashMap<String, String>();

                                supportDTO.setLongIds(newCheckedCustomerIdList.toArray(new Long[0]));
//                param.put("id","10");
                                HttpEntity<SupportDTO> requestEntity = new HttpEntity<SupportDTO>(supportDTO, headers);
                                HttpEntity<Integer> response = restTemplate.exchange(itgreenToolServer + "/api-ads-account/update_list_status?list_type=" + scanListDTO.getType(), HttpMethod.PUT, requestEntity, Integer.class, param);

                                System.out.println("Đã cập nhật list: " + response.getBody());
                            }
                        } else {
                            break;
                        }


                    }
                    while (driver.findElements(By.cssSelector(".pager-buttons material-button.next.is-disabled")).size() == 0);


                } catch (Exception ignore) {
                }

                // ********** END LẤY THÔNG TIN TK HOẠT ĐỘNG


            }


//
//
//
//
//
            // Quét link tự động

            driver.get("https://quanlyads.com/can-theo-doi/theo-doi-10-phut?check=1");
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".noi_dung")));
            } catch (Exception ignore) {
            }


            driver.get("https://quanlyads.com/can-theo-doi/theoi-doi-30-phut?check=1");
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".noi_dung")));
            } catch (Exception ignore) {
            }


        }


        driver.quit();


    }


    @Override

//    @Scheduled(initialDelay = 0, fixedDelay = 5 * 60 * 1000)
    public void trackingLink() {

        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.of("+7"));
        RestTemplate restTemplate = new RestTemplate();

        try {
            Runtime.getRuntime().exec("pkill firefox");
        } catch (Exception e) {
            System.out.println("No current firefox processes are running");
        }

        // delete cache
        System.out.println(java.time.LocalTime.now());

        System.out.println("Kill old temp files");

        String[] cmd = {"/bin/bash", "-c", "echo 9550 | sudo -S find /tmp -mmin +720 -delete"};
        try {
            Process pb = Runtime.getRuntime().exec(cmd);
        } catch (IOException ignore) {
        }


        System.out.println(System.getenv("GECKODRIVER"));

        System.out.println(System.getenv("FIREFOX_BINARY"));
        System.out.println(System.getenv("HD_PROFILE"));


        System.setProperty("webdriver.gecko.driver", System.getenv("GECKODRIVER"));


//
//        // LOAD PROFILE
//
        String firefoxBinary = System.getenv("FIREFOX_BINARY");
        String firefoxProfile = System.getenv("HD_PROFILE");

//        firefoxProfile = "C:/Users/DinhVB-P16S/AppData/Roaming/Mozilla/Firefox/Profiles/f1pid8zo.default-release";


        // UBUNTU
//            firefoxBinary = "/usr/bin/firefox";
//            firefoxProfile = "/home/dinhvb/.mozilla/firefox/ep2ji49n.click_ads";
//
//        // WINDOWS
//        firefoxBinary = "C:/Program Files/Mozilla Firefox/firefox.exe";
////        firefoxProfile = "C:/Users/bangd/AppData/Roaming/Mozilla/Firefox/Profiles/zo222cai.test";
//        firefoxProfile = "C:/Users/bangd/AppData/Roaming/Mozilla/Firefox/Profiles/gvtqcrwc.ads_test";

        FirefoxOptions opt = new FirefoxOptions();
        File pathToBinary = new File(firefoxBinary);
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        opt.setBinary(ffBinary);


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("acceptInsecureCerts", "true");

        opt.merge(capabilities);


        File profileDirectory = new File(firefoxProfile);
        FirefoxProfile profile = new FirefoxProfile(profileDirectory);
        profile.setAcceptUntrustedCertificates(true);

        opt.setProfile(profile);
        WebDriver driver = new FirefoxDriver(opt);


        driver.manage().window().setSize(new Dimension(1920, 3000));


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofSeconds(10));
        WebDriverWait fastWait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(10));
        WebDriverWait superFastWait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ArrayList<String> tabs = null;

        Actions action = new Actions(driver);


        String query = "window.scrollTo({top: 3000, behavior: 'smooth'})";
        try {
            js.executeScript(query);
        } catch (Exception ignored) {

        }

        String formatCustomerId = "";


//        String tkHoatDongLink = "https://ads.google.com/aw/notifications?ocid=194893325&__u=7656001808&__c=9578557925&ascid=194893325&tableState=Eg8SDQoEdHlwZRADIgMI2w8%3D";
        String tkHoatDongLink = "https://ads.google.com/aw/notifications?ocid=194893325&__u=7656001808&__c=9578557925&ascid=194893325&tableState=Eg8SDQoEdHlwZRADIgMI2w8%3D";

//                                System.out.println(tkHoatDongLink);

        driver.get(tkHoatDongLink);


        String activeAccountList = "";
        try {


            boolean firstDomainPage = true;
            do {

                if (firstDomainPage) {
                    firstDomainPage = false;
                } else {
                    // next page
                    driver.findElement(By.cssSelector(".pager-buttons material-button.next")).click();
                    try {
                        fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                        fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                    } catch (Exception ignore) {

                    }
                }


                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-table-row")));

                query = "document.querySelector(\".base-root\").scrollTop=6000;";
                try {
                    js.executeScript(query);
                } catch (Exception ignored) {

                }


                List<WebElement> mtCustomerRows = driver.findElements(By.cssSelector(".particle-table-row"));


                for (WebElement mtCustomerRow : mtCustomerRows) {

                    try {


                        WebElement mtAccountCellLink = mtCustomerRow.findElement(By.cssSelector(".account-cell-link"));


                        String mtCustomerCellId = "";
                        try {
                            mtCustomerCellId = mtCustomerRow.findElement(By.cssSelector(".external-customer-id")).getText();
                        } catch (Exception u) {
                            mtCustomerCellId = mtAccountCellLink.getText();
                        }

                        mtCustomerCellId = mtCustomerCellId.replaceAll("-", "");

                        formatCustomerId = "";

                        for (int i = 0; i < mtCustomerCellId.length(); i++) {
                            if (Character.isDigit(mtCustomerCellId.charAt(i))) {
                                formatCustomerId += mtCustomerCellId.charAt(i);
                            } else {
                                break;
                            }
                        }

                        if (!activeAccountList.equals("")) {
                            activeAccountList += ",";
                        }

                        activeAccountList += formatCustomerId;


                        // Cap nhat thong bao, ly do bi ngung

                        try {

                            // CAP NHAT TAI KHOAN

                            String requestUrl2 = itgreenToolServer + "/api-ads-account";

                            AdsAccountDTO currentAdsAccountDTO = restTemplate.getForObject(itgreenToolServer + "/api-ads-account/" + formatCustomerId, AdsAccountDTO.class);

                            if (currentAdsAccountDTO != null) {

                                WebElement notification = mtCustomerRow.findElement(By.cssSelector("notification-type-text.notification-type-text .has-hover"));
                                action.moveToElement(notification);
                                notification.click();

//                                                    Thread.sleep(1000);
//                                                    String accNotification = driver.findElement(By.cssSelector(".relative-elements-container .particle-tooltip-container .particle-tooltip")).getText();
                                String accNotification = fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".relative-elements-container .particle-tooltip-container .particle-tooltip"))).getText();

                                if (currentAdsAccountDTO.getNotificationCheckingTime() == null || !currentAdsAccountDTO.getNotificationCheckingTime().equals(accNotification)) {
                                    AdsAccountDTO newAdsAccountDTO = new AdsAccountDTO();

                                    newAdsAccountDTO.setId(currentAdsAccountDTO.getId());
                                    newAdsAccountDTO.setNotificationCheckingTime(new Timestamp(System.currentTimeMillis()));
                                    newAdsAccountDTO.setNotification(accNotification);

                                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                                    HttpHeaders headers = new HttpHeaders();
                                    Map<String, String> param = new HashMap<String, String>();
//                param.put("id","10");
                                    HttpEntity<AdsAccountDTO> requestEntity = new HttpEntity<AdsAccountDTO>(newAdsAccountDTO, headers);
                                    HttpEntity<AdsAccountDTO> response = restTemplate.exchange(requestUrl2, HttpMethod.PUT, requestEntity, AdsAccountDTO.class, param);
                                }


                            }
                        } catch (Exception ignore) {

                        }

                        // END CAP NHAT TAI KHOAN


                    } catch (Exception ignore) {
                    }
                }


            }
            while (driver.findElements(By.cssSelector(".pager-buttons material-button.next.is-disabled")).size() == 0);


        } catch (Exception ignore) {
        }

        if (!activeAccountList.equals("")) {
            GeneralDTO generalDTO = new GeneralDTO();
            generalDTO.setId("active_acc_list");
            generalDTO.setValue(activeAccountList);
            utc = ZonedDateTime.now(ZoneOffset.of("+7"));
            generalDTO.setValue2(utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            restTemplate.put(searchPartnerInfoServer + "/api-general", generalDTO, GeneralDTO.class);
        }


        // ********** END LẤY THÔNG TIN TK HOẠT ĐỘNG


        // ********** LẤY THÔNG TIN TK TẠM NGỪNG


        String tkTamDungLink = "https://ads.google.com/aw/notifications?ocid=194893325&__u=7656001808&__c=9578557925&tableState=EhUSEwoEdHlwZRADIgMI2Q8iBAiKmwQ%3D&ascid=194893325";

        driver.get(tkTamDungLink);

        activeAccountList = "";
        try {

            boolean firstDomainPage = true;
            do {

                if (firstDomainPage) {
                    firstDomainPage = false;
                } else {
                    // next page
                    driver.findElement(By.cssSelector(".pager-buttons material-button.next")).click();
                    try {
                        fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                        fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                    } catch (Exception ignore) {

                    }
                }


                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-table-row")));

                query = "document.querySelector(\".base-root\").scrollTop=6000;";
                try {
                    js.executeScript(query);
                } catch (Exception ignored) {

                }

                List<WebElement> mtCustomerRows = driver.findElements(By.cssSelector(".particle-table-row"));


                for (WebElement mtCustomerRow : mtCustomerRows) {

                    try {


                        WebElement mtAccountCellLink = mtCustomerRow.findElement(By.cssSelector(".account-cell-link"));


                        String mtCustomerCellId = "";
                        try {
                            mtCustomerCellId = mtCustomerRow.findElement(By.cssSelector(".external-customer-id")).getText();
                        } catch (Exception u) {
                            mtCustomerCellId = mtAccountCellLink.getText();
                        }

                        mtCustomerCellId = mtCustomerCellId.replaceAll("-", "");

                        formatCustomerId = "";

                        for (int i = 0; i < mtCustomerCellId.length(); i++) {
                            if (Character.isDigit(mtCustomerCellId.charAt(i))) {
                                formatCustomerId += mtCustomerCellId.charAt(i);
                            } else {
                                break;
                            }
                        }


                        if (!activeAccountList.equals("")) {
                            activeAccountList += ",";
                        }

                        activeAccountList += formatCustomerId;

                        // Cap nhat thong bao, ly do bi ngung

                        try {

                            // CAP NHAT TAI KHOAN

                            String requestUrl2 = itgreenToolServer + "/api-ads-account";

                            AdsAccountDTO currentAdsAccountDTO = restTemplate.getForObject(itgreenToolServer + "/api-ads-account/" + formatCustomerId, AdsAccountDTO.class);

                            if (currentAdsAccountDTO != null) {

                                WebElement notification = mtCustomerRow.findElement(By.cssSelector("notification-type-text.notification-type-text .has-hover"));
                                action.moveToElement(notification);
                                notification.click();

//                                                    Thread.sleep(1000);
//                                                    String accNotification = driver.findElement(By.cssSelector(".relative-elements-container .particle-tooltip-container .particle-tooltip")).getText();
                                String accNotification = fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".relative-elements-container .particle-tooltip-container .particle-tooltip"))).getText();

                                if (currentAdsAccountDTO.getNotificationCheckingTime() == null || !currentAdsAccountDTO.getNotificationCheckingTime().equals(accNotification)) {
                                    AdsAccountDTO newAdsAccountDTO = new AdsAccountDTO();

                                    newAdsAccountDTO.setId(currentAdsAccountDTO.getId());
                                    newAdsAccountDTO.setNotificationCheckingTime(new Timestamp(System.currentTimeMillis()));
                                    newAdsAccountDTO.setNotification(accNotification);

                                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                                    HttpHeaders headers = new HttpHeaders();
                                    Map<String, String> param = new HashMap<String, String>();
//                param.put("id","10");
                                    HttpEntity<AdsAccountDTO> requestEntity = new HttpEntity<AdsAccountDTO>(newAdsAccountDTO, headers);
                                    HttpEntity<AdsAccountDTO> response = restTemplate.exchange(requestUrl2, HttpMethod.PUT, requestEntity, AdsAccountDTO.class, param);
                                }


                            }
                        } catch (Exception ignore) {

                        }

                        // END CAP NHAT TAI KHOAN


                    } catch (Exception ignore) {
                    }
                }


            }
            while (driver.findElements(By.cssSelector(".pager-buttons material-button.next.is-disabled")).size() == 0);


        } catch (Exception ignore) {
        }

        if (!activeAccountList.equals("")) {
            GeneralDTO generalDTO = new GeneralDTO();
            generalDTO.setId("paused_acc_list");
            generalDTO.setValue(activeAccountList);

            utc = ZonedDateTime.now(ZoneOffset.of("+7"));
            generalDTO.setValue2(utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            restTemplate.put(searchPartnerInfoServer + "/api-general", generalDTO, GeneralDTO.class);
        }


        // ********** END LẤY THÔNG TIN TK TẠM NGỪNG


        // ********** LẤY THÔNG TIN TK HOẠT ĐỘNG 2


        tkHoatDongLink = "https://ads.google.com/aw/notifications?ocid=390375515&euid=102741392&__u=7656001808&uscid=390375515&__c=5390887235&tableState=Eg8SDQoEdHlwZRADIgMI2w8%3D&ascid=390375515";

        driver.get(tkHoatDongLink);


        activeAccountList = "";
        try {


            boolean firstDomainPage = true;
            do {

                if (firstDomainPage) {
                    firstDomainPage = false;
                } else {
                    // next page
                    driver.findElement(By.cssSelector(".pager-buttons material-button.next")).click();
                    try {
                        fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                        fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                    } catch (Exception ignore) {

                    }
                }


                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-table-row")));

                query = "document.querySelector(\".base-root\").scrollTop=6000;";
                try {
                    js.executeScript(query);
                } catch (Exception ignored) {

                }

                List<WebElement> mtCustomerRows = driver.findElements(By.cssSelector(".particle-table-row"));


                for (WebElement mtCustomerRow : mtCustomerRows) {

                    try {


                        WebElement mtAccountCellLink = mtCustomerRow.findElement(By.cssSelector(".account-cell-link"));


                        String mtCustomerCellId = "";
                        try {
                            mtCustomerCellId = mtCustomerRow.findElement(By.cssSelector(".external-customer-id")).getText();
                        } catch (Exception u) {
                            mtCustomerCellId = mtAccountCellLink.getText();
                        }

                        mtCustomerCellId = mtCustomerCellId.replaceAll("-", "");

                        formatCustomerId = "";

                        for (int i = 0; i < mtCustomerCellId.length(); i++) {
                            if (Character.isDigit(mtCustomerCellId.charAt(i))) {
                                formatCustomerId += mtCustomerCellId.charAt(i);
                            } else {
                                break;
                            }
                        }


                        if (!activeAccountList.equals("")) {
                            activeAccountList += ",";
                        }

                        activeAccountList += formatCustomerId;

                        // Cap nhat thong bao, ly do bi ngung

                        try {

                            // CAP NHAT TAI KHOAN

                            String requestUrl2 = itgreenToolServer + "/api-ads-account";

                            AdsAccountDTO currentAdsAccountDTO = restTemplate.getForObject(itgreenToolServer + "/api-ads-account/" + formatCustomerId, AdsAccountDTO.class);

                            if (currentAdsAccountDTO != null) {

                                WebElement notification = mtCustomerRow.findElement(By.cssSelector("notification-type-text.notification-type-text .has-hover"));
                                action.moveToElement(notification);
                                notification.click();

//                                                    Thread.sleep(1000);
//                                                    String accNotification = driver.findElement(By.cssSelector(".relative-elements-container .particle-tooltip-container .particle-tooltip")).getText();
                                String accNotification = fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".relative-elements-container .particle-tooltip-container .particle-tooltip"))).getText();

                                if (currentAdsAccountDTO.getNotificationCheckingTime() == null || !currentAdsAccountDTO.getNotificationCheckingTime().equals(accNotification)) {
                                    AdsAccountDTO newAdsAccountDTO = new AdsAccountDTO();

                                    newAdsAccountDTO.setId(currentAdsAccountDTO.getId());
                                    newAdsAccountDTO.setNotificationCheckingTime(new Timestamp(System.currentTimeMillis()));
                                    newAdsAccountDTO.setNotification(accNotification);

                                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                                    HttpHeaders headers = new HttpHeaders();
                                    Map<String, String> param = new HashMap<String, String>();
//                param.put("id","10");
                                    HttpEntity<AdsAccountDTO> requestEntity = new HttpEntity<AdsAccountDTO>(newAdsAccountDTO, headers);
                                    HttpEntity<AdsAccountDTO> response = restTemplate.exchange(requestUrl2, HttpMethod.PUT, requestEntity, AdsAccountDTO.class, param);
                                }


                            }
                        } catch (Exception ignore) {

                        }

                        // END CAP NHAT TAI KHOAN


                    } catch (Exception ignore) {
                    }
                }


            }
            while (driver.findElements(By.cssSelector(".pager-buttons material-button.next.is-disabled")).size() == 0);


        } catch (Exception ignore) {
        }

        if (!activeAccountList.equals("")) {
            GeneralDTO generalDTO = new GeneralDTO();
            generalDTO.setId("active_acc_list_2");
            generalDTO.setValue(activeAccountList);
            utc = ZonedDateTime.now(ZoneOffset.of("+7"));
            generalDTO.setValue2(utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            restTemplate.put(searchPartnerInfoServer + "/api-general", generalDTO, GeneralDTO.class);
        }


        // ********** END LẤY THÔNG TIN TK HOẠT ĐỘNG 2


        // ********** LẤY THÔNG TIN TK TẠM NGỪNG 2


        tkTamDungLink = "https://ads.google.com/aw/notifications?ocid=390375515&euid=102741392&__u=7656001808&uscid=390375515&__c=5390887235&ascid=390375515&tableState=EhUSEwoEdHlwZRADIgMI2Q8iBAiKmwQ%3D";

        driver.get(tkTamDungLink);

        activeAccountList = "";
        try {

            boolean firstDomainPage = true;
            do {

                if (firstDomainPage) {
                    firstDomainPage = false;
                } else {
                    // next page
                    driver.findElement(By.cssSelector(".pager-buttons material-button.next")).click();
                    try {
                        fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                        fastWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".particle-content-loading")));
                    } catch (Exception ignore) {

                    }
                }


                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".particle-table-row")));

                query = "document.querySelector(\".base-root\").scrollTop=6000;";
                try {
                    js.executeScript(query);
                } catch (Exception ignored) {

                }

                List<WebElement> mtCustomerRows = driver.findElements(By.cssSelector(".particle-table-row"));


                for (WebElement mtCustomerRow : mtCustomerRows) {

                    try {


                        WebElement mtAccountCellLink = mtCustomerRow.findElement(By.cssSelector(".account-cell-link"));


                        String mtCustomerCellId = "";
                        try {
                            mtCustomerCellId = mtCustomerRow.findElement(By.cssSelector(".external-customer-id")).getText();
                        } catch (Exception u) {
                            mtCustomerCellId = mtAccountCellLink.getText();
                        }

                        mtCustomerCellId = mtCustomerCellId.replaceAll("-", "");

                        formatCustomerId = "";

                        for (int i = 0; i < mtCustomerCellId.length(); i++) {
                            if (Character.isDigit(mtCustomerCellId.charAt(i))) {
                                formatCustomerId += mtCustomerCellId.charAt(i);
                            } else {
                                break;
                            }
                        }


                        if (!activeAccountList.equals("")) {
                            activeAccountList += ",";
                        }

                        activeAccountList += formatCustomerId;

                        // Cap nhat thong bao, ly do bi ngung

                        try {

                            // CAP NHAT TAI KHOAN

                            String requestUrl2 = itgreenToolServer + "/api-ads-account";

                            AdsAccountDTO currentAdsAccountDTO = restTemplate.getForObject(itgreenToolServer + "/api-ads-account/" + formatCustomerId, AdsAccountDTO.class);

                            if (currentAdsAccountDTO != null) {

                                WebElement notification = mtCustomerRow.findElement(By.cssSelector("notification-type-text.notification-type-text .has-hover"));
                                action.moveToElement(notification);
                                notification.click();

//                                                    Thread.sleep(1000);
//                                                    String accNotification = driver.findElement(By.cssSelector(".relative-elements-container .particle-tooltip-container .particle-tooltip")).getText();
                                String accNotification = fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".relative-elements-container .particle-tooltip-container .particle-tooltip"))).getText();

                                if (currentAdsAccountDTO.getNotificationCheckingTime() == null || !currentAdsAccountDTO.getNotificationCheckingTime().equals(accNotification)) {
                                    AdsAccountDTO newAdsAccountDTO = new AdsAccountDTO();

                                    newAdsAccountDTO.setId(currentAdsAccountDTO.getId());
                                    newAdsAccountDTO.setNotificationCheckingTime(new Timestamp(System.currentTimeMillis()));
                                    newAdsAccountDTO.setNotification(accNotification);

                                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                                    HttpHeaders headers = new HttpHeaders();
                                    Map<String, String> param = new HashMap<String, String>();
//                param.put("id","10");
                                    HttpEntity<AdsAccountDTO> requestEntity = new HttpEntity<AdsAccountDTO>(newAdsAccountDTO, headers);
                                    HttpEntity<AdsAccountDTO> response = restTemplate.exchange(requestUrl2, HttpMethod.PUT, requestEntity, AdsAccountDTO.class, param);
                                }


                            }
                        } catch (Exception ignore) {

                        }

                        // END CAP NHAT TAI KHOAN


                    } catch (Exception ignore) {
                    }
                }


            }
            while (driver.findElements(By.cssSelector(".pager-buttons material-button.next.is-disabled")).size() == 0);


        } catch (Exception ignore) {
        }

        if (!activeAccountList.equals("")) {
            GeneralDTO generalDTO = new GeneralDTO();
            generalDTO.setId("paused_acc_list_2");
            generalDTO.setValue(activeAccountList);

            utc = ZonedDateTime.now(ZoneOffset.of("+7"));
            generalDTO.setValue2(utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            restTemplate.put(searchPartnerInfoServer + "/api-general", generalDTO, GeneralDTO.class);
        }


        // ********** END LẤY THÔNG TIN TK TẠM NGỪNG 2


//      Quét link tự động

        driver.get("https://quanlyads.com/can-theo-doi/theo-doi-10-phut?check=1");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".noi_dung")));
        } catch (Exception ignore) {
        }


        driver.get("https://quanlyads.com/can-theo-doi/theoi-doi-30-phut?check=1");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".noi_dung")));
        } catch (Exception ignore) {
        }


        // Xong
        driver.quit();

    }


    public static Map<String, List<String>> getQueryParams(String url) {
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }

                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            }

            return params;
        } catch (UnsupportedEncodingException ex) {
//            throw new AssertionError(ex);
            return null;
        }
    }


    public static void safeClick(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        System.out.println(element);

        wait.until(d -> element.isDisplayed() && element.isEnabled());

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            element.click();
        } catch (Exception e) {
            // fallback khi click thường bị che hoặc fail
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }


    public static void safeClickMaterial(WebDriver driver, By locator) {
        System.out.println("=== [safeClickMaterial] Start click: " + locator + " ===");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element;

        try {
            // STEP 1: Presence
            System.out.println("[STEP 1] Wait presence...");
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            System.out.println("[OK] Presence done");

            // STEP 2: Lấy element thật sự hiển thị
            System.out.println("[STEP 2] Wait visible element (filter invisible duplicates)...");
            element = wait.until(driver1 -> {
                List<WebElement> els = driver1.findElements(locator);
                for (WebElement el : els) {
                    if (el.isDisplayed() && el.getSize().getHeight() > 0 && el.getSize().getWidth() > 0) {
                        return el;
                    }
                }
                return null; // vẫn chờ đến khi có
            });
            System.out.println("[OK] Visible element found: size=" + element.getSize() + ", loc=" + element.getLocation());

            // STEP 3: Chờ clickable
            System.out.println("[STEP 3] Wait clickable...");
            wait.until(ExpectedConditions.elementToBeClickable(element));
            System.out.println("[OK] Element clickable");

        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for visible/clickable element: " + locator, e);
        }

        // Thử các cách click
        try {
            System.out.println("[TRY 1] Normal click...");
            element.click();
            System.out.println("[SUCCESS] Normal click");
            return;
        } catch (Exception e1) {
            System.out.println("[FAIL] Normal click: " + e1.getMessage());
        }

        try {
            System.out.println("[TRY 2] JS click with scroll...");
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            js.executeScript("arguments[0].click();", element);
            System.out.println("[SUCCESS] JS click");
            return;
        } catch (Exception e2) {
            System.out.println("[FAIL] JS click: " + e2.getMessage());
        }

        try {
            System.out.println("[TRY 3] Inner .content click...");
            WebElement inner = element.findElement(By.cssSelector(".content"));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", inner);
            js.executeScript("arguments[0].click();", inner);
            System.out.println("[SUCCESS] Inner .content click");
            return;
        } catch (Exception e3) {
            System.out.println("[FAIL] Inner click: " + e3.getMessage());
        }

        try {
            System.out.println("[TRY 4] Dispatch MouseEvents...");
            String script =
                    "var el = arguments[0];" +
                            "el.dispatchEvent(new MouseEvent('mousedown', {bubbles:true}));" +
                            "el.dispatchEvent(new MouseEvent('mouseup', {bubbles:true}));" +
                            "el.click();";
            js.executeScript(script, element);
            System.out.println("[SUCCESS] Event dispatch click");
            return;
        } catch (Exception e4) {
            System.out.println("[FAIL] Event dispatch click: " + e4.getMessage());
            throw new RuntimeException("safeClickMaterial: All click methods failed", e4);
        }
    }


}
