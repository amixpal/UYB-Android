package com.coderwall.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button show;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = (Button) findViewById(R.id.button_show);
		show.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(show.getText().toString().equalsIgnoreCase("Show Notification"))
					notifyAndroid(MainActivity.this);
				else
					hideNotification();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void notifyAndroid(Context context)
	{
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(context)
		        .setSmallIcon(R.drawable.emergency)
		        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
		        ;
		mBuilder.setWhen(0);
		NotificationManager mNotificationManager =
		    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(12, mBuilder.build());
		show.setText("Hide Notification");
		
		//setAlarm(context);
	}

	public void hideNotification() {
		// TODO Auto-generated method stub
		NotificationManager mNotificationManager =
			    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancel(12);
		show.setText("Show Notification");
	}
}
