package com.barsu.convert.converter.impl;

import com.barsu.convert.converter.ToPdfConverter;
import com.barsu.convert.entity.Convert;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ToPdfConverterImpl implements ToPdfConverter {

    @Override
    public Document convert(Convert convert) {
        String filePdf = "C:/Users/user/Desktop/PdfFile/convert.pdf";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePdf));
            document.open();
            addMessage(document, buildConvertMessage(convert));
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        return document;
    }

    private List<String> buildConvertMessage(Convert convert) {
        List<String> result = new ArrayList<>();
        result.add("Idea : " + convert.getIdea());
        result.add("Resources : " + convert.getResources());
        result.add("Market : " + convert.getMarket());
        result.add(("Results : ") + convert.getResults());
        return result;
    }

    private void addMessage(Document document, List<String> messages) throws DocumentException {
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        for (String message : messages) {
            Chunk chunk = new Chunk(message, font);
            document.add(chunk);
        }
    }
}
