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
import android.widget.ProgressBar;
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

public class Audio extends Activity {
	private static final String LOG_TAG = "Audio";
	private static String mFileName = null;
	private RecordButton mRecordButton = null;
	private MediaRecorder mRecorder = null;
	private PlayButton mPlayButton = null;
	private MediaPlayer mPlayer = null;
	private SaveButton mSaveButton = null;
	private int currentTime;

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
		//mPlayer.reset();
		mPlayer.release();
		mPlayer = null;
	}

	private void startRecording() {
		mRecorder = new MediaRecorder();
		//mRecorder.setMaxDuration(5000);
		
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
				mPlayButton.setEnabled(false);
				mSaveButton.setEnabled(false);
					
					
					
				} else {
					setBackgroundResource(R.drawable.recordicon);
					mPlayButton.setEnabled(true);
					mSaveButton.setEnabled(true);
					
				}
				mStartRecording = !mStartRecording;
			}
		};
		
		

		public RecordButton(Context ctx) {
			super(ctx);
			setBackgroundResource(R.drawable.recordicon);
			setOnClickListener(clicker);
		}
	}

	class PlayButton extends Button {
		boolean mStartPlaying = true;
		OnClickListener clicker = new OnClickListener() {
			public void onClick(View v) {
				onPlay(mStartPlaying);
				if (mStartPlaying) {
					setBackgroundResource(R.drawable.stopicon);
					mRecordButton.setEnabled(false);
					mSaveButton.setEnabled(false);
				} else {
					setBackgroundResource(R.drawable.replayicon);
					mRecordButton.setEnabled(true);
					mSaveButton.setEnabled(true);
				}
				mStartPlaying = !mStartPlaying;
			}
		};

		public PlayButton(Context ctx) {
			super(ctx);
			setBackgroundResource(R.drawable.replayicon);
			setOnClickListener(clicker);

		}
	}

	class SaveButton extends Button {
		boolean mStartPlaying = true;

		OnClickListener clicker = new OnClickListener() {
			public void onClick(View v) {
				onPlay(mStartPlaying);
				if (mStartPlaying) {
					setBackgroundResource(R.drawable.saveicon);
					mPlayButton.setEnabled(false);
					mRecordButton.setEnabled(false);
				} else {
					setBackgroundResource(R.drawable.saveicon);
					mPlayButton.setEnabled(true);
					mRecordButton.setEnabled(true);
				}
				mStartPlaying = !mStartPlaying;
			}
		};

		public SaveButton(Context ctx) {
			super(ctx);
			setBackgroundResource(R.drawable.saveicon);
			setOnClickListener(clicker);
		}
	}
	
	


	public Audio() {
		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();

		mFileName += "/audiorecordtest.3gp";
	}

	

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		LinearLayout ll = new LinearLayout(this);
		ll.setBackgroundResource(R.drawable.audioicon);
		mRecordButton = new RecordButton(this);
		ll.setWeightSum(100);

		ll.addView(mRecordButton, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, 0));
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		params.gravity = Gravity.BOTTOM;
		params.bottomMargin=50;
		params.height = 80;
		params.width = 40;
		params.weight = 40;
		mRecordButton.setLayoutParams(params);

		mPlayButton = new PlayButton(this);
		ll.addView(mPlayButton, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, 0));
		LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		paramss.gravity = Gravity.BOTTOM;
		paramss.bottomMargin=50;
		paramss.height = 80;
		paramss.weight = 40;
		paramss.width = 80;

		mPlayButton.setLayoutParams(paramss);

		mSaveButton = new SaveButton(this);
		ll.addView(mSaveButton, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, 0));
		LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		para.gravity = Gravity.BOTTOM;
		para.bottomMargin=50;
		paramss.height = 80;
		paramss.weight = 20;
		paramss.width = 80;

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

	}
}