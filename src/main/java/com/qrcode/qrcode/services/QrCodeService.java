package com.qrcode.qrcode.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException; 
import java.util.Base64;
import java.util.regex.Pattern;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qrcode.qrcode.models.Contact;
import com.qrcode.qrcode.models.NetworkConnection;

import org.springframework.stereotype.Service;

@Service
public class QrCodeService {

    
    public String formatUrltextQrCode(String content) throws IOException, WriterException {
        
        if (content.trim().isEmpty()) { content = "Empty QrCode";};
        
        return generateQrCodeImage(content);
    }

    public String formatPackage(String appPackage) throws IOException, WriterException {
        
        appPackage = "market://details?id=" + appPackage;

        return generateQrCodeImage(appPackage);
    }

    public String formatPhoneNumber(String phoneNumber) throws IOException, WriterException {
        
        String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        phoneNumber = "tel: " + phoneNumber.replaceAll(pattern, "");

        return generateQrCodeImage(phoneNumber);
    }
    
    public String formatNetwork(NetworkConnection network) throws IOException, WriterException {
        
        String wifiConnection = "WIFI:T:" + network.getSecurityType() + ";S:" + network.getName()  +";P:"+ network.getPassword() + ";;";    

        return generateQrCodeImage(wifiConnection);
    }
    
    public String formatContact(Contact contact) throws IOException, WriterException {

        String contactText = "MECARD:N:"+ contact.getName() 
        + ";ADR:"+ contact.getAddress()
        + ";TEL:" + contact.getAddress() 
        + ";EMAIL:" + contact.getEmail() +";;";

        return generateQrCodeImage(contactText);
    }
    
    public String formatContact(String appPackage) throws IOException, WriterException {
        
        appPackage = "market://details?id=" + appPackage;

        return generateQrCodeImage(appPackage);
    }

    public String generateQrCodeImage(String content) throws IOException, WriterException {
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300);  
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        String pngData = Base64.getEncoder().encodeToString(pngOutputStream.toByteArray()); 

        return pngData;
    }
}