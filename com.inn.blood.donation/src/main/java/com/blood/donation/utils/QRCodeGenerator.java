package com.blood.donation.utils;

import com.blood.donation.model.BloodGroup;
import com.blood.donation.model.Donor;
import com.blood.donation.model.Gender;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {
    public static void generateQRCode(Donor donor) throws IOException, WriterException {
//        String qrCodePath = "F:\\blood donor management system\\QRcode\\";
//        String qrCodeName = qrCodePath+ donor.getFirstName() + donor.getDonor_ID()+ "-QRCODE.png";
//        var qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix = qrCodeWriter.encode("Donor_ID:" + donor.getDonor_ID()+ "\n"+
//                "first name: "+donor.getFirstName()+ "\n"+
//                "Last name: "+donor.getLastName() + "\n"+
//                "full name: "+ donor.getLastName()+ "\n"+
//                "NIC number: "+ donor.getNIC()+ "\n" +
//                "Date of Birth: " + donor.getDOB() + "\n"+
//                "gender: "+ donor.getGender() + "\n" +
//                "Address: " + donor.getAddress() + "\n"+
//                "city: " + donor.getCity() + "\n"+
//                "Postal code: "+ donor.getPostalCode() + "\n"+
//                "email: "+ donor.getEmail() + "\n"+
//                "contact number(mobile): "+ donor.getContactMobile() + "\n"+
//                "contact number(home): " + donor.getContactHome() + "\n"+
//                "Blood Group: " + donor.getBloodGroup() + "\n"+
//                "Height: "+ donor.getHeight() + "\n"+
//                "Weight: " + donor.getWeight() + "\n" +
//                "Special health conditions: " + donor.getSpecialConditions() + "\n", BarcodeFormat.QR_CODE,400, 400 );
//
//                Path path = FileSystems.getDefault().getPath(qrCodeName);
//                MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
