package com.example.avanza_integration.services;

import com.example.avanza_integration.Connection;
import org.springframework.stereotype.Service;

@Service
public class TempService {

    /**
     * Returns A QRCode For Avanza's BankId Login.
     * @return String That represents a qrCode
     */
    public static String login(){
        String json = "";
        String url = "https://www.avanza.se/_api/authentication/sessions/bankid";

        String temp = Connection.post(url,json);

        // TODO: 07/08/2021 Return bankid:///?autostarttoken=[TOKEN]
        return temp;
     }

    /**
     * Checks That you are LoggedIn to Avanza With BankId.
     * @return Boolean: True = LoggedIn | False = Not LoggedIn
     */
    public static boolean authenticate(){
        // TODO: 07/08/2021 is it supposed to check both the links? i could not get the other one to return anything..
        String url = "https://www.avanza.se/_cqbe/authentication/session";

        String temp = Connection.get(url);

        return temp.contains("\"loggedin\"=true");

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
