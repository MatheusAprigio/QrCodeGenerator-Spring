package com.qrcode.qrcode.controllers;

import java.io.IOException;

import com.google.zxing.WriterException;
import com.qrcode.qrcode.Model.NetworkConnection;
import com.qrcode.qrcode.services.QrCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class QrCodeController {

    @Autowired
    QrCodeService qrCodeService;

    @GetMapping
    public String getGeneratorPage() {
        return "generatorPage";
    }

    @PostMapping("generateTextUrlQrCode")
    public String postGenerateTextQrCode(@RequestParam(value = "qrCodeText", required = true) String textOrUrl,
            RedirectAttributes attributes) throws WriterException, IOException {

        String pngImage = qrCodeService.formatUrltextQrCode(textOrUrl);
        attributes.addFlashAttribute("qrcode", pngImage);
        return "redirect: ";
    }

    @PostMapping("generatePhoneQrCode")
    public String postGeneratePhoneQrCode(@RequestParam(value = "phoneNumber", required = true) String phoneNumber,
            RedirectAttributes attributes) throws WriterException, IOException {

        String pngImage = qrCodeService.formatPhoneNumberQrCode(phoneNumber);
        attributes.addFlashAttribute("qrcode", pngImage);
        return "redirect: ";
    }

    @PostMapping("generateNetworkQrCode")
    public String postGenerateNetworkQrCode(NetworkConnection network, RedirectAttributes attributes) throws WriterException, IOException {

        String pngImage = qrCodeService.formatNetworkQrCode(network.getName(), network.getPassword(), network.getSecurityType());
        attributes.addFlashAttribute("qrcode", pngImage);
        return "redirect: ";
    }

    @ExceptionHandler({ IOException.class, WriterException.class })
    public ModelAndView handleIOException(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "Something went wrong, try again later!");
        mav.setViewName("generatorPage");
        return mav;
    }
}