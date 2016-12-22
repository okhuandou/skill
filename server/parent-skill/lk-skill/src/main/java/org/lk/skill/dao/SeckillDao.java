package org.lk.skill.dao;

import org.lk.skill.entiy.Seckill;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author lk
 * 
 *
 */
public interface SeckillDao {

	/**
	 *  减库存
	 * @param seckillId
	 * @param killTime
	 * @return
	 */
	int reduceNumber(long seckillId,Date killTime);
	
	Seckill getById(long seckillId);
	
	List<Seckill> queryAll(int offet,int limit);
}
