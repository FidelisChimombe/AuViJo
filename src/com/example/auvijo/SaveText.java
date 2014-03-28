package com.example.auvijo;

import java.io.File;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SaveText extends Activity {
    
	String userEntry;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_text_layout);
		Intent intent = getIntent();
		userEntry = intent.getStringExtra(Text.USER_ENTRY);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_text, menu);
		return true;
	}
	
	public void saveEntry(View view){
		EditText entry_title=(EditText)findViewById(R.id.title_entry);
		String entry_title_s=entry_title.getText().toString();
		
		FileOutputStream outputStream;

		try {
		  outputStream = openFileOutput(entry_title_s, this.MODE_PRIVATE);
		  outputStream.write(userEntry.getBytes());
		  outputStream.close();
		  Log.d("fileSaved","the file saved");
		} catch (Exception e) {
		  e.printStackTrace();
		}
		
		File file=new File(this.getFilesDir(), entry_title_s);
		Log.d("file path",file.toString());
		
		Intent intent = new Intent(this, MyJournal.class);
		startActivity(intent);
	}

}
