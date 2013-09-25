package com.example.masterarbeit_test_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.util.Base64;
//import android.util.Base64;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String clearText = "sdcard tmp test";
		byte[] encText = null;

		encText = new String("bHM=").getBytes();

//		String strEncCommand = Arrays.toString(encText);
//		byte[] decCommand = Base64.decode(strEncCommand, Base64.DEFAULT);

		String decCommandString = "Hallo";
		String request = new String(Base64.encodeToString(decCommandString.getBytes(), Base64.DEFAULT));

		
/*
		// for ( int i=0; i<decCommand.length; i++ ) {
		try {
			decCommandString += new String(decCommand, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String request = "/system/bin/ls /sdcard/";
		StringBuffer output = null;
		try {
			//Process process = new ProcessBuilder()
			//		.command(request.getBytes())
			//		.redirectErrorStream(true).start();
			Process process = Runtime.getRuntime().exec(request);
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(process.getInputStream()));

				int read;
				char[] buffer = new char[4096];
				output = new StringBuffer();
				while ((read = reader.read(buffer)) > 0) {
					output.append(buffer, 0, read);
				}
				reader.close();
			} finally {
				process.destroy();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		request = output.toString();
		*/

		TextView textView = (TextView) findViewById(R.id.TextOne);
		textView.setText("Command: " + request);
	}

	private void readStream(InputStream in) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
