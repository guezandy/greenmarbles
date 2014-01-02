package com.zipper.zipcloset;

import android.content.Intent;
import android.util.Log;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.sample.TestFragment;

public class BaseSherlockeFragmentActivity extends SherlockFragmentActivity
{
	private final String TAG = "";  
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		
			case R.id.action_settings:
				Log.i(TAG,"Settings item selected");
				//startActivity(new Intent(this, SettingsFragmentActivity.class));
				return true;
				
			case android.R.id.home:
			case R.id.gotohome:
				Log.i(TAG,"Home Activity item selected");
				startActivity(new Intent(this, MainMenu.class));
				return true;
				
			case R.id.gotowishlist:
				Log.i(TAG,"Wishlist Activity item selected");
				startActivity(new Intent(this, WishlistActivity.class));
				return true;
				
			case R.id.gototaghistory:
				Log.i(TAG,"Tag History Activity item selected");
				startActivity(new Intent(this, TagHistoryActivity.class));
				return true;
				
			case R.id.gotopurchased:
				Log.i(TAG,"Purchased Activity item selected");
				startActivity(new Intent(this, PurchasedActivity.class));
				return true;	
				
			case R.id.gotologin:
				Log.i(TAG,"Login Activity item selected");
				startActivity(new Intent(this, MainActivity.class));
				return true;
				
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}


	    private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums", "Songs", "Playlists", "Genres" };

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

	    class GoogleMusicAdapter extends FragmentPagerAdapter {
	        public GoogleMusicAdapter(FragmentManager fm) {
	            super(fm);
	        }

	        @Override
	        public Fragment getItem(int position) {
	            return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
	        }

	        @Override
	        public CharSequence getPageTitle(int position) {
	            return CONTENT[position % CONTENT.length].toUpperCase();
	        }

	        @Override
	        public int getCount() {
	            return CONTENT.length;
	        }
	    }

}
