package org.lk.skill.controller;

import org.lk.skill.dto.Exposer;
import org.lk.skill.dto.SeckillExcution;
import org.lk.skill.dto.SeckillResult;
import org.lk.skill.entiy.Seckill;
import org.lk.skill.service.ISkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SkillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ISkillService skillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> list = skillService.list();
        model.addAttribute("list", list);
        return "skill/list";
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Model model) {
        if (id == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = skillService.getDetailById(id);
        if (seckill == null) {
            return "redirect:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "skill/detail";
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    @RequestMapping(value = "/time/now", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Long> getServerTime() {
        return new SeckillResult<Long>(true,System.currentTimeMillis());
    }

    /**
     * 秒杀接口暴露
     *
     * @return
     */
    @RequestMapping(value = "/{id}/exposer", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposerUrl(@PathVariable("id") Long id) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = skillService.exportSeckillUrl(id);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    /**
     * 执行秒杀
     *
     * @return
     */
    @RequestMapping(value = "/{id}/{md5}/execution",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public SeckillResult<SeckillExcution> executionSkill(@PathVariable("id") Long id,
                                 @PathVariable("md5") String md5,
                                 @CookieValue(value = "killPhone") Long killPhone) {
        if(killPhone==null){
            return new SeckillResult<SeckillExcution>(false,"未注册");
        }

        try {
            SeckillExcution seckillExcution = skillService.executeSeckill(id, killPhone, md5);
            return new SeckillResult<SeckillExcution>(true,seckillExcution);
        } catch (Exception e) {
            //重复秒杀
            //秒杀结束
            logger.error(e.getMessage(),e);
            return  new SeckillResult<SeckillExcution>(false,e.getMessage());
        }

    }

}
