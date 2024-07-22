package com.redcatdev86.itextsevendemo;

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
public class HelloWorldExporterTest {

    @Autowired
    @Qualifier("helloWorldExporter")
    private PdfExporter<String> helloWorldExporter;

    @Test
    public void exportData(){
        try {
            helloWorldExporter.exportPDF("Hello World!", "pdfdata/HelloWorld.pdf");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
