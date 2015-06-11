package com.tmount.business.product.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tmount.db.product.vo.GdItemstypeDicInfo;

public class ItemsTypeTreeData {
	
	private Map<Integer, GdItemstypeDicInfo> itemsTypeMap;

	public ItemsTypeTreeData(List<GdItemstypeDicInfo> gdItemstypeDicInfoList) {
		itemsTypeMap = new LinkedHashMap<Integer, GdItemstypeDicInfo>(13);
		try {
			load(gdItemstypeDicInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<Integer, GdItemstypeDicInfo> getFunctionMap() {
		return itemsTypeMap;
	}

	public void load(List<GdItemstypeDicInfo> queryList) throws SQLException {

		Map<Integer, GdItemstypeDicInfo> itemsTypeMapTmp = new LinkedHashMap<Integer, GdItemstypeDicInfo>(13);
		
		for (GdItemstypeDicInfo itemsType : queryList) {
			itemsTypeMapTmp.put(itemsType.getItemsType(), itemsType);
		}

		Iterator<Integer> iter = itemsTypeMapTmp.keySet().iterator();
		while (iter.hasNext()) {
			GdItemstypeDicInfo itemsType = (GdItemstypeDicInfo) itemsTypeMapTmp.get(iter
					.next());

			if (itemsType.getParentId() == -1)
			{
				itemsTypeMap.put(itemsType.getItemsType(), itemsType);
				continue;
			}
			GdItemstypeDicInfo parentItemsType = itemsTypeMapTmp.get(itemsType.getParentId());
			if (parentItemsType != null) {
				if (parentItemsType.getChildItemsType() == null) {
					List<GdItemstypeDicInfo> childList = new ArrayList<GdItemstypeDicInfo>();
					childList.add(itemsType);
					parentItemsType.setChildItemsType(childList);
	
				} else {
					parentItemsType.getChildItemsType().add(itemsType);
				}
			}
		}
	}
}
