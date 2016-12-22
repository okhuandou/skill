package org.lk.skill.service;

import org.lk.skill.dto.Exposer;
import org.lk.skill.dto.SeckillExcution;
import org.lk.skill.entiy.Seckill;

import java.util.List;

/**
 * 
 * @author morichou
 *
 */
public interface ISkillService {

    List<Seckill> list();

    Seckill getDetailById(Long id);

    Exposer exportSeckillUrl(Long id);

    SeckillExcution executeSeckill(Long id, Long killPhone, String md5);
}
