package com.nanorefri.validacaopagamento;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.nanorefri.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ValidacaoPagamento extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_validacao_pagamento);
		
		/*
		 *  Requisicao de leitura de novas transacoes nano em blocos confirmados
		 */
		
		try {
			
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		
			OkHttpClient client = new OkHttpClient();
	
		    // Requisicao
			String url = "https://snapy.io/api/v1/webhooks";
	
			Request request = new Request.Builder()
		    .url(url)
		    .header("x-api-key", "pub_5cf8389da5376d365a0a325d-b840790d-50b5-49b0-a8dc-7f7ce9863a9f")
		    .get()
		    .build();
			
			client.newCall(request).enqueue(new Callback() {
				
				@Override
				public void onResponse(Call call, final Response response) throws IOException {
					// TODO Auto-generated method stub
					Log.i("TESTE", response.body().string());
					
					if (response.isSuccessful()) {
						
						ValidacaoPagamento.this.runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								 try {
                                     try {

                                         String data = response.body().string();

                                         JSONArray jsonArray = null;
                                         try {
                                             jsonArray = new JSONArray(data);
                                         } catch (JSONException e) {
                                             e.printStackTrace();
                                         }

                                         JSONObject jsonObject;

                                         // Leitura de dados
                                         jsonObject = jsonArray.getJSONObject(0);
                                         String count = (jsonObject.getString("count"));
                                         JSONArray history = (jsonObject.getJSONArray("history"));
                                         
                                     } catch (JSONException e) {
                                     
                                     }
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }
							}
						});
					}
					
				}
				
				@Override
				public void onFailure(Call call, IOException e) {
					// TODO Auto-generated method stub
					e.printStackTrace();
					
				}
			
			});
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}
		
				
				
		
				
	
