package com.esgi.myratp.application;

import android.app.Application;
import android.content.Context;

public class MyRATPApplication extends Application {
	
	private static MyRATPApplication _instance;
	
	public MyRATPApplication()
	{
		_instance = this;
	}
	
	public static Context getContext()
	{
		return _instance;
	}
	
	

}
