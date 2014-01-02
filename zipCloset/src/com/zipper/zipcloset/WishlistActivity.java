package com.zipper.zipcloset;

import android.os.Bundle;
import android.app.Activity;
import com.actionbarsherlock.view.Menu;

public class WishlistActivity extends BaseSherlockeFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wishlist);
	}

	@Override 
	public boolean onCreateOptionsMenu(Menu menu){
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.wishlist,  (com.actionbarsherlock.view.Menu) menu);
		return super.onCreateOptionsMenu(menu);
	}

}
