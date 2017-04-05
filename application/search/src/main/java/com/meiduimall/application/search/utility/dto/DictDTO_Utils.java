package com.meiduimall.application.search.utility.dto;

import java.util.List;

public class DictDTO_Utils  {
	
	private Dict_Utils  parentDict;
	private List<Dict_Utils> childDicts;
	public Dict_Utils getParentDict() {
		return parentDict;
	}
	public void setParentDict(Dict_Utils parentDict) {
		this.parentDict = parentDict;
	}
	public List<Dict_Utils> getChildDicts() {
		return childDicts;
	}
	public void setChildDicts(List<Dict_Utils> childDicts) {
		this.childDicts = childDicts;
	}

}
