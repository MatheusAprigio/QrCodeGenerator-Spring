package com.qrcode.qrcode.controllers;

import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qrcode.qrcode.services.QrCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class QrCodeController {

    @Autowired
    QrCodeService qrCodeService;

    @GetMapping
    public String getGeneratorPage() {
        return "generatorPage";
    }

    @PostMapping("generate")

    public String postMethodName(
    @RequestParam(value = "qrCodeText", required = true) String qrCodeText , @RequestParam(value="textType") String textType, RedirectAttributes attributes) throws WriterException, IOException{
         
        attributes.addFlashAttribute("qrcode", qrCodeService.generateQrCode(qrCodeText));
        return "redirect:generatorPage";
    }
    
    @ExceptionHandler({IOException.class, WriterException.class})
    public void handleIOException(Exception ex) {
        //TODO Handle Exception
    }
}