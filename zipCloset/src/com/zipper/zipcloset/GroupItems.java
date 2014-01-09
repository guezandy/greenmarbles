package com.zipper.zipcloset;

import java.util.ArrayList;
import java.util.List;

public class GroupItems {

	public String string;
	public final List<xEntity> children = new ArrayList<xEntity>();

	public GroupItems(String string) {
		this.string = string;
	}

} 