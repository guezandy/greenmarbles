package com.zipper.zipcloset;

import android.os.Bundle;
import android.app.Activity;
import com.actionbarsherlock.view.Menu;

public class TagHistoryActivity extends BaseSherlockeFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_history);
	}

	@Override 
	public boolean onCreateOptionsMenu(Menu menu){
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.tags_history,  (com.actionbarsherlock.view.Menu) menu);
		return super.onCreateOptionsMenu(menu);
	}

}
