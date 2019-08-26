package com.turyng.appnanorefri;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Instancia da classe de requisicao
        OkHttpClient client = new OkHttpClient();

        // Requisicao
       String url = "https://snapy.io/api/v1/send";

       RequestBody formBody = new FormBody.Builder()
                .add("to", "xrb_33kuw5z8jdsx8bssnnzom1oj1hdejuwsb6adbj4b8u9tkuat4a7a1bxg4efs")
                .add("from", "xrb_3tg3c1pjkzy1s7bgep4rjw7c5gb7rwi15mt9my6ioy8ow4sj3jnsh4t9yykt")
                .add("amount", "1")
                .add("password", "123456789teste")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .header("x-api-key", "pub_5cf8389da5376d365a0a325d-b840790d-50b5-49b0-a8dc-7f7ce9863a9f")
                .post(formBody)
                .build();

       client.newCall(request).enqueue(new Callback() {
    	   
    	@Override
   		public void onFailure(Call call, IOException e) {
   			// TODO Auto-generated method stub
    		 e.printStackTrace();
   		}
		
		@Override
		public void onResponse(Call call, Response response) throws IOException {
			// TODO Auto-generated method stub
			if(response.isSuccessful()){
                final String myResponse = response.body().string();
                Log.i("TESTE", myResponse);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("CARTEIRA", myResponse);
                    }
                });
            }
			
		}
		
       });
	}
}
