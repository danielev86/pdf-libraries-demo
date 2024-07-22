package com.redcatdev86.itextsevendemo.service.impl;

import com.itextpdf.layout.Document;
import com.redcatdev86.itextsevendemo.service.CommonPdfExporter;
import com.redcatdev86.itextsevendemo.service.PdfExporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
public class HelloWorldExporterImpl extends CommonPdfExporter<String> implements PdfExporter<String> {

    @Override
    protected void populateData(Document document, String text) throws IOException {
        log.info("Create Hello World Document");
        addParagraph(document, text, null);
    }

}
