package org.lk.skill.dao;

import java.util.Date;
import java.util.List;

import org.lk.skill.entiy.Seckill;

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
