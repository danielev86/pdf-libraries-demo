package com.redcatdev86.itextsevendemo.service.impl;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.redcatdev86.itextsevendemo.bean.SimpleDataBean;
import com.redcatdev86.itextsevendemo.service.CommonPdfExporter;
import com.redcatdev86.itextsevendemo.service.PdfExporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
public class SimplePdfExporterImpl extends CommonPdfExporter<SimpleDataBean> implements PdfExporter<SimpleDataBean> {

    protected void populateData(Document document, SimpleDataBean data) throws IOException {
        log.info("Create simple listed elements");
        PdfFont timesNewRomanFont = getFont(FontConstants.TIMES_ROMAN);
        addParagraph(document, "Listed elements: ", timesNewRomanFont);
        List elementList = new List().setSymbolIndent(14).setListSymbol("\u2022").setFont(timesNewRomanFont);
        data.getNotes().forEach(ele -> elementList.add(new ListItem(ele)));
        document.add(elementList);
    }

}