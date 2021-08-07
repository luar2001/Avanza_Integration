package com.example.avanza_integration.services;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class TempService {
    // TODO: 07/08/2021 move all okHttp to another class

    private static final OkHttpClient client = new OkHttpClient();

    /**
     * Returns A QRCode For Avanza's BankId Login.
     * @return String That represents a qrCode
     */
    public static String login(){
        String json = "";
        String temp = "temp";
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"),json);

        assert false;
        Request request = new Request.Builder()
                .url("https://www.avanza.se/_api/authentication/sessions/bankid") //Avanza session status for bank id login
                .post(body)
                .build();

        try(Response response = client.newCall(request).execute()){

            if(!response.isSuccessful()) throw new IOException("\nConnection ERROR: \n" + response); //if not successful

            temp = response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return "\nBankId Request ERROR: \n";
        }
        return temp;
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

            if(!response.isSuccessful()) throw new IOException("\nConnection ERROR: \n" + response); //if not successful

            System.out.println(Objects.requireNonNull(response.body()).string());

        } catch (Exception e) {
            System.out.println("\nAuthentication ERROR: \n");
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
