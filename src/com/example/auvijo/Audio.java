package com.example.auvijo;

/*
 * The application needs to have the permission to write to external storage
 * if the output file is written to the external storage, and also the
 * permission to record audio. These permissions must be set in the
 * application's AndroidManifest.xml file, with something like:
 *
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.RECORD_AUDIO" />
 *
 */

import android.app.Activity;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.content.Context;
import android.util.Log;
import android.media.MediaRecorder;
import android.media.MediaPlayer;

import java.io.IOException;


public class Audio extends Activity
{
    private static final String LOG_TAG = "Audio";
    private static String mFileName = null;
    Button startButt,saveButt,deleteButt,playButt;

    private RecordButton mRecordButton = null;
    private MediaRecorder mRecorder = null;

    private PlayButton   mPlayButton = null;
    private MediaPlayer   mPlayer = null;
    private SaveButton mSaveButton=null;
   

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    class RecordButton extends Button {
        boolean mStartRecording = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onRecord(mStartRecording);
                if (mStartRecording) {                	
                	setBackgroundResource(R.drawable.stopicon);
                    //setText("Stop recording");
                } else {
                	setBackgroundResource(R.drawable.startbutton);
                    //setText("Start recording");
                }
                mStartRecording = !mStartRecording;
            }
        };

        public RecordButton(Context ctx) {
            super(ctx);
            setBackgroundResource(R.drawable.startbutton);
            setOnClickListener(clicker);
        }
    }

    class PlayButton extends Button {
        boolean mStartPlaying = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onPlay(mStartPlaying);
                if (mStartPlaying) {
                    setText("Stop playing");
                } else {
                    setText("Start playing");
                }
                mStartPlaying = !mStartPlaying;
            }
        };

        public PlayButton(Context ctx) {
            super(ctx);
            setText("Start playing");
            setOnClickListener(clicker);
            
        	

            
            
        }
    }
   
    
    class SaveButton extends Button {
        boolean mStartPlaying = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onPlay(mStartPlaying);
                if (mStartPlaying) {
                    setText("Stop playing");
                } else {
                    setText("Start playing");
                }
                mStartPlaying = !mStartPlaying;
            }
        };

        public SaveButton(Context ctx) {
            super(ctx);
            setText("SAVE");
            setOnClickListener(clicker);
            
        	

            
            
        }
    }
    
//public void onClick(View v){
//	boolean mStartPlaying=true;
//	switch(v.getId()){
//	case R.id.startButton:
//		onRecord(mStartPlaying);
//		if(mStartPlaying){
//			Log.d("fide-butt",String.valueOf(startButt==null));
//			startButt.setBackgroundResource(R.drawable.stopicon);			
//			
//		
//		}else{
//			startButt.setBackgroundResource(R.drawable.startbutton);
//			playButt.setVisibility(RESULT_OK);
//		}
//		mStartPlaying = !mStartPlaying;
//		break;
//	case R.id.saveButton:
//		break;
//	case R.id.deleteButton:
//		break;
//	case R.id.playButton:
//		onPlay(mStartPlaying);
//		if(mStartPlaying){
//			startButt.setBackgroundResource(R.drawable.stopicon);			
//		}else{
//			startButt.setBackgroundResource(R.drawable.audioicon);
//			
//		}
//		break;
//	}
//}
    public Audio() {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
    }
public void initialize(){
	startButt=(Button)findViewById(R.id.startButton);
    saveButt=(Button)findViewById(R.id.save_entry_button);
    deleteButt=(Button)findViewById(R.id.deleteButton);
    playButt=(Button)findViewById(R.id.playButton);
}
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        LinearLayout ll = new LinearLayout(this);
        mRecordButton = new RecordButton(this);
        ll.setWeightSum(100);
        
        ll.addView(mRecordButton,
            new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            
            
            params.gravity=Gravity.BOTTOM;
            params.height=100;
            params.width=100;
            params.weight=40;
            mRecordButton.setLayoutParams(params);
//        ll.addView(mRecordButton,
//                new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    0));
       
        
        
        
        
        
        mPlayButton = new PlayButton(this);
        ll.addView(mPlayButton,
            new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        LinearLayout.LayoutParams paramss= new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            
            paramss.gravity=Gravity.BOTTOM;
            paramss.height=100;
            paramss.weight=40;
            paramss.width=100;
            
            

            mPlayButton.setLayoutParams(paramss);
         
            
            mSaveButton = new SaveButton(this);
            ll.addView(mSaveButton,
                new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    0));
            LinearLayout.LayoutParams para= new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                
                para.gravity=Gravity.BOTTOM;
                paramss.height=100;
                paramss.weight=20;
                paramss.width=100;
                
                

                mSaveButton.setLayoutParams(paramss);
        
        setContentView(ll);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}