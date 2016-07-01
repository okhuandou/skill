package org.util.demo.comparator;

import java.util.Comparator;

/**
 * 用户创建时间比较器
 * 
 * @author lk
 *
 */
public class DateComparator implements Comparator<UserForComparator> {

	//o1大 则为大于1
	public int compare(UserForComparator o1, UserForComparator o2) {
		if (o1.getCreateTime().after(o2.getCreateTime()))
			return -1;
		
		return 1;
	}

}
