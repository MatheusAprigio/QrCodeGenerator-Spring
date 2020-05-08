package com.qrcode.qrcode.controllers;

import java.io.IOException;

import com.google.zxing.WriterException;
import com.qrcode.qrcode.models.Contact;
import com.qrcode.qrcode.models.NetworkConnection;
import com.qrcode.qrcode.services.QrCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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

        String pngImage = qrCodeService.formatPhoneNumber(phoneNumber);
        attributes.addFlashAttribute("qrcode", pngImage);
        return "redirect: ";
    }

    @PostMapping("generatePackageQrCode")
    public String postGeneratePackageQrCode(@RequestParam(value = "package", required = true) String packageName,
            RedirectAttributes attributes) throws WriterException, IOException {

        String pngImage = qrCodeService.formatPackage(packageName);
        attributes.addFlashAttribute("qrcode", pngImage);
        return "redirect: ";
    }

    @PostMapping("generateNetworkQrCode")
    public String postGenerateNetworkQrCode(NetworkConnection network, RedirectAttributes attributes) throws WriterException, IOException {

        String pngImage = qrCodeService.formatNetwork(network);
        attributes.addFlashAttribute("qrcode", pngImage);
        return "redirect: ";
    }


    @PostMapping("generateContactQrCode")
    public String postGenerateContactQrCode(Contact contact, RedirectAttributes attributes) throws WriterException, IOException {

        String pngImage = qrCodeService.formatContact(contact);
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