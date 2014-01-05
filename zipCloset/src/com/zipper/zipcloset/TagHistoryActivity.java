package com.zipper.zipcloset;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.actionbarsherlock.view.Menu;
import com.kinvey.android.Client;

public class TagHistoryActivity extends BaseSherlockeFragmentActivity {

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tag_history);
//	}
//	protected Client kinveyClient;
//	public static final String appKey = "kid_PVAtuuzi2f";
//	public static final String appSecret = "2cab4a07424945e981478fcfc02341af";
	
	  SparseArray<Group> groups = new SparseArray<Group>();
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_tag_history);
	    createData();
	    ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
	    if (listView == null) { Log.w("", "TextView is null"); }
	    MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
	        groups);
	    listView.setAdapter(adapter);
//		kinveyClient = new Client.Builder(appKey, appSecret
//			    , this.getApplicationContext()).build();
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
	@Override 
	public boolean onCreateOptionsMenu(Menu menu){
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.tag_history,  (com.actionbarsherlock.view.Menu) menu);
		return super.onCreateOptionsMenu(menu);
	}

}
