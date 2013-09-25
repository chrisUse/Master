package com.example.demoservicetest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		boolean findService = false;

		//--writeToFile("onCreate MainActivity");
				
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (WebPullService.class.getName().equals(service.service.getClassName())) {
	            //return true;
	        	findService = true;
	            
	        }
	    }
	    if ( !findService )
	      startService(new Intent(this, WebPullService.class));

		finish();
	}
	
	@Override
	protected void onStop () {
		//--writeToFile("onStop MainActivity");
	}
	
	private void writeToFile(String content) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(Environment.getExternalStorageDirectory()
							.getPath() + "/Log.log", true)));
			String logOutString = "LOG: " + content + " \n";
			out.println(logOutString);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onDestroy () {
		super.onDestroy();
		//--writeToFile("onDestroy MainActivity");
		//startService(new Intent(getApplicationContext(), WebPullService.class));
		//--startService(new Intent(getApplicationContext(), WakeupActivityService.class));
	}

}