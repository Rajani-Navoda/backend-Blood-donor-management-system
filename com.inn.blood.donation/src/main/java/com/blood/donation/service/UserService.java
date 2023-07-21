package com.blood.donation.service;

import com.blood.donation.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    public ResponseEntity<String> signUp(Map<String, String> requestMap);
}
