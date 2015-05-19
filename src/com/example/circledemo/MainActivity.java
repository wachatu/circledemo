package com.example.circledemo;

import android.os.Bundle;
import android.app.Activity;

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
