package com.zipper.zipcloset;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.view.Menu;

public class PurchasedActivity extends BaseSherlockeFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased);
	}

	@Override 
	public boolean onCreateOptionsMenu(Menu menu){
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.purchased,  (com.actionbarsherlock.view.Menu) menu);
		return super.onCreateOptionsMenu(menu);
	}
}
