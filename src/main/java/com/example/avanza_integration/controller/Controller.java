package com.example.avanza_integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    /**
     * BankId Login Post Mapper
     */
    @PostMapping(path = "/login/bankid")
    public void login(){

    }

    /**
     * BankId Status Get Mapper
     */
    @GetMapping(path = "/login/bankid/status")
    public void authenticate(){

    }

    /**
     * Account Overview Get Mapper
     */
    @GetMapping(path = "/account/overview")
    public void overview(){

    }

}