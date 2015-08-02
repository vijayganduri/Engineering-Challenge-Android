package com.vijayganduri.nutricheck;

import android.app.Application;

import com.splunk.mint.Mint;

public class MainApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		//initialize Bugsense
		Mint.initAndStartSession(this, Config.BUGSENSE_KEY);
		
	}

}
