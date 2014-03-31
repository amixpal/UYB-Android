package com.coderwall.executer;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button button;
	Handler handler;
	Runnable runnable;
	Handler handlerStarting;
	Runnable runnableStarting;
	Handler handlerStoping;
	Runnable runnableStoping;
	boolean runHandler = true;
	boolean stopingBool = true;
	boolean stopBool = true;
	boolean isStopped = false;
	AsyncTask<Void, Void, Void> onStart;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				if(button.getText().toString().equals("Start"))
				{
					isStopped = false;
					runHandler = true;
					stopingBool = true;
					button.setText("Starting");
					if(onStart!=null && onStart.getStatus()!=AsyncTask.Status.FINISHED)
					{
						onStart.cancel(true);
					}
					onStart = new AsyncTask<Void, Void, Void>() {

						@Override
						protected void onPostExecute(Void result) {
							// TODO Auto-generated method stub
							super.onPostExecute(result);
							new AsyncTask<Void, Void, Void>(){

								@Override
								protected Void doInBackground(Void... params) {
									// TODO Auto-generated method stub
									return null;
								}
								protected void onPostExecute(Void result) {
									button.setText("Stop");
								}
							}.execute();
							
							handler = new Handler();
							runnable = new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									//if(!isStopped)
									{
										if(runHandler)
										{
											handler.postDelayed(runnable, 5000);
											runHandler = false;
										}
										else{
											if(stopingBool)
											{
												button.setText("Stopping");
												handler.postDelayed(runnable, 2000);
												stopingBool = false;
											}
											else{
												button.setText("Start");
											}
										}
									}
									
									
								}
							};
							runnable.run();
							
						}
						
						@Override
						protected Void doInBackground(Void... params) {
							// TODO Auto-generated method stub
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						}
					}.execute();
				}
				else if(button.getText().toString().equals("Starting"))
				{
					onStart.cancel(true);
					button.setText("Start");
					//onClick(v);
				}
				else if(button.getText().toString().equals("Stop"))
				{
					isStopped = true;
					handler.removeCallbacks(runnable);
					stopBool = true;
					onStart.cancel(false);
					//onStart.
					handlerStoping = new Handler();
					runnableStoping = new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							if(stopBool)
							{
								button.setText("Stopping");
								handlerStoping.postDelayed(runnableStoping, 2000);
								stopBool = false;
							}
							else{
								button.setText("Start");
							}
						}
					};
					runnableStoping.run();
//					new AsyncTask<Void, Void, Void>(){
//
//						protected void onPostExecute(Void result) {
//							
//						}
//						
//						@Override
//						protected Void doInBackground(Void... params) {
//							// TODO Auto-generated method stub
//							try {
//								Thread.sleep(2000);
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//							return null;
//						}
//						protected void onPreExecute() {
//							
//						}
//						
//					}.execute();
				}
				else if(button.getText().toString().equals("Stopping"))
				{
					runHandler = true;
					stopingBool = true;
					if(runnable!=null)
						handler.removeCallbacks(runnable);
					if(runnableStoping!=null)
						handlerStoping.removeCallbacks(runnableStoping);
					new AsyncTask<Void, Void, Void>(){

						@Override
						protected Void doInBackground(Void... params) {
							// TODO Auto-generated method stub
							return null;
						}
						protected void onPostExecute(Void result) {
							button.setText("Stop");
						}
					}.execute();
					
					handler = new Handler();
					runnable = new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							//if(!isStopped)
							{
								if(runHandler)
								{
									handler.postDelayed(runnable, 5000);
									runHandler = false;
								}
								else{
									if(stopingBool)
									{
										button.setText("Stopping");
										handler.postDelayed(runnable, 2000);
										stopingBool = false;
									}
									else{
										button.setText("Start");
									}
								}
							}
							
							
						}
					};
					runnable.run();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
}
