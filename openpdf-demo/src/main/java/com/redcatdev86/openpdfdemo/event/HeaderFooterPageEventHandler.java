package com.redcatdev86.openpdfdemo.event;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import com.redcatdev86.openpdfdemo.utility.PdfUtility;
import lombok.extern.slf4j.Slf4j;

import static com.redcatdev86.openpdfdemo.utility.PdfUtility.buildCell;
import static com.redcatdev86.openpdfdemo.utility.PdfUtility.buildParagraph;
import static java.awt.Color.BLACK;

@Slf4j
public class HeaderFooterPageEventHandler extends PdfPageEventHelper {

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
            headerTable.addCell(text);

            int x = Math.round(document.left());
            int y = Math.round(document.top()) + Math.round(document.topMargin()) -20;
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

            PdfPCell text = buildCell(buildParagraph("", null));
            PdfUtility.decorateCell(text, 1f, BLACK, 0f, null
                    , 0f, null, 0f, null);
            footerTable.addCell(text);

            int x = Math.round(document.left());
            int y = Math.round(document.bottom());
            footerTable.writeSelectedRows(0, -1, x, y, writer.getDirectContent());
        }catch (Exception e){
            log.error("Error header creation {}", e.getMessage(), e);
        }
    }

}
