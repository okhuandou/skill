package org.lk.skill.controller;

import org.lk.skill.service.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skill")
public class SkillController {

	@Autowired
	private  ISkillService skillService;

	@RequestMapping("/list")
	public String list(){
		return "skill/list";
	}
}
