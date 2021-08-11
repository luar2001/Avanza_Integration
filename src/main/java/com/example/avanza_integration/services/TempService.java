package com.example.avanza_integration.services;

import com.example.avanza_integration.Connection;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class TempService {

    /**
     * Returns A QRCode For Avanza's BankId Login.
     * @return String That represents a qrCode
     */
    @NotNull
    public static String login(){
        String url = "https://www.avanza.se/_api/authentication/sessions/bankid";

        // TODO: 07/08/2021 Return bankid:///?autostarttoken=[TARGET]&redirect=null
        return Connection.post(url,"");
     }

    /**
     * Checks That you are LoggedIn to Avanza With BankId.
     * @return Boolean: True = LoggedIn | False = Not LoggedIn
     */
    public static boolean authenticate(){
        String url = "https://www.avanza.se/_cqbe/authentication/session";
        String temp = Connection.get(url);
        System.out.println("TEST2: " + temp); //TEST
        return temp.contains("\"loggedin\"=true");
    }

    /**
     * collects info from when user loges in with bankid?
     */
    public static void collect(){
       String url = "https://www.avanza.se/_api/authentication/sessions/bankid/collect";
       String temp = Connection.get(url); //This being error 400 is standard until login??? (the same on the actual Avanza page at least for me)
       System.out.println("TEST1: " + temp); //TEST
    }

    /**
     * Returns an overview of User's Account.
     * @return String with JSON that represents the overview.
     */
    public static String overview() {
        String url = "https://www.avanza.se/_api/account-overview/overview/categorizedAccounts "; //Why does it need blank space at the end?
        String temp = "You are not loggedin!";

        if (authenticate()){ //checks if the user is loggedin. otherwise, you get HTTP code 401.
            temp = Connection.get(url);
        }

        return temp;
    }

}
