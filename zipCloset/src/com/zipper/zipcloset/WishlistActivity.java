package com.zipper.zipcloset;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.zipper.zipcloset.InternalData;
import com.actionbarsherlock.view.Menu;
//http://slimages.macys.com/is/image/MCY/products/8/optimized/1242258_fpx.tif
public class WishlistActivity extends BaseSherlockeFragmentActivity {
	private final String TAG = "WishList Activity";
	private final int LIST_VIEW_ID = R.id.listView4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        SharedPreferences prefs = this.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
       // InternalData.addItem(prefs, CART_KEY, "Lacoste~$89.5~http://slimages.macys.com/is/image/MCY/products/8/optimized/1242258_fpx.tif|");
        refreshCartList(LIST_VIEW_ID, TAG, CART_KEY, prefs);
        
	}

	@Override 
	public boolean onCreateOptionsMenu(Menu menu){
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.wishlist,  (com.actionbarsherlock.view.Menu) menu);
		return super.onCreateOptionsMenu(menu);
	}


		// Adds or deletes toggled item from toDelete
		public void toggleCheck(View v){
			ListView lv1 = (ListView) findViewById(LIST_VIEW_ID);
			Log.i(TAG,"toggleCheck, found listview");
			Object o = lv1.getItemAtPosition((Integer) v.getTag());
			Log.i(TAG,"toggleCheck, got item");
			Entity entity = (Entity) o;
			Log.i(TAG,entity.toString());
			// initialize SharedPreferences
			SharedPreferences prefs = this.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
			String entityString = entity.toString();
			
			// get current toDelete
			String toDelete = prefs.getString(TO_DELETE_KEY,"");
			if(!toDelete.contains(entityString)){
				toDelete += entity.toString();
			}else{
				toDelete = toDelete.replaceFirst(entityString, "");
			}
			
			//Adds new custom Entitys to Entitys string
			Editor editor = prefs.edit();
			editor.putString(TO_DELETE_KEY, toDelete);
			editor.commit();
			Log.i(TAG,"toggleCheck, end");
			}
		
		// deletes all Entitys in toDelete from personalEntitys
		public void deleteSelectedEntities(View v){
			Log.i(TAG,"deleteSelectedEntities");
			// initialize SharedPreferences
			SharedPreferences prefs = this.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
			String toDelete = prefs.getString(TO_DELETE_KEY, "");
			if (!toDelete.isEmpty()){
			InternalData.removeToDeleteEntities(prefs);
			//updates custom Entitys ListView
			
			refreshCartList(LIST_VIEW_ID, TAG, CART_KEY, prefs);
			}
			}
}
