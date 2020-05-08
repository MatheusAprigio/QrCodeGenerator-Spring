package com.qrcode.qrcode.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException; 
import java.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.springframework.stereotype.Service;

@Service
public class QrCodeService {

    
    public String formatUrltextQrCode(String content) throws IOException, WriterException {
        
        if (content.trim().isEmpty()) { content = "Empty QrCode";};
        
        return generateQrCodeImage(content);
    }

    public String formatPhoneNumberQrCode(String phoneNumber) throws IOException, WriterException {
        
        if (phoneNumber.trim().isEmpty()) { phoneNumber = "Empty QrCode";};
        phoneNumber = "tel: " + phoneNumber;

        return generateQrCodeImage(phoneNumber);
    }
    
    public String formatNetworkQrCode(String networkName, String password, String securityType) throws IOException, WriterException {
        
        String wifiConnection = "WIFI:T:" + securityType + ";S:" + networkName  +";P:"+ password + ";;";    

        return generateQrCodeImage(wifiConnection);
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