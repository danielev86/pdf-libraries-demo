package com.redcatdev86.openpdfdemo;

import com.redcatdev86.openpdfdemo.service.PdfExporter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

@SpringBootTest
@Slf4j
public class HeaderFooterPdfPageExporterTest {

    @Autowired
    @Qualifier("simpleHeaderFooterPdfPageExporter")
    private PdfExporter<String> simpleExporter;

    @Autowired
    @Qualifier("simpleHeaderFooterWithPageExporter")
    private PdfExporter<String> pdfPageNumberExporter;

    @Test
    public void testExport() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        simpleExporter.exportPDF("", out);
        OutputStream outStream = new FileOutputStream("simpleHeaderFooter.pdf");
        out.writeTo(outStream);
        out.close();
    }

    @Test
    public void testExportPageWithNumber() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        pdfPageNumberExporter.exportPDF("", out);
        OutputStream outStream = new FileOutputStream("simpleHeaderFooterNumber.pdf");
        out.writeTo(outStream);
        out.close();
    }

}
