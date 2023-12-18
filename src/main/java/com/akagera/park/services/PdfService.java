package com.akagera.park.services;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Document;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
@Service
public class PdfService {
    public byte[] generatePdf(String data) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();
        document.add(new Paragraph(data));
        document.close();

        return byteArrayOutputStream.toByteArray();
}
}
