package com.redcatdev86.itextsevendemo.service;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public abstract class CommonPdfExporter<T>{

    public void exportPDF(T data, String path) throws Exception {
        log.info("Start create new pdf");
        PdfWriter writer = new PdfWriter(path);
        Document document = createDocument(writer);
        populateData(document, data);
        document.close();
        writer.close();
        log.info("New pdf created! close stream");
    }

    protected abstract void populateData(Document document, T data) throws IOException;

    protected PdfFont getFont(String fontName) throws IOException {
        return PdfFontFactory.createFont(fontName);
    }

    protected void addParagraph(Document document, String text) {
        addParagraph(document, text, null);
    }

    protected void addParagraph(Document document, String text, PdfFont font) {
        Paragraph paragraph = new Paragraph(text);
        if (font != null) {
            paragraph.setFont(font);
        }
        document.add(new Paragraph(text));
    }

    protected Document createDocument(PdfWriter writer) {
        PdfDocument pdfDocument = new PdfDocument(writer);
        return new Document(pdfDocument);
    }
}
