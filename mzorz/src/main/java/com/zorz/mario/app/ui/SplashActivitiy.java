package com.zorz.mario.app.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.utils.Utils;
import com.utils.database.DataBaseManager;
import com.zorz.mario.R;

public class SplashActivitiy extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_splash);

		new LoadingTask().execute();
	}

	class LoadingTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				if (Utils.getSharedPreferences(SplashActivitiy.this).getAll().size() == 0) {
 					DataBaseManager.initializeDB(SplashActivitiy.this);
					Utils.getPreferencesEditor(SplashActivitiy.this).putBoolean("firstStart", true).commit();
				} else {
					/*DataBaseManager.checkUpdatesForSpinnerTables(SplashActivitiy.this);
					DataBaseManager.migratefromV1toV2();*/
				}
				
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
            startActivity(new Intent(SplashActivitiy.this, CoverLetterActivity.class));
			finish();
			
		}

	}

}
