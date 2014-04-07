package com.example.auvijo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SaveAudio extends Activity {
	String audioFileName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_audio_layout);
		Intent intent = getIntent();
		Log.d("as","asasasa");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_audio, menu);
		return true;
	}
	
	public void saveEntry(View view){
		EditText title=(EditText)findViewById(R.id.title_entry2);
		EditText notes=(EditText)findViewById(R.id.entry_notes2);
		String titleString=title.getText().toString();
		String notesString=notes.getText().toString();
		
		File parent=new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"AuViJo");
		if (!parent.exists()) {parent.mkdirs();}
		
		File diaryEntriesFolder=new File(parent.getAbsolutePath(),"Diary Entries");
		if (!diaryEntriesFolder.exists()) {diaryEntriesFolder.mkdirs();}
		
		File audioNotesFolder=new File(parent.getAbsolutePath(),"Audio Notes");
		if (!audioNotesFolder.exists()) {audioNotesFolder.mkdirs();}
		
		File originalAudioFile=new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"audiorecordtest.3gp");
		File newAudioFile = new File(diaryEntriesFolder.getAbsolutePath(),titleString+".3gp");
		originalAudioFile.renameTo(newAudioFile);
		
		File notes_file = new File(audioNotesFolder, titleString+".txt");
		FileOutputStream outputStream;
		OutputStreamWriter myOutWriter;
		try {
			  outputStream = new FileOutputStream(notes_file,true);
			  myOutWriter=new OutputStreamWriter(outputStream);
			  myOutWriter.append(notesString);
			  myOutWriter.close();
		} 
		catch (Exception e) {
			  e.printStackTrace();
		}
		
		
		
		Intent intent = new Intent(this, MyJournal.class);
		startActivity(intent);
		finish();
		
	}

}
