package com.zipper.zipcloset;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.app.Activity;
import com.actionbarsherlock.view.Menu;
import com.viewpagerindicator.TabPageIndicator;
import com.zipper.zipcloset.BaseSherlockeFragmentActivity.GoogleMusicAdapter;

public class PurchasedActivity extends BaseSherlockeFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_tabs);

        FragmentPagerAdapter adapter = new GoogleMusicAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
	}

	@Override 
	public boolean onCreateOptionsMenu(Menu menu){
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.purchased,  (com.actionbarsherlock.view.Menu) menu);
		return super.onCreateOptionsMenu(menu);
	}
}
