package com.akagera.park.controller;
import com.akagera.park.model.Form;
import com.akagera.park.repository.FormRepo;
import com.akagera.park.services.PdfService;
import com.itextpdf.text.DocumentException;
//import com.sf.stylefusion.model.Portfolio;
//import com.sf.stylefusion.repository.PortfolioRepository;
//import com.sf.stylefusion.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PdfController {
    private final FormRepo formRepo;
    private final PdfService pdfService;



    public PdfController(FormRepo portfolioRepository, PdfService pdfService) {
        this.formRepo = portfolioRepository;
        this.pdfService = pdfService;
    }

        @GetMapping("/download-pdf")
        public ResponseEntity<byte[]> downloadPdf () throws DocumentException {
            List<Form> portfolios = formRepo.findAll();

            StringBuilder dataBuilder = new StringBuilder();
            for (Form portfolio : portfolios) {
                dataBuilder.append("ID: ").append(portfolio.getId()).append("\n")
                        .append("Car name: ").append(portfolio.getCarName()).append("\n")
                        .append("car price: ").append(portfolio.getCarPrice()).append("\n")
                        .append("payment ").append(portfolio.getPayment()).append("\n")
                        .append("status ").append(portfolio.getStatus()).append("\n");
//                    .append("Name: ").append(portfolio.getName()).append("\n\n");
            }

            String data = dataBuilder.toString();

            byte[] pdfBytes = pdfService.generatePdf(data);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "CRUDTable.pdf");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(pdfBytes);
        }
    }
