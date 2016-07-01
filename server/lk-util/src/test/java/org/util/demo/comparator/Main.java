package org.util.demo.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class Main {

	@Test
	public void compare() throws InterruptedException{
		List<UserForComparator> list  = new ArrayList<UserForComparator>();
		list.add(new UserForComparator(1, new Date()));
		Thread.sleep(1000l);
		list.add(new UserForComparator(2, new Date()));
		Thread.sleep(1000l);
		list.add(new UserForComparator(3, new Date()));
		Thread.sleep(1000l);
		list.add(new UserForComparator(4, new Date()));
		
		Collections.sort(list,new DateComparator());
		
		for (UserForComparator user : list) {
			System.out.println(user.getId());
		}
	}
}
