package com.redcatdev86.itextsevendemo;

import com.redcatdev86.itextsevendemo.bean.SimpleDataBean;
import com.redcatdev86.itextsevendemo.service.PdfExporter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SimplePdfExporterTest {

    @Autowired
    @Qualifier("simplePdfExporter")
    private PdfExporter<SimpleDataBean> pdfExporter;

    @Test
    public void testExport() {
        SimpleDataBean simpleDataBean = new SimpleDataBean();
        simpleDataBean.addNote("First note");
        simpleDataBean.addNote("Second note");
        simpleDataBean.addNote("Third note");
        simpleDataBean.addNote("Fourth note");
        simpleDataBean.addNote("Fifth note");
        try {
            pdfExporter.exportPDF(simpleDataBean, "pdfdata/SimplePdfExporter.pdf");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
