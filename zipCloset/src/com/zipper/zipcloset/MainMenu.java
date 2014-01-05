package com.zipper.zipcloset;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.kinvey.android.Client;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainMenu extends BaseSherlockeFragmentActivity {
	protected Client kinveyClient;
	public static final String appKey = "kid_PVAtuuzi2f";
	public static final String appSecret = "2cab4a07424945e981478fcfc02341af";
	public static Context appContext;
	
	SparseArray<Group> groups = new SparseArray<Group>();
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main_menu);
		appContext = getApplicationContext();
		kinveyClient = new Client.Builder(appKey, appSecret
			    , this.getApplicationContext()).build();
	    createData();
	    ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
	    if (listView == null) { Log.w("", "TextView is null"); }
	    ListAdapterMainMenu adapter = new ListAdapterMainMenu(this,
	        groups);
	    listView.setAdapter(adapter);
	    
		Button zipButton = (Button) findViewById(R.id.zipButtonAct);
		zipButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent goToZip = new Intent(MainMenu.this, ZipActivity.class);
				goToZip.putExtra("nfcId", "n");
				startActivity(goToZip);
			}
		});

	  }


		public String[] grouplist4 =  {"Recent", "Vince Camuto", "Theory", "Lulu Lemon"};
		
	  public void createData() {
	    for (int j = 0; j < 4; j++) {
	      Group group = new Group(grouplist4[j]);
	      for (int i = 0; i < 5; i++) {
	        group.children.add("Sub Item" + i);
	      }
	      groups.append(j, group);
	    }
	  }
	  
	  

	
	
	
	
/*	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		appContext = getApplicationContext();

		
		kinveyClient = new Client.Builder(appKey, appSecret
			    , this.getApplicationContext()).build();*/
		/*
		Button goToCloset = (Button) findViewById(R.id.goToClosetButton);
		goToCloset.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainMenu.this, GoToClosetActivity.class));
			}
		});
		*/
//		Button zipButton = (Button) findViewById(R.id.zipButtonAct);
//		zipButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Intent goToZip = new Intent(MainMenu.this, ZipActivity.class);
//				goToZip.putExtra("nfcId", "n");
//				startActivity(goToZip);
//			}
//		});
		
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
	//}
	
	@Override 
	public boolean onCreateOptionsMenu(Menu menu){
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main,  (com.actionbarsherlock.view.Menu) menu);
		return super.onCreateOptionsMenu(menu);
	}

		
	    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@Override
	    protected void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
	    }
}

//class MyTabsListener implements ActionBar.TabListener {
//	public Fragment fragment;
//
//	public MyTabsListener(Fragment fragment) {
//		this.fragment = fragment;
//	}
//
//	@Override
//	public void onTabReselected(Tab tab, FragmentTransaction ft) {
//		Toast.makeText(MainMenu.appContext, "Reselected!", Toast.LENGTH_LONG).show();
//	}
//
//	@Override
//	public void onTabSelected(Tab tab, FragmentTransaction ft) {
//		ft.replace(R.id.fragment_container, fragment);
//	}
//
//	@Override
//	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
//		ft.remove(fragment);
//	}
//}


