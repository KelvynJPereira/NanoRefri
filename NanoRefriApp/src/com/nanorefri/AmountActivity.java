package com.nanorefri;

import com.example.nanorefri.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class AmountActivity extends Activity {
	
	TextView teste;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_amount);
		
		SodaOrder order = (SodaOrder) getIntent().getSerializableExtra("orderFlavorSelected");
		
		teste = (TextView) findViewById(R.id.textView_flavor);
		teste.setText(order.getFlavor());
		
		
	
	}
}