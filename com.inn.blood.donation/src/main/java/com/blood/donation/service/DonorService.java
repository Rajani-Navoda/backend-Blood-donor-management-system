package com.blood.donation.service;

import com.blood.donation.dto.DonorRegisterRequestDTO;
import com.blood.donation.dto.UpdateDonorRequestDTO;
import com.blood.donation.model.Donor;
import com.blood.donation.model.User;
import com.blood.donation.repo.DonorRepo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class DonorService {

    @Autowired
    DonorRepo donorRepo;
    public void addDonor(User user, DonorRegisterRequestDTO donorRegisterRequestDTO) {
        Donor donor = new Donor();
        donor.setUser(user);
        donor.setFullName(donorRegisterRequestDTO.getFullName());
        donor.setNic(donorRegisterRequestDTO.getNic());
        donor.setDob(donorRegisterRequestDTO.getDob());
        donor.setGender(donorRegisterRequestDTO.getGender());
        donor.setAddress(donorRegisterRequestDTO.getAddress());
        donor.setCity(donorRegisterRequestDTO.getCity());
        donor.setPostalCode(donorRegisterRequestDTO.getPostalCode());
        donor.setContactHome(donorRegisterRequestDTO.getContactHome());
        donor.setContactMobile(donorRegisterRequestDTO.getContactMobile());
        donor.setBloodGroup(donorRegisterRequestDTO.getBloodGroup());
        donor.setWeight(donorRegisterRequestDTO.getWeight());
        donor.setHeight(donorRegisterRequestDTO.getHeight());
        donor.setBmi(donorRegisterRequestDTO.getBmi());
        donor.setSpecialConditions(donorRegisterRequestDTO.getSpecialConditions());
        donor.setImageUrl(donorRegisterRequestDTO.getImageUrl());

        String registrationNumber = generateRegistrationNumber(donorRegisterRequestDTO.getBloodGroup(), donorRegisterRequestDTO.getPostalCode(), donor.getUser().getUserId());
        donor.setRegistrationNumber(registrationNumber);

        try {
            generateQRCode(donor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        donorRepo.save(donor);
    }

    public void updateDonor(User user, UpdateDonorRequestDTO updateDonorRequestDTO) {

        Donor donor = donorRepo.findByUserUserId(user.getUserId());

        donor.setUser(user);
        donor.setFullName(updateDonorRequestDTO.getFullName());
        donor.setGender(updateDonorRequestDTO.getGender());
        donor.setAddress(updateDonorRequestDTO.getAddress());
        donor.setCity(updateDonorRequestDTO.getCity());
        donor.setPostalCode(updateDonorRequestDTO.getPostalCode());
        donor.setContactHome(updateDonorRequestDTO.getContactHome());
        donor.setContactMobile(updateDonorRequestDTO.getContactMobile());
        donor.setWeight(updateDonorRequestDTO.getWeight());
        donor.setHeight(updateDonorRequestDTO.getHeight());
        donor.setBmi(updateDonorRequestDTO.getBmi());
        donor.setSpecialConditions((updateDonorRequestDTO.getSpecialConditions()));
        donor.setNic(donor.getNic());
        donor.setDob(donor.getDob());
        donor.setBloodGroup(donor.getBloodGroup());

        donorRepo.save(donor);
    }

    public Donor getDonorByUserId(Integer userID) {
        return donorRepo.findByUserUserId(userID);
    }

    public String getPathToDonorQrCode(Integer userId) {
        Donor donor = donorRepo.findByUserUserId(userId);
        return donor.getUser().getUserName()+donor.getUser().getUserId()+"-QRCODE.png";
    }

    private String generateRegistrationNumber(String bloodGroup, String postalCode, Integer userId) {
        return String.format("%s%s-%d", bloodGroup, postalCode, userId);
    }

    private void generateQRCode(Donor donor) throws IOException, WriterException {
        String qrCodePath = "D:\\University\\Projects\\blood-donor-management-system-front-end\\src\\assets\\images\\QR_Codes\\";
        String qrCodeName = qrCodePath + donor.getUser().getUserName() + donor.getUser().getUserId() + "-QRCODE.png";
        var qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(

                "Donor_ID: " + donor.getDonorId() + "\n" +
                        "full name: " + donor.getFullName() + "\n" +
                        "NIC number: " + donor.getNic() + "\n" +
                        "Date of Birth: " + donor.getDob() + "\n" +
                        "gender: " + donor.getGender() + "\n" +
                        "Address: " + donor.getAddress() + "\n" +
                        "city: " + donor.getCity() + "\n" +
                        "Postal code: " + donor.getPostalCode() + "\n" +
                        "contact number(mobile): " + donor.getContactMobile() + "\n" +
                        "contact number(home): " + donor.getContactHome() + "\n" +
                        "Blood Group: " + donor.getBloodGroup() + "\n" +
                        "Height: " + donor.getHeight() + "\n" +
                        "Weight: " + donor.getWeight() + "\n" +
                        "Special health conditions: " + donor.getSpecialConditions() + "\n",
                BarcodeFormat.QR_CODE, 400, 400);

        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }
}
