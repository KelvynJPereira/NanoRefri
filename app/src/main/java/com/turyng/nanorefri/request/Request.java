package com.turyng.nanorefri.request;

import android.os.StrictMode;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class Request {

    // Att
    final String url_webhook = "";

    // Metodo de requisicao
    public void request(){

        try {

            // Criacao de nova thread
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            // Instancia do objeto request
            OkHttpClient conn = new OkHttpClient();

            // Inicio da requisicao
            HttpUrl.Builder urlBuilder = HttpUrl.parse(url_webhook).newBuilder();
            urlBuilder.






        }catch (Exception e){

        }



    }







}
