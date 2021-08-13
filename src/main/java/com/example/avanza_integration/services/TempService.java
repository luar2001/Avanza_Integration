package com.example.avanza_integration.services;

import com.example.avanza_integration.Connection;
import okhttp3.Headers;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class TempService {

    /**
     * BankId Login Transaction id.
     */
    static String transactionId = null;

    /**
     * BankId Expiration time
     */
    static String expiration = null;


    /**
     * Returns A QRCode For Avanza's BankId Login.
     * @return String That represents a qrCode
     */
    @NotNull
    public static String login(){
        String url = "https://www.avanza.se/_api/authentication/sessions/bankid";

        Headers headers = new Headers.Builder()
                .add("Accept", "application/json, text/plain, */*")
                .add("Accept-Language", "en-US,en;q=0.5")
                .add("Referer", "https://www.avanza.se/start")
                .add("Content-Type", "application/json;charset=UTF-8")
                .add("X-SecurityToken", "-")
                .add("Origin", "https://www.avanza.se")
                .add("DNT", "1")
                .add("Connection", "keep-alive")
                .add("Cookie", "AZAFTGPERSISTANCE=02b03496e5-da57-48Bhz2X3JiCSUL2qdrP8YmGpCPjxAiolnuI_Yda-FclLZlx7H3ysKEwfHUVL46PAgCFJE; AZAABSESSION=node015tf27r4hl16sbaywujc5nm8h63350.node0; AZAPERSISTANCE=0253c8bd2e-1942-40BTM_7Kja6ZXaRHuQdSv2nJJn1zp8puLDyfo3htFzBiAEtBhcGjdJ1DUGpFw2q135t9I; AZABANKIDTRANSID=6e0e0348-cd14-4d04-b2d9-b30e7f783145")
                .add("Sec-Fetch-Dest", "empty")
                .add("Sec-Fetch-Mode", "cors")
                .add("Sec-Fetch-Site", "same-origin")
                .add("Sec-GPC", "1")
                .build();

        String temp = Connection.post(url,"{}",headers);
        String[] split = temp.split("\""); //splits the String at "

        transactionId = split[3];

        expiration = split[7];

        return "bankid:///?autostarttoken=" + split[11] + "&redirect=null";
     }

    /**
     * Checks That you are LoggedIn to Avanza With BankId.s
     * @return Boolean: True = LoggedIn | False = Not LoggedIn
     */
    public static boolean authenticate(){
        String url = "https://www.avanza.se/_cqbe/authentication/session";
        Headers headers = new Headers.Builder()
                .add("Accept", "application/json, text/plain, */*")
                .add("Accept-Language", "en-US,en;q=0.5")
                .add("Referer", "https://www.avanza.se/start")
                .add("Content-Type", "application/json;charset=UTF-8")
                .add("X-SecurityToken", "06ae9bee-344c-48b7-8164-a41094ff0ed1")
                .add("DNT", "1")
                .add("Connection", "keep-alive")
                .add("Cookie", "Humany__parameters={\"isLoggedIn\":[\"Ja\"]}; Humany__clientId=d17d3e19-0399-2ee3-4e66-c9f040d4ffcf; AZAFTGPERSISTANCE=02b03496e5-da57-48Bhz2X3JiCSUL2qdrP8YmGpCPjxAiolnuI_Yda-FclLZlx7H3ysKEwfHUVL46PAgCFJE; AZAABSESSION=node015tf27r4hl16sbaywujc5nm8h63350.node0; AZAPERSISTANCE=0253c8bd2e-1942-4088f2YvXIspRq-ni-c3MNBe09qrK-uPlSFu4Q-XJoz9uXxQSyqA4WnpsPmlVJD3-N7Uk; csid=813525c3-6df4-4462-b002-0aaf849dda70; AZAPERSISTANCE=0253c8bd2e-1942-40vL7vCRvmzcl_y2Xrzn1XSzDgBPWf9XAccbORCSj13euBJvjpQO9T9Edj20LizNBonrc")
                .add("Sec-Fetch-Dest", "empty")
                .add("Sec-Fetch-Mode", "cors")
                .add("Sec-Fetch-Site", "same-origin")
                .add("Sec-GPC", "1")
                .add("Pragma", "no-cache")
                .add("Cache-Control", "no-cache")
                .build();
        String temp = Connection.get(url, headers);
        System.out.println("TEST2: " + temp); //TEST
        return temp.contains("\"loggedin\"=true");
    }

    /**
     * collects info from when user loges in with bankid?
     */
    public static void collect(){
       String url = "https://www.avanza.se/_api/authentication/sessions/bankid/collect";
        Headers headers = new Headers.Builder()
                .add("Accept", "application/json, text/plain, */*")
                .add("Accept-Language", "en-US,en;q=0.5")
                .add("Referer", "https://www.avanza.se/start")
                .add("Content-Type", "application/json;charset=UTF-8")
                .add("X-SecurityToken", "-")
                .add("sentry-trace", "afe53d0e6dd844e3b94f65ee1499c50d-b14b7fd231f38a59-0")
                .add("DNT", "1")
                .add("Connection", "keep-alive")
                .add("Cookie", "AZAFTGPERSISTANCE=02b03496e5-da57-48Bhz2X3JiCSUL2qdrP8YmGpCPjxAiolnuI_Yda-FclLZlx7H3ysKEwfHUVL46PAgCFJE; AZAABSESSION=node015tf27r4hl16sbaywujc5nm8h63350.node0; AZAPERSISTANCE=0253c8bd2e-1942-40BTM_7Kja6ZXaRHuQdSv2nJJn1zp8puLDyfo3htFzBiAEtBhcGjdJ1DUGpFw2q135t9I")
                .add("Sec-Fetch-Dest", "empty")
                .add("Sec-Fetch-Mode", "cors")
                .add("Sec-Fetch-Site", "same-origin")
                .add("Sec-GPC", "1")
                .build();

       String temp = Connection.get(url,headers); //This being error 400 is standard until login??? (the same on the actual Avanza page at least for me)
       System.out.println("TEST1: " + temp); //TEST
    }

    /**
     * Returns an overview of User's Account.
     * @return String with JSON that represents the overview.
     */
    public static String overview() {
        String url = "https://www.avanza.se/_api/account-overview/overview/categorizedAccounts "; //Why does it need blank space at the end?
        String temp = "You are not loggedin!";
        Headers headers = new Headers.Builder()
                .add("Accept", "application/json, text/plain, */*")
                .add("Accept-Language", "en-US,en;q=0.5")
                .add("Referer", "https://www.avanza.se/start")
                .add("Content-Type", "application/json;charset=UTF-8")
                .add("X-SecurityToken", "-")
                .add("sentry-trace", "afe53d0e6dd844e3b94f65ee1499c50d-b14b7fd231f38a59-0")
                .add("DNT", "1")
                .add("Connection", "keep-alive")
                .add("Cookie", "AZAFTGPERSISTANCE=02b03496e5-da57-48Bhz2X3JiCSUL2qdrP8YmGpCPjxAiolnuI_Yda-FclLZlx7H3ysKEwfHUVL46PAgCFJE; AZAABSESSION=node015tf27r4hl16sbaywujc5nm8h63350.node0; AZAPERSISTANCE=0253c8bd2e-1942-40BTM_7Kja6ZXaRHuQdSv2nJJn1zp8puLDyfo3htFzBiAEtBhcGjdJ1DUGpFw2q135t9I; AZABANKIDTRANSID=3dca27ac-c814-43bf-918f-c72d388e53b8; AZAPERSISTANCE=0253c8bd2e-1942-40rHS41_hTldPJQVocDYwtk-0rXkHsfcdXGrBTr_Jpt3R4NTUeY7A-bxNQ20d0IDPakA0")
                .add("Sec-Fetch-Dest", "empty")
                .add("Sec-Fetch-Mode", "cors")
                .add("Sec-Fetch-Site", "same-origin")
                .add("Sec-GPC", "1")
                .build();

        if (authenticate()){ //checks if the user is loggedin. otherwise, you get HTTP code 401.
            temp = Connection.get(url,headers);
        }

        return temp;
    }

}
