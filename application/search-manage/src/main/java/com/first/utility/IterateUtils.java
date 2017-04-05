package com.first.utility;

import java.util.ArrayList;
import java.util.List;

import com.first.utility.dto.DictDTO_Utils;
import com.first.utility.dto.Dict_Utils;

public class IterateUtils {
	
	public static List<DictDTO_Utils> getDictsByPID(String pid,List<Dict_Utils> dicts){
		
		List<DictDTO_Utils> result = new ArrayList<DictDTO_Utils>();
		for(Dict_Utils d:dicts){
			if(d.getDictParentId().equals(pid)){
				DictDTO_Utils dto = new DictDTO_Utils();
				List<Dict_Utils> childList = null;
				dto.setParentDict(d);
				for(Dict_Utils dc:dicts){
					childList = new ArrayList<Dict_Utils>();
					if(dc.getDictParentId().equals(d.getDictId())){
						childList.add(dc);
					}
				}
				dto.setChildDicts(childList);
				result.add(dto);
			}
		}
		return result;
		
	}
	
	
}
