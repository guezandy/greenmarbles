package com.zipper.zipcloset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;

public class TagHistoryActivity extends BaseSherlockeFragmentActivity {

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tag_history);
//	}
	protected Client kinveyClient;
	public static final String appKey = "kid_PVAtuuzi2f";
	public static final String appSecret = "2cab4a07424945e981478fcfc02341af";
	public static final int KINVEY_LISTVIEW_CASE = 0;
	public static final int KINVEY_UPDATE_ZIP_ACTIVITY_CASE = 1;
	public static final int KINVEY_TAGS_HISTORY_CASE = 2;
	public static final String KINVEY_ENTITY_COLLECTION_KEY ="EntityCollection";
	public static final String KINVEY_FAVORITES_KEY = "Favorites";
	public static final String KINVEY_TAGS_KEY = "Tags";
	//public ArrayList<Entity> storetags;
	SparseArray<GroupItems> groups = new SparseArray<GroupItems>();
	private Context context;
	//MyExpandableListAdapter adapter;
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    context = this;
	    setContentView(R.layout.activity_tag_history);
		kinveyClient = new Client.Builder(appKey, appSecret, this.getApplicationContext()).build();
		//getCollection2(KINVEY_ENTITY_COLLECTION_KEY, KINVEY_TAGS_HISTORY_CASE, groups, kinveyClient, "Vince Camuto");
	    System.out.println("Size of groups inside oncreate "+this.groups.size());
		ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView2);
	    if (listView == null) { Log.w("", "TextView is null"); }
	    MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
	        groups);
	    listView.setAdapter(adapter);
	   // listView.getItemAtPosition(position)

	    
		 //System.out.println("Store tags: "+storetags.toString());
		 //System.out.println("size of storetags"+ storetags.size());
		 createData();
	  }
	@Override 
	public boolean onCreateOptionsMenu(Menu menu){
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.tag_history,  (com.actionbarsherlock.view.Menu) menu);
		return super.onCreateOptionsMenu(menu);
	}
	xEntity ep1 = new xEntity("1","Vince Camuto","http://i945.photobucket.com/albums/ad297/jake_elliott3/vince1_zpsa2539d38.jpg","189.00","http://www.vincecamuto.com/product/9163904/leopard-zip-front-dress.html","Dress","LEOPARD ZIP FRONT DRESS");
	xEntity ep2 = new xEntity("2","Vince Camuto","http://i945.photobucket.com/albums/ad297/jake_elliott3/vincec1_zps531b346d.jpg","189.00","http://www.vincecamuto.com/product/9163907/perforated-slim-dress.html","Dress","Perforated Slim dress");
	xEntity ep3 = new xEntity("3","Vince Camuto","http://i945.photobucket.com/albums/ad297/jake_elliott3/vince1_zps50399f3a.jpg","250.00","http://www.vincecamuto.com/product/greta/vc-signature-greta.html","Shoes","VC SIGNATURE GRETA");
	xEntity ep4 = new xEntity("4","Vince Camuto","http://i945.photobucket.com/albums/ad297/jake_elliott3/vince2_zpsda96b119.jpg","129.00","http://www.vincecamuto.com/product/ombra/ombra.html","Shoes","OMBRA");
	xEntity ep5 = new xEntity("5","Vince Camuto","http://i945.photobucket.com/albums/ad297/jake_elliott3/vince3_zps21895ea2.jpg","89.00","http://www.vincecamuto.com/product/9199364/tie-waist-crepe-pant.html","Pants","TIE WAIST CREPE PANT");
	xEntity ep6 = new xEntity("6","Vince Camuto","http://i945.photobucket.com/albums/ad297/jake_elliott3/Untitled_zps982a7675.jpg","34.00","http://www.vincecamuto.com/product/9199324/ankle-pant.html","Pants","ANKLE PANT");
	xEntity ep7 = new xEntity("7","Vince Camuto","http://i945.photobucket.com/albums/ad297/jake_elliott3/Untitled1_zpsd9618f3b.jpg","178.00","http://www.vincecamuto.com/product/horncl/horn-clutch.html","HandBag","HORN CLUTCH");
	xEntity ep8 = new xEntity("8","Vince Camuto","http://i945.photobucket.com/albums/ad297/jake_elliott3/heh_zps6d31c52d.jpg","139.00","http://www.vincecamuto.com/product/margocl/margo-clutch.html","HandBag","MARGO CLUTCH");
	xEntity ep9 = new xEntity("9","Vince Camuto","http://i945.photobucket.com/albums/ad297/jake_elliott3/un_zps4c6ed391.jpg","119.99","http://www.vincecamuto.com/product/9163506/faux-fur-side-zip-vest.html","Jacket","Faux Fur Side Zip Vest");
	xEntity ep10 = new xEntity("10","Vince Camuto","http://i945.photobucket.com/albums/ad297/jake_elliott3/gg_zps8ba902e4.jpg","159.00","http://www.vincecamuto.com/product/9163503/ponyhair-topper-coat.html","Jacket","Ponyhair Topper Coat");
	xEntity E1 = new xEntity("11","American Apparel", "http://i945.photobucket.com/albums/ad297/jake_elliott3/pants2_zpsd0965529.jpg", "95.00", "http://store.americanapparel.net/product/?productId=rsaah300p", "Pants","Giraffe Printed Disco Pant");
	xEntity E2 = new xEntity("12", "American Apparel", "http://i945.photobucket.com/albums/ad297/jake_elliott3/pants_zps6a7ca3f7.jpg", "95.00", "http://store.americanapparel.net/product/?productId=rsaah300p", "Pants","Watercolor Cheetah Printed Disco Pant");
	xEntity E3 = new xEntity("13", "American Apparel", "http://i945.photobucket.com/albums/ad297/jake_elliott3/whiteshirt_zpsdc617de9.jpg", "24.00", "http://store.americanapparel.net/product/?productId=rsa2457w", "Shirts","Unisex Fine Jersey/Tab Shirt");
	xEntity E4 = new xEntity("14", "American Apparel", "http://i945.photobucket.com/albums/ad297/jake_elliott3/purpledress_zpsc4949c42.jpg", "26.00", "http://store.americanapparel.net/product/?productId=2471w", "Shirts","Uni-Sex Fine Jersey");
	xEntity E5 = new xEntity("15", "Lulu Lemon", "http://i945.photobucket.com/albums/ad297/jake_elliott3/pants2_zpsfbc92667.jpg", "98.00", "http://shop.lululemon.com/products/clothes-accessories/pants-yoga/Groove-Pant-Slim-Tall?cc=11624&skuId=3514545&catId=pants-yoga", "Pants","Groove Pant");
	xEntity E6 = new xEntity("16", "Lulu Lemon", "http://i945.photobucket.com/albums/ad297/jake_elliott3/pants_zps9b0bf18a.jpg", "92.00", "http://shop.lululemon.com/products/clothes-accessories/pants-yoga/Wunder-Under-Pant-IPLUX?cc=0001&skuId=3516205&catId=pants-yoga", "Pants","Wunder Under");
	xEntity E7 = new xEntity("17", "Lulu Lemon", "http://i945.photobucket.com/albums/ad297/jake_elliott3/top2_zps9ef4fe8d.jpg", "128.00", "http://shop.lululemon.com/products/clothes-accessories/tops-long-sleeve/Fluff-Off-Crew?cc=11199&skuId=3528635&catId=tops-long-sleeve", "Shirts","Fluff Off Crew");
	xEntity E8 = new xEntity("18", "Lulu Lemon", "http://i945.photobucket.com/albums/ad297/jake_elliott3/top1_zps3bfcc005.jpg", "68.00", "http://shop.lululemon.com/products/clothes-accessories/tops-long-sleeve/Flip-Your-Dog-Reversible-Long-Sleeve?cc=12325&skuId=3528376&catId=tops-long-sleeve", "Shirts","Flip Your Dog Long Sleeve");
	xEntity E9 = new xEntity("19", "Lulu Lemon", "http://i945.photobucket.com/albums/ad297/jake_elliott3/greenlulu_zps79f81708.jpg", "64.00", "http://shop.lululemon.com/products/clothes-accessories/mens-shorts-run/Surge-Short-III?cc=10302&skuId=3527451&catId=mens-shorts-run", "Shorts","Surge Short III");
	xEntity E10 = new xEntity("20", "Lulu Lemon", "http://i945.photobucket.com/albums/ad297/jake_elliott3/lulu_zpsbee455b3.jpg", "58.00", "http://shop.lululemon.com/products/clothes-accessories/mens-shorts-gym/Core-Short-32404?cc=12537&skuId=3528987&catId=mens-shorts-gym", "Shorts","Core Short");
	xEntity E11 = new xEntity("21", "Theory", "http://i945.photobucket.com/albums/ad297/jake_elliott3/jacket_zps1cffc3e0.jpg", "997.50", "http://www.theory.com/magnus-ql-oblique-coat/D0970418,default,pd.html?dwvar_D0970418_color=001&start=4&cgid=mens-jackets-outerwear", "Jacket","Magnus QL Coat");
	xEntity E12 = new xEntity("22", "Theory", "http://i945.photobucket.com/albums/ad297/jake_elliott3/hello_zps7d98b833.jpg", "347.50", "http://www.theory.com/magnus-qc-kingsburg-coat/D0977413,default,pd.html?dwvar_D0977413_color=RLL&start=3&cgid=mens-jackets-outerwear", "Jacket","Magnus QC Coat");
	xEntity E13 = new xEntity("23", "Theory", "http://i945.photobucket.com/albums/ad297/jake_elliott3/bluehanu_zps170172c0.jpg", "195.00", "http://www.theory.com/stephan-scrath-print-shirt/D0974521,default,pd.html?dwvar_D0974521_color=RLU&start=6&cgid=mens-shirts", "Shirts","Stephan Shirtn");
	xEntity E14 = new xEntity("24", "Theory", "http://i945.photobucket.com/albums/ad297/jake_elliott3/hehhh_zpsef886dd8.jpg", "195.00", "http://www.theory.com/sylvain-listow-shirt/D1074524,default,pd.html?dwvar_D1074524_color=RLT&start=2&cgid=mens-shirts", "Shirts","Sylvain Shirt");
	xEntity E15 = new xEntity("25", "Theory", "http://i945.photobucket.com/albums/ad297/jake_elliott3/red1_zps2ec70fe6.jpg", "272.50", "http://www.theory.com/Rodolf-Frestin-Wool-Blend-Sportcoat/D0671106,default,pd.html?dwvar_D0671106_color=A08&start=16&cgid=mens-sportcoats-jackets", "Jackets","Rodolf CF Sportcoat");
	xEntity E16 = new xEntity("26", "Theory", "http://i945.photobucket.com/albums/ad297/jake_elliott3/untitles_zpsb44f672b.jpg", "322.50", "http://www.theory.com/Wellar-Lorient-Wool-Jacket/D0671112,default,pd.html?dwvar_D0671112_color=A14&start=15&cgid=mens-sportcoats-jackets", "Jackets","Wellar Suit Jacket");
	xEntity E17 = new xEntity("27", "Theory", "http://i945.photobucket.com/albums/ad297/jake_elliott3/theorypants_zps91cdec4b.jpg", "102.50", "http://www.theory.com/haydin-enz-activist-pant/D0974235,default,pd.html?dwvar_D0974235_color=RLG&start=13&cgid=mens-pants-shorts", "Pants","Haydin ENZ Pant");
	xEntity E18 = new xEntity("28", "Theory", "http://i945.photobucket.com/albums/ad297/jake_elliott3/123_zpsf1e9fdd3.jpg", "245.00", "http://www.theory.com/Marlo-Lorient-Wool-Pant/D0671214,default,pd.html?dwvar_D0671214_color=A14&start=8&cgid=mens-pants-shorts", "Pants","Marlo U Suit Pant");

	
	public ArrayList<xEntity> recentlist = new ArrayList<xEntity>();
	public ArrayList<xEntity> Vincelist = new ArrayList<xEntity>();
	public ArrayList<xEntity> Theorylist = new ArrayList<xEntity>();
	public ArrayList<xEntity> AmericanApparel = new ArrayList<xEntity>();
	public ArrayList<xEntity> Lululist = new ArrayList<xEntity>();
	public String[] grouplist4 =  {"Recent", "Vince Camuto", "Theory", "Lulu Lemon","American Apparel"};
	
	
	public void populatelists(){
		
		recentlist.add(ep1);
		recentlist.add(E5);
		recentlist.add(E10);
		recentlist.add(E1);
		recentlist.add(ep2);
		recentlist.add(E13);
		
		
		Lululist.add(E5);
		Lululist.add(E6);
		Lululist.add(E7);
		Lululist.add(E8);
		Lululist.add(E9);
		Lululist.add(E10);
		
		AmericanApparel.add(E1);
		AmericanApparel.add(E2);
		AmericanApparel.add(E3);
		AmericanApparel.add(E4);
		
		Vincelist.add(ep1);
		Vincelist.add(ep2);
		//Vincelist.add(ep3);
		Vincelist.add(ep4);
		//Vincelist.add(ep5);
		Vincelist.add(ep6);
		Vincelist.add(ep7);
		//Vincelist.add(ep8);
		Vincelist.add(ep9);
		Vincelist.add(ep10);
		
		Theorylist.add(E11);
		Theorylist.add(E12);
		Theorylist.add(E13);
		Theorylist.add(E14);
		Theorylist.add(E15);
		Theorylist.add(E16);
		Theorylist.add(E17);
		Theorylist.add(E18);
		
	}
	
	  public void createData() {
		populatelists();
	    for (int j = 0; j < grouplist4.length; j++) {
	    	ArrayList<xEntity> nowlist = new ArrayList<xEntity>(); 
	      GroupItems group = new GroupItems(grouplist4[j]);
	      if(j==0){
	    	  nowlist = recentlist;
	      }
	      else if (j==1){
	    	  nowlist = Vincelist;
	      }
	      else if (j==2){
	    	  nowlist = Theorylist;
	      }
	      else if (j==3){
	    	  nowlist = Lululist;
	      }
	      else if (j==4){
	    	  nowlist = AmericanApparel;
	      }
	      
	      for (int i = 0; i < nowlist.size(); i++) {
	        group.children.add(nowlist.get(i));
	      }
	      groups.append(j, group);
	    }
	  }
}
	
