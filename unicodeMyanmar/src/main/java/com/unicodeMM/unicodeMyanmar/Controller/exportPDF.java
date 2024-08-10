package com.unicodeMM.unicodeMyanmar.Controller;

import com.itextpdf.text.DocumentException;
import com.unicodeMM.unicodeMyanmar.Service.generatePDF;
import org.apache.coyote.Response;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/home")
public class exportPDF {

    @GetMapping("/downloadPDF")
    @ResponseBody
    public ResponseEntity<byte[]> generatePdf() throws DocumentException {
        byte[] dataBytes = generatePDF.downloadPDF();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "document.pdf");

        return new ResponseEntity<>(dataBytes, headers, HttpStatus.OK);
    }
}
