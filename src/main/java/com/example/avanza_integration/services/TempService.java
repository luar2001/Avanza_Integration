package com.example.avanza_integration.services;

import com.example.avanza_integration.Connection;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class TempService {

    private static final OkHttpClient client = new OkHttpClient();

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
        String url = "https://www.avanza.se/_cqbe/authentication/session";

        String temp = Connection.get(url);

        if(temp.equals("\n Get ERROR \n")){
            return false;
        }

        // TODO: 07/08/2021 make it check for "loggedin"=true

        return true;

    }

    /**
     * Returns an overview of User's Account.
     * @return String with JSON that represents the overview.
     */
    public static String overview() {
        Request request = new Request.Builder()
                .url("https://www.avanza.se/_api/account-overview/overview/categorizedAccounts ") //Avanza Overview
                .get()
                .build();

        try(Response response = client.newCall(request).execute()) {

            if(!response.isSuccessful()) {
                if (response.code() == 401) { //run authenticate before instead of this ?
                    return "Unauthorised";
                }
                throw new IOException("\nConnection ERROR: " + response); //if not successful
            }

            return Objects.requireNonNull(response.body()).string();
        } catch (Exception e) {
            e.printStackTrace();
            return "\nOverview ERROR\n";
        }
    }

}
