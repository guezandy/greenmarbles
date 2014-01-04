package com.zipper.zipcloset;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kinvey.android.Client;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class TagsHistoryActivity extends Fragment { 
	protected Client kinveyClient;
	public ListView lv1;
	private static final String KINVEY_KEY = "kid_PVAtuuzi2f";
	private static final String KINVEY_SECRET_KEY = "2cab4a07424945e981478fcfc02341af";
	public static final String KINVEY_ENTITY_COLLECTION_KEY ="EntityCollection";
	public static final int KINVEY_TAGS_HISTORY_CASE = 2;
	public static final int KINVEY_LISTVIEW_CASE = 0;
	
	
	@Override 
	public void onCreate(Bundle savedBundleInstance) {
		super.onCreate(savedBundleInstance);
	//	getActivity().findViewById(R.id.tv1).setVisibility(View.INVISIBLE);
		getActivity().findViewById(R.id.menulist).setVisibility(View.VISIBLE);
	}
	
	@Override 
	public void onPause() {
		super.onPause();
	//	lv1.setVisibility(View.INVISIBLE);
	}
	
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.frag_tags, container, false);
       
       String viewList[] = {"Pete", "John", "Sam"};
	   ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), 
	            android.R.layout.simple_list_item_1,  viewList);
	   
       ListView lv = ((ListView) getActivity().findViewById(R.id.menulist));
       //lv.setAdapter(adapter);
		kinveyClient = new Client.Builder(KINVEY_KEY, KINVEY_SECRET_KEY,
				getActivity().getApplicationContext()).build();

       getCollection(KINVEY_ENTITY_COLLECTION_KEY, KINVEY_TAGS_HISTORY_CASE, lv ,kinveyClient);
       
       return view;
    }
    public void getCollection(String collection, final int methodIndex, final ListView lv, Client kinveyClient) {
    	final String TAG = "Query";
    	
    	final Context context = getActivity().getApplicationContext();
    	/*
    	Query myQuery = kinveyClient.query();
    	//myQuery.equals("Name","Launch Party");
    	AsyncAppData<Entity> myEvents = kinveyClient.appData(collection, Entity.class);
    	myEvents.get(myQuery, new KinveyListCallback<Entity>() {
            @Override
            public void onSuccess(Entity[] result) {
                if (!(result == null)){
                	ArrayList<Entity> resultlist = new ArrayList<Entity>(Arrays.asList(result));
                entitiesSwitch( methodIndex,resultlist, lv);
               
                for (Entity entity : result) {
                    Toast.makeText(context,"Entity Retrieved\nTitle: " + entity.getId()
                            + "\nDescription: " + entity.get("Brand"), Toast.LENGTH_LONG).show();
                	}
                }
            }
            @Override
            public void onFailure(Throwable error) {
                Log.e(TAG, "AppData.get all Failure", error);
                Toast.makeText(context, "Get All error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
            
        });
        */
    }
    // Switch statement with cases based on methodCase, and corresponding methods
    public void entitiesSwitch(int methodCase, ArrayList<Entity> entities, ListView lv){
		String tag = "Kinvey, entitiesSwitch";
		String switchCase = null;
		switch (methodCase){
		    case KINVEY_LISTVIEW_CASE :
		    	switchCase = "Update ListView";
		    	updateListView(entities,lv);
		    	break;
		    default:
		    	Log.e(tag,switchCase);
		    	break;
			}
		}
	public void updateListView(final ArrayList<Entity> entities, final ListView lv){
		//ListView lv2 = (ListView) findViewById(R.id.custom_list);
		//System.out.println("first Brand= "+ entities[0].get("Brand"));
		final Context context = getActivity().getApplicationContext();
		//System.out.println("arraysize " +entities.length);
		ArrayList<Entity> arrayResults = new ArrayList<Entity>();
		for(Entity x:entities){
			arrayResults.add(x);
		}
		System.out.println("brand= "+ arrayResults.get(0).get("Brand"));
		
		
		CustomListAdapter clist=  new CustomListAdapter(context, entities);
		//  System.out.println(lv.toString());
		lv.setAdapter(clist);
		//lv.setOnItemClickListener(new OnItemClickListener() {	
//			@Override
//			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//				Object o = lv.getItemAtPosition(position);
//				Entity closetData = (Entity) o;
//				Toast.makeText(context, "Selected :" + " " + closetData,
//						Toast.LENGTH_LONG).show();
//					}
		//});
		
	}
}