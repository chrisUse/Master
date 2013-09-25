package com.example.demoservicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class WakeupActivityService extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		startActivity(new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
