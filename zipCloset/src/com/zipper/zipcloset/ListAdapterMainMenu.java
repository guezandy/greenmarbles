package com.zipper.zipcloset;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

public class ListAdapterMainMenu extends BaseExpandableListAdapter {

  private final SparseArray<Group> groups;
  public LayoutInflater inflater;
  public Activity activity;

  public ListAdapterMainMenu(Activity act, SparseArray<Group> groups) {
    activity = act;
    this.groups = groups;
    inflater = act.getLayoutInflater();
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return groups.get(groupPosition).children.get(childPosition);
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return 0;
  }

  @Override
  public View getChildView(int groupPosition, final int childPosition,
      boolean isLastChild, View convertView, ViewGroup parent) {
    final String children = (String) getChild(groupPosition, childPosition);
    TextView text = null;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.detailsformainmenu, null);
    }
    text = (TextView) convertView.findViewById(R.id.secondLine);
    text.setText(children);
    convertView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(activity, children,
            Toast.LENGTH_SHORT).show();
      }
    });
    return convertView;
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    return groups.get(groupPosition).children.size();
  }

  @Override
  public Object getGroup(int groupPosition) {
    return groups.get(groupPosition);
  }

  @Override
  public int getGroupCount() {
    return groups.size();
  }

  @Override
  public void onGroupCollapsed(int groupPosition) {
    super.onGroupCollapsed(groupPosition);
  }

  @Override
  public void onGroupExpanded(int groupPosition) {
    super.onGroupExpanded(groupPosition);
  }

  @Override
  public long getGroupId(int groupPosition) {
    return 0;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded,
      View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.listrow_group, null);
    }
    Group group = (Group) getGroup(groupPosition);
    Context c = MyApplication.getAppContext();
    ((CheckedTextView) convertView).setChecked(isExpanded);
    
    if(groupPosition == 0){
        ((CheckedTextView) convertView).setBackground(c.getResources().getDrawable(R.drawable.recent_banner));
        ((CheckedTextView) convertView).setText(" ");
    }
    else if(groupPosition == 1){
        ((CheckedTextView) convertView).setBackground(c.getResources().getDrawable(R.drawable.vince_camuto));
        ((CheckedTextView) convertView).setText(" ");
    }
    else if (groupPosition==2){
        ((CheckedTextView) convertView).setBackground(c.getResources().getDrawable(R.drawable.theory_banner));
        ((CheckedTextView) convertView).setText(" ");
    }
    else if (groupPosition==3){
        ((CheckedTextView) convertView).setBackground(c.getResources().getDrawable(R.drawable.lulu_lemon));
        ((CheckedTextView) convertView).setText(" ");
    }
    else {
        ((CheckedTextView) convertView).setBackground(c.getResources().getDrawable(R.drawable.no_banner));
        ((CheckedTextView) convertView).setText(group.string);
    }
    return convertView;
  }


  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return false;
  }
} 