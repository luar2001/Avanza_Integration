package com.example.avanza_integration;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

class Connection {

    private final OkHttpClient client = new OkHttpClient();

    /**
     * HTTP GET Request Using okHttp
     * @param url GET Http Request url
     * @return String with the response from the url.
     */
    protected String get(String url){
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try(Response response = client.newCall(request).execute()) {

            if(!response.isSuccessful()) throw new IOException("\nConnection ERROR: " + response);

            return Objects.requireNonNull(response.body()).string();

        } catch (Exception e) {
            e.printStackTrace();
            return "\n Get ERROR \n";
        }
    }

    /**
     * HTTP POST Request using okHttp
     * @param url POST Http Request url
     * @param json sends as a response with the request
     * @return SString with the response from the url.
     */
    protected String post(String url, String json){
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"),json); // TODO: 07/08/2021 use something that's not deprecated...

        assert false;
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try(Response response = client.newCall(request).execute()){

            if(!response.isSuccessful()) throw new IOException("\nConnection ERROR: \n" + response);

            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return "\nBankId Request ERROR: \n";
        }

    }

}