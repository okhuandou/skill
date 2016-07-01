package org.lk.skill.dao;

import org.lk.skill.entiy.SuccessKilled;

/**
 * 
 * @author lk
 *
 */
public interface SuccessKillDao {
	
	int insertSuccess(long seckillId,String phone);
	
	SuccessKilled queryByIdwithSeckill(long seckillId);

}
