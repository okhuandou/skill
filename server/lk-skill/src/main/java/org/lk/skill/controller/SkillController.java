package org.lk.skill.controller;

import javax.annotation.Resource;

import org.lk.skill.service.ISkillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skill")
public class SkillController {

	@Resource
	private  ISkillService skillService;
	
	@RequestMapping("/list")
	public String list(){
		return "skill/list";
	}
}
