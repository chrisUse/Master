package com.example.masterarbeit_test_1;

import java.util.List;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartOnUri extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String username = "...";
		String password = "";

		Uri data = getIntent().getData();
		if (data != null) {
			List params = data.getPathSegments();
			username = (String) params.get(0);
			password = (String) params.get(1);
		} else {
			username = "!!!";
		}

		setContentView(R.layout.activity_main);
		
        
        TextView textView = (TextView) findViewById(R.id.textTwo);
        textView.setText("Parameter 1: "+username + "Parameter 2: "+password);
        
        Button button2 = (Button) findViewById(R.id.button2);

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textView = (TextView) findViewById(R.id.textTwo);
				textView.setText("mooooop");
				
			}
		});
	}

}
