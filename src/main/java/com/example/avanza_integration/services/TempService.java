package com.example.avanza_integration.services;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.Arrays;

@Service
public class TempService {

    /**
     * Returns A QRCode For Avanza's BankId Login.
     * @return String That represents a qrCode
     */
    public static String login(){

        return "TEMP: Login";
    }

    /**
     * Checks That you are LoggedIn to Avanza With BankId.
     * @return Boolean: True = LoggedIn | False = Not LoggedIn
     */
    public static boolean authenticate(){
        String temp = null;
        try {
            temp = "TEMP: authentication";
        } catch (ServerErrorException s) {
            System.out.println("ERROR: Server");
            s.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Other");
            e.printStackTrace();
            return false;
        }

        return false;
    }

    /**
     * Returns an overview of User's Account.
     * @return String with JSON that represents the overview.
     */
    public static String overview() {
        try {
            return "TEMP: Overview";
        } catch (ServerErrorException s) {
            s.printStackTrace();
            return "ERROR: Server " + Arrays.toString(s.getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: Other " + Arrays.toString(e.getStackTrace());
        }
    }

}
