package com.example.avanza_integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping(path = "/login/bankid")
    public void login(){

    }

    @GetMapping(path = "/login/bankid/status")
    public void authenticate(){

    }

    @GetMapping(path = "/account/overview")
    public void overview(){

    }

}
