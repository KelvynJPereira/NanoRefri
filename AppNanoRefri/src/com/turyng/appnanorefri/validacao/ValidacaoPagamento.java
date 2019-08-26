package com.turyng.appnanorefri.validacao;

import java.io.IOException;

import android.os.StrictMode;
import android.util.Log;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ValidacaoPagamento {
	
	// Url de verificao transacional da API
	final String URL_API = "https://snapy.io/api/v1/webhooks";
	
	// Metodo de requisicao
	public void validar_pagamento() {
		
		try {
			
			// Cria nova thread 
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            
            // Instancia de requesicao
            OkHttpClient client = new OkHttpClient();
            
            
            // Debug de excptions
            Log.i("Resposta ->", "PASSOU");
            
            /*
             * Exception ao tentar chamar classe OkhttpClient
             * 	Caused by: java.lang.NoClassDefFoundError: Class not found using the boot class loader;
             *  no stack trace available
             */
            
            
            // Passagem de URL de conexao
            HttpUrl.Builder urlBuilder = HttpUrl.parse(URL_API).newBuilder();
            
            
            // Construcao da url conectada
            String url = urlBuilder.toString();
            
            // Criacao da requisicao
            Request request = new Request.Builder()
            		.url(url)
            		.build();
            
            // Criacao do callback
            client.newCall(request).enqueue(new Callback() {
				
				@Override
				public void onResponse(Call arg0, final Response arg1) throws IOException {
					// TODO Auto-generated method stub
					
						new Runnable() {
							public void run() {
								try {
									// Recebe resposta
									String response = arg1.body().string();
									
									// Mostra resposta de requisicao no log
									Log.i("Resposta ->", response);
									
								} catch (Exception e) {
									// TODO: handle exception
								}
								
							}
						};
				}
			
				@Override
				public void onFailure(Call arg0, IOException arg1) {
					// TODO Auto-generated method stub
					
				}
			});
          
		} catch (Exception e) {
			
        }
	}
}
