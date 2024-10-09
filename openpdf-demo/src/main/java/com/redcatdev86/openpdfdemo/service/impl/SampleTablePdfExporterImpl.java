package com.redcatdev86.openpdfdemo.service.impl;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.redcatdev86.openpdfdemo.bean.PersonBean;
import com.redcatdev86.openpdfdemo.event.HeaderFooterWithPageNumberEventHandler;
import com.redcatdev86.openpdfdemo.utility.PdfUtility;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.redcatdev86.openpdfdemo.utility.PdfUtility.*;
import static java.awt.Color.BLACK;

public class SampleTablePdfExporterImpl extends AbstractPdfExporterImpl<List<PersonBean>> {
    @Override
    protected void populateData(List<PersonBean> data, ByteArrayOutputStream out) throws IOException {
        Document document = PdfUtility.buildDocument();
        PdfWriter writer = PdfWriter.getInstance(document, out);
        writer.setPageEvent(new HeaderFooterWithPageNumberEventHandler());
        document.open();
        Paragraph title = buildParagraph("Sample table", buildFont(FontFactory.HELVETICA, FontFactory.HELVETICA, 12, Color.RED));
        title.setAlignment(Element.ALIGN_LEFT);
        title.setSpacingAfter(15f);
        document.add(title);
        document.add(buildTable(data));
        document.close();
        writer.close();
    }

    private PdfPTable buildTable(List<PersonBean> persons) {
        float[] columnDefinitionSize = {33.33F, 33.33F, 33.33f, 33.33F};
        PdfPTable table = new PdfPTable(columnDefinitionSize);
        table.getRows().add(buildHeaders());
        for (PersonBean person : persons) {
            table.getRows().add(buildPersonRow(person));
        }
        table.setWidthPercentage(100f);
        table.setSpacingAfter(20f);
        table.setSpacingBefore(20f);
        return table;
    }

    private PdfPRow buildPersonRow(PersonBean person) {
        List<PdfPCell> cells = new ArrayList<>();
        cells.add(buildTextCell(String.valueOf(person.getId()), buildFont(FontFactory.HELVETICA, FontFactory.HELVETICA, 10, BLACK)));
        cells.add(buildTextCell(person.getFirstName(), buildFont(FontFactory.HELVETICA, FontFactory.HELVETICA, 10, BLACK)));
        cells.add(buildTextCell(person.getLastName(), buildFont(FontFactory.HELVETICA, FontFactory.HELVETICA, 10, BLACK)));
        cells.add(buildDateCell(person.getDateOfBirth(), buildFont(FontFactory.HELVETICA, FontFactory.HELVETICA, 10, BLACK), null));
        return new PdfPRow(cells.toArray(new PdfPCell[0]));
    }

    private PdfPRow buildHeaders(){
        List<String> headers = new ArrayList<>();
        headers.add("ID");
        headers.add("Firstname");
        headers.add("Lastname");
        headers.add("Age");
        List<PdfPCell> cells = headers.stream()
                .map(header -> buildTextCell(header, buildFont(FontFactory.HELVETICA, FontFactory.HELVETICA, 10, BLACK)))
                .collect(Collectors.toList());
        return new PdfPRow(cells.toArray(new PdfPCell[0]));
    }
}
