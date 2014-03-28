package com.example.auvijo;

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
		//setContentView(R.layout.text);
		setContentView(R.layout.enter_text_layout_zz);
		initializeButtons();
	}
	
/*
 * initializes buttons
 */
	
	EditText writtenText;
	Button saveTextButton;
	final static String USER_ENTRY="com.example.auvijo.UserEntry";
	
public void initializeButtons(){
	
	saveTextButton=(Button)findViewById(R.id.save_entry_button);
	writtenText=(EditText)findViewById(R.id.text_entry_view);
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
			Intent intent = new Intent(this, SaveText.class);
			String writtenTextString = writtenText.getText().toString();
			intent.putExtra(USER_ENTRY, writtenTextString);
			startActivity(intent);
			break;
		}
		
	}

}
