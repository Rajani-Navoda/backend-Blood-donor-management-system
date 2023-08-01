package com.blood.donation.controller;

import com.blood.donation.constants.Constants;
import com.blood.donation.dto.DonorRegisterRequestDTO;
import com.blood.donation.dto.UpdateDonorRequestDTO;
import com.blood.donation.model.Donor;
import com.blood.donation.model.User;
import com.blood.donation.service.DonorService;
import com.blood.donation.service.UserService;
import com.blood.donation.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/donor")
public class DonorController {

    @Autowired
    private DonorService donorService;
    @Autowired
    private UserService userService;

//    @GetMapping("/getAllDonors")
//    public ResponseEntity<List<Donor>> getAllDonors(){
//        try{
//            List<Donor> donorList = new ArrayList<>();
//            donorRepo.findAll().forEach(donorList::add);
//
//            if(donorList.isEmpty()){
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(donorList, HttpStatus.OK);
//
//        }catch (Exception ex){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @GetMapping("/getDonorByUserName/{userName}")
    @PreAuthorize("hasRole('ROLE_donor')")
    public ResponseEntity<Donor> getDonorByUserName(@PathVariable String userName){
        try {
            User user = userService.getUserByName(userName);
            Donor donor = donorService.getDonorByUserId(user.getUserId());
            return new ResponseEntity<>(donor, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getPathToQrCode/{userName}")
    @PreAuthorize("hasRole('ROLE_donor')")
    public ResponseEntity<String> getPathToQrCode(@PathVariable String userName){
        try {
            User user = userService.getUserByName(userName);
            String path = donorService.getPathToDonorQrCode(user.getUserId());
            return new ResponseEntity<>(path, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addDonor/{userName}")
    @PreAuthorize("hasRole('ROLE_donor')")
    public ResponseEntity<String> addDonor(@PathVariable("userName") String userName, @RequestBody DonorRegisterRequestDTO donorRegisterRequestDTO) {
        try {
            User user = userService.getUserByName(userName);
            userService.updateIsFirstLoginToFalse(user);
            donorService.addDonor(user, donorRegisterRequestDTO);
            return Utils.getResponseEntity("Donor added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateDonor/{userName}")
    @PreAuthorize("hasRole('ROLE_donor')")
    public  ResponseEntity<String> updateDonor(@PathVariable("userName") String userName, @RequestBody UpdateDonorRequestDTO updateDonorRequestDTO) {
        try {
            User user = userService.getUserByName(userName);
            User updatedUser = userService.updateUserDetails(user, updateDonorRequestDTO.getFirstName(), updateDonorRequestDTO.getLastName(), updateDonorRequestDTO.getEmail());
            donorService.updateDonor(updatedUser, updateDonorRequestDTO);
            return Utils.getResponseEntity("Donor updated successfully", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @DeleteMapping("/deleteDonorById/{id}")
//     public ResponseEntity<HttpStatus> deleteDonorById(@PathVariable Integer id){
//        donorRepo.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//     }

}

