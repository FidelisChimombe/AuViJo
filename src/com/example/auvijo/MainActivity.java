package com.example.auvijo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	

	/*
	 * (non-Javadoc) .Lets do this Buddy
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
	}
	public void initialize(){
		Button audio=(Button)findViewById(R.id.mainAudio);
    	Button video=(Button)findViewById(R.id.mainVideo);
    	Button text=(Button)findViewById(R.id.mainText);
    	Button journal=(Button)findViewById(R.id.mainJournal);
    	Button exit=(Button)findViewById(R.id.mainExit);
    	audio.setOnClickListener(this);
    	video.setOnClickListener(this);
    	text.setOnClickListener(this);
    	journal.setOnClickListener(this);
    	exit.setOnClickListener(this);
		
	}

	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.mainAudio:
			Intent k=new Intent(this,Audio.class);
			startActivity(k);
			break;
		case R.id.mainText:
			Intent j=new Intent(this,Text.class);
			startActivity(j);
			break;
		case R.id.mainVideo:
			Intent l=new Intent(this,Video.class);
			startActivity(l);
			break;
		case R.id.mainExit:
			finish();
			break;
		case R.id.mainJournal:
			Intent m=new Intent(this,MyJournal.class);
			startActivity(m);
			break;
		}
		
		}

}
