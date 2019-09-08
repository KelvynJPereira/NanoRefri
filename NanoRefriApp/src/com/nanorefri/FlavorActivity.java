package com.nanorefri;

import com.example.nanorefri.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FlavorActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_flavor);
		
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
			
			// Instancia de classe
			SodaOrder order = new SodaOrder();
			
			// Escolha de porcao 
			switch (v.getId()) {
				
			case R.id.imageButton1:
				
				order.setFlavor("Guaraná Antartica");
				amountScreen(order);
				break;
			
			case R.id.imageButton2:
				
				order.setFlavor("Coca-Cola");
				amountScreen(order);
				break;
			
			case R.id.imageButton3:
				
				order.setFlavor("Fanta");
				amountScreen(order);
				break;
				
			}
			
		}
	};

	// Passagem activity com envio de objeto
	public void amountScreen(SodaOrder order) {
		Intent i = new Intent(this, AmountActivity.class);
		i.putExtra("orderFlavorSelected", order);
		startActivity(i);		
	}
	
}