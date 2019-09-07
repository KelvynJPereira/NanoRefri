package com.example.new_app;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Channel extends Activity {
	
	Button buttonA, buttonB, buttonCanal;
	TextView textViewMsg;
	
	String mobile_address;	
	
	private final static UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
	
	BluetoothAdapter adapter = null;
	BluetoothDevice device = null;
	BluetoothSocket socket = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channel);
		
		buttonA = (Button) findViewById(R.id.buttonA);
		buttonB = (Button) findViewById(R.id.buttonB);
		buttonCanal = (Button) findViewById(R.id.buttonCanal);
		textViewMsg = (TextView) findViewById(R.id.textViewMsg);
		
		Intent intent = getIntent();
		mobile_address = intent.getStringExtra("ADDRESS");
		
		buttonA.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				// TODO Auto-generated method stub
				if(me.getAction() == MotionEvent.ACTION_DOWN) {
					saidaDados("A");
				}
				return true;
			}
		});
		
	}
	
	public void canal(View View) {
		
		adapter = BluetoothAdapter.getDefaultAdapter();
		
		device = adapter.getRemoteDevice(mobile_address);
		
		int count = 0;
		
		do {
			try {
				
				socket = device.createRfcommSocketToServiceRecord(uuid);
				socket.connect();
			}catch (IOException e) {
				// TODO: handle exception
				Toast.makeText(getApplicationContext(), "Canal criado? "+e.getMessage(), Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
			count++;
	    }while(!socket.isConnected() && count < 5);
		
		
		if(socket.isConnected()) {
			textViewMsg.setText("Canal estabelecido com "+mobile_address);
		}else {
			textViewMsg.setText("Erro ao estabelecer canal.");
		}
		
		Toast.makeText(getApplicationContext(), "Canal criado? "+socket.isConnected(), Toast.LENGTH_SHORT).show();
		
	}
	
	public void buttonB(View view) {
		
		saidaDados("B");
		
	}
	
	// Função para enviar os dados.
	public void saidaDados (String s) {
		// Criando o a saída de dados com a classe outPutStream, que recebe o canal criado com a função getOutputStream.
		// Posteriormente escreve essa função, no caso envia os dados com a função write e passa o parâmetro que no caso o que for enviar.
		try {
			OutputStream output = socket.getOutputStream();
			output.write(s.getBytes());
			Toast.makeText(getApplicationContext(), "Dado enviado: "+output, Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "Mensagem: "+e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		disconnect();
		finish();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		disconnect();
	}
	
	private void disconnect() {
	    if (socket != null) {
		    try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

}
