package com.redcatdev86.openpdfdemo.utility;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.redcatdev86.openpdfdemo.type.PdfOrientation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import java.awt.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.lowagie.text.Element.*;
import static com.lowagie.text.PageSize.A4;
import static com.redcatdev86.openpdfdemo.type.PdfOrientation.LANDSCAPE;

public class PdfUtility {

    public static Document buildDocument(){
        return new Document(A4);
    }

    public static Document buildDocument(PdfOrientation orientation) {
        Document document = buildDocument();
        document.setMargins(30, 30, 60, 60);
        if (orientation.equals(LANDSCAPE)){
            document.setPageSize(A4.rotate());
        }
        return document;
    }

    public static Document buildDocument(PdfOrientation orientation, float marginLeft, float marginRight, float marginTop, float marginBottom){
        Document document = buildDocument(orientation);
        document.setMargins(marginLeft, marginRight, marginTop, marginBottom);
        return document;
    }

    public static Font buildFont(String fontName, String fontFamily, int fontSize, Color color) {
        Font font = FontFactory.getFont(fontName);
        font.setFamily(fontFamily);
        font.setSize(fontSize);
        font.setColor(color);
        return font;
    }

    public static PdfPCell buildCell(Paragraph paragraph){
        return new PdfPCell(paragraph);
    }

    public static Paragraph buildParagraph(String text, Font font){
        Paragraph paragraph = new Paragraph(text, font);
        return paragraph;
    }

    public static Paragraph buildParagraph(String text, Font font, int alignment){
        Paragraph paragraph = buildParagraph(text, font);
        paragraph.setAlignment(alignment);
        return paragraph;
    }

    public static void decorateCell(PdfPCell cell, float borderTop, Color borderTopColor
            , float borderBottom, Color borderBottomColor
            , float borderLeft, Color borderLeftColor
            , float borderRight, Color borderRightColor){
        cell.setBorderWidthRight(borderRight);
        cell.setBorderWidthLeft(borderLeft);
        cell.setBorderWidthTop(borderTop);
        cell.setBorderWidthBottom(borderBottom);
        cell.setBorderColorTop(borderTopColor);
        cell.setBorderColorBottom(borderBottomColor);
        cell.setBorderColorLeft(borderLeftColor);
        cell.setBorderColorRight(borderRightColor);
    }

    public static PdfPCell buildNumericCell(Number value, Font font, String defaultValue){
        PdfPCell cell;
        if (value == null){
            cell = new PdfPCell(new Phrase(defaultValue, font));
        }else{
            NumberFormat nf = NumberFormat.getInstance(LocaleContextHolder.getLocale());
            nf.setMaximumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            cell = new PdfPCell(new Phrase(nf.format(value), font));
        }
        cell.setVerticalAlignment(ALIGN_MIDDLE);
        cell.setHorizontalAlignment(ALIGN_RIGHT);
        return cell;
    }

    public static PdfPCell buildPercentageCell(Number value, Font font, String defaultValue){
        PdfPCell cell;
        if (value == null){
            cell = new PdfPCell(new Phrase(defaultValue, font));
        }else{
            NumberFormat nf = NumberFormat.getInstance(LocaleContextHolder.getLocale());
            nf.setMaximumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            cell = new PdfPCell(new Phrase(nf.format(value) + "%", font));
        }
        cell.setVerticalAlignment(ALIGN_MIDDLE);
        cell.setHorizontalAlignment(ALIGN_RIGHT);
        return cell;
    }

    public static PdfPCell buildTextCell(String text, Font font){
        PdfPCell cell = new PdfPCell(buildParagraph(text, font));
        return cell;
    }

    public static PdfPCell buildDateCell(Date date, Font font, String pattern){
        PdfPCell cell;
        if (date == null){
            cell = new PdfPCell(new Phrase("n.d", font));
        }else{
            if (StringUtils.isEmpty(pattern)){
                pattern = "dd/MM/yyyy";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            cell = new PdfPCell(new Phrase(sdf.format(date), font));
        }
        cell.setHorizontalAlignment(ALIGN_LEFT);
        return cell;
    }


}
