package com.zipper.zipcloset;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.kinvey.android.Client;

public class GoToClosetActivity extends KinveyActivity {
	private static final String KINVEY_KEY = "kid_PVAtuuzi2f";
	private static final String KINVEY_SECRET_KEY = "2cab4a07424945e981478fcfc02341af";

	protected Client kinveyClient;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("im in the gotocloset");
		setContentView(R.layout.activity_go_to_closet);
		super.onCreate(savedInstanceState);
		ListView lv = (ListView) findViewById(R.id.custom_list);
		System.out.println("created the listview");
		kinveyClient = new Client.Builder(KINVEY_KEY, KINVEY_SECRET_KEY,
				this.getApplicationContext()).build();
		//getCollection(KINVEY_ENTITY_COLLECTION_KEY,KINVEY_LISTVIEW_CASE, lv ,kinveyClient);
	}
	public void closetButtonClick(View v){
		ListView lv = (ListView) findViewById(R.id.custom_list);
		Client kinveyClient = new Client.Builder(KINVEY_KEY, KINVEY_SECRET_KEY, this).build();
		String kinveyCollectionKey = v.getTag().toString();
		//getCollection(kinveyCollectionKey,KINVEY_LISTVIEW_CASE, lv,kinveyClient);
	}

}


