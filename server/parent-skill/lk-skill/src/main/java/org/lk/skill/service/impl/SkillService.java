package org.lk.skill.service.impl;

import org.lk.skill.dto.Exposer;
import org.lk.skill.dto.SeckillExcution;
import org.lk.skill.entiy.Seckill;
import org.lk.skill.service.ISkillService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author morichou
 *
 */
@Service("skillService")
public class SkillService implements ISkillService{

    @Override
    public List<Seckill> list() {
        return null;
    }

    @Override
    public Seckill getDetailById(Long id) {
        return null;
    }

    @Override
    public Exposer exportSeckillUrl(Long id) {
        return null;
    }

    @Override
    public SeckillExcution executeSeckill(Long id, Long killPhone, String md5) {
        return null;
    }
}
