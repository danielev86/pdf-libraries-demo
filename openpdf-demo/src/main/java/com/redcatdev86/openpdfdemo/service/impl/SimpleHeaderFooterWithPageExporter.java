package com.redcatdev86.openpdfdemo.service.impl;

import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.PdfWriter;
import com.redcatdev86.openpdfdemo.event.HeaderFooterWithPageNumberEventHandler;
import com.redcatdev86.openpdfdemo.utility.PdfUtility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.redcatdev86.openpdfdemo.utility.PdfUtility.buildParagraph;
import static java.awt.Color.BLACK;

public class SimpleHeaderFooterWithPageExporter extends AbstractPdfExporterImpl<String>{
    @Override
    protected void populateData(String data, ByteArrayOutputStream out) throws IOException {
        Document document = PdfUtility.buildDocument();
        PdfWriter writer = PdfWriter.getInstance(document, out);
        writer.setPageEvent(new HeaderFooterWithPageNumberEventHandler());
        document.open();
        document.add(buildParagraph("Hello world", PdfUtility.buildFont(FontFactory.HELVETICA, FontFactory.HELVETICA, 10, BLACK)));
        document.close();
        writer.close();
    }
}
