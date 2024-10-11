package com.redcatdev86.openpdfdemo.service.impl;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.redcatdev86.openpdfdemo.bean.ProductBean;
import com.redcatdev86.openpdfdemo.bean.SummaryShopDataBean;
import com.redcatdev86.openpdfdemo.event.HeaderFooterWithPageNumberEventHandler;
import com.redcatdev86.openpdfdemo.utility.PdfUtility;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static com.lowagie.text.Element.ALIGN_CENTER;
import static com.lowagie.text.Element.ALIGN_LEFT;
import static com.lowagie.text.Font.BOLD;
import static java.awt.Color.BLACK;

public class SummaryShopPdfExporterImpl extends AbstractPdfExporterImpl<SummaryShopDataBean> {

    private static final Font TIME_FONT_DEFAULT = PdfUtility.buildFont(FontFactory.TIMES, FontFactory.TIMES, 10, BLACK);

    @Autowired
    private MessageSource messageSource;

    private List<String> headers;

    public void init(){
        headers = Collections.unmodifiableList(Arrays.asList("summary.shop.productsummary.desc"
                , "summary.shop.productsummary.code", "summary.shop.productsummary.unit"
                , "summary.shop.productsummary.price"));
    }

    @Override
    protected void populateData(SummaryShopDataBean data, ByteArrayOutputStream out) throws IOException {
        Document document = PdfUtility.buildDocument();
        PdfWriter writer = PdfWriter.getInstance(document, out);
        writer.setPageEvent(new HeaderFooterWithPageNumberEventHandler());
        document.open();

        Paragraph orderSummary = PdfUtility.buildParagraph(messageSource.getMessage("summary.shop.report.title", new Object[]{"15151515151"}, Locale.UK), PdfUtility.buildFont(FontFactory.TIMES, FontFactory.TIMES, 14, BLACK));
        orderSummary.setAlignment(ALIGN_CENTER);
        orderSummary.setSpacingAfter(50f);
        document.add(orderSummary);

        document.add(buildSummaryTable(data));

        document.add(buildProductOrdered(data));
        document.close();
        writer.close();
    }

    private PdfPTable buildSummaryTable(SummaryShopDataBean data) {
        PdfPTable table = new PdfPTable(new float[]{10f, 10f});
        table.setWidthPercentage(30f);
        table.setSpacingAfter(40f);
        table.setHorizontalAlignment(ALIGN_LEFT);

        PdfPCell firstNameC1 = PdfUtility.buildTextCell(messageSource.getMessage("summary.shop.summary.firstname", null, Locale.UK), TIME_FONT_DEFAULT);
        PdfUtility.decorateCell(firstNameC1, 0f, null, 0f, null, 0f, null, 0f, null);
        table.addCell(firstNameC1);

        PdfPCell firstNameC2 = PdfUtility.buildTextCell(data.getPersonBean().getFirstName(), TIME_FONT_DEFAULT);
        PdfUtility.decorateCell(firstNameC2, 0f, null, 0.5f, BLACK, 0f, null, 0f, null);
        table.addCell(firstNameC2);

        PdfPCell lasttNameC1 = PdfUtility.buildTextCell(messageSource.getMessage("summary.shop.summary.lastname", null, Locale.UK), TIME_FONT_DEFAULT);
        PdfUtility.decorateCell(lasttNameC1, 0f, null, 0f, null, 0f, null, 0f, null);
        table.addCell(lasttNameC1);

        PdfPCell lasttNameC2 = PdfUtility.buildTextCell(data.getPersonBean().getLastName(), TIME_FONT_DEFAULT);
        PdfUtility.decorateCell(lasttNameC2, 0f, null, 0.5f, BLACK, 0f, null, 0f, null);
        table.addCell(lasttNameC2);

        PdfPCell datetNameC1 = PdfUtility.buildTextCell(messageSource.getMessage("summary.shop.summary.birth", null, Locale.UK), TIME_FONT_DEFAULT);
        PdfUtility.decorateCell(datetNameC1, 0f, null, 0f, null, 0f, null, 0f, null);
        table.addCell(datetNameC1);

        PdfPCell datetNameC2 = PdfUtility.buildDateCell(data.getPersonBean().getDateOfBirth(), TIME_FONT_DEFAULT, "dd/MM/yyyy");
        PdfUtility.decorateCell(datetNameC2, 0f, null, 0.5f, BLACK, 0f, null, 0f, null);
        table.addCell(datetNameC2);

        PdfPCell phoneNumberC1 = PdfUtility.buildTextCell(messageSource.getMessage("summary.shop.summary.phonenumber", null, Locale.UK), TIME_FONT_DEFAULT);
        PdfUtility.decorateCell(phoneNumberC1, 0f, null, 0f, null, 0f, null, 0f, null);
        table.addCell(phoneNumberC1);

        PdfPCell phoneNumberC2 = PdfUtility.buildTextCell(data.getPersonBean().getPhoneNumber(), TIME_FONT_DEFAULT);
        PdfUtility.decorateCell(phoneNumberC2, 0f, null, 0.5f, BLACK, 0f, null, 0f, null);
        table.addCell(phoneNumberC2);

        return table;
    }

