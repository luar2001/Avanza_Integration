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
    private static String transactionId = " ";

    /**
     * Customer identification code
     */
    private static String customerId = " ";

    /**
     * Session authentication code
     */
    private static String authenticationSession = " ";

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
        String url = "https://www.avanza.se/_api/account-overview/overview/categorizedAccounts";
        String temp = "You are not loggedin!";
        Headers headers = new Headers.Builder()
                .add("Connection", "keep-alive")
                .add("sec-ch-ua", "\"Chromium\";v=\"92\", \" Not A;Brand\";v=\"99\", \"Google Chrome\";v=\"92\"")
                .add("Accept", "application/json, text/plain, */*")
                .add("X-SecurityToken", "36e6de0f-49a2-4335-87e3-ab5df2c74421")
                .add("sec-ch-ua-mobile", "?0")
                .add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36")
                .add("Content-Type", "application/json;charset=UTF-8")
                .add("Sec-Fetch-Site", "same-origin")
                .add("Sec-Fetch-Mode", "cors")
                .add("Sec-Fetch-Dest", "empty")
                .add("Referer", "https://www.avanza.se/min-ekonomi/oversikt.html")
                .add("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.8")
                .add("Cookie", "AZAPERSISTANCE=0253c8bd2e-1942-40-99f2Gks-d8CR1raguhnrtOeCOLkKQ8QPxmtcuPQ6QVL13skB2l_nrhePXgutS0sCCk; AZACOOKIECONSENT_UX=YES; AZACOOKIECONSENT_ANALYSIS=YES; AZACOOKIECONSENT_MARKETING=YES; _gcl_au=1.1.402549023.1629190458; _ga=GA1.2.1657350706.1629190458; _gid=GA1.2.889662377.1629190458; AZAHLI=bankId; Humany__parameters={\"isLoggedIn\":[\"Ja\"]}; Humany__clientId=3555c263-388a-664d-5ef2-8891cffe4a37; _gat_UA-1234489-15=1; csid="+authenticationSession+"; AZAMENUTAB=/min-ekonomi/oversikt.html||")
                .build();
            if(authenticate()){
                temp = Connection.get(url,headers);
            }
        return temp;
    }

}
