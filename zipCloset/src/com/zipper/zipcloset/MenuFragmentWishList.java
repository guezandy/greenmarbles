package com.zipper.zipcloset;

import java.util.ArrayList;
import java.util.Arrays;

import com.actionbarsherlock.view.Menu;
import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MenuFragmentWishList extends BaseSherlockeFragmentActivity {
	protected Client kinveyClient;
	public ListView lv1;
	private static final String KINVEY_KEY = "kid_PVAtuuzi2f";
	private static final String KINVEY_SECRET_KEY = "2cab4a07424945e981478fcfc02341af";
	public static final String KINVEY_ENTITY_COLLECTION_KEY ="Favorites";

	public static final int KINVEY_TAGS_HISTORY_CASE = 2;
	public static final int KINVEY_LISTVIEW_CASE = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		findViewById(R.id.menulist).setVisibility(View.VISIBLE);
		findViewById(R.id.textView1).setVisibility(View.VISIBLE);
		findViewById(R.id.zipButtonAct).setVisibility(View.INVISIBLE);
		//getActivity().findViewById(R.id.img1).setVisibility(View.INVISIBLE);
		kinveyClient = new Client.Builder(KINVEY_KEY, KINVEY_SECRET_KEY,
				getApplicationContext()).build();
		TextView intro = (TextView) findViewById(R.id.textView1);
		//ImageView main = (ImageView) getActivity().findViewById(R.id.img1);
		//main.setVisibility(View.VISIBLE);
		intro.setText("Your personal wish list");
		intro.setVisibility(View.VISIBLE);
		
	}
	
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.frag_wish, container, false);
//        ListView lv1 = ((ListView) getActivity().findViewById(R.id.menulist));
//        lv1.setAdapter(null);
//        
//        getCollection(KINVEY_ENTITY_COLLECTION_KEY, KINVEY_TAGS_HISTORY_CASE, lv1 ,kinveyClient);
//        
//        return view;
//    }
    
    public void getCollection(String collection, final int methodIndex, final ListView lv, Client kinveyClient) {
    	final String TAG = "Query";
    	
    	final Context context = getApplicationContext();
    	
    	Query myQuery = kinveyClient.query();
    	//myQuery.equals("Name","Launch Party");
    	AsyncAppData<Entity> myEvents = kinveyClient.appData(collection, Entity.class);
    	myEvents.get(myQuery, new KinveyListCallback<Entity>() {
            @Override
            public void onSuccess(Entity[] result) {
            	
                if (!(result == null)){
                	ArrayList<Entity> resultlist = new ArrayList<Entity>(Arrays.asList(result));
                //	entitiesSwitch( methodIndex,resultlist, lv1);
  
            		CustomListAdapter clist=  new CustomListAdapter(context, resultlist);
            		//  System.out.pricntln(lv.toString());
            		lv.setAdapter(clist);
            		lv.setOnItemClickListener(new OnItemClickListener() {	
            			@Override
            			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            				Object o = lv.getItemAtPosition(position);
            				Entity closetData = (Entity) o;
            		
            				Intent zip = new Intent(MenuFragmentWishList.this, ZipActivity.class);
            				zip.putExtra("nfcId", closetData.getId());
            				startActivity(zip);
            		
            					}
            			//send to zipactivity and pass the object as an intent
            		});

                }
                
            }
            @Override
            public void onFailure(Throwable error) {
                Log.e(TAG, "AppData.get all Failure", error);
                Toast.makeText(context, "Get All error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
            
        });
        
    }
    
}