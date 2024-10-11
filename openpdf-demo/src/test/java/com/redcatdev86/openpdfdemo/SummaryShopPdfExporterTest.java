package com.redcatdev86.openpdfdemo;

import com.redcatdev86.openpdfdemo.bean.PersonBean;
import com.redcatdev86.openpdfdemo.bean.ProductBean;
import com.redcatdev86.openpdfdemo.bean.SummaryShopDataBean;
import com.redcatdev86.openpdfdemo.service.PdfExporter;
import com.redcatdev86.openpdfdemo.utility.DateUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SummaryShopPdfExporterTest {

    @Autowired
    @Qualifier("summaryShopPdfExporter")
    private PdfExporter<SummaryShopDataBean> exporter;

    @Test
    public void exportData() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        exporter.exportPDF(getSummaryShopBean(), out);
        OutputStream outStream = new FileOutputStream("summaryShopData.pdf");
        out.writeTo(outStream);
        out.close();
    }

    private SummaryShopDataBean getSummaryShopBean(){
        SummaryShopDataBean summaryShopBean = new SummaryShopDataBean();
        PersonBean personBean = new PersonBean();
        personBean.setFirstName("John");
        personBean.setLastName("Doe");
        personBean.setDateOfBirth(DateUtility.randomDate());
        personBean.setPhoneNumber("+55 123456789");
        summaryShopBean.setPersonBean(personBean);

        List<ProductBean> productBeans = new ArrayList<>();
        productBeans.add(getProductBean("Smartphone Samsung", "samsung001", 1, BigDecimal.valueOf(250.50f)));
        productBeans.add(getProductBean("Smartphone Lg", "lg001", 1, BigDecimal.valueOf(250.50f)));
        productBeans.add(getProductBean("Smartphone Xiaomi", "Xiaomi001", 1, BigDecimal.valueOf(250.50f)));
        productBeans.add(getProductBean("Smartphone Oppo", "oppo001", 1, BigDecimal.valueOf(250.50f)));
        productBeans.add(getProductBean("Iphone 7", "iphone007", 1, BigDecimal.valueOf(250.50f)));

        summaryShopBean.setProducts(productBeans);

        return summaryShopBean;
    }

    private ProductBean getProductBean(String productName, String productCode, Integer unit, BigDecimal price){
        ProductBean productBean = new ProductBean();
        productBean.setProductName(productName);
        productBean.setProductCode(productCode);
        productBean.setUnit(unit);
        productBean.setPrice(price);
        return productBean;
    }
}
