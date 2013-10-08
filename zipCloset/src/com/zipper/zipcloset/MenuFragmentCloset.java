package com.zipper.zipcloset;

import java.util.ArrayList;
import java.util.Arrays;

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
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MenuFragmentCloset extends Fragment {
	protected Client kinveyClient;
	public ListView lv1;
	private static final String KINVEY_KEY = "kid_PVAtuuzi2f";
	private static final String KINVEY_SECRET_KEY = "2cab4a07424945e981478fcfc02341af";
	public static final String KINVEY_ENTITY_COLLECTION_KEY ="Closet"; 
	public static final int KINVEY_TAGS_HISTORY_CASE = 2;
	public static final int KINVEY_LISTVIEW_CASE = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActivity().findViewById(R.id.menulist).setVisibility(View.VISIBLE);
		getActivity().findViewById(R.id.textView1).setVisibility(View.VISIBLE);
		getActivity().findViewById(R.id.zipButtonAct).setVisibility(View.INVISIBLE);
		//getActivity().findViewById(R.id.img1).setVisibility(View.INVISIBLE);
		kinveyClient = new Client.Builder(KINVEY_KEY, KINVEY_SECRET_KEY,
				getActivity().getApplicationContext()).build();
		TextView intro = (TextView) getActivity().findViewById(R.id.textView1);
		//ImageView main = (ImageView) getActivity().findViewById(R.id.img1);
		//main.setVisibility(View.VISIBLE);
		intro.setText("All the items you've purchased!");
		intro.setVisibility(View.VISIBLE);
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frag_tags, container, false);
 	   
        ListView lv1 = ((ListView) getActivity().findViewById(R.id.menulist));
        lv1.setAdapter(null);

        getCollection(KINVEY_ENTITY_COLLECTION_KEY, KINVEY_TAGS_HISTORY_CASE, lv1 ,kinveyClient);
        
        return view;
    }	
    public void getCollection(String collection, final int methodIndex, final ListView lv, Client kinveyClient) {
    	final String TAG = "Query";
    	
    	final Context context = getActivity().getApplicationContext();
    	
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
            			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            				Object o = lv.getItemAtPosition(position);
            				Entity closetData = (Entity) o;

            				Intent zip = new Intent(getActivity(), ZipActivity.class);
            				zip.putExtra("nfcId", closetData.getId());
            				startActivity(zip);
            					}
            		});                	
                /*	
                for (Entity entity : resultlist) {
                    Toast.makeText(context,"Entity Retrieved\nTitle: " + entity.getId()
                            + "\nDescription: " + entity.get("Brand"), Toast.LENGTH_LONG).show();
                	}
                	*/
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