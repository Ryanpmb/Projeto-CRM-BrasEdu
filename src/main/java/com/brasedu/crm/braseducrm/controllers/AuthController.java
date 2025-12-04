package com.brasedu.crm.braseducrm.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brasedu.crm.braseducrm.dto.request.LoginDto;
import com.brasedu.crm.braseducrm.dto.response.TokenDto;
import com.brasedu.crm.braseducrm.entities.SalesmanEntity;
import com.brasedu.crm.braseducrm.services.JwtService;
import com.brasedu.crm.braseducrm.services.SalesmanService;
import com.brasedu.crm.braseducrm.userdetails.SalesmanUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final SalesmanService salesmanService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {

        SalesmanEntity salesman = salesmanService.authenticate(
                loginDto.getEmail(),
                loginDto.getPassword());

        SalesmanUserDetails userDetails = new SalesmanUserDetails(salesman);

        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new TokenDto(token));
    }
}
