package com.example.auvijo;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Text extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.enter_text_layout_zz);
		setContentView(R.layout.enter_save_text_layout);
		initializeButtons();
	}
	
/*
 * initializes buttons
 */
	
	EditText writtenText;
	EditText entry_title;
	Button saveTextButton;
	final static String USER_ENTRY="com.example.auvijo.UserEntry";
	
public void initializeButtons(){
	
	saveTextButton=(Button)findViewById(R.id.save_entry_button);
	writtenText=(EditText)findViewById(R.id.text_entry_view);
	entry_title=(EditText)findViewById(R.id.title_entry);
	saveTextButton.setOnClickListener(this);
	
	
	
}
	@Override
	/*
	 * 
	 * probably we need to start some activity after the button is clicked in order to get the text
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {
		initializeButtons();
		switch(v.getId()){
		case R.id.save_entry_button:
			writtenText.clearFocus();
			//Intent intent = new Intent(this, SaveText.class);
			String writtenTextString = writtenText.getText().toString();
			String entry_title_s=entry_title.getText().toString(); 
			//intent.putExtra(USER_ENTRY, writtenTextString);
			//startActivity(intent);
			saveEntry(entry_title_s,writtenTextString);
			break;
		}
	}
	
	private void saveEntry(String entry_title_s,String writtenTextString) {
		FileOutputStream outputStream;

		try {
		  outputStream = openFileOutput(entry_title_s, this.MODE_PRIVATE);
		  outputStream.write(writtenTextString.getBytes());
		  outputStream.close();
		  Log.d("fileSaved","the file saved");
		} catch (Exception e) {
		  e.printStackTrace();
		}
		
		File file=new File(this.getFilesDir(), entry_title_s);
		Log.d("file path",file.toString());
		
		Intent intent = new Intent(this, MyJournal.class);
		startActivity(intent);
		finish();
	}

}
