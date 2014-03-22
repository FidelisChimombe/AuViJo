package com.example.auvijo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Text extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		Button savedText=(Button)findViewById(R.id.saveText);
		EditText writtenText=(EditText)findViewById(R.id.writtenTextEntry);
		savedText.setOnClickListener(this);
	}
	
/*
 * initializes buttons
 */
	
	EditText writtenText;
	Button savedText;
	TextView txtExp;
	
public void initializeButtons(){
	savedText=(Button)findViewById(R.id.saveText);
	writtenText=(EditText)findViewById(R.id.writtenTextEntry);
	txtExp=(TextView)findViewById(R.id.experimental);
	savedText.setOnClickListener(this);
	
	
	
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
		case R.id.saveText:
			String receivedText=writtenText.getText().toString();
			txtExp.setText(receivedText);
			writtenText.clearFocus();
			break;
		}
		
	}

}
