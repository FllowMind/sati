package com.jyu.sati.common.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jyu.sati.entity.Menu;

/**
 * 
 * 菜单工具类
 * 
 * @author 淋雨又调皮
 *
 */
public class MenuUtil {

	/**
	 * 菜单顺序排序
	 * 
	 * @param menus
	 * @return
	 */
	public static List<Menu> menuSort(List<Menu> menus) {

		Collections.sort(menus, new Comparator<Menu>() {

			@Override
			public int compare(Menu m1, Menu m2) {
				// TODO Auto-generated method stub
				if (m1.getMenuOrder() > m2.getMenuOrder()) {
					return 1;
				}
				if (m1.getMenuOrder() == m2.getMenuOrder()) {
					return 0;
				}
				return -1;
			}
		});

		return menus;
	}
}
