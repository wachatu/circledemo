package com.example.circledemo;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	private TimerCircleBar mCircleBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mCircleBar = (TimerCircleBar) findViewById(R.id.circle);
		mCircleBar.setSweepAngle(360);
		mCircleBar.startCustomAnimation();
		
	}

}