    private PdfPTable buildProductOrdered(SummaryShopDataBean data){
        PdfPTable table = new PdfPTable(new float[]{40f, 30f, 15f, 15f});
        table.setWidthPercentage(100f);
        table.setSpacingAfter(40f);

        int index = 0;
        for (String header : headers){
            Font headerFont = PdfUtility.buildFont(FontFactory.TIMES, FontFactory.TIMES, 10, BLACK);
            headerFont.setStyle(BOLD);
            PdfPCell headerCell = PdfUtility.buildTextCell(messageSource.getMessage(header, null, Locale.UK), headerFont);
            if (index == headers.size() - 1){
                PdfUtility.decorateCell(headerCell, 0.6f, BLACK, 0.6f, BLACK, 0f, null, 0f, null);
            }else{
                PdfUtility.decorateCell(headerCell, 0.6f, BLACK, 0.6f, BLACK, 0f, null, 0.6f, BLACK);
            }
            headerCell.setPaddingTop(5f);
            headerCell.setPaddingBottom(10f);
            headerCell.setHorizontalAlignment(ALIGN_CENTER);
            table.addCell(headerCell);
            index++;
        }

        if (CollectionUtils.isNotEmpty(data.getProducts())){
            for (ProductBean product : data.getProducts()){
                PdfPCell prodDescCell = PdfUtility.buildTextCell(product.getProductName(), TIME_FONT_DEFAULT);
                prodDescCell.setHorizontalAlignment(ALIGN_LEFT);
                prodDescCell.setPaddingTop(5f);
                prodDescCell.setPaddingBottom(10f);
                PdfUtility.decorateCell(prodDescCell, 0f, null, 0f, null, 0f, null, 0.6f, BLACK);

                PdfPCell prodCodeCell = PdfUtility.buildTextCell(product.getProductCode(), TIME_FONT_DEFAULT);
                prodCodeCell.setHorizontalAlignment(ALIGN_LEFT);
                prodCodeCell.setPaddingTop(5f);
                prodCodeCell.setPaddingBottom(10f);
                PdfUtility.decorateCell(prodCodeCell, 0f, null, 0f, null, 0f, null, 0.6f, BLACK);

                PdfPCell prodUnit = PdfUtility.buildNumericCell(product.getUnit(), TIME_FONT_DEFAULT, "-");
                prodUnit.setPaddingTop(5f);
                prodUnit.setPaddingBottom(10f);
                PdfUtility.decorateCell(prodUnit, 0f, null, 0f, null, 0f, null, 0.6f, BLACK);

                PdfPCell prodPrice = PdfUtility.buildNumericCell(product.getPrice(), TIME_FONT_DEFAULT, "-");
                prodPrice.setPaddingTop(5f);
                prodPrice.setPaddingBottom(10f);
                PdfUtility.decorateCell(prodPrice, 0f, null, 0f, null, 0f, null, 0f, null);

                table.addCell(prodDescCell);
                table.addCell(prodCodeCell);
                table.addCell(prodUnit);
                table.addCell(prodPrice);
            }
        }

        return table;
    }
}
