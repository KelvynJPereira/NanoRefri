package com.nanorefri;

import com.example.nanorefri.R;
import com.nanorefri.payment.Payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class AmountActivity extends Activity {
	
	TextView orderInProcess;
	private SodaOrder order;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_amount);
		
		// Recuperacao de dados do pedido
		order = (SodaOrder) getIntent().getSerializableExtra("orderFlavorSelected");
		
		// Exibicao de informacoes do pedido selecionado 
		orderInProcess = (TextView) findViewById(R.id.textView_flavor);
		orderInProcess.setText(order.getFlavor());
		
		// Recuperacao de componentes 
		(findViewById(R.id.imageButton1)).setOnClickListener(actionHandle);
		(findViewById(R.id.imageButton2)).setOnClickListener(actionHandle);
		(findViewById(R.id.imageButton3)).setOnClickListener(actionHandle);
	
	}
	
	// Metodo de click
	View.OnClickListener actionHandle = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Escolha de porcao 
				switch (v.getId()) {
					
				case R.id.imageButton1:
				
					order.setAmount("20%");
					paymentScreen(order);
					break;
				
				case R.id.imageButton2:
					
					order.setAmount("50%");
					paymentScreen(order);
					break;
				
				case R.id.imageButton3:
					
					order.setAmount("100%");
					paymentScreen(order);
					break;
					
				}
			}
		};
	
		// Passagem activity com envio de objeto
		public void paymentScreen(SodaOrder order) {
			Intent i = new Intent(this, Payment.class);
			i.putExtra("orderComplete", order);
			startActivity(i);		
		}
}