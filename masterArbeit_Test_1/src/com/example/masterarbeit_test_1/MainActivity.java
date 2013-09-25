/*
 * /daten/android/Entwicklung/android-sdk-linux/platform-tools/adb -s HT06GPL00748 install -r /daten/android/mastArbeit/masterArbeit_Test_1/bin/masterArbeit_Test_1.apk
 */
package com.example.masterarbeit_test_1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.telephony.TelephonyManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * String username ="..."; String password ="";
		 * 
		 * Uri data = getIntent().getData(); if(data != null){ List params =
		 * data.getPathSegments(); username = (String) params.get(0); password =
		 * (String) params.get(1); } else { username = "!!!"; }
		 */
		
		setContentView(R.layout.activity_main);

		Button button = (Button) findViewById(R.id.button1);

		// TextView textView = (TextView) findViewById(R.id.textTwo);
		// textView.setText("User: "+username);

		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String test = android.os.Build.USER;
				// PowerManager pm = (PowerManager)
				// getSystemService(Context.POWER_SERVICE);
				// long time=(long) 0.10;
				// pm.goToSleep(time);
				TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				String imei = tm.getDeviceId();
				// tm.SIM_STATE_READY;
				// get The Phone Number
				String phone = tm.getLine1Number();

				TextView textView = (TextView) findViewById(R.id.textTwo);

				textView.setText(imei + ":" + tm.SIM_STATE_READY + ":"
						+ tm.CALL_STATE_RINGING);
			}
		});
		
		callBrowserForRequest ();

		Button button2 = (Button) findViewById(R.id.button2);

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textView = (TextView) findViewById(R.id.textTwo);
				textView.setText(".......");
				callBrowserForRequest();	
			}
		});

		// int requestCode=0;

		// finishActivity(requestCode);
		// getApplication ().onTerminate();
		// finish();
		// String myDeviceModel = android.os.Build.MODEL;
	}
	
	@Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        callBrowserForRequest ();
    }


	public void callBrowserForRequest() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("http://my.fh-sm.de/~linde1/redi.html"));
				//Uri.parse("https://81.89.98.215/android/redi.html"));
		browserIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivityForResult(browserIntent, 0);
	}

	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);// must store the new intent so getIntent() won't
							// return the old one

		String username = "...";
		String password = "";

		Uri data = getIntent().getData();
		if (data != null) {
			List params = data.getPathSegments();
			username = (String) params.get(0);
			password = (String) params.get(1);
		} else {
			username = "!";
		}

		TextView textView = (TextView) findViewById(R.id.textTwo);
		textView.setText("User: " + username);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
