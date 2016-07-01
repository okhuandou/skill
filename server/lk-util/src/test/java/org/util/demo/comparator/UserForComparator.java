package org.util.demo.comparator;

import java.util.Date;

/**
 *  用于 比较器 测试
 * @author lk
 *
 */
public class UserForComparator {

	private int id;
	private int age;
	private String name;
	private Date createTime;
	
	public UserForComparator(int id, Date createTime) {
		super();
		this.id = id;
		this.createTime = createTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
