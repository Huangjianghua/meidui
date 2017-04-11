package com.meiduimall.application.search.manage.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.meiduimall.application.search.manage.domain.Members;

/**
 * 根据会员的mem_id，和会员的parent_id，获取会员的所有父节点和所有子节点
 */
public class ParentValueUtils {
	
	/**
	 * 获取会员的直接子节点
	 * @param map
	 * @return
	 */
	public static Map<String, String> getSonGroup(Map<String, String> map) {
		Map<String, String> temp = new HashMap<String, String>();
		Set<String> keySet = map.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			String value = map.get(key);
			if(value != null && !value.equals("")){
				if(temp.get(value) == null || temp.get(value) == ""){
					temp.put(value, key);
				}else {
					temp.put(value, temp.get(value) + "," + key);
				}
			}
		}
		return temp;
	}

	/**
	 * 返回mem_parent_value1 的Map,其中键是mem_id,值是mem_parent_value1
	 * 
	 * @param map
	 *            原始数据Map(Map的键是mem_id,值是mem_parent_id)
	 * @return 返回mem_parent_value1 的Map
	 */
	public static Map<String, String> getParentGroupValue(
			Map<String, String> map) {
		Map<String, String> container1 = new HashMap<String, String>();
		getContainer1(map, container1);
		return container1;
	}

	/**
	 * 返回mem_parent_value2 的Map,其中键是mem_id,值是mem_parent_value2
	 * 
	 * @param map
	 *            原始数据Map(Map的键是mem_id,值是mem_parent_id)
	 * @return 返回mem_parent_value2的Map
	 */
	public static Map<String, String> getChildGroupValue(Map<String, String> map) {
		Map<String, String> container2 = new HashMap<String, String>();
		getContainer2(map, container2);
		return container2;
	}
	
	/**
	 * 根据注册会员信息，和数据库中所有的会员信息，返回需要修改的会员集合
	 * @param allMembers 所有的会员信息
	 * @param registMem 注册的会员信息
	 * @return 需要修改的会员的集合
	 */
	public static List<Members> getUpdateMembers(List<Members> allMembers, Members registMem){
		List<Members> list = new ArrayList<Members>();
		list.add(registMem);
		Map<String, Members> mapAll = new HashMap<String, Members>();
		Map<String, String> mapMsg = new HashMap<String, String>();
		mapMsg.put(registMem.getMemId(), registMem.getMemParentId());
		for (Members members : allMembers) {
			mapAll.put(members.getMemId(), members);
		}
		Members parentMem = mapAll.get(registMem.getMemParentId());
		if(parentMem != null){
			String parentValue2 = parentMem.getMemParentValue2();
			if(parentValue2 != null && !parentValue2.equals("")){
				String[] split = parentValue2.split(",");
			}
		}
		
		return null;
	}
	
	/**
	 * 根据mem_id的值，以及mem_id所有父节点的对应关系，获取mem_id的所有父节点
	 * @param map mem_id所有节点的对应关系Map
	 * @param regist_mem_id 
	 * @return mem_id的所有父节点字符串
	 */
	public static String getParentsByMemId(Map<String, String> map, String regist_mem_id){
		String value = map.get(regist_mem_id);
		StringBuffer sb = new StringBuffer();
		return getParentValues(map, value, sb);
	}

	/**
	 * 获取集合1
	 * 
	 * @param map
	 * @param container
	 */
	private static void getContainer1(Map<String, String> map,
			Map<String, String> container) {
		Set<String> keySet = map.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			String value = map.get(key);
			StringBuffer sb = new StringBuffer();
			value = getParentValues(map, value, sb);
			if(value != null && value.endsWith(",")){
				value = value.substring(0, value.length()-1);
			}
			container.put(key, value);
		}
	}

	/**
	 * 利用递归，获取集合1的value
	 * 
	 * @param map
	 * @param value
	 * @param sb
	 * @return
	 */
	private static String getParentValues(Map<String, String> map,
			String value, StringBuffer sb) {
		if (value != null) {
			sb.append(value + ",");
			String key = map.get(value);
			getParentValues(map, key, sb);
			return sb.substring(0, sb.length() - 1);
		}
		return null;
	}

	/**
	 * 获取集合2
	 * 
	 * @param map
	 * @param container
	 */
	private static void getContainer2(Map<String, String> map,
			Map<String, String> container) {
		Map<String, String> temp = new HashMap<String, String>();
		Set<String> keySet = map.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			String value = map.get(key);
			if (value != null) {
				if (temp.get(value) == null) {
					temp.put(value, key);
				} else {
					temp.put(value, temp.get(value) + "," + key);
				}
			}
		}

		// ========== 遍历临时Map,获取所有子节点 ===============
		Set<String> temp_keySet = temp.keySet();
		Iterator<String> iterator = temp_keySet.iterator();
		while (iterator.hasNext()) {
			StringBuffer sb = new StringBuffer();
			String temp_key = iterator.next();
			String temp_value = temp.get(temp_key);
			String values = getTempValues(temp, temp_value, sb, 0);
			container.put(temp_key, values);
		}
	}

	/**
	 * 利用递归，获取集合2的value
	 * 
	 * @param temp
	 * @param temp_value
	 * @param sb
	 * @param level
	 * @return
	 */
	private static String getTempValues(Map<String, String> temp,
			String temp_value, StringBuffer sb, int level) {
		if (temp_value != null) {
			level++;
			String[] split = temp_value.split(",");
			for (String s : split) {
				sb.append(s + ",");
				String value = temp.get(s);
				getTempValues(temp, value, sb, level);
			}
			level = 0;
			return sb.substring(0, sb.length() - 1);
		}
		return null;
	}
	
	/**
	 * 迭代遍历打印Map集合
	 * 
	 * @param map
	 */
	public static void printMap(Map<String, String> map) {
		Set<String> keySet = map.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
		}
	}

	/**
	 * 计算每一个会员所在的层级
	 * @param map memId和memParentValue1组成的map
	 * @return memId和level层级的map
	 */
	public static Map<String, String> getGroupLevel(Map<String, String> map) {
		Map<String, String> temp = new HashMap<String, String>();
		Set<String> keySet = map.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			String value = map.get(key);
			int level = 1;
			if(!StringUtil.isEmptyByString(value)){
				if(value.trim().endsWith(",")){
					value = value.substring(0, value.length()-1);
				}
				String[] split = value.split(",");
				level += split.length;
			}
			temp.put(key, level + "");
		}
		return temp;
	}
}
