package com.turyng.nanorefri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Att
        TextView text_response;
        Button btn_request;

        // Recuperacao de variaveis
        text_response = findViewById(R.id.textView);
        btn_request = findViewById(R.id.button_1);

    }


}
