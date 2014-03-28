package com.example.auvijo;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;



public class MyJournal extends ListActivity {

    private MyCustomAdapter mAdapter;
    ArrayList<FileView> fileViews = new ArrayList<FileView>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareJournal();
        mAdapter = new MyCustomAdapter(fileViews);
        setListAdapter(mAdapter);
    }

    private void prepareJournal() {
		File dir = getFilesDir();
		String[] files=dir.list();
		File[] filesO=dir.listFiles();
		
		
		int a=0;
		for(File file:filesO){
			fileViews.add(getFileView(file));
			fileViews.add(new FileView("","","",3));
		}
	}

	private FileView getFileView(File file) {
		FileInputStream fis;
	    byte[] data = new byte[(int)file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(data);
		    fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
		String text="";
	    try {
			text = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new FileView(file.getName(), new Date(file.lastModified()).toString(), text, 2);
	}



	private class MyCustomAdapter extends BaseAdapter {
        private static final int TYPE_VIDEO=0;
        private static final int TYPE_AUDIO=1;
        private static final int TYPE_TEXT=2;
        private static final int TYPE_SEPERATOR=3;

        private ArrayList<String> mData = new ArrayList<String>();
        private ArrayList<FileView> fileViews= new ArrayList<FileView>();
        private LayoutInflater mInflater;

        public MyCustomAdapter(ArrayList<FileView> views) {//new addition here
            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            fileViews = views;//my code
            notifyDataSetChanged();//my code
        }

        @Override
        public int getItemViewType(int position) {
        	return fileViews.get(position).getType();
        }

        @Override
        public int getViewTypeCount() {
        	return 4;
            //return TYPE_MAX_COUNT;
        }

        @Override
        public int getCount() {
        	return fileViews.size();
            //return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            int type = getItemViewType(position);
            System.out.println("getView " + position + " " + convertView + " type = " + type);
            if (convertView == null) {
                holder = new ViewHolder();
                switch (type) {
                case TYPE_VIDEO:
                    convertView = mInflater.inflate(R.layout.video_file_layout, null);
                    holder.nameView = (TextView)convertView.findViewById(R.id.video_name);
                    holder.dateView = (TextView)convertView.findViewById(R.id.video_date);
                    holder.textView = (TextView)convertView.findViewById(R.id.video_note);
                    break;
                
                case TYPE_AUDIO:
                    convertView = mInflater.inflate(R.layout.audio_file_layout, null);
                    holder.nameView = (TextView)convertView.findViewById(R.id.audio_name);
                    holder.dateView = (TextView)convertView.findViewById(R.id.audio_date);
                    holder.textView = (TextView)convertView.findViewById(R.id.audio_note);
                    break;
                    
                case TYPE_TEXT:
                    convertView = mInflater.inflate(R.layout.text_file_layout, null);
                    holder.nameView = (TextView)convertView.findViewById(R.id.text_name);
                    holder.dateView = (TextView)convertView.findViewById(R.id.text_date);
                    holder.textView = (TextView)convertView.findViewById(R.id.textSpace);
                    break;
                
                case TYPE_SEPERATOR:
                	convertView = mInflater.inflate(R.layout.seperator_layout, null);
                	holder.nameView = (TextView)convertView.findViewById(R.id.separator);
                    holder.dateView = (TextView)convertView.findViewById(R.id.separator);
                    holder.textView = (TextView)convertView.findViewById(R.id.separator);
                	break;
                }
                convertView.setTag(holder);
            } else {
            	holder = (ViewHolder)convertView.getTag();
            }
            
            if(type!=TYPE_SEPERATOR){
            	holder.nameView.setText(fileViews.get(position).getName());
                holder.dateView.setText(fileViews.get(position).getDate());
                holder.textView.setText(fileViews.get(position).getText());
            }
            return convertView;
        }
    }

    public static class ViewHolder {
    	public TextView dateView;
    	public TextView nameView;
    	public TextView textView;
    }
}


class FileView{
	String date, name, text;
	int type;
	public FileView(String name, String date, String text,int type){
		this.date=date;
		this.name=name;
		this.text=text;
		this.type=type;
	}
	
	public String getName(){
		return name;
	}
    
    public String getDate(){
		return date;
	}
    
    public String getText(){
		return text;
	}
    
    public int getType(){
    	return type;
    }
    
}








//package com.example.auvijo;
//
//import java.io.File;
//
//import android.app.Activity;
//import android.app.ListActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//
//public class MyJournal extends ListActivity implements OnClickListener{
//	
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		
//		prepareJournal();
//		setContentView(R.layout.myjournal);
//	}
//
//	private void prepareJournal() {
//		File dir = getFilesDir();
//		String[] files=dir.list();
//		File[] filesO=dir.listFiles();
//		for(String i:files){
//			Log.d("file",i);
//		}
//		
//		for(File file:filesO){
//			getFileView();
//		}
//	}
//	
//
//
//	private void getFileView() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onClick(View arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	
//	
//	
//
//}
//
//
//class FileView{
//	String date, name, text;
//	int type;
//	public FileView(String name, String date, String text,int type){
//		this.date=date;
//		this.name=name;
//		this.text=text;
//		this.type=type;
//	}
//	
//	public String getName(){
//		return name;
//	}
//  
//  public String getDate(){
//		return date;
//	}
//  
//  public String getText(){
//		return text;
//	}
//  
//  public int getType(){
//  	return type;
//  }
//  
//}
//

/*package com.example.auvijofileviewexp2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
*/