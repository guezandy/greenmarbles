package com.zipper.zipcloset;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kinvey.android.Client;
import com.zipper.zipcloset.KinveyActivity;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainMenu extends FragmentActivity {
	protected Client kinveyClient;
	public static final String appKey = "kid_PVAtuuzi2f";
	public static final String appSecret = "2cab4a07424945e981478fcfc02341af";
	public static Context appContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		appContext = getApplicationContext();
		ActionBar actionbar = getActionBar();
		
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.Tab HomeTab = actionbar.newTab().setText("Home");
		ActionBar.Tab TagHistoryTab = actionbar.newTab().setText("Tags");
		ActionBar.Tab WishListTab = actionbar.newTab().setText("Wish");
		ActionBar.Tab ClosetTab = actionbar.newTab().setText("Purchase");
		
		Fragment HomeFragment = new MenuFragmentHome();
		Fragment TagHistoryFragment = new MenuFragmentTagHistory();
		Fragment WishListFragment = new MenuFragmentWishList();
		Fragment ClosetFragment = new MenuFragmentCloset();
		
		HomeTab.setTabListener(new MyTabsListener(HomeFragment));
		TagHistoryTab.setTabListener(new MyTabsListener(TagHistoryFragment));
		WishListTab.setTabListener(new MyTabsListener(WishListFragment));
		ClosetTab.setTabListener(new MyTabsListener(ClosetFragment));
		
		actionbar.addTab(HomeTab);
		actionbar.addTab(TagHistoryTab);
		actionbar.addTab(WishListTab);
		actionbar.addTab(ClosetTab);
		
		kinveyClient = new Client.Builder(appKey, appSecret
			    , this.getApplicationContext()).build();
		/*
		Button goToCloset = (Button) findViewById(R.id.goToClosetButton);
		goToCloset.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainMenu.this, GoToClosetActivity.class));
			}
		});
		*/
		Button zipButton = (Button) findViewById(R.id.zipButtonAct);
		zipButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent goToZip = new Intent(MainMenu.this, ZipActivity.class);
				goToZip.putExtra("nfcId", "n");
				startActivity(goToZip);
			}
		});
		
		/*
		Button tagHistoryButton = (Button) findViewById(R.id.zipButton);
		tagHistoryButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainMenu.this, TagsHistoryActivity.class));
			}
		});
		/*
SUPEEEERRR IMPORTANT LOGOUT BUTTON ON ACTION BAR!!!
		Button logout = (Button) findViewById(R.id.logout);
		logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				kinveyClient.user().logout().execute();
				startActivity(new Intent(MainMenu.this, MainActivity.class));
			}
		});
		*/
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	   
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch(item.getItemId()) {
				case R.id.menuitem_search:
					Toast.makeText(appContext, "search", Toast.LENGTH_SHORT).show();
					return true;
				case R.id.menuitem_add:
					Toast.makeText(appContext, "add", Toast.LENGTH_SHORT).show();
					return true;
				case R.id.menuitem_share:
					Toast.makeText(appContext, "share", Toast.LENGTH_SHORT).show();
					return true;
				case R.id.menuitem_feedback:
					Toast.makeText(appContext, "feedback", Toast.LENGTH_SHORT).show();
					return true;
				case R.id.menuitem_about:
					Toast.makeText(appContext, "about", Toast.LENGTH_SHORT).show();
					return true;
				case R.id.menuitem_quit:
					Toast.makeText(appContext, "quit", Toast.LENGTH_SHORT).show();
					return true;
			}
			return false;
		}

	    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@Override
	    protected void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
	    }
}

class MyTabsListener implements ActionBar.TabListener {
	public Fragment fragment;

	public MyTabsListener(Fragment fragment) {
		this.fragment = fragment;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		Toast.makeText(MainMenu.appContext, "Reselected!", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(R.id.fragment_container, fragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(fragment);
	}
}


