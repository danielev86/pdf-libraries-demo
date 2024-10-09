package com.redcatdev86.openpdfdemo.event;

import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import com.redcatdev86.openpdfdemo.utility.PdfUtility;
import lombok.extern.slf4j.Slf4j;

import static com.lowagie.text.Element.ALIGN_RIGHT;
import static com.redcatdev86.openpdfdemo.utility.PdfUtility.*;
import static java.awt.Color.BLACK;

@Slf4j
public class HeaderFooterWithPageNumberEventHandler extends PdfPageEventHelper {

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        customizeHeader(writer, document);
        customizeFooter(writer, document);
    }

    private void customizeHeader(PdfWriter writer, Document document) {
        PdfPTable headerTable = new PdfPTable(1);
        try{
            headerTable.setTotalWidth(document.right()-document.rightMargin());
            headerTable.setLockedWidth(true);

            PdfPCell text = buildCell(buildParagraph("", null));
            PdfUtility.decorateCell(text, 1f, BLACK, 0f, null
                    , 0f, null, 0f, null);
            text.setPaddingBottom(5);
            text.setPaddingLeft(5);
            headerTable.addCell(text);

            int x = Math.round(document.left());
            int y = Math.round(document.top()) + Math.round(document.topMargin()) -30;
            headerTable.writeSelectedRows(0, -1, x, y, writer.getDirectContent());
        }catch (Exception e){
            log.error("Error header creation {}", e.getMessage(), e);
        }
    }

    private void customizeFooter(PdfWriter writer, Document document) {
        PdfPTable footerTable = new PdfPTable(1);
        try{
            footerTable.setTotalWidth(document.right()-document.rightMargin());
            footerTable.setLockedWidth(true);

            PdfPCell text = buildCell(buildParagraph("Page " + document.getPageNumber(), buildFont(FontFactory.HELVETICA, FontFactory.HELVETICA, 10, BLACK)));
            PdfUtility.decorateCell(text, 1f, BLACK, 0f, null
                    , 0f, null, 0f, null);
            text.setHorizontalAlignment(ALIGN_RIGHT);
            text.setPaddingTop(5);
            text.setPaddingLeft(5);
            footerTable.addCell(text);

            int x = Math.round(document.left());
            int y = Math.round(document.bottom())-5;
            footerTable.writeSelectedRows(0, -1, x, y, writer.getDirectContent());
        }catch (Exception e){
            log.error("Error header creation {}", e.getMessage(), e);
        }
    }

}
