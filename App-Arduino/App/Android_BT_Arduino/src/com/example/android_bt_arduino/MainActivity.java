package com.example.android_bt_arduino;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	// Views.
	Button button1, button2, buttonA, buttonB, buttonEnviar;
	TextView textViewAparelho, textViewResposta;
	EditText editText;
	
	// ArrayList para pegar o nome e o endereço do aparelho.
	ArrayList<String> arrayList = new ArrayList<String>();
	// ArrayList para pegar apenas o endereço e trabalhar com ele.
	ArrayList<String> arrayList2 = new ArrayList<String>();
	
	// Uuid - Endereço único, que será usado no canal criado de comunicação.
	// Tornando a comunicação única.
	private final static UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	
	// --------------------------------------------------------------------------------------
	
	// Bluetooth adapter - É o adaptador bluetooth do aparelho, caso retorne null é porquê o aparelho não suporta o bluetooth,
	// caso não, é porquê o aparelho suporta.
	// É usado para diversas atividades com o bluetooth pois é o próprio adaptador do aparelho.
	BluetoothAdapter BT_Adapter = null;
	
	// Usado para pegar os aparelhos pareados.
	Set<BluetoothDevice> pairedDevices;
    
    // Bluetooth socket - Responsável por criar o meio de comunicação, recebendo o dispositivo pego e
	// chamando a função createInsecureRfcommSocketToServiceRecord como parâmetor o uuid.
    // Então será criado um canal com o dispositivo com um id/endereço específico.
    // Necessário setar como null depois usa o try/catch para tentar realizar a criação do canal.
	BluetoothSocket BT_Socket = null;
	
	boolean connection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		buttonA = (Button) findViewById(R.id.buttonA);
		buttonB = (Button) findViewById(R.id.buttonB);
		buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
		textViewAparelho = (TextView) findViewById(R.id.textViewAparelho);
		textViewResposta = (TextView) findViewById(R.id.textViewResposta);
		editText = (EditText) findViewById(R.id.editText);
		
		// --------------------------------------------------------------------------------------
		
		// Bluetooth.
		BT_Adapter = BluetoothAdapter.getDefaultAdapter();
		
		// Usado para pegar os aparelhos pareados
		pairedDevices = BT_Adapter.getBondedDevices();
		
		// Buscando os aparelhos pareados e armazenando no arrayList.
		if (pairedDevices.size() > 0) {

		    for (BluetoothDevice device : pairedDevices) {
		        arrayList.add(device.getName() + " -> " + device.getAddress());
		        arrayList2.add(device.getAddress());
		    }
		}
		
		// Verificando se o arrayList criado contém dados dos aparelhos pareados, para assim então ser mostrado.
		// Pegando apenas a primeira opção dos aparelhos pareados.
		if(arrayList.size() > 0) {
			textViewAparelho.setText(arrayList.get(0));
		}
		
		// -------------------------------------------------------------------------
		
		// Após verificar se tem aparelho pareado e guardar suas informações, tenta-se criar o canal coma função criada.
		connection();
		
		Toast.makeText(getApplicationContext(), "Resposta: "+connection, Toast.LENGTH_SHORT).show();

		
		// -----------------------------------------------------------------------------
		
		//Pegando as ações dos botões.
		
		// Testando com ontouchlistener e return true.
		button1.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					saidaDados("1");
				}
				return true;
			}
		});
		
		// Testando com ontouchlistener e return false.
		buttonA.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					saidaDados("A");
				}
				return false;
			}
		});
		
		
	}
	
	// Testando o click do botão2.
	public void click2(View view) {
		saidaDados("2");
	}
	
	// Testando o click do botãoB.
	public void clickB(View view) {
		saidaDados("B");
	}
	
	// Testando o click do botão.
	public void enviar(View view) {
		String dados = editText.getText().toString();
		if(dados == "" || dados.isEmpty()) {
			Toast.makeText(getApplicationContext(), "Digite algo pelo menos.", Toast.LENGTH_SHORT).show();
		}else {
			Toast.makeText(getApplicationContext(), dados, Toast.LENGTH_SHORT).show();
			saidaDados(dados);
		}
		
	}
	
	// Função de conexão.
	public void connection () {
		
		// Contador que será usado nas tentativas de se tentar conectar ao bluetooth.
		int count = 0;
		
		// Bluetooth.
		BT_Adapter = BluetoothAdapter.getDefaultAdapter();
		
		// Conecta com o aparelho que foi pareado pelo address que for especificado.
		// No caso foi pego o primeiro address e guardado dentro do arraylist.
	    BluetoothDevice BT_Device = BT_Adapter.getRemoteDevice(arrayList2.get(0));
		
		// Tentando criar e se conectar ao canal.
	    // Utilizando um loop para tentar se conectar mais de uma vez.
	    
	    do {
	    	
			try {
				BT_Socket = BT_Device.createRfcommSocketToServiceRecord(uuid);
				BT_Socket.connect();
				connection = BT_Socket.isConnected();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			count++;
	    }while(!BT_Socket.isConnected() && count < 3);
	}
	
	// Função para enviar os dados.
	public void saidaDados (String s) {
		// Criando o a saída de dados com a classe outPutStream, que recebe o canal criado com a função getOutputStream.
		// Posteriormente escreve essa função, no caso envia os dados com a função write e passa o parâmetro que no caso o que for enviar.
		try {
			OutputStream output = BT_Socket.getOutputStream();
			output.write(s.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
