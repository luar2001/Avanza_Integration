package com.example.avanza_integration.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.net.MalformedURLException;
import java.net.URL;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Service
public class TempService {

    private static final OkHttpClient client = new OkHttpClient();

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

        Request request = new Request.Builder()
                .url("https://www.avanza.se/_cqbe/authentication/session") //Avanza session status for bank id login
                .get()
                .build();

        try(Response response = client.newCall(request).execute()){

            if(!response.isSuccessful()) throw new IOException("Connection ERROR: " + response); //if not successful

            System.out.println(Objects.requireNonNull(response.body()).string());

        } catch (Exception e) {
            System.out.println("Authentication ERROR: ");
            e.printStackTrace();
            return false;
        }
        // TODO: 07/08/2021 Find in String "loggedin":true or "loggedin":false  | return accordingly
        return true;
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
