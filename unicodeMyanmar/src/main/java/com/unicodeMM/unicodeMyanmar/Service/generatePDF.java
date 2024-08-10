package com.unicodeMM.unicodeMyanmar.Service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.unicodeMM.unicodeMyanmar.serviceUtil.MMUnicode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class generatePDF {

    public static byte[] downloadPDF() throws DocumentException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        FontFactory.register("E:\\Development\\Unicode_Myanmar\\unicodeMyanmar\\Contents\\fonts\\NotoSansMyanmar-Regular.ttf" , "mmFont");
        FontFactory.register("E:\\Development\\Unicode_Myanmar\\unicodeMyanmar\\Contents\\fonts\\Roboto-Regular.ttf", "engFont");

        Font mmFont = FontFactory.getFont(
                "mmFont" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED , 12 ,Font.NORMAL,
                BaseColor.BLACK);

        Document document = new Document();
        PdfWriter.getInstance(document , outputStream);
        document.open();

        PdfPTable table = new PdfPTable(13);
        table.setWidthPercentage(100);

        String myanmarText = MMUnicode.fixFont(
                " သီဟိုဠ်မှ ဉာဏ်ကြီးရှင်သည်အာယုဝဎ္ဍနဆေးညွှန်းစာကို ဇလွန်ဈေးဘေးဗာဒံပင်ထက် အဓိဋ္ဌာန်လျက် ဂဃနဏဖတ်ခဲ့သည်။ "
        );

        generatePDFHeaders(myanmarText , table , mmFont);

        document.add(table);
        document.close();

        return outputStream.toByteArray();
    }

    private static void generatePDFHeaders(String header , PdfPTable table, Font mmFont) {
        Phrase headerPhrase = new Phrase();

        headerPhrase.add(new Chunk(header , mmFont));

        PdfPCell headerCell = new PdfPCell(headerPhrase);
        headerCell.setColspan(13);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setBackgroundColor(new BaseColor(0, 0, 0 ,0));
        table.addCell(headerCell);
    }
}
