/*
 * Based on: http://stackoverflow.com/questions/10782187/how-to-encrypt-file-from-sd-card-using-aes-in-android
 * 
 */
package com.example.masterarbeitcrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import android.os.Bundle;
import android.os.Environment;
import android.accounts.AccountManager;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private String PASSWORD = "MasterarbeitAndroid123456"; // MasterarbeitAndroid123456
	// private String PASSWORD = "123456";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView textView = (TextView) findViewById(R.id.textView2);

		// The password must have a base 2
		if ((PASSWORD.length() % 8) == 0) {

			textView.setText("Base 8");
		} else {
			String toWrite = "";
			toWrite += "No Base 8: " + (PASSWORD.length() % 8) + " - "
					+ (PASSWORD.length() / 8);
			if (PASSWORD.length() >= 8) {
				int cutAfter = (PASSWORD.length() / 8) * 8;
				toWrite += " > " + cutAfter;
				PASSWORD = PASSWORD.substring(0, cutAfter);
				toWrite += "| cut: " + PASSWORD;
			}
			textView.setText(toWrite);
		}

		// Check passwort higher then 8 characters
		if (PASSWORD.length() >= 8) {
			Button b1 = (Button) findViewById(R.id.button1);
			Button b2 = (Button) findViewById(R.id.button2);

			b1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					EditText edit1 = (EditText) findViewById(R.id.editText1);
					TextView textView = (TextView) findViewById(R.id.textView1);
					String clearContent = edit1.getText().toString();

					try {
						encryptComFile(PASSWORD, clearContent);
					} catch (IOException e) {
						textView.setText("Ex IO: " + e);
						// Create a file
					} catch (NoSuchAlgorithmException r) {
						textView.setText("Ex NoSuchAlgorithmException: " + r);
					} catch (NoSuchPaddingException t) {
						textView.setText("Ex NoSuchPaddingException: " + t);
					} catch (InvalidKeyException z) {
						textView.setText("Ex InvalidKeyException: " + z);
					}
				}
			});

			b2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					TextView textView = (TextView) findViewById(R.id.textView1);
					EditText edit1 = (EditText) findViewById(R.id.editText1);
					String edit2Text = "";

					try {
						edit2Text = decrytpComFile(PASSWORD);
					} catch (IOException e) {
						textView.setText("Ex IO: " + e);
						// Create a file
					} catch (NoSuchAlgorithmException r) {
						textView.setText("Ex NoSuchAlgorithmException: " + r);
					} catch (NoSuchPaddingException t) {
						textView.setText("Ex NoSuchPaddingException: " + t);
					} catch (InvalidKeyException z) {
						textView.setText("Ex InvalidKeyException: " + z);
					}

					edit1.setText(edit2Text);
				}

			});

		} else {
			TextView textView2 = (TextView) findViewById(R.id.textView1);
			textView2.setText("Passwort kleiner als 8 Zeichen.");
		}

	}

	private void encryptComFile(String pw, String content) throws IOException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException {
		FileOutputStream fos = new FileOutputStream(Environment
				.getExternalStorageDirectory().getPath() + "/encrypted");

		// Length is 16 byte
		SecretKeySpec sks = new SecretKeySpec(pw.getBytes(), "AES");
		// Create cipher
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, sks);
		// Wrap the output stream
		CipherOutputStream cos = new CipherOutputStream(fos, cipher);
		// Write bytes
		byte b[] = content.getBytes();
		for (int i = 0; i < content.length(); i++) {
			cos.write(b[i]);
		}
		cos.flush();
		cos.close();
	}

	private String decrytpComFile(String pw) throws IOException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException {

		String outDecryptCont = "";
		FileInputStream fis = new FileInputStream(Environment
				.getExternalStorageDirectory().getPath() + "/encrypted");
		SecretKeySpec sks = new SecretKeySpec(pw.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, sks);

		CipherInputStream cis = new CipherInputStream(fis, cipher);
		int b;
		byte[] d = new byte[1];
		while ((b = cis.read(d)) != -1) {
			outDecryptCont += new String(d, "UTF-8");
		}
		cis.close();

		return outDecryptCont;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
