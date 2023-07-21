package com.blood.donation.controller;

import com.blood.donation.model.Organizer;
import com.blood.donation.repo.OrganizerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrganizerController {
    @Autowired
    private OrganizerRepo organizerRepo;

    @GetMapping("/getAllOrganizers")
    public ResponseEntity<List<Organizer>> getAllOrganizers(){
    try{
        List<Organizer> organizerList = new ArrayList<>();
        organizerRepo.findAll().forEach(organizerList::add);

        if(organizerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(organizerList,HttpStatus.OK);

    } catch (Exception ex){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOrganizerById/{Id}")
    public ResponseEntity<Organizer> getOrganizerById(@PathVariable Long Id){
    Optional<Organizer> organizerData =organizerRepo.findById(Id);

    if(organizerData.isPresent()){
        return new ResponseEntity<>(organizerData.get(),HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/addOrganizer")
    public ResponseEntity<Organizer> AddOrganizer(@RequestBody Organizer organizer){
        Organizer organizerObj = organizerRepo.save(organizer);

        return new ResponseEntity<>(organizerObj,HttpStatus.OK);

    }
    @PostMapping("/updateOrganizerById")
    public ResponseEntity<Organizer> updateOrganizerById(@PathVariable Long Id, @RequestBody Organizer newOrganizerData){
        Optional<Organizer> oldOrganizerData=organizerRepo.findById(Id);

        if(oldOrganizerData.isPresent()){
            Organizer updatedOrganizerData = oldOrganizerData.get();
            updatedOrganizerData.setOrg_name(newOrganizerData.getOrg_name());
            updatedOrganizerData.setOrg_email(newOrganizerData.getOrg_email());
            updatedOrganizerData.setOrg_contact(newOrganizerData.getOrg_contact());

            Organizer organizerObj = organizerRepo.save(updatedOrganizerData);
            return new ResponseEntity<>(organizerObj,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/DeleteOrganizerById")
    public ResponseEntity<HttpStatus> deleteOrganizerById(@PathVariable Long Id){
        organizerRepo.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
