package com.blood.donation.controller;

import com.blood.donation.model.Donor;
import com.blood.donation.repo.DonorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DonorController {
    @Autowired
    private DonorRepo donorRepo;

    @GetMapping("/getAllDonors")
    public ResponseEntity<List<Donor>> getAllDonors(){
        try{
            List<Donor> donorList = new ArrayList<>();
            donorRepo.findAll().forEach(donorList::add);

            if(donorList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(donorList, HttpStatus.OK);

        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getDonorById/{id}")
    public ResponseEntity<Donor> getDonorByID(@PathVariable Long id){
       Optional<Donor> donorData = donorRepo.findById(id);

       if(donorData.isPresent()){
           return new ResponseEntity<>( donorData.get(), HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addDonor")
    public ResponseEntity<Donor> addDonor(@RequestBody Donor donor){
        Donor donorObj = donorRepo.save(donor);

        return new ResponseEntity<>(donorObj, HttpStatus.OK);
    }
    @PostMapping("/updateDonorById/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Long id, @RequestBody Donor newDonorData){
        Optional<Donor> oldDonorData =donorRepo.findById(id);
        if(oldDonorData.isPresent()){

        Donor updateDonorData  = oldDonorData.get();
        updateDonorData.setFirstName(newDonorData.getFirstName());
        updateDonorData.setLastName(newDonorData.getLastName());
        updateDonorData.setFullName(newDonorData.getFullName());
        updateDonorData.setNIC(newDonorData.getNIC());
        updateDonorData.setDOB(newDonorData.getDOB());
        updateDonorData.setGender(newDonorData.getGender());
        updateDonorData.setAddress(newDonorData.getAddress());
        updateDonorData.setCity(newDonorData.getCity());
        updateDonorData.setPostalCode(newDonorData.getPostalCode());
        updateDonorData.setEmail(newDonorData.getEmail());
        updateDonorData.setContactMobile(newDonorData.getContactMobile());
        updateDonorData.setContactHome(newDonorData.getContactHome());
        updateDonorData.setHeight(newDonorData.getHeight());
        updateDonorData.setWeight(newDonorData.getWeight());
        updateDonorData.setBMI(newDonorData.getBMI());
        updateDonorData.setSpecialConditions(newDonorData.getSpecialConditions());

            Donor donorObj = donorRepo.save(updateDonorData);
            return new ResponseEntity<>(donorObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteDonorById/{id}")
     public ResponseEntity<HttpStatus> deleteDonorById(@PathVariable Long id){
        donorRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
     }

}

