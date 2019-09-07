package com.example.new_app;

import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button buttonConectar;
	TextView textViewAparelho;
	
	// Adaptador.
	BluetoothAdapter BT_adapter = null;
	
	// Usado para pegar os aparelhos pareados.
	Set<BluetoothDevice> pairedDevices;
	
	String address = null;
	ArrayList<String> arrayList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonConectar = (Button) findViewById(R.id.buttonConectar);
		textViewAparelho = (TextView) findViewById(R.id.textViewAparelho);
		
		BT_adapter = BluetoothAdapter.getDefaultAdapter();
		
		if(BT_adapter == null) {
			Toast.makeText(getApplicationContext(), "Aparelho não suporta o bluetooth.", Toast.LENGTH_SHORT).show();
		}else if(!BT_adapter.isEnabled() || BT_adapter.isEnabled()) {
			Intent intent =  new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			startActivity(intent);
		}		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if(BT_adapter.isEnabled() == true) {
			
			// Usado para pegar os aparelhos pareados
			pairedDevices = BT_adapter.getBondedDevices();
			
			// Buscando os aparelhos pareados e armazenando no arrayList.
			if (pairedDevices.size() > 0) {

			    for (BluetoothDevice device : pairedDevices) {
			        arrayList.add(device.getName() + " -> " + device.getAddress());
			        address = device.getAddress();
			    }
			    
			    // Verificando se o arrayList criado contém dados dos aparelhos pareados, para assim então ser mostrado.
				// Pegando apenas a primeira opção dos aparelhos pareados.
				if(arrayList.size() > 0) {
					textViewAparelho.setText(arrayList.get(0));
				}
			    
			}else {
				textViewAparelho.setText("");
				textViewAparelho.setText("Bluetooth desativado ou nenhuma aparelho pareado.");
			}
			
		}else {
			Toast.makeText(getApplicationContext(), "Bluetooth desativado.", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public void conectar(View view) {
		
		if(arrayList.size() == 0) {
			Toast.makeText(getApplicationContext(), "Nenhum aparelho pareado.", Toast.LENGTH_SHORT).show();
		}else {
			
			BT_adapter.cancelDiscovery();
			Intent i = new Intent(getApplicationContext(), Channel.class);
		    i.putExtra("ADDRESS", address);
		    startActivity(i);
		    
		}
		
	}
	
	
	
}
