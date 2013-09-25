package com.example.demoservicetest;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class TestBrowser extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		long lDateTime = new Date().getTime();
		
		Intent browserIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("http://212.201.64.227/chrisMaster/redi.php?devID=1&ds="+lDateTime));
				//Uri.parse("https://81.89.98.215/android/redi.html"));
		browserIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		browserIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		this.startActivity(browserIntent);
		/*
		try {
			Thread.sleep(1000*3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		browserIntent = null;
		
		browserIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("http://www.google.de"));
				//Uri.parse("https://81.89.98.215/android/redi.html"));
		browserIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		this.startActivity(browserIntent);
		*/
		
		finish();
		
	}
	
}