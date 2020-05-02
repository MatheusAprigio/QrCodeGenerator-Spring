package com.qrcode.qrcode.controllers;

import java.io.IOException;

import com.google.zxing.WriterException;
import com.qrcode.qrcode.services.QrCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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

    public String postGenerateQrCode(
    @RequestParam(value = "qrCodeText", required = true) String qrCodeText, Model model) throws WriterException, IOException{
         
        model.addAttribute("qrcode", qrCodeService.generateQrCode(qrCodeText));
        return "generatorPage";
    }
     
    @ExceptionHandler({IOException.class, WriterException.class})
    public ModelAndView handleIOException(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "Something went wrong, try again later!");
        mav.setViewName("generatorPage");
        return mav;  
    }
}