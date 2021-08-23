package com.example.avanza_integration.controller;

import com.example.avanza_integration.services.TempService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    /**
     * BankId Login Post Mapper
     * @return String That represents a qrCode
     */
    @PostMapping(path = "/login/bankid")
    public String login(){
       return TempService.login();
    }

    /**
     * BankId Status Get Mapper
     * @return Boolean: True = LoggedIn | False = Not LoggedIn
     */
    @GetMapping(path = "/login/bankid/status")
    public boolean authenticate(){
        return TempService.authenticate();
    }

    /**
     * Account Overview Get Mapper
     * @return String with JSON that represents the overview.
     */
    @GetMapping(path = "/account/overview")
    public String overview(){
        return TempService.overview();
    }

}
