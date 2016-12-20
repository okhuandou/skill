package org.lk.skill.controller;

import org.lk.skill.service.ISkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seckill")
public class SkillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ISkillService skillService;

    @RequestMapping("/list")
    public String list() {


        return "skill/list";
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @RequestMapping("/{id}/detail")
    public String detail(Long id) {
        return "skill/detail";
    }

    /**
     * 获取系统时间
     * @return
     */
    @RequestMapping("/time/now")
    public String getServerTime() {
        return "skill/list";
    }

    /**
     * 秒杀接口暴露
     * @return
     */
    @RequestMapping("/{id}/exposer")
    public String exposerUrl() {
        return "skill/list";
    }

    /**
     * 执行秒杀
     * @return
     */
    @RequestMapping("/{id}/{md5}/execution")
    public String executionSkill() {
        return "skill/list";
    }

}
